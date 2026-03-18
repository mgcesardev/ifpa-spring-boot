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

import com.cmg.ifpa.model.ComprobacionCapacitacion;
import com.cmg.ifpa.repository.ComprobacionCapacitacionRepository;

@ExtendWith(MockitoExtension.class)
public class ComprobacionCapacitacionServiceImplTest {

    @Mock
    private ComprobacionCapacitacionRepository comprobacionCapacitacionRepository;

    @InjectMocks
    private ComprobacionCapacitacionServiceImpl comprobacionCapacitacionService;

    private ComprobacionCapacitacion comprobacion;

    @BeforeEach
    void setUp() {
        comprobacion = new ComprobacionCapacitacion();
        comprobacion.setIdComprobacionCapacitacion(1L);
        comprobacion.setMonto(100.0);
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ComprobacionCapacitacion> page = new PageImpl<>(Arrays.asList(comprobacion));
        when(comprobacionCapacitacionRepository.findAll(any(Pageable.class))).thenReturn(page);
        
        Page<ComprobacionCapacitacion> result = comprobacionCapacitacionService.findAll(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(comprobacionCapacitacionRepository).findAll(pageable);
    }

    @Test
    void testFindById() {
        when(comprobacionCapacitacionRepository.findById(1L)).thenReturn(Optional.of(comprobacion));
        
        ComprobacionCapacitacion result = comprobacionCapacitacionService.findById(1L);
        
        assertNotNull(result);
        assertEquals(100.0, result.getMonto());
        verify(comprobacionCapacitacionRepository).findById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(comprobacionCapacitacionRepository.save(any(ComprobacionCapacitacion.class))).thenReturn(comprobacion);
        
        ComprobacionCapacitacion result = comprobacionCapacitacionService.save(comprobacion);
        
        assertNotNull(result);
        assertEquals(100.0, result.getMonto());
        verify(comprobacionCapacitacionRepository).save(comprobacion);
    }

    @Test
    void testDelete() {
        doNothing().when(comprobacionCapacitacionRepository).deleteById(1L);
        comprobacionCapacitacionService.delete(1L);
        verify(comprobacionCapacitacionRepository).deleteById(1L);
    }

    @Test
    void testFindAllError() {
        when(comprobacionCapacitacionRepository.findAll(any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> comprobacionCapacitacionService.findAll(PageRequest.of(0, 10)));
    }

    @Test
    void testFindByIdError() {
        when(comprobacionCapacitacionRepository.findById(1L)).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> comprobacionCapacitacionService.findById(1L));
    }

    @Test
    void testSaveError() {
        when(comprobacionCapacitacionRepository.save(any())).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> comprobacionCapacitacionService.save(comprobacion));
    }

    @Test
    void testDeleteError() {
        doThrow(new RuntimeException("Error")).when(comprobacionCapacitacionRepository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> comprobacionCapacitacionService.delete(1L));
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ComprobacionCapacitacion> page = new PageImpl<>(Arrays.asList(comprobacion));
        when(comprobacionCapacitacionRepository.buscarPorNombre("TEST", pageable)).thenReturn(page);
        Page<ComprobacionCapacitacion> result = comprobacionCapacitacionService.buscarPorNombre("TEST", pageable);
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }

    @Test
    void testBuscarPorNombreError() {
        when(comprobacionCapacitacionRepository.buscarPorNombre(anyString(), any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> comprobacionCapacitacionService.buscarPorNombre("TEST", PageRequest.of(0, 10)));
    }
}

