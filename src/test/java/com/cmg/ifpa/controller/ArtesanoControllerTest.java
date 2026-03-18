package com.cmg.ifpa.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.io.ByteArrayInputStream;

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

import com.cmg.ifpa.model.Artesano;
import com.cmg.ifpa.service.ArtesanoService;
import com.cmg.ifpa.service.CredentialService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ArtesanoController.class)
public class ArtesanoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ArtesanoService artesanoService;

    @MockitoBean
    private CredentialService credentialService;

    @Autowired
    private ObjectMapper objectMapper;

    private Artesano artesano;

    @BeforeEach
    void setUp() {
        artesano = new Artesano();
        artesano.setIdArtesano(1L);
        artesano.setNombre("Juan");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() throws Exception {
        Page<Artesano> page = new PageImpl<>(List.of(artesano));
        when(artesanoService.findAll(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/artesanos")
                .param("estatus", "A")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nombre").value("Juan"));
    }

    @Test
    void testFindById() throws Exception {
        when(artesanoService.findById(1L)).thenReturn(artesano);

        mockMvc.perform(get("/artesanos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    @SuppressWarnings("null")
    void testSave() throws Exception {
        when(artesanoService.save(any(Artesano.class))).thenReturn(artesano);

        mockMvc.perform(post("/artesanos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(artesano)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    @SuppressWarnings("null")
    void testUpdate() throws Exception {
        when(artesanoService.save(any(Artesano.class))).thenReturn(artesano);

        mockMvc.perform(put("/artesanos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(artesano)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/artesanos/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testExportExcel() throws Exception {
        when(artesanoService.exportToExcel(any())).thenReturn(new ByteArrayInputStream(new byte[0]));

        mockMvc.perform(get("/artesanos/export-excel"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=artesanos.xlsx"));
    }

    @Test
    @SuppressWarnings("null")
    void testGetCredencial() throws Exception {
        when(artesanoService.findById(1L)).thenReturn(artesano);
        when(credentialService.generateCredentialImage(any(Artesano.class))).thenReturn(new byte[0]);

        mockMvc.perform(get("/artesanos/credencial/1"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=1.jpg"))
                .andExpect(content().contentType(MediaType.IMAGE_JPEG));
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() throws Exception {
        Page<Artesano> page = new PageImpl<>(List.of(artesano));
        when(artesanoService.buscarPorNombre(anyString(), anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/artesanos/buscar")
                .param("nombre", "Juan")
                .param("estatus", "A")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].nombre").value("Juan"));
    }

    @Test
    void testGetCredencialNotFound() throws Exception {
        when(artesanoService.findById(1L)).thenReturn(null);
        
        mockMvc.perform(get("/artesanos/credencial/1"))
                .andExpect(status().isNotFound());
    }
}
