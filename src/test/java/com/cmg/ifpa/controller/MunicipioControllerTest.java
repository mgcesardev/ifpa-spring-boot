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

import com.cmg.ifpa.model.Municipio;
import com.cmg.ifpa.service.MunicipioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MunicipioController.class)
public class MunicipioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MunicipioService municipioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Municipio municipio;

    @BeforeEach
    void setUp() {
        municipio = new Municipio();
        municipio.setIdMunicipio(1L);
        municipio.setMunicipio("Oaxaca");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() throws Exception {
        Page<Municipio> page = new PageImpl<>(List.of(municipio));
        when(municipioService.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/municipios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].municipio").value("Oaxaca"));
    }

    @Test
    void testFindById() throws Exception {
        when(municipioService.findById(1L)).thenReturn(municipio);

        mockMvc.perform(get("/municipios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.municipio").value("Oaxaca"));
    }

    @Test
    @SuppressWarnings("null")
    void testSave() throws Exception {
        when(municipioService.save(any(Municipio.class))).thenReturn(municipio);

        mockMvc.perform(post("/municipios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(municipio)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.municipio").value("Oaxaca"));
    }

    @Test
    @SuppressWarnings("null")
    void testUpdate() throws Exception {
        when(municipioService.save(any(Municipio.class))).thenReturn(municipio);

        mockMvc.perform(put("/municipios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(municipio)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.municipio").value("Oaxaca"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/municipios/1"))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() throws Exception {
        Page<Municipio> page = new PageImpl<>(List.of(municipio));
        when(municipioService.buscarPorNombre(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/municipios/buscar")
                .param("nombre", "Oaxaca")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].municipio").value("Oaxaca"));
    }
}
