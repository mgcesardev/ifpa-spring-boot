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
        organizacion.setNombre("Org Test");
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
        verify(organizacionRepository).findAll(pageable);
    }

    @Test
    void testFindById() {
        when(organizacionRepository.findById(1L)).thenReturn(Optional.of(organizacion));
        
        Organizacion result = organizacionService.findById(1L);
        
        assertNotNull(result);
        assertEquals("Org Test", result.getNombre());
        verify(organizacionRepository).findById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(organizacionRepository.save(any(Organizacion.class))).thenReturn(organizacion);
        
        Organizacion result = organizacionService.save(organizacion);
        
        assertNotNull(result);
        assertEquals("Org Test", result.getNombre());
        verify(organizacionRepository).save(organizacion);
    }

    @Test
    void testDelete() {
        doNothing().when(organizacionRepository).deleteById(1L);
        
        organizacionService.delete(1L);
        
        verify(organizacionRepository).deleteById(1L);
    }
}
