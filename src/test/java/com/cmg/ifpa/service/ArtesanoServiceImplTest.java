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

import com.cmg.ifpa.model.Artesano;
import com.cmg.ifpa.repository.ArtesanoRepository;
import com.cmg.ifpa.repository.RegionRepository;
import com.cmg.ifpa.repository.DistritoRepository;
import com.cmg.ifpa.repository.MunicipioRepository;
import com.cmg.ifpa.repository.LocalidadRepository;
import com.cmg.ifpa.repository.RamaArtesanalRepository;
import com.cmg.ifpa.repository.TecnicaRepository;

@ExtendWith(MockitoExtension.class)
public class ArtesanoServiceImplTest {

    @Mock
    private ArtesanoRepository artesanoRepository;
    @Mock
    private RegionRepository regionRepository;
    @Mock
    private DistritoRepository distritoRepository;
    @Mock
    private MunicipioRepository municipioRepository;
    @Mock
    private LocalidadRepository localidadRepository;
    @Mock
    private RamaArtesanalRepository ramaArtesanalRepository;
    @Mock
    private TecnicaRepository tecnicaRepository;

    @InjectMocks
    private ArtesanoServiceImpl artesanoService;

    private Artesano artesano;

    @BeforeEach
    void setUp() {
        artesano = new Artesano();
        artesano.setIdArtesano(1L);
        artesano.setNombre("Juan");
        artesano.setPrimerApellido("Perez");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Artesano> page = new PageImpl<>(Arrays.asList(artesano));
        when(artesanoRepository.findAll(pageable)).thenReturn(page);
        
        Page<Artesano> result = artesanoService.findAll(null, pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(artesanoRepository).findAll(pageable);
    }

    @Test
    @SuppressWarnings("null")
    void testFindAllWithEstatus() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Artesano> page = new PageImpl<>(Arrays.asList(artesano));
        when(artesanoRepository.findByEstatus("A", pageable)).thenReturn(page);
        
        Page<Artesano> result = artesanoService.findAll("A", pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(artesanoRepository).findByEstatus("A", pageable);
    }

    @Test
    void testFindById() {
        when(artesanoRepository.findById(1L)).thenReturn(Optional.of(artesano));
        
        Artesano result = artesanoService.findById(1L);
        
        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(artesanoRepository).findById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(artesanoRepository.save(any(Artesano.class))).thenReturn(artesano);
        
        Artesano result = artesanoService.save(artesano);
        
        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(artesanoRepository).save(artesano);
    }

    @Test
    void testDelete() {
        doNothing().when(artesanoRepository).deleteById(1L);
        
        artesanoService.delete(1L);
        
        verify(artesanoRepository).deleteById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Artesano> page = new PageImpl<>(Arrays.asList(artesano));
        when(artesanoRepository.buscarPorNombreCompleto("Juan", pageable)).thenReturn(page);
        
        Page<Artesano> result = artesanoService.buscarPorNombre("Juan", null, pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(artesanoRepository).buscarPorNombreCompleto("Juan", pageable);
    }
}
