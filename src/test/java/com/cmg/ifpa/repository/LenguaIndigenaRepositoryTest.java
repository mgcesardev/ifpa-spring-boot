package com.cmg.ifpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cmg.ifpa.model.LenguaIndigena;

@DataJpaTest
public class LenguaIndigenaRepositoryTest {

    @Autowired
    private LenguaIndigenaRepository lenguaIndigenaRepository;

    @Test
    @SuppressWarnings("null")
    void testSaveAndFindById() {
        LenguaIndigena lengua = new LenguaIndigena();
        lengua.setLenguaIndigena("Mixteco");
        lengua.setEstatus("A");
        
        LenguaIndigena saved = lenguaIndigenaRepository.save(lengua);
        
        assertNotNull(saved.getIdLenguaIndigena());
        
        LenguaIndigena found = lenguaIndigenaRepository.findById(saved.getIdLenguaIndigena()).orElse(null);
        assertNotNull(found);
        assertEquals("Mixteco", found.getLenguaIndigena());
    }
}
