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

import com.cmg.ifpa.model.GrupoEtnico;
import com.cmg.ifpa.repository.GrupoEtnicoRepository;

@ExtendWith(MockitoExtension.class)
public class GrupoEtnicoServiceImplTest {

    @Mock
    private GrupoEtnicoRepository grupoEtnicoRepository;

    @InjectMocks
    private GrupoEtnicoServiceImpl grupoEtnicoService;

    private GrupoEtnico grupo;

    @BeforeEach
    void setUp() {
        grupo = new GrupoEtnico();
        grupo.setIdGrupoEtnico(1L);
        grupo.setNombreEtnia("Zapoteco");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<GrupoEtnico> page = new PageImpl<>(Arrays.asList(grupo));
        when(grupoEtnicoRepository.findAll(any(Pageable.class))).thenReturn(page);
        
        Page<GrupoEtnico> result = grupoEtnicoService.findAll(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(grupoEtnicoRepository).findAll(pageable);
    }

    @Test
    void testFindById() {
        when(grupoEtnicoRepository.findById(1L)).thenReturn(Optional.of(grupo));
        
        GrupoEtnico result = grupoEtnicoService.findById(1L);
        
        assertNotNull(result);
        assertEquals("Zapoteco", result.getNombreEtnia());
        verify(grupoEtnicoRepository).findById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(grupoEtnicoRepository.save(any(GrupoEtnico.class))).thenReturn(grupo);
        
        GrupoEtnico result = grupoEtnicoService.save(grupo);
        
        assertNotNull(result);
        assertEquals("Zapoteco", result.getNombreEtnia());
        verify(grupoEtnicoRepository).save(grupo);
    }

    @Test
    void testDelete() {
        doNothing().when(grupoEtnicoRepository).deleteById(1L);
        grupoEtnicoService.delete(1L);
        verify(grupoEtnicoRepository).deleteById(1L);
    }

    @Test
    void testFindAllError() {
        when(grupoEtnicoRepository.findAll(any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> grupoEtnicoService.findAll(PageRequest.of(0, 10)));
    }

    @Test
    void testFindByIdError() {
        when(grupoEtnicoRepository.findById(1L)).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> grupoEtnicoService.findById(1L));
    }

    @Test
    void testSaveError() {
        when(grupoEtnicoRepository.save(any())).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> grupoEtnicoService.save(grupo));
    }

    @Test
    void testDeleteError() {
        doThrow(new RuntimeException("Error")).when(grupoEtnicoRepository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> grupoEtnicoService.delete(1L));
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<GrupoEtnico> page = new PageImpl<>(Arrays.asList(grupo));
        when(grupoEtnicoRepository.buscarPorNombre("TEST", pageable)).thenReturn(page);
        Page<GrupoEtnico> result = grupoEtnicoService.buscarPorNombre("TEST", pageable);
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }

    @Test
    void testBuscarPorNombreError() {
        when(grupoEtnicoRepository.buscarPorNombre(anyString(), any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> grupoEtnicoService.buscarPorNombre("TEST", PageRequest.of(0, 10)));
    }
}

