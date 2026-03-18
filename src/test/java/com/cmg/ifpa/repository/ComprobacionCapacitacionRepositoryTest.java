package com.cmg.ifpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cmg.ifpa.model.ComprobacionCapacitacion;

@DataJpaTest
public class ComprobacionCapacitacionRepositoryTest {

    @Autowired
    private ComprobacionCapacitacionRepository comprobacionCapacitacionRepository;

    @Test
    @SuppressWarnings("null")
    void testSaveAndFindById() {
        ComprobacionCapacitacion comprobacion = new ComprobacionCapacitacion();
        comprobacion.setMonto(1500.50);
        comprobacion.setEstatus("A");
        
        ComprobacionCapacitacion saved = comprobacionCapacitacionRepository.save(comprobacion);
        
        assertNotNull(saved.getIdComprobacionCapacitacion());
        
        ComprobacionCapacitacion found = comprobacionCapacitacionRepository.findById(saved.getIdComprobacionCapacitacion()).orElse(null);
        assertNotNull(found);
        assertEquals(1500.50, found.getMonto());
    }
}
