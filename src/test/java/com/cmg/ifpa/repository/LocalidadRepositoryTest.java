package com.cmg.ifpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cmg.ifpa.model.Localidad;

@DataJpaTest
public class LocalidadRepositoryTest {

    @Autowired
    private LocalidadRepository localidadRepository;

    @Test
    @SuppressWarnings("null")
    void testSaveAndFindById() {
        Localidad localidad = new Localidad();
        localidad.setLocalidad("San Juan");
        localidad.setEstatus("A");
        
        Localidad saved = localidadRepository.save(localidad);
        
        assertNotNull(saved.getIdLocalidad());
        
        Localidad found = localidadRepository.findById(saved.getIdLocalidad()).orElse(null);
        assertNotNull(found);
        assertEquals("San Juan", found.getLocalidad());
    }
}
