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

import com.cmg.ifpa.model.Distrito;
import com.cmg.ifpa.service.DistritoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DistritoController.class)
public class DistritoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DistritoService distritoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Distrito distrito;

    @BeforeEach
    void setUp() {
        distrito = new Distrito();
        distrito.setIdDistrito(1L);
        distrito.setDistrito("Distrito Test");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() throws Exception {
        Page<Distrito> page = new PageImpl<>(List.of(distrito));
        when(distritoService.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/distritos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].distrito").value("Distrito Test"));
    }

    @Test
    void testFindById() throws Exception {
        when(distritoService.findById(1L)).thenReturn(distrito);

        mockMvc.perform(get("/distritos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.distrito").value("Distrito Test"));
    }

    @Test
    @SuppressWarnings("null")
    void testSave() throws Exception {
        when(distritoService.save(any(Distrito.class))).thenReturn(distrito);

        mockMvc.perform(post("/distritos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(distrito)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.distrito").value("Distrito Test"));
    }

    @Test
    @SuppressWarnings("null")
    void testUpdate() throws Exception {
        when(distritoService.save(any(Distrito.class))).thenReturn(distrito);

        mockMvc.perform(put("/distritos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(distrito)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.distrito").value("Distrito Test"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/distritos/1"))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() throws Exception {
        Page<Distrito> page = new PageImpl<>(List.of(distrito));
        when(distritoService.buscarPorNombre(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/distritos/buscar")
                .param("nombre", "Distrito")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].distrito").value("Distrito Test"));
    }
}
