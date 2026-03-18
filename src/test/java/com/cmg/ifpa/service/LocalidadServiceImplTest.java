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

import com.cmg.ifpa.model.Localidad;
import com.cmg.ifpa.repository.LocalidadRepository;

@ExtendWith(MockitoExtension.class)
public class LocalidadServiceImplTest {

    @Mock
    private LocalidadRepository localidadRepository;

    @InjectMocks
    private LocalidadServiceImpl localidadService;

    private Localidad localidad;

    @BeforeEach
    void setUp() {
        localidad = new Localidad();
        localidad.setIdLocalidad(1L);
        localidad.setLocalidad("San Juan");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Localidad> page = new PageImpl<>(Arrays.asList(localidad));
        when(localidadRepository.findAll(pageable)).thenReturn(page);
        
        Page<Localidad> result = localidadService.findAll(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(localidadRepository).findAll(pageable);
    }

    @Test
    void testFindById() {
        when(localidadRepository.findById(1L)).thenReturn(Optional.of(localidad));
        
        Localidad result = localidadService.findById(1L);
        
        assertNotNull(result);
        assertEquals("San Juan", result.getLocalidad());
        verify(localidadRepository).findById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(localidadRepository.save(any(Localidad.class))).thenReturn(localidad);
        
        Localidad result = localidadService.save(localidad);
        
        assertNotNull(result);
        assertEquals("San Juan", result.getLocalidad());
        verify(localidadRepository).save(localidad);
    }

    @Test
    void testDelete() {
        doNothing().when(localidadRepository).deleteById(1L);
        
        localidadService.delete(1L);
        
        verify(localidadRepository).deleteById(1L);
    }
}
