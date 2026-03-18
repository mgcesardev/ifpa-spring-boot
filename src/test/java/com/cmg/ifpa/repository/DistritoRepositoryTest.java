package com.cmg.ifpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cmg.ifpa.model.Distrito;

@DataJpaTest
public class DistritoRepositoryTest {

    @Autowired
    private DistritoRepository distritoRepository;

    @Test
    @SuppressWarnings("null")
    void testSaveAndFindById() {
        Distrito distrito = new Distrito();
        distrito.setDistrito("Centro");
        distrito.setEstatus("A");
        
        Distrito saved = distritoRepository.save(distrito);
        
        assertNotNull(saved.getIdDistrito());
        
        Distrito found = distritoRepository.findById(saved.getIdDistrito()).orElse(null);
        assertNotNull(found);
        assertEquals("Centro", found.getDistrito());
    }
}
