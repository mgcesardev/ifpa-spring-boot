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

import com.cmg.ifpa.model.InscripcionCapacitacion;
import com.cmg.ifpa.repository.InscripcionCapacitacionRepository;

@ExtendWith(MockitoExtension.class)
public class InscripcionCapacitacionServiceImplTest {

    @Mock
    private InscripcionCapacitacionRepository inscripcionCapacitacionRepository;

    @InjectMocks
    private InscripcionCapacitacionServiceImpl inscripcionCapacitacionService;

    private InscripcionCapacitacion inscripcion;

    @BeforeEach
    void setUp() {
        inscripcion = new InscripcionCapacitacion();
        inscripcion.setIdInscripcionCapacitacion(1L);
        inscripcion.setSolicitud("S-001");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<InscripcionCapacitacion> page = new PageImpl<>(Arrays.asList(inscripcion));
        when(inscripcionCapacitacionRepository.findAll(pageable)).thenReturn(page);
        
        Page<InscripcionCapacitacion> result = inscripcionCapacitacionService.findAll(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(inscripcionCapacitacionRepository).findAll(pageable);
    }

    @Test
    void testFindById() {
        when(inscripcionCapacitacionRepository.findById(1L)).thenReturn(Optional.of(inscripcion));
        
        InscripcionCapacitacion result = inscripcionCapacitacionService.findById(1L);
        
        assertNotNull(result);
        assertEquals("S-001", result.getSolicitud());
        verify(inscripcionCapacitacionRepository).findById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(inscripcionCapacitacionRepository.save(any(InscripcionCapacitacion.class))).thenReturn(inscripcion);
        
        InscripcionCapacitacion result = inscripcionCapacitacionService.save(inscripcion);
        
        assertNotNull(result);
        assertEquals("S-001", result.getSolicitud());
        verify(inscripcionCapacitacionRepository).save(inscripcion);
    }

    @Test
    void testDelete() {
        doNothing().when(inscripcionCapacitacionRepository).deleteById(1L);
        
        inscripcionCapacitacionService.delete(1L);
        
        verify(inscripcionCapacitacionRepository).deleteById(1L);
    }
}
