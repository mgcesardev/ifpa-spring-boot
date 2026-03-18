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

import com.cmg.ifpa.model.TipoComprador;
import com.cmg.ifpa.repository.TipoCompradorRepository;

@ExtendWith(MockitoExtension.class)
public class TipoCompradorServiceImplTest {

    @Mock
    private TipoCompradorRepository tipoCompradorRepository;

    @InjectMocks
    private TipoCompradorServiceImpl tipoCompradorService;

    private TipoComprador tipo;

    @BeforeEach
    void setUp() {
        tipo = new TipoComprador();
        tipo.setIdTipoComprador(1L);
        tipo.setNombre("Minorista");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<TipoComprador> page = new PageImpl<>(Arrays.asList(tipo));
        when(tipoCompradorRepository.findAll(any(Pageable.class))).thenReturn(page);
        
        Page<TipoComprador> result = tipoCompradorService.findAll(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(tipoCompradorRepository).findAll(pageable);
    }

    @Test
    void testFindById() {
        when(tipoCompradorRepository.findById(1L)).thenReturn(Optional.of(tipo));
        
        TipoComprador result = tipoCompradorService.findById(1L);
        
        assertNotNull(result);
        assertEquals("Minorista", result.getNombre());
        verify(tipoCompradorRepository).findById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(tipoCompradorRepository.save(any(TipoComprador.class))).thenReturn(tipo);
        
        TipoComprador result = tipoCompradorService.save(tipo);
        
        assertNotNull(result);
        assertEquals("Minorista", result.getNombre());
        verify(tipoCompradorRepository).save(tipo);
    }

    @Test
    void testDelete() {
        doNothing().when(tipoCompradorRepository).deleteById(1L);
        tipoCompradorService.delete(1L);
        verify(tipoCompradorRepository).deleteById(1L);
    }

    @Test
    void testFindAllError() {
        when(tipoCompradorRepository.findAll(any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> tipoCompradorService.findAll(PageRequest.of(0, 10)));
    }

    @Test
    void testFindByIdError() {
        when(tipoCompradorRepository.findById(1L)).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> tipoCompradorService.findById(1L));
    }

    @Test
    void testSaveError() {
        when(tipoCompradorRepository.save(any())).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> tipoCompradorService.save(tipo));
    }

    @Test
    void testDeleteError() {
        doThrow(new RuntimeException("Error")).when(tipoCompradorRepository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> tipoCompradorService.delete(1L));
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<TipoComprador> page = new PageImpl<>(Arrays.asList(tipo));
        when(tipoCompradorRepository.buscarPorNombre("TEST", pageable)).thenReturn(page);
        Page<TipoComprador> result = tipoCompradorService.buscarPorNombre("TEST", pageable);
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }

    @Test
    void testBuscarPorNombreError() {
        when(tipoCompradorRepository.buscarPorNombre(anyString(), any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> tipoCompradorService.buscarPorNombre("TEST", PageRequest.of(0, 10)));
    }
}

