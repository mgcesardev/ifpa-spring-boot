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

import com.cmg.ifpa.model.RamaArtesanal;
import com.cmg.ifpa.repository.RamaArtesanalRepository;

@ExtendWith(MockitoExtension.class)
public class RamaArtesanalServiceImplTest {

    @Mock
    private RamaArtesanalRepository ramaArtesanalRepository;

    @InjectMocks
    private RamaArtesanalServiceImpl ramaArtesanalService;

    private RamaArtesanal rama;

    @BeforeEach
    void setUp() {
        rama = new RamaArtesanal();
        rama.setIdRamaArtesanal(1L);
        rama.setNombreRama("Rama Test");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<RamaArtesanal> page = new PageImpl<>(Arrays.asList(rama));
        when(ramaArtesanalRepository.findAll(pageable)).thenReturn(page);
        
        Page<RamaArtesanal> result = ramaArtesanalService.findAll(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(ramaArtesanalRepository).findAll(pageable);
    }

    @Test
    void testFindById() {
        when(ramaArtesanalRepository.findById(1L)).thenReturn(Optional.of(rama));
        
        RamaArtesanal result = ramaArtesanalService.findById(1L);
        
        assertNotNull(result);
        assertEquals("Rama Test", result.getNombreRama());
        verify(ramaArtesanalRepository).findById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(ramaArtesanalRepository.save(any(RamaArtesanal.class))).thenReturn(rama);
        
        RamaArtesanal result = ramaArtesanalService.save(rama);
        
        assertNotNull(result);
        assertEquals("Rama Test", result.getNombreRama());
        verify(ramaArtesanalRepository).save(rama);
    }

    @Test
    void testDelete() {
        doNothing().when(ramaArtesanalRepository).deleteById(1L);
        
        ramaArtesanalService.delete(1L);
        
        verify(ramaArtesanalRepository).deleteById(1L);
    }
}
