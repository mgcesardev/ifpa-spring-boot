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

import com.cmg.ifpa.model.ProgramaCapacitacion;
import com.cmg.ifpa.service.ProgramaCapacitacionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProgramaCapacitacionController.class)
public class ProgramaCapacitacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProgramaCapacitacionService programaCapacitacionService;

    @Autowired
    private ObjectMapper objectMapper;

    private ProgramaCapacitacion programa;

    @BeforeEach
    void setUp() {
        programa = new ProgramaCapacitacion();
        programa.setIdProgramaCapacitacion(1L);
        programa.setNombrePrograma("Prog Test");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() throws Exception {
        Page<ProgramaCapacitacion> page = new PageImpl<>(List.of(programa));
        when(programaCapacitacionService.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/programas-capacitaciones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nombrePrograma").value("Prog Test"));
    }

    @Test
    void testFindById() throws Exception {
        when(programaCapacitacionService.findById(1L)).thenReturn(programa);

        mockMvc.perform(get("/programas-capacitaciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombrePrograma").value("Prog Test"));
    }

    @Test
    @SuppressWarnings("null")
    void testSave() throws Exception {
        when(programaCapacitacionService.save(any(ProgramaCapacitacion.class))).thenReturn(programa);

        mockMvc.perform(post("/programas-capacitaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(programa)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombrePrograma").value("Prog Test"));
    }

    @Test
    @SuppressWarnings("null")
    void testUpdate() throws Exception {
        when(programaCapacitacionService.save(any(ProgramaCapacitacion.class))).thenReturn(programa);

        mockMvc.perform(put("/programas-capacitaciones/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(programa)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombrePrograma").value("Prog Test"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/programas-capacitaciones/1"))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() throws Exception {
        Page<ProgramaCapacitacion> page = new PageImpl<>(List.of(programa));
        when(programaCapacitacionService.buscarPorNombre(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/programas-capacitaciones/buscar")
                .param("nombre", "Prog")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nombrePrograma").value("Prog Test"));
    }
}
