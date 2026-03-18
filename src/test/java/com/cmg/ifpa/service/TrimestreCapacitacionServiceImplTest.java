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

import com.cmg.ifpa.model.TrimestreCapacitacion;
import com.cmg.ifpa.repository.TrimestreCapacitacionRepository;

@ExtendWith(MockitoExtension.class)
public class TrimestreCapacitacionServiceImplTest {

    @Mock
    private TrimestreCapacitacionRepository trimestreCapacitacionRepository;

    @InjectMocks
    private TrimestreCapacitacionServiceImpl trimestreCapacitacionService;

    private TrimestreCapacitacion trimestre;

    @BeforeEach
    void setUp() {
        trimestre = new TrimestreCapacitacion();
        trimestre.setIdTrimestreCapacitacion(1L);
        trimestre.setMesInicio("Enero");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<TrimestreCapacitacion> page = new PageImpl<>(Arrays.asList(trimestre));
        when(trimestreCapacitacionRepository.findAll(any(Pageable.class))).thenReturn(page);
        
        Page<TrimestreCapacitacion> result = trimestreCapacitacionService.findAll(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(trimestreCapacitacionRepository).findAll(pageable);
    }

    @Test
    void testFindById() {
        when(trimestreCapacitacionRepository.findById(1L)).thenReturn(Optional.of(trimestre));
        
        TrimestreCapacitacion result = trimestreCapacitacionService.findById(1L);
        
        assertNotNull(result);
        assertEquals("Enero", result.getMesInicio());
        verify(trimestreCapacitacionRepository).findById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(trimestreCapacitacionRepository.save(any(TrimestreCapacitacion.class))).thenReturn(trimestre);
        
        TrimestreCapacitacion result = trimestreCapacitacionService.save(trimestre);
        
        assertNotNull(result);
        assertEquals("Enero", result.getMesInicio());
        verify(trimestreCapacitacionRepository).save(trimestre);
    }

    @Test
    void testDelete() {
        doNothing().when(trimestreCapacitacionRepository).deleteById(1L);
        trimestreCapacitacionService.delete(1L);
        verify(trimestreCapacitacionRepository).deleteById(1L);
    }

    @Test
    void testFindAllError() {
        when(trimestreCapacitacionRepository.findAll(any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> trimestreCapacitacionService.findAll(PageRequest.of(0, 10)));
    }

    @Test
    void testFindByIdError() {
        when(trimestreCapacitacionRepository.findById(1L)).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> trimestreCapacitacionService.findById(1L));
    }

    @Test
    void testSaveError() {
        when(trimestreCapacitacionRepository.save(any())).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> trimestreCapacitacionService.save(trimestre));
    }

    @Test
    void testDeleteError() {
        doThrow(new RuntimeException("Error")).when(trimestreCapacitacionRepository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> trimestreCapacitacionService.delete(1L));
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<TrimestreCapacitacion> page = new PageImpl<>(Arrays.asList(trimestre));
        when(trimestreCapacitacionRepository.buscarPorNombre("Enero", pageable)).thenReturn(page);
        Page<TrimestreCapacitacion> result = trimestreCapacitacionService.buscarPorNombre("Enero", pageable);
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }

    @Test
    void testBuscarPorNombreError() {
        when(trimestreCapacitacionRepository.buscarPorNombre(anyString(), any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> trimestreCapacitacionService.buscarPorNombre("Enero", PageRequest.of(0, 10)));
    }
}

