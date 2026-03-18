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

import com.cmg.ifpa.model.TipoComprador;
import com.cmg.ifpa.service.TipoCompradorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TipoCompradorController.class)
public class TipoCompradorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TipoCompradorService tipoCompradorService;

    @Autowired
    private ObjectMapper objectMapper;

    private TipoComprador tipo;

    @BeforeEach
    void setUp() {
        tipo = new TipoComprador();
        tipo.setIdTipoComprador(1L);
        tipo.setNombre("Minorista");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() throws Exception {
        Page<TipoComprador> page = new PageImpl<>(List.of(tipo));
        when(tipoCompradorService.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/tipos-compradores")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nombre").value("Minorista"));
    }

    @Test
    void testFindById() throws Exception {
        when(tipoCompradorService.findById(1L)).thenReturn(tipo);

        mockMvc.perform(get("/tipos-compradores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Minorista"));
    }

    @Test
    @SuppressWarnings("null")
    void testSave() throws Exception {
        when(tipoCompradorService.save(any(TipoComprador.class))).thenReturn(tipo);

        mockMvc.perform(post("/tipos-compradores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Minorista"));
    }

    @Test
    @SuppressWarnings("null")
    void testUpdate() throws Exception {
        when(tipoCompradorService.save(any(TipoComprador.class))).thenReturn(tipo);

        mockMvc.perform(put("/tipos-compradores/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Minorista"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/tipos-compradores/1"))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() throws Exception {
        Page<TipoComprador> page = new PageImpl<>(List.of(tipo));
        when(tipoCompradorService.buscarPorNombre(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/tipos-compradores/buscar")
                .param("nombre", "Minorista")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nombre").value("Minorista"));
    }
}
