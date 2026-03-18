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

import com.cmg.ifpa.model.GrupoEtnico;
import com.cmg.ifpa.service.GrupoEtnicoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(GrupoEtnicoController.class)
public class GrupoEtnicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GrupoEtnicoService grupoEtnicoService;

    @Autowired
    private ObjectMapper objectMapper;

    private GrupoEtnico grupo;

    @BeforeEach
    void setUp() {
        grupo = new GrupoEtnico();
        grupo.setIdGrupoEtnico(1L);
        grupo.setNombreEtnia("Zapoteco");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() throws Exception {
        Page<GrupoEtnico> page = new PageImpl<>(List.of(grupo));
        when(grupoEtnicoService.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/grupos-etnicos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nombreEtnia").value("Zapoteco"));
    }

    @Test
    void testFindById() throws Exception {
        when(grupoEtnicoService.findById(1L)).thenReturn(grupo);

        mockMvc.perform(get("/grupos-etnicos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreEtnia").value("Zapoteco"));
    }

    @Test
    @SuppressWarnings("null")
    void testSave() throws Exception {
        when(grupoEtnicoService.save(any(GrupoEtnico.class))).thenReturn(grupo);

        mockMvc.perform(post("/grupos-etnicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(grupo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreEtnia").value("Zapoteco"));
    }

    @Test
    @SuppressWarnings("null")
    void testUpdate() throws Exception {
        when(grupoEtnicoService.save(any(GrupoEtnico.class))).thenReturn(grupo);

        mockMvc.perform(put("/grupos-etnicos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(grupo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreEtnia").value("Zapoteco"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/grupos-etnicos/1"))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() throws Exception {
        Page<GrupoEtnico> page = new PageImpl<>(List.of(grupo));
        when(grupoEtnicoService.buscarPorNombre(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/grupos-etnicos/buscar")
                .param("nombre", "Zapoteco")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nombreEtnia").value("Zapoteco"));
    }
}
