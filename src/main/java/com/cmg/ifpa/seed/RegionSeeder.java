package com.cmg.ifpa.seed;

import com.cmg.ifpa.model.Region;
import com.cmg.ifpa.repository.RegionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@Order(1)
public class RegionSeeder implements CommandLineRunner {

    private final RegionRepository regionRepository;

    public RegionSeeder(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedRegions();
    }

    @SuppressWarnings("null")
    private void seedRegions() {
        if (regionRepository.count() == 0) {
            log.info("Seeding initial regions...");

            List<Region> regions = Arrays.asList(
                    new Region(null, "Cañada", "A", null, null),
                    new Region(null, "Costa", "A", null, null),
                    new Region(null, "Istmo", "A", null, null),
                    new Region(null, "Mixteca", "A", null, null),
                    new Region(null, "Papaloapan", "A", null, null),
                    new Region(null, "Sierra de Flores Magón", "A", null, null),
                    new Region(null, "Sierra de Juárez", "A", null, null),
                    new Region(null, "Sierra Sur", "A", null, null),
                    new Region(null, "Valles Centrales", "A", null, null));

            regionRepository.saveAll(regions);
            log.info("Successfully seeded {} regions.", regions.size());
        } else {
            log.info("Regions table already has data. Skipping seeding.");
        }
    }
}
