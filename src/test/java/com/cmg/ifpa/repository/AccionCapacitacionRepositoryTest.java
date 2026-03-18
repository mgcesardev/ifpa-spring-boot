package com.cmg.ifpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cmg.ifpa.model.AccionCapacitacion;

@DataJpaTest
public class AccionCapacitacionRepositoryTest {

    @Autowired
    private AccionCapacitacionRepository accionCapacitacionRepository;

    @Test
    @SuppressWarnings("null")
    void testSaveAndFindById() {
        AccionCapacitacion accion = new AccionCapacitacion();
        accion.setNombre("Curso de Tejido");
        accion.setArea("Textiles");
        accion.setEstatus("A");
        
        AccionCapacitacion saved = accionCapacitacionRepository.save(accion);
        
        assertNotNull(saved.getIdAccionCapacitacion());
        
        AccionCapacitacion found = accionCapacitacionRepository.findById(saved.getIdAccionCapacitacion()).orElse(null);
        assertNotNull(found);
        assertEquals("Curso de Tejido", found.getNombre());
    }
}
