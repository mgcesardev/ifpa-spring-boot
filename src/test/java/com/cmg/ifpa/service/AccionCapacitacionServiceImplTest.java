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
        when(accionCapacitacionRepository.findAll(any(Pageable.class))).thenReturn(page);
        
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

    @Test
    void testFindAllError() {
        when(accionCapacitacionRepository.findAll(any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> accionCapacitacionService.findAll(PageRequest.of(0, 10)));
    }

    @Test
    void testFindByIdError() {
        when(accionCapacitacionRepository.findById(1L)).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> accionCapacitacionService.findById(1L));
    }

    @Test
    void testSaveError() {
        when(accionCapacitacionRepository.save(any())).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> accionCapacitacionService.save(accion));
    }

    @Test
    void testDeleteError() {
        doThrow(new RuntimeException("Error")).when(accionCapacitacionRepository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> accionCapacitacionService.delete(1L));
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<AccionCapacitacion> page = new PageImpl<>(Arrays.asList(accion));
        when(accionCapacitacionRepository.buscarPorNombre("TEST", pageable)).thenReturn(page);
        Page<AccionCapacitacion> result = accionCapacitacionService.buscarPorNombre("TEST", pageable);
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }

    @Test
    void testBuscarPorNombreError() {
        when(accionCapacitacionRepository.buscarPorNombre(anyString(), any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> accionCapacitacionService.buscarPorNombre("TEST", PageRequest.of(0, 10)));
    }
}

