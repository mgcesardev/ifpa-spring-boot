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

import com.cmg.ifpa.model.Evento;
import com.cmg.ifpa.service.EventoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EventoController.class)
public class EventoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EventoService eventoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Evento evento;

    @BeforeEach
    void setUp() {
        evento = new Evento();
        evento.setIdEvento(1L);
        evento.setFolio("EV-001");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() throws Exception {
        Page<Evento> page = new PageImpl<>(List.of(evento));
        when(eventoService.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/eventos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].folio").value("EV-001"));
    }

    @Test
    void testFindById() throws Exception {
        when(eventoService.findById(1L)).thenReturn(evento);

        mockMvc.perform(get("/eventos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.folio").value("EV-001"));
    }

    @Test
    @SuppressWarnings("null")
    void testSave() throws Exception {
        when(eventoService.save(any(Evento.class))).thenReturn(evento);

        mockMvc.perform(post("/eventos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(evento)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.folio").value("EV-001"));
    }

    @Test
    @SuppressWarnings("null")
    void testUpdate() throws Exception {
        when(eventoService.save(any(Evento.class))).thenReturn(evento);

        mockMvc.perform(put("/eventos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(evento)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.folio").value("EV-001"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/eventos/1"))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() throws Exception {
        Page<Evento> page = new PageImpl<>(List.of(evento));
        when(eventoService.buscarPorNombre(anyString(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/eventos/buscar")
                .param("nombre", "EV")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].folio").value("EV-001"));
    }
}
