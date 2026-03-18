package com.cmg.ifpa.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
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

import com.cmg.ifpa.model.Region;
import com.cmg.ifpa.repository.RegionRepository;

@ExtendWith(MockitoExtension.class)
public class RegionServiceImplTest {

    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private RegionServiceImpl regionService;

    private Region region;

    @BeforeEach
    void setUp() {
        region = new Region();
        region.setIdRegion(1L);
        region.setRegion("SIERRA NORTE");
    }

    @Test
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Region> page = new PageImpl<>(Arrays.asList(region));
        when(regionRepository.findAll(pageable)).thenReturn(page);
        
        Page<Region> result = regionService.findAll(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(regionRepository).findAll(pageable);
    }

    @Test
    void testFindAllError() {
        Pageable pageable = PageRequest.of(0, 10);
        when(regionRepository.findAll(pageable)).thenThrow(new RuntimeException("DB Error"));
        
        assertThrows(RuntimeException.class, () -> regionService.findAll(pageable));
    }

    @Test
    void testFindById() {
        when(regionRepository.findById(1L)).thenReturn(Optional.of(region));
        
        Region result = regionService.findById(1L);
        
        assertNotNull(result);
        assertEquals("SIERRA NORTE", result.getRegion());
    }

    @Test
    void testFindByIdNotFound() {
        when(regionRepository.findById(1L)).thenReturn(Optional.empty());
        
        Region result = regionService.findById(1L);
        
        assertNull(result);
    }

    @Test
    void testFindByIdError() {
        when(regionRepository.findById(1L)).thenThrow(new RuntimeException("DB Error"));
        
        assertThrows(RuntimeException.class, () -> regionService.findById(1L));
    }

    @Test
    void testSave() {
        when(regionRepository.save(any(Region.class))).thenReturn(region);
        
        Region result = regionService.save(region);
        
        assertNotNull(result);
        verify(regionRepository).save(region);
    }

    @Test
    void testSaveError() {
        when(regionRepository.save(any(Region.class))).thenThrow(new RuntimeException("DB Error"));
        
        assertThrows(RuntimeException.class, () -> regionService.save(region));
    }

    @Test
    void testFindByEstatus() {
        when(regionRepository.findByEstatusNative("A")).thenReturn(Arrays.asList(region));
        
        List<Region> result = regionService.findByEstatus("A");
        
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testFindByEstatusError() {
        when(regionRepository.findByEstatusNative("A")).thenThrow(new RuntimeException("DB Error"));
        
        assertThrows(RuntimeException.class, () -> regionService.findByEstatus("A"));
    }

    @Test
    void testDelete() {
        doNothing().when(regionRepository).deleteById(1L);
        
        regionService.delete(1L);
        
        verify(regionRepository).deleteById(1L);
    }

    @Test
    void testDeleteError() {
        doThrow(new RuntimeException("DB Error")).when(regionRepository).deleteById(1L);
        
        assertThrows(RuntimeException.class, () -> regionService.delete(1L));
    }

    @Test
    void testBuscarPorNombre() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Region> page = new PageImpl<>(Arrays.asList(region));
        when(regionRepository.buscarPorNombre("SIERRA", pageable)).thenReturn(page);
        
        Page<Region> result = regionService.buscarPorNombre("SIERRA", pageable);
        
        assertNotNull(result);
        verify(regionRepository).buscarPorNombre("SIERRA", pageable);
    }

    @Test
    void testBuscarPorNombreError() {
        Pageable pageable = PageRequest.of(0, 10);
        when(regionRepository.buscarPorNombre("SIERRA", pageable)).thenThrow(new RuntimeException("DB Error"));
        
        assertThrows(RuntimeException.class, () -> regionService.buscarPorNombre("SIERRA", pageable));
    }
}
