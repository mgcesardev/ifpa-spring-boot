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

import com.cmg.ifpa.model.ComprobacionCapacitacion;
import com.cmg.ifpa.service.ComprobacionCapacitacionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ComprobacionCapacitacionController.class)
public class ComprobacionCapacitacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ComprobacionCapacitacionService comprobacionCapacitacionService;

    @Autowired
    private ObjectMapper objectMapper;

    private ComprobacionCapacitacion comprobacion;

    @BeforeEach
    void setUp() {
        comprobacion = new ComprobacionCapacitacion();
        comprobacion.setIdComprobacionCapacitacion(1L);
        comprobacion.setMonto(100.0);
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() throws Exception {
        Page<ComprobacionCapacitacion> page = new PageImpl<>(List.of(comprobacion));
        when(comprobacionCapacitacionService.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/comprobaciones-capacitaciones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].monto").value(100.0));
    }

    @Test
    void testFindById() throws Exception {
        when(comprobacionCapacitacionService.findById(1L)).thenReturn(comprobacion);

        mockMvc.perform(get("/comprobaciones-capacitaciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.monto").value(100.0));
    }

    @Test
    @SuppressWarnings("null")
    void testSave() throws Exception {
        when(comprobacionCapacitacionService.save(any(ComprobacionCapacitacion.class))).thenReturn(comprobacion);

        mockMvc.perform(post("/comprobaciones-capacitaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comprobacion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.monto").value(100.0));
    }

    @Test
    @SuppressWarnings("null")
    void testUpdate() throws Exception {
        when(comprobacionCapacitacionService.save(any(ComprobacionCapacitacion.class))).thenReturn(comprobacion);

        mockMvc.perform(put("/comprobaciones-capacitaciones/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comprobacion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.monto").value(100.0));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/comprobaciones-capacitaciones/1"))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() throws Exception {
        Page<ComprobacionCapacitacion> page = new PageImpl<>(List.of(comprobacion));
        when(comprobacionCapacitacionService.buscarPorNombre(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/comprobaciones-capacitaciones/buscar")
                .param("nombre", "test")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].monto").value(100.0));
    }
}
