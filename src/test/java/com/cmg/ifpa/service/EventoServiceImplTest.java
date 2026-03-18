package com.cmg.ifpa.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cmg.ifpa.model.Evento;
import com.cmg.ifpa.repository.EventoRepository;

@ExtendWith(MockitoExtension.class)
public class EventoServiceImplTest {

    @Mock
    private EventoRepository eventoRepository;

    @InjectMocks
    private EventoServiceImpl eventoService;

    private Evento evento;

    @BeforeEach
    void setUp() {
        evento = new Evento();
        evento.setIdEvento(1L);
        evento.setFolio("EV-001");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Evento> page = new PageImpl<>(Arrays.asList(evento));
        when(eventoRepository.findAll(pageable)).thenReturn(page);
        
        Page<Evento> result = eventoService.findAll(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(eventoRepository).findAll(pageable);
    }

    @Test
    void testFindById() {
        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));
        
        Evento result = eventoService.findById(1L);
        
        assertNotNull(result);
        assertEquals("EV-001", result.getFolio());
        verify(eventoRepository).findById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(eventoRepository.save(any(Evento.class))).thenReturn(evento);
        
        Evento result = eventoService.save(evento);
        
        assertNotNull(result);
        assertEquals("EV-001", result.getFolio());
        verify(eventoRepository).save(evento);
    }

    @Test
    void testDelete() {
        doNothing().when(eventoRepository).deleteById(1L);
        
        eventoService.delete(1L);
        
        verify(eventoRepository).deleteById(1L);
    }
}
