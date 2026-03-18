package com.cmg.ifpa.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cmg.ifpa.model.LenguaIndigena;
import com.cmg.ifpa.service.LenguaIndigenaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LenguaIndigenaController.class)
public class LenguaIndigenaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LenguaIndigenaService lenguaIndigenaService;

    @Autowired
    private ObjectMapper objectMapper;

    private LenguaIndigena lengua;

    @BeforeEach
    void setUp() {
        lengua = new LenguaIndigena();
        lengua.setIdLenguaIndigena(1L);
        lengua.setLenguaIndigena("Mixteco");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() throws Exception {
        Page<LenguaIndigena> page = new PageImpl<>(List.of(lengua));
        when(lenguaIndigenaService.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/lenguas-indigenas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].lenguaIndigena").value("Mixteco"));
    }

    @Test
    void testFindById() throws Exception {
        when(lenguaIndigenaService.findById(1L)).thenReturn(lengua);

        mockMvc.perform(get("/lenguas-indigenas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lenguaIndigena").value("Mixteco"));
    }

    @Test
    @SuppressWarnings("null")
    void testSave() throws Exception {
        when(lenguaIndigenaService.save(any(LenguaIndigena.class))).thenReturn(lengua);

        mockMvc.perform(post("/lenguas-indigenas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(lengua)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lenguaIndigena").value("Mixteco"));
    }

    @Test
    @SuppressWarnings("null")
    void testUpdate() throws Exception {
        when(lenguaIndigenaService.save(any(LenguaIndigena.class))).thenReturn(lengua);

        mockMvc.perform(put("/lenguas-indigenas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(lengua)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lenguaIndigena").value("Mixteco"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/lenguas-indigenas/1"))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() throws Exception {
        Page<LenguaIndigena> page = new PageImpl<>(List.of(lengua));
        when(lenguaIndigenaService.buscarPorNombre(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/lenguas-indigenas/buscar")
                .param("nombre", "Mixteco")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].lenguaIndigena").value("Mixteco"));
    }
}
