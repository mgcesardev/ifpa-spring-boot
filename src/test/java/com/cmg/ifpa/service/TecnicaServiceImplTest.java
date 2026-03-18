package com.cmg.ifpa.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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

import com.cmg.ifpa.model.Tecnica;
import com.cmg.ifpa.repository.TecnicaRepository;

@ExtendWith(MockitoExtension.class)
public class TecnicaServiceImplTest {

    @Mock
    private TecnicaRepository tecnicaRepository;

    @InjectMocks
    private TecnicaServiceImpl tecnicaService;

    private Tecnica tecnica;

    @BeforeEach
    void setUp() {
        tecnica = new Tecnica();
        tecnica.setIdTecnica(1L);
        tecnica.setNombreTecnica("Bordado");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Tecnica> page = new PageImpl<>(Arrays.asList(tecnica));
        when(tecnicaRepository.findAll(any(Pageable.class))).thenReturn(page);
        
        Page<Tecnica> result = tecnicaService.findAll(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(tecnicaRepository).findAll(pageable);
    }

    @Test
    void testFindById() {
        when(tecnicaRepository.findById(1L)).thenReturn(Optional.of(tecnica));
        
        Tecnica result = tecnicaService.findById(1L);
        
        assertNotNull(result);
        assertEquals("Bordado", result.getNombreTecnica());
        verify(tecnicaRepository).findById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(tecnicaRepository.save(any(Tecnica.class))).thenReturn(tecnica);
        
        Tecnica result = tecnicaService.save(tecnica);
        
        assertNotNull(result);
        assertEquals("Bordado", result.getNombreTecnica());
        verify(tecnicaRepository).save(tecnica);
    }

    @Test
    void testDelete() {
        doNothing().when(tecnicaRepository).deleteById(1L);
        tecnicaService.delete(1L);
        verify(tecnicaRepository).deleteById(1L);
    }

    @Test
    void testFindAllError() {
        when(tecnicaRepository.findAll(any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> tecnicaService.findAll(PageRequest.of(0, 10)));
    }

    @Test
    void testFindByIdError() {
        when(tecnicaRepository.findById(1L)).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> tecnicaService.findById(1L));
    }

    @Test
    void testSaveError() {
        when(tecnicaRepository.save(any())).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> tecnicaService.save(tecnica));
    }

    @Test
    void testDeleteError() {
        doThrow(new RuntimeException("Error")).when(tecnicaRepository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> tecnicaService.delete(1L));
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Tecnica> page = new PageImpl<>(Arrays.asList(tecnica));
        when(tecnicaRepository.buscarPorNombre("TEST", pageable)).thenReturn(page);
        Page<Tecnica> result = tecnicaService.buscarPorNombre("TEST", pageable);
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }

    @Test
    void testBuscarPorNombreError() {
        when(tecnicaRepository.buscarPorNombre(anyString(), any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> tecnicaService.buscarPorNombre("TEST", PageRequest.of(0, 10)));
    }
}

