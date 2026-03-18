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

import com.cmg.ifpa.model.AccionCapacitacion;
import com.cmg.ifpa.service.AccionCapacitacionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AccionCapacitacionController.class)
public class AccionCapacitacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AccionCapacitacionService accionCapacitacionService;

    @Autowired
    private ObjectMapper objectMapper;

    private AccionCapacitacion accion;

    @BeforeEach
    void setUp() {
        accion = new AccionCapacitacion();
        accion.setIdAccionCapacitacion(1L);
        accion.setNombre("Curso Test");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() throws Exception {
        Page<AccionCapacitacion> page = new PageImpl<>(List.of(accion));
        when(accionCapacitacionService.findAll(any(Pageable.class))).thenReturn(page);
        mockMvc.perform(get("/acciones-capacitaciones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nombre").value("Curso Test"));
    }

    @Test
    void testFindById() throws Exception {
        when(accionCapacitacionService.findById(1L)).thenReturn(accion);

        mockMvc.perform(get("/acciones-capacitaciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Curso Test"));
    }

    @Test
    @SuppressWarnings("null")
    void testSave() throws Exception {
        when(accionCapacitacionService.save(any(AccionCapacitacion.class))).thenReturn(accion);

        mockMvc.perform(post("/acciones-capacitaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Curso Test"));
    }

    @Test
    @SuppressWarnings("null")
    void testUpdate() throws Exception {
        when(accionCapacitacionService.save(any(AccionCapacitacion.class))).thenReturn(accion);

        mockMvc.perform(put("/acciones-capacitaciones/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Curso Test"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/acciones-capacitaciones/1"))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() throws Exception {
        Page<AccionCapacitacion> page = new PageImpl<>(List.of(accion));
        when(accionCapacitacionService.buscarPorNombre(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/acciones-capacitaciones/buscar")
                .param("nombre", "Curso")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nombre").value("Curso Test"));
    }
}
