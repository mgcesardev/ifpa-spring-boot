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

import com.cmg.ifpa.model.RamaArtesanal;
import com.cmg.ifpa.service.RamaArtesanalService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(RamaArtesanalController.class)
public class RamaArtesanalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RamaArtesanalService ramaArtesanalService;

    @Autowired
    private ObjectMapper objectMapper;

    private RamaArtesanal rama;

    @BeforeEach
    void setUp() {
        rama = new RamaArtesanal();
        rama.setIdRamaArtesanal(1L);
        rama.setNombreRama("Rama Test");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() throws Exception {
        Page<RamaArtesanal> page = new PageImpl<>(List.of(rama));
        when(ramaArtesanalService.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/ramas-artesanales")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nombreRama").value("Rama Test"));
    }

    @Test
    void testFindById() throws Exception {
        when(ramaArtesanalService.findById(1L)).thenReturn(rama);

        mockMvc.perform(get("/ramas-artesanales/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreRama").value("Rama Test"));
    }

    @Test
    @SuppressWarnings("null")
    void testSave() throws Exception {
        when(ramaArtesanalService.save(any(RamaArtesanal.class))).thenReturn(rama);

        mockMvc.perform(post("/ramas-artesanales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rama)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreRama").value("Rama Test"));
    }

    @Test
    @SuppressWarnings("null")
    void testUpdate() throws Exception {
        when(ramaArtesanalService.save(any(RamaArtesanal.class))).thenReturn(rama);

        mockMvc.perform(put("/ramas-artesanales/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rama)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreRama").value("Rama Test"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/ramas-artesanales/1"))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() throws Exception {
        Page<RamaArtesanal> page = new PageImpl<>(List.of(rama));
        when(ramaArtesanalService.buscarPorNombre(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/ramas-artesanales/buscar")
                .param("nombre", "Rama")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nombreRama").value("Rama Test"));
    }
}
