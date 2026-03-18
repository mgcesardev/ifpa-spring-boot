package com.cmg.ifpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cmg.ifpa.model.Tecnica;

@DataJpaTest
public class TecnicaRepositoryTest {

    @Autowired
    private TecnicaRepository tecnicaRepository;

    @Test
    @SuppressWarnings("null")
    void testSaveAndFindById() {
        Tecnica tecnica = new Tecnica();
        tecnica.setNombreTecnica("Bruñido");
        tecnica.setClaveTecnica("TEC-001");
        tecnica.setEstatus("A");
        
        Tecnica saved = tecnicaRepository.save(tecnica);
        
        assertNotNull(saved.getIdTecnica());
        
        Tecnica found = tecnicaRepository.findById(saved.getIdTecnica()).orElse(null);
        assertNotNull(found);
        assertEquals("Bruñido", found.getNombreTecnica());
    }
}
