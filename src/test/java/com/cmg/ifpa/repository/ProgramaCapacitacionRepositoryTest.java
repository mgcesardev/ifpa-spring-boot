package com.cmg.ifpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cmg.ifpa.model.ProgramaCapacitacion;

@DataJpaTest
public class ProgramaCapacitacionRepositoryTest {

    @Autowired
    private ProgramaCapacitacionRepository programaCapacitacionRepository;

    @Test
    @SuppressWarnings("null")
    void testSaveAndFindById() {
        ProgramaCapacitacion programa = new ProgramaCapacitacion();
        programa.setNombrePrograma("Fomento Artesanal");
        programa.setEstatus("A");
        
        ProgramaCapacitacion saved = programaCapacitacionRepository.save(programa);
        
        assertNotNull(saved.getIdProgramaCapacitacion());
        
        ProgramaCapacitacion found = programaCapacitacionRepository.findById(saved.getIdProgramaCapacitacion()).orElse(null);
        assertNotNull(found);
        assertEquals("Fomento Artesanal", found.getNombrePrograma());
    }
}
