package com.cmg.ifpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cmg.ifpa.model.Evento;

@DataJpaTest
public class EventoRepositoryTest {

    @Autowired
    private EventoRepository eventoRepository;

    @Test
    @SuppressWarnings("null")
    void testSaveAndFindById() {
        Evento evento = new Evento();
        evento.setFolio("EV-2024-001");
        evento.setEstatus("A");
        
        Evento saved = eventoRepository.save(evento);
        
        assertNotNull(saved.getIdEvento());
        
        Evento found = eventoRepository.findById(saved.getIdEvento()).orElse(null);
        assertNotNull(found);
        assertEquals("EV-2024-001", found.getFolio());
    }
}
