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

import com.cmg.ifpa.model.ProgramaCapacitacion;
import com.cmg.ifpa.repository.ProgramaCapacitacionRepository;

@ExtendWith(MockitoExtension.class)
public class ProgramaCapacitacionServiceImplTest {

    @Mock
    private ProgramaCapacitacionRepository programaCapacitacionRepository;

    @InjectMocks
    private ProgramaCapacitacionServiceImpl programaCapacitacionService;

    private ProgramaCapacitacion programa;

    @BeforeEach
    void setUp() {
        programa = new ProgramaCapacitacion();
        programa.setIdProgramaCapacitacion(1L);
        programa.setNombrePrograma("Prog Test");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ProgramaCapacitacion> page = new PageImpl<>(Arrays.asList(programa));
        when(programaCapacitacionRepository.findAll(any(Pageable.class))).thenReturn(page);
        
        Page<ProgramaCapacitacion> result = programaCapacitacionService.findAll(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(programaCapacitacionRepository).findAll(pageable);
    }

    @Test
    void testFindById() {
        when(programaCapacitacionRepository.findById(1L)).thenReturn(Optional.of(programa));
        
        ProgramaCapacitacion result = programaCapacitacionService.findById(1L);
        
        assertNotNull(result);
        assertEquals("Prog Test", result.getNombrePrograma());
        verify(programaCapacitacionRepository).findById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(programaCapacitacionRepository.save(any(ProgramaCapacitacion.class))).thenReturn(programa);
        
        ProgramaCapacitacion result = programaCapacitacionService.save(programa);
        
        assertNotNull(result);
        assertEquals("Prog Test", result.getNombrePrograma());
        verify(programaCapacitacionRepository).save(programa);
    }

    @Test
    void testDelete() {
        doNothing().when(programaCapacitacionRepository).deleteById(1L);
        programaCapacitacionService.delete(1L);
        verify(programaCapacitacionRepository).deleteById(1L);
    }

    @Test
    void testFindAllError() {
        when(programaCapacitacionRepository.findAll(any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> programaCapacitacionService.findAll(PageRequest.of(0, 10)));
    }

    @Test
    void testFindByIdError() {
        when(programaCapacitacionRepository.findById(1L)).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> programaCapacitacionService.findById(1L));
    }

    @Test
    void testSaveError() {
        when(programaCapacitacionRepository.save(any())).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> programaCapacitacionService.save(programa));
    }

    @Test
    void testDeleteError() {
        doThrow(new RuntimeException("Error")).when(programaCapacitacionRepository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> programaCapacitacionService.delete(1L));
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ProgramaCapacitacion> page = new PageImpl<>(Arrays.asList(programa));
        when(programaCapacitacionRepository.buscarPorNombre("TEST", pageable)).thenReturn(page);
        Page<ProgramaCapacitacion> result = programaCapacitacionService.buscarPorNombre("TEST", pageable);
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }

    @Test
    void testBuscarPorNombreError() {
        when(programaCapacitacionRepository.buscarPorNombre(anyString(), any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> programaCapacitacionService.buscarPorNombre("TEST", PageRequest.of(0, 10)));
    }
}

