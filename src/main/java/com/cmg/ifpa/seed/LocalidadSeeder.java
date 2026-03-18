package com.cmg.ifpa.seed;

import com.cmg.ifpa.model.Localidad;
import com.cmg.ifpa.repository.LocalidadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@Order(12)
public class LocalidadSeeder implements CommandLineRunner {

    private final LocalidadRepository localidadRepository;

    public LocalidadSeeder(LocalidadRepository localidadRepository) {
        this.localidadRepository = localidadRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedLocalidades();
    }

    @SuppressWarnings("null")
    private void seedLocalidades() {
        if (localidadRepository.count() == 0) {
            log.info("Seeding initial localidades...");

            List<Localidad> localidades = Arrays.asList(
                    new Localidad(null, "Centro", "1", "A", null, null),
                    new Localidad(null, "Santa Rosa Panzacola", "1", "A", null, null),
                    new Localidad(null, "San Felipe del Agua", "1", "A", null, null),
                    new Localidad(null, "Xochimilco", "1", "A", null, null),
                    new Localidad(null, "San Bartolo Coyotepec Centro", "4", "A", null, null),
                    new Localidad(null, "La Crucecita", "7", "A", null, null),
                    new Localidad(null, "Santa María Huatulco Centro", "7", "A", null, null),
                    new Localidad(null, "Tlaxiaco Centro", "8", "A", null, null),
                    new Localidad(null, "Juxtlahuaca Centro", "9", "A", null, null),
                    new Localidad(null, "Tuxtepec Centro", "10", "A", null, null),
                    new Localidad(null, "Tehuantepec Centro", "11", "A", null, null),
                    new Localidad(null, "Juchitán Centro", "12", "A", null, null),
                    new Localidad(null, "Miahuatlán Centro", "13", "A", null, null),
                    new Localidad(null, "Tlacolula Centro", "14", "A", null, null),
                    new Localidad(null, "Mitla Centro", "15", "A", null, null));

            localidadRepository.saveAll(localidades);
            log.info("Successfully seeded {} localidades.", localidades.size());
        } else {
            log.info("Localidades table already has data. Skipping seeding.");
        }
    }
}
