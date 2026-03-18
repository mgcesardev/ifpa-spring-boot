package com.cmg.ifpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cmg.ifpa.model.Artesano;

@DataJpaTest
public class ArtesanoRepositoryTest {

    @Autowired
    private ArtesanoRepository artesanoRepository;

    @Test
    @SuppressWarnings("null")
    void testSaveAndFindById() {
        Artesano artesano = new Artesano();
        artesano.setNombre("Juan");
        artesano.setPrimerApellido("Perez");
        artesano.setEstatus("A");
        
        Artesano saved = artesanoRepository.save(artesano);
        
        assertNotNull(saved.getIdArtesano());
        
        Artesano found = artesanoRepository.findById(saved.getIdArtesano()).orElse(null);
        assertNotNull(found);
        assertEquals("Juan", found.getNombre());
    }

    @Test
    void testFindByEstatus() {
        Artesano artesano1 = new Artesano();
        artesano1.setNombre("Juan");
        artesano1.setEstatus("A");
        artesanoRepository.save(artesano1);
        
        Artesano artesano2 = new Artesano();
        artesano2.setNombre("Maria");
        artesano2.setEstatus("I");
        artesanoRepository.save(artesano2);
        
        Page<Artesano> result = artesanoRepository.findByEstatus("A", PageRequest.of(0, 10));
        
        assertEquals(1, result.getContent().size());
        assertEquals("Juan", result.getContent().get(0).getNombre());
    }
}
