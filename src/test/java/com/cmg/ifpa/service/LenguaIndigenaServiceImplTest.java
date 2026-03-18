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

import com.cmg.ifpa.model.LenguaIndigena;
import com.cmg.ifpa.repository.LenguaIndigenaRepository;

@ExtendWith(MockitoExtension.class)
public class LenguaIndigenaServiceImplTest {

    @Mock
    private LenguaIndigenaRepository lenguaIndigenaRepository;

    @InjectMocks
    private LenguaIndigenaServiceImpl lenguaIndigenaService;

    private LenguaIndigena lengua;

    @BeforeEach
    void setUp() {
        lengua = new LenguaIndigena();
        lengua.setIdLenguaIndigena(1L);
        lengua.setLenguaIndigena("Mixteco");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<LenguaIndigena> page = new PageImpl<>(Arrays.asList(lengua));
        when(lenguaIndigenaRepository.findAll(any(Pageable.class))).thenReturn(page);
        
        Page<LenguaIndigena> result = lenguaIndigenaService.findAll(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(lenguaIndigenaRepository).findAll(pageable);
    }

    @Test
    void testFindById() {
        when(lenguaIndigenaRepository.findById(1L)).thenReturn(Optional.of(lengua));
        
        LenguaIndigena result = lenguaIndigenaService.findById(1L);
        
        assertNotNull(result);
        assertEquals("Mixteco", result.getLenguaIndigena());
        verify(lenguaIndigenaRepository).findById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(lenguaIndigenaRepository.save(any(LenguaIndigena.class))).thenReturn(lengua);
        
        LenguaIndigena result = lenguaIndigenaService.save(lengua);
        
        assertNotNull(result);
        assertEquals("Mixteco", result.getLenguaIndigena());
        verify(lenguaIndigenaRepository).save(lengua);
    }

    @Test
    void testDelete() {
        doNothing().when(lenguaIndigenaRepository).deleteById(1L);
        lenguaIndigenaService.delete(1L);
        verify(lenguaIndigenaRepository).deleteById(1L);
    }

    @Test
    void testFindAllError() {
        when(lenguaIndigenaRepository.findAll(any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> lenguaIndigenaService.findAll(PageRequest.of(0, 10)));
    }

    @Test
    void testFindByIdError() {
        when(lenguaIndigenaRepository.findById(1L)).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> lenguaIndigenaService.findById(1L));
    }

    @Test
    void testSaveError() {
        when(lenguaIndigenaRepository.save(any())).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> lenguaIndigenaService.save(lengua));
    }

    @Test
    void testDeleteError() {
        doThrow(new RuntimeException("Error")).when(lenguaIndigenaRepository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> lenguaIndigenaService.delete(1L));
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<LenguaIndigena> page = new PageImpl<>(Arrays.asList(lengua));
        when(lenguaIndigenaRepository.buscarPorNombre("TEST", pageable)).thenReturn(page);
        Page<LenguaIndigena> result = lenguaIndigenaService.buscarPorNombre("TEST", pageable);
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }

    @Test
    void testBuscarPorNombreError() {
        when(lenguaIndigenaRepository.buscarPorNombre(anyString(), any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> lenguaIndigenaService.buscarPorNombre("TEST", PageRequest.of(0, 10)));
    }
}

