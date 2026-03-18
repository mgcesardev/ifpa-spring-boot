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

import com.cmg.ifpa.model.TrimestreCapacitacion;
import com.cmg.ifpa.service.TrimestreCapacitacionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TrimestreCapacitacionController.class)
public class TrimestreCapacitacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TrimestreCapacitacionService trimestreCapacitacionService;

    @Autowired
    private ObjectMapper objectMapper;

    private TrimestreCapacitacion trimestre;

    @BeforeEach
    void setUp() {
        trimestre = new TrimestreCapacitacion();
        trimestre.setIdTrimestreCapacitacion(1L);
        trimestre.setMesInicio("Enero");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() throws Exception {
        Page<TrimestreCapacitacion> page = new PageImpl<>(List.of(trimestre));
        when(trimestreCapacitacionService.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/trimestres-capacitaciones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].mesInicio").value("Enero"));
    }

    @Test
    void testFindById() throws Exception {
        when(trimestreCapacitacionService.findById(1L)).thenReturn(trimestre);

        mockMvc.perform(get("/trimestres-capacitaciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mesInicio").value("Enero"));
    }

    @Test
    @SuppressWarnings("null")
    void testSave() throws Exception {
        when(trimestreCapacitacionService.save(any(TrimestreCapacitacion.class))).thenReturn(trimestre);

        mockMvc.perform(post("/trimestres-capacitaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(trimestre)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mesInicio").value("Enero"));
    }

    @Test
    @SuppressWarnings("null")
    void testUpdate() throws Exception {
        when(trimestreCapacitacionService.save(any(TrimestreCapacitacion.class))).thenReturn(trimestre);

        mockMvc.perform(put("/trimestres-capacitaciones/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(trimestre)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mesInicio").value("Enero"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/trimestres-capacitaciones/1"))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() throws Exception {
        Page<TrimestreCapacitacion> page = new PageImpl<>(List.of(trimestre));
        when(trimestreCapacitacionService.buscarPorNombre(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/trimestres-capacitaciones/buscar")
                .param("nombre", "Enero")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].mesInicio").value("Enero"));
    }
}
