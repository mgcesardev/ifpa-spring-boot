package com.cmg.ifpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cmg.ifpa.model.Region;

@DataJpaTest
public class RegionRepositoryTest {

    @Autowired
    private RegionRepository regionRepository;

    @Test
    @SuppressWarnings("null")
    void testSaveAndFindById() {
        Region region = new Region();
        region.setRegion("Costa");
        region.setEstatus("A");
        
        Region saved = regionRepository.save(region);
        
        assertNotNull(saved.getIdRegion());
        
        Region found = regionRepository.findById(saved.getIdRegion()).orElse(null);
        assertNotNull(found);
        assertEquals("Costa", found.getRegion());
    }
}
