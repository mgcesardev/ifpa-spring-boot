package com.cmg.ifpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cmg.ifpa.model.Municipio;

@DataJpaTest
public class MunicipioRepositoryTest {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Test
    @SuppressWarnings("null")
    void testSaveAndFindById() {
        Municipio municipio = new Municipio();
        municipio.setMunicipio("Oaxaca de Juárez");
        municipio.setEstatus("A");
        
        Municipio saved = municipioRepository.save(municipio);
        
        assertNotNull(saved.getIdMunicipio());
        
        Municipio found = municipioRepository.findById(saved.getIdMunicipio()).orElse(null);
        assertNotNull(found);
        assertEquals("Oaxaca de Juárez", found.getMunicipio());
    }
}
