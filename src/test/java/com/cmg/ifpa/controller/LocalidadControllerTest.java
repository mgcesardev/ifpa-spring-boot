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

import com.cmg.ifpa.model.Localidad;
import com.cmg.ifpa.service.LocalidadService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LocalidadController.class)
public class LocalidadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LocalidadService localidadService;

    @Autowired
    private ObjectMapper objectMapper;

    private Localidad localidad;

    @BeforeEach
    void setUp() {
        localidad = new Localidad();
        localidad.setIdLocalidad(1L);
        localidad.setLocalidad("San Juan");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() throws Exception {
        Page<Localidad> page = new PageImpl<>(List.of(localidad));
        when(localidadService.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/localidades")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].localidad").value("San Juan"));
    }

    @Test
    void testFindById() throws Exception {
        when(localidadService.findById(1L)).thenReturn(localidad);

        mockMvc.perform(get("/localidades/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.localidad").value("San Juan"));
    }

    @Test
    @SuppressWarnings("null")
    void testSave() throws Exception {
        when(localidadService.save(any(Localidad.class))).thenReturn(localidad);

        mockMvc.perform(post("/localidades")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(localidad)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.localidad").value("San Juan"));
    }

    @Test
    @SuppressWarnings("null")
    void testUpdate() throws Exception {
        when(localidadService.save(any(Localidad.class))).thenReturn(localidad);

        mockMvc.perform(put("/localidades/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(localidad)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.localidad").value("San Juan"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/localidades/1"))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() throws Exception {
        Page<Localidad> page = new PageImpl<>(List.of(localidad));
        when(localidadService.buscarPorNombre(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/localidades/buscar")
                .param("nombre", "San Juan")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].localidad").value("San Juan"));
    }
}
