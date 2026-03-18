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

import com.cmg.ifpa.model.Region;
import com.cmg.ifpa.service.RegionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(RegionController.class)
public class RegionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RegionService regionService;

    @Autowired
    private ObjectMapper objectMapper;

    private Region region;

    @BeforeEach
    void setUp() {
        region = new Region();
        region.setIdRegion(1L);
        region.setRegion("Valles Centrales");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() throws Exception {
        Page<Region> page = new PageImpl<>(List.of(region));
        when(regionService.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/regiones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].region").value("Valles Centrales"));
    }

    @Test
    void testFindById() throws Exception {
        when(regionService.findById(1L)).thenReturn(region);

        mockMvc.perform(get("/regiones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.region").value("Valles Centrales"));
    }

    @Test
    @SuppressWarnings("null")
    void testSave() throws Exception {
        when(regionService.save(any(Region.class))).thenReturn(region);

        mockMvc.perform(post("/regiones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(region)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.region").value("Valles Centrales"));
    }

    @Test
    @SuppressWarnings("null")
    void testUpdate() throws Exception {
        when(regionService.save(any(Region.class))).thenReturn(region);

        mockMvc.perform(put("/regiones/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(region)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.region").value("Valles Centrales"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/regiones/1"))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void testFindByEstatus() throws Exception {
        when(regionService.findByEstatus("A")).thenReturn(List.of(region));

        mockMvc.perform(get("/regiones/estatus")
                .param("estatus", "A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].region").value("Valles Centrales"));
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() throws Exception {
        Page<Region> page = new PageImpl<>(List.of(region));
        when(regionService.buscarPorNombre(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/regiones/buscar")
                .param("nombre", "Valles")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].region").value("Valles Centrales"));
    }
}
