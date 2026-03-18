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

import com.cmg.ifpa.model.AccionCapacitacion;
import com.cmg.ifpa.repository.AccionCapacitacionRepository;

@ExtendWith(MockitoExtension.class)
public class AccionCapacitacionServiceImplTest {

    @Mock
    private AccionCapacitacionRepository accionCapacitacionRepository;

    @InjectMocks
    private AccionCapacitacionServiceImpl accionCapacitacionService;

    private AccionCapacitacion accion;

    @BeforeEach
    void setUp() {
        accion = new AccionCapacitacion();
        accion.setIdAccionCapacitacion(1L);
        accion.setNombre("Curso Test");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<AccionCapacitacion> page = new PageImpl<>(Arrays.asList(accion));
        when(accionCapacitacionRepository.findAll(pageable)).thenReturn(page);
        
        Page<AccionCapacitacion> result = accionCapacitacionService.findAll(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(accionCapacitacionRepository).findAll(pageable);
    }

    @Test
    void testFindById() {
        when(accionCapacitacionRepository.findById(1L)).thenReturn(Optional.of(accion));
        
        AccionCapacitacion result = accionCapacitacionService.findById(1L);
        
        assertNotNull(result);
        assertEquals("Curso Test", result.getNombre());
        verify(accionCapacitacionRepository).findById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(accionCapacitacionRepository.save(any(AccionCapacitacion.class))).thenReturn(accion);
        
        AccionCapacitacion result = accionCapacitacionService.save(accion);
        
        assertNotNull(result);
        assertEquals("Curso Test", result.getNombre());
        verify(accionCapacitacionRepository).save(accion);
    }

    @Test
    void testDelete() {
        doNothing().when(accionCapacitacionRepository).deleteById(1L);
        
        accionCapacitacionService.delete(1L);
        
        verify(accionCapacitacionRepository).deleteById(1L);
    }
}
