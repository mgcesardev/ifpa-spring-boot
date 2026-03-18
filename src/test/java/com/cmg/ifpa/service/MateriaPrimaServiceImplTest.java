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

import com.cmg.ifpa.model.MateriaPrima;
import com.cmg.ifpa.repository.MateriaPrimaRepository;

@ExtendWith(MockitoExtension.class)
public class MateriaPrimaServiceImplTest {

    @Mock
    private MateriaPrimaRepository materiaPrimaRepository;

    @InjectMocks
    private MateriaPrimaServiceImpl materiaPrimaService;

    private MateriaPrima materia;

    @BeforeEach
    void setUp() {
        materia = new MateriaPrima();
        materia.setIdMateriaPrima(1L);
        materia.setNombre("Barro");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<MateriaPrima> page = new PageImpl<>(Arrays.asList(materia));
        when(materiaPrimaRepository.findAll(any(Pageable.class))).thenReturn(page);
        
        Page<MateriaPrima> result = materiaPrimaService.findAll(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(materiaPrimaRepository).findAll(pageable);
    }

    @Test
    void testFindById() {
        when(materiaPrimaRepository.findById(1L)).thenReturn(Optional.of(materia));
        
        MateriaPrima result = materiaPrimaService.findById(1L);
        
        assertNotNull(result);
        assertEquals("Barro", result.getNombre());
        verify(materiaPrimaRepository).findById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(materiaPrimaRepository.save(any(MateriaPrima.class))).thenReturn(materia);
        
        MateriaPrima result = materiaPrimaService.save(materia);
        
        assertNotNull(result);
        assertEquals("Barro", result.getNombre());
        verify(materiaPrimaRepository).save(materia);
    }

    @Test
    void testDelete() {
        doNothing().when(materiaPrimaRepository).deleteById(1L);
        materiaPrimaService.delete(1L);
        verify(materiaPrimaRepository).deleteById(1L);
    }

    @Test
    void testFindAllError() {
        when(materiaPrimaRepository.findAll(any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> materiaPrimaService.findAll(PageRequest.of(0, 10)));
    }

    @Test
    void testFindByIdError() {
        when(materiaPrimaRepository.findById(1L)).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> materiaPrimaService.findById(1L));
    }

    @Test
    void testSaveError() {
        when(materiaPrimaRepository.save(any())).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> materiaPrimaService.save(materia));
    }

    @Test
    void testDeleteError() {
        doThrow(new RuntimeException("Error")).when(materiaPrimaRepository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> materiaPrimaService.delete(1L));
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<MateriaPrima> page = new PageImpl<>(Arrays.asList(materia));
        when(materiaPrimaRepository.buscarPorNombre("TEST", pageable)).thenReturn(page);
        Page<MateriaPrima> result = materiaPrimaService.buscarPorNombre("TEST", pageable);
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }

    @Test
    void testBuscarPorNombreError() {
        when(materiaPrimaRepository.buscarPorNombre(anyString(), any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> materiaPrimaService.buscarPorNombre("TEST", PageRequest.of(0, 10)));
    }
}

