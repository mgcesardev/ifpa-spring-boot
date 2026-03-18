package com.cmg.ifpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cmg.ifpa.model.TipoComprador;

@DataJpaTest
public class TipoCompradorRepositoryTest {

    @Autowired
    private TipoCompradorRepository tipoCompradorRepository;

    @Test
    @SuppressWarnings("null")
    void testSaveAndFindById() {
        TipoComprador tipo = new TipoComprador();
        tipo.setNombre("Minorista");
        tipo.setEstatus("A");
        
        TipoComprador saved = tipoCompradorRepository.save(tipo);
        
        assertNotNull(saved.getIdTipoComprador());
        
        TipoComprador found = tipoCompradorRepository.findById(saved.getIdTipoComprador()).orElse(null);
        assertNotNull(found);
        assertEquals("Minorista", found.getNombre());
    }
}
