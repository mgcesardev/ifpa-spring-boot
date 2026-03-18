package com.cmg.ifpa.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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

import com.cmg.ifpa.model.Organizacion;
import com.cmg.ifpa.repository.OrganizacionRepository;

@ExtendWith(MockitoExtension.class)
public class OrganizacionServiceImplTest {

    @Mock
    private OrganizacionRepository organizacionRepository;

    @InjectMocks
    private OrganizacionServiceImpl organizacionService;

    private Organizacion organizacion;

    @BeforeEach
    void setUp() {
        organizacion = new Organizacion();
        organizacion.setIdOrganizacion(1L);
        organizacion.setNombreOrganizacion("ORG TEST");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Organizacion> page = new PageImpl<>(Arrays.asList(organizacion));
        when(organizacionRepository.findAll(pageable)).thenReturn(page);
        
        Page<Organizacion> result = organizacionService.findAll(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }

    @Test
    @SuppressWarnings("null")
    void testFindAllError() {
        Pageable pageable = PageRequest.of(0, 10);
        when(organizacionRepository.findAll(pageable)).thenThrow(new RuntimeException("DB Error"));
        assertThrows(RuntimeException.class, () -> organizacionService.findAll(pageable));
    }

    @Test
    void testFindById() {
        when(organizacionRepository.findById(1L)).thenReturn(Optional.of(organizacion));
        Organizacion result = organizacionService.findById(1L);
        assertNotNull(result);
        assertEquals("ORG TEST", result.getNombreOrganizacion());
    }

    @Test
    void testFindByIdNotFound() {
        when(organizacionRepository.findById(1L)).thenReturn(Optional.empty());
        assertNull(organizacionService.findById(1L));
    }

    @Test
    void testFindByIdError() {
        when(organizacionRepository.findById(1L)).thenThrow(new RuntimeException("DB Error"));
        assertThrows(RuntimeException.class, () -> organizacionService.findById(1L));
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(organizacionRepository.save(any(Organizacion.class))).thenReturn(organizacion);
        Organizacion result = organizacionService.save(organizacion);
        assertNotNull(result);
    }

    @Test
    @SuppressWarnings("null")
    void testSaveError() {
        when(organizacionRepository.save(any(Organizacion.class))).thenThrow(new RuntimeException("DB Error"));
        assertThrows(RuntimeException.class, () -> organizacionService.save(organizacion));
    }

    @Test
    void testDelete() {
        doNothing().when(organizacionRepository).deleteById(1L);
        organizacionService.delete(1L);
        verify(organizacionRepository).deleteById(1L);
    }

    @Test
    void testDeleteError() {
        doThrow(new RuntimeException("DB Error")).when(organizacionRepository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> organizacionService.delete(1L));
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Organizacion> page = new PageImpl<>(Arrays.asList(organizacion));
        when(organizacionRepository.buscarPorNombre("ORG", pageable)).thenReturn(page);
        Page<Organizacion> result = organizacionService.buscarPorNombre("ORG", pageable);
        assertNotNull(result);
    }

    @Test
    void testBuscarPorNombreError() {
        when(organizacionRepository.buscarPorNombre(anyString(), any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> organizacionService.buscarPorNombre("ORG", PageRequest.of(0, 10)));
    }
}

