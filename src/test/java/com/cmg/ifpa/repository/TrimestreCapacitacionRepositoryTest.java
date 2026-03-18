package com.cmg.ifpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cmg.ifpa.model.TrimestreCapacitacion;

@DataJpaTest
public class TrimestreCapacitacionRepositoryTest {

    @Autowired
    private TrimestreCapacitacionRepository trimestreCapacitacionRepository;

    @Test
    @SuppressWarnings("null")
    void testSaveAndFindById() {
        TrimestreCapacitacion trimestre = new TrimestreCapacitacion();
        trimestre.setMesInicio("Enero");
        trimestre.setMesFin("Marzo");
        trimestre.setAnio("2024");
        trimestre.setActivo('S');
        trimestre.setEstatus("A");
        
        TrimestreCapacitacion saved = trimestreCapacitacionRepository.save(trimestre);
        
        assertNotNull(saved.getIdTrimestreCapacitacion());
        
        TrimestreCapacitacion found = trimestreCapacitacionRepository.findById(saved.getIdTrimestreCapacitacion()).orElse(null);
        assertNotNull(found);
        assertEquals("Enero", found.getMesInicio());
    }
}
