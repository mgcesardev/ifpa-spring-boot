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

import com.cmg.ifpa.model.Distrito;
import com.cmg.ifpa.repository.DistritoRepository;

@ExtendWith(MockitoExtension.class)
public class DistritoServiceImplTest {

    @Mock
    private DistritoRepository distritoRepository;

    @InjectMocks
    private DistritoServiceImpl distritoService;

    private Distrito distrito;

    @BeforeEach
    void setUp() {
        distrito = new Distrito();
        distrito.setIdDistrito(1L);
        distrito.setDistrito("Distrito Test");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Distrito> page = new PageImpl<>(Arrays.asList(distrito));
        when(distritoRepository.findAll(pageable)).thenReturn(page);
        
        Page<Distrito> result = distritoService.findAll(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(distritoRepository).findAll(pageable);
    }

    @Test
    void testFindById() {
        when(distritoRepository.findById(1L)).thenReturn(Optional.of(distrito));
        
        Distrito result = distritoService.findById(1L);
        
        assertNotNull(result);
        assertEquals("Distrito Test", result.getDistrito());
        verify(distritoRepository).findById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(distritoRepository.save(any(Distrito.class))).thenReturn(distrito);
        
        Distrito result = distritoService.save(distrito);
        
        assertNotNull(result);
        assertEquals("Distrito Test", result.getDistrito());
        verify(distritoRepository).save(distrito);
    }

    @Test
    void testDelete() {
        doNothing().when(distritoRepository).deleteById(1L);
        
        distritoService.delete(1L);
        
        verify(distritoRepository).deleteById(1L);
    }
}
