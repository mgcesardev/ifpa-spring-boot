package com.cmg.ifpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cmg.ifpa.model.GrupoEtnico;

@DataJpaTest
public class GrupoEtnicoRepositoryTest {

    @Autowired
    private GrupoEtnicoRepository grupoEtnicoRepository;

    @Test
    @SuppressWarnings("null")
    void testSaveAndFindById() {
        GrupoEtnico grupo = new GrupoEtnico();
        grupo.setNombreEtnia("Zapoteco");
        grupo.setEstatus("A");
        
        GrupoEtnico saved = grupoEtnicoRepository.save(grupo);
        
        assertNotNull(saved.getIdGrupoEtnico());
        
        GrupoEtnico found = grupoEtnicoRepository.findById(saved.getIdGrupoEtnico()).orElse(null);
        assertNotNull(found);
        assertEquals("Zapoteco", found.getNombreEtnia());
    }
}
