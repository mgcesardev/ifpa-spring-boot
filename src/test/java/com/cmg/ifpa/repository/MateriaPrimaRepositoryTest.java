package com.cmg.ifpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cmg.ifpa.model.MateriaPrima;

@DataJpaTest
public class MateriaPrimaRepositoryTest {

    @Autowired
    private MateriaPrimaRepository materiaPrimaRepository;

    @Test
    @SuppressWarnings("null")
    void testSaveAndFindById() {
        MateriaPrima materia = new MateriaPrima();
        materia.setNombre("Barro Negro");
        materia.setEstatus("A");
        
        MateriaPrima saved = materiaPrimaRepository.save(materia);
        
        assertNotNull(saved.getIdMateriaPrima());
        
        MateriaPrima found = materiaPrimaRepository.findById(saved.getIdMateriaPrima()).orElse(null);
        assertNotNull(found);
        assertEquals("Barro Negro", found.getNombre());
    }
}
