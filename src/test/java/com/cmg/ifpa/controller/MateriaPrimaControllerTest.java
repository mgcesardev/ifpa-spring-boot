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

import com.cmg.ifpa.model.MateriaPrima;
import com.cmg.ifpa.service.MateriaPrimaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MateriaPrimaController.class)
public class MateriaPrimaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MateriaPrimaService materiaPrimaService;

    @Autowired
    private ObjectMapper objectMapper;

    private MateriaPrima materia;

    @BeforeEach
    void setUp() {
        materia = new MateriaPrima();
        materia.setIdMateriaPrima(1L);
        materia.setNombre("Barro");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() throws Exception {
        Page<MateriaPrima> page = new PageImpl<>(List.of(materia));
        when(materiaPrimaService.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/materias-primas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nombre").value("Barro"));
    }

    @Test
    void testFindById() throws Exception {
        when(materiaPrimaService.findById(1L)).thenReturn(materia);

        mockMvc.perform(get("/materias-primas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Barro"));
    }

    @Test
    @SuppressWarnings("null")
    void testSave() throws Exception {
        when(materiaPrimaService.save(any(MateriaPrima.class))).thenReturn(materia);

        mockMvc.perform(post("/materias-primas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(materia)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Barro"));
    }

    @Test
    @SuppressWarnings("null")
    void testUpdate() throws Exception {
        when(materiaPrimaService.save(any(MateriaPrima.class))).thenReturn(materia);

        mockMvc.perform(put("/materias-primas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(materia)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Barro"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/materias-primas/1"))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() throws Exception {
        Page<MateriaPrima> page = new PageImpl<>(List.of(materia));
        when(materiaPrimaService.buscarPorNombre(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/materias-primas/buscar")
                .param("nombre", "Barro")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nombre").value("Barro"));
    }
}
