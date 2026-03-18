package com.cmg.ifpa.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

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

import com.cmg.ifpa.model.Municipio;
import com.cmg.ifpa.repository.MunicipioRepository;

@ExtendWith(MockitoExtension.class)
public class MunicipioServiceImplTest {

    @Mock
    private MunicipioRepository municipioRepository;

    @InjectMocks
    private MunicipioServiceImpl municipioService;

    private Municipio municipio;

    @BeforeEach
    void setUp() {
        municipio = new Municipio();
        municipio.setIdMunicipio(1L);
        municipio.setMunicipio("MUNICIPIO TEST");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Municipio> page = new PageImpl<>(Arrays.asList(municipio));
        when(municipioRepository.findAll(pageable)).thenReturn(page);
        
        Page<Municipio> result = municipioService.findAll(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }

    @Test
    @SuppressWarnings("null")
    void testFindAllError() {
        Pageable pageable = PageRequest.of(0, 10);
        when(municipioRepository.findAll(pageable)).thenThrow(new RuntimeException("DB Error"));
        assertThrows(RuntimeException.class, () -> municipioService.findAll(pageable));
    }

    @Test
    void testFindById() {
        when(municipioRepository.findById(1L)).thenReturn(Optional.of(municipio));
        Municipio result = municipioService.findById(1L);
        assertNotNull(result);
        assertEquals("MUNICIPIO TEST", result.getMunicipio());
    }

    @Test
    void testFindByIdNotFound() {
        when(municipioRepository.findById(1L)).thenReturn(Optional.empty());
        assertNull(municipioService.findById(1L));
    }

    @Test
    void testFindByIdError() {
        when(municipioRepository.findById(1L)).thenThrow(new RuntimeException("DB Error"));
        assertThrows(RuntimeException.class, () -> municipioService.findById(1L));
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(municipioRepository.save(any(Municipio.class))).thenReturn(municipio);
        Municipio result = municipioService.save(municipio);
        assertNotNull(result);
    }

    @Test
    @SuppressWarnings("null")
    void testSaveError() {
        when(municipioRepository.save(any(Municipio.class))).thenThrow(new RuntimeException("DB Error"));
        assertThrows(RuntimeException.class, () -> municipioService.save(municipio));
    }

    @Test
    void testDelete() {
        doNothing().when(municipioRepository).deleteById(1L);
        municipioService.delete(1L);
        verify(municipioRepository).deleteById(1L);
    }

    @Test
    void testDeleteError() {
        doThrow(new RuntimeException("DB Error")).when(municipioRepository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> municipioService.delete(1L));
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Municipio> page = new PageImpl<>(Arrays.asList(municipio));
        when(municipioRepository.buscarPorNombre("MUN", pageable)).thenReturn(page);
        Page<Municipio> result = municipioService.buscarPorNombre("MUN", pageable);
        assertNotNull(result);
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombreError() {
        when(municipioRepository.buscarPorNombre(anyString(), any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> municipioService.buscarPorNombre("MUN", PageRequest.of(0, 10)));
    }
}

