package com.cmg.ifpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cmg.ifpa.model.InscripcionCapacitacion;

@DataJpaTest
public class InscripcionCapacitacionRepositoryTest {

    @Autowired
    private InscripcionCapacitacionRepository inscripcionCapacitacionRepository;

    @Test
    @SuppressWarnings("null")
    void testSaveAndFindById() {
        InscripcionCapacitacion inscripcion = new InscripcionCapacitacion();
        inscripcion.setSolicitud("Solicitud-001");
        inscripcion.setAsistencia('S');
        inscripcion.setEstatus("A");
        
        InscripcionCapacitacion saved = inscripcionCapacitacionRepository.save(inscripcion);
        
        assertNotNull(saved.getIdInscripcionCapacitacion());
        
        InscripcionCapacitacion found = inscripcionCapacitacionRepository.findById(saved.getIdInscripcionCapacitacion()).orElse(null);
        assertNotNull(found);
        assertEquals("Solicitud-001", found.getSolicitud());
    }
}
