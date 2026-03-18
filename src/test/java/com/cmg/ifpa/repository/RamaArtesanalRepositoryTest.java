package com.cmg.ifpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cmg.ifpa.model.RamaArtesanal;

@DataJpaTest
public class RamaArtesanalRepositoryTest {

    @Autowired
    private RamaArtesanalRepository ramaArtesanalRepository;

    @Test
    @SuppressWarnings("null")
    void testSaveAndFindById() {
        RamaArtesanal rama = new RamaArtesanal();
        rama.setNombreRama("Alfarería");
        rama.setClaveRama("RAM-001");
        rama.setEstatus("A");
        
        RamaArtesanal saved = ramaArtesanalRepository.save(rama);
        
        assertNotNull(saved.getIdRamaArtesanal());
        
        RamaArtesanal found = ramaArtesanalRepository.findById(saved.getIdRamaArtesanal()).orElse(null);
        assertNotNull(found);
        assertEquals("Alfarería", found.getNombreRama());
    }
}
