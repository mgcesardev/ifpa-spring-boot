package com.cmg.ifpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cmg.ifpa.model.Organizacion;

@DataJpaTest
public class OrganizacionRepositoryTest {

    @Autowired
    private OrganizacionRepository organizacionRepository;

    @Test
    @SuppressWarnings("null")
    void testSaveAndFindById() {
        Organizacion organizacion = new Organizacion();
        organizacion.setNombre("Cooperativa Arte");
        organizacion.setClaveOrganizacion("ORG-001");
        organizacion.setEstatus("A");
        
        Organizacion saved = organizacionRepository.save(organizacion);
        
        assertNotNull(saved.getIdOrganizacion());
        
        Organizacion found = organizacionRepository.findById(saved.getIdOrganizacion()).orElse(null);
        assertNotNull(found);
        assertEquals("Cooperativa Arte", found.getNombre());
    }
}
