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

import com.cmg.ifpa.model.Organizacion;
import com.cmg.ifpa.service.OrganizacionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(OrganizacionController.class)
public class OrganizacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrganizacionService organizacionService;

    @Autowired
    private ObjectMapper objectMapper;

    private Organizacion organizacion;

    @BeforeEach
    void setUp() {
        organizacion = new Organizacion();
        organizacion.setIdOrganizacion(1L);
        organizacion.setNombre("Org Test");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() throws Exception {
        Page<Organizacion> page = new PageImpl<>(List.of(organizacion));
        when(organizacionService.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/organizaciones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nombre").value("Org Test"));
    }

    @Test
    void testFindById() throws Exception {
        when(organizacionService.findById(1L)).thenReturn(organizacion);

        mockMvc.perform(get("/organizaciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Org Test"));
    }

    @Test
    @SuppressWarnings("null")
    void testSave() throws Exception {
        when(organizacionService.save(any(Organizacion.class))).thenReturn(organizacion);

        mockMvc.perform(post("/organizaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organizacion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Org Test"));
    }

    @Test
    @SuppressWarnings("null")
    void testUpdate() throws Exception {
        when(organizacionService.save(any(Organizacion.class))).thenReturn(organizacion);

        mockMvc.perform(put("/organizaciones/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organizacion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Org Test"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/organizaciones/1"))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() throws Exception {
        Page<Organizacion> page = new PageImpl<>(List.of(organizacion));
        when(organizacionService.buscarPorNombre(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/organizaciones/buscar")
                .param("nombre", "Org")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nombre").value("Org Test"));
    }
}
