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

import com.cmg.ifpa.model.Tecnica;
import com.cmg.ifpa.service.TecnicaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TecnicaController.class)
public class TecnicaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TecnicaService tecnicaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Tecnica tecnica;

    @BeforeEach
    void setUp() {
        tecnica = new Tecnica();
        tecnica.setIdTecnica(1L);
        tecnica.setNombreTecnica("Bordado");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() throws Exception {
        Page<Tecnica> page = new PageImpl<>(List.of(tecnica));
        when(tecnicaService.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/tecnicas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nombreTecnica").value("Bordado"));
    }

    @Test
    void testFindById() throws Exception {
        when(tecnicaService.findById(1L)).thenReturn(tecnica);

        mockMvc.perform(get("/tecnicas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreTecnica").value("Bordado"));
    }

    @Test
    @SuppressWarnings("null")
    void testSave() throws Exception {
        when(tecnicaService.save(any(Tecnica.class))).thenReturn(tecnica);

        mockMvc.perform(post("/tecnicas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tecnica)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreTecnica").value("Bordado"));
    }

    @Test
    @SuppressWarnings("null")
    void testUpdate() throws Exception {
        when(tecnicaService.save(any(Tecnica.class))).thenReturn(tecnica);

        mockMvc.perform(put("/tecnicas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tecnica)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreTecnica").value("Bordado"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/tecnicas/1"))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() throws Exception {
        Page<Tecnica> page = new PageImpl<>(List.of(tecnica));
        when(tecnicaService.buscarPorNombre(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/tecnicas/buscar")
                .param("nombre", "Bordado")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nombreTecnica").value("Bordado"));
    }
}
