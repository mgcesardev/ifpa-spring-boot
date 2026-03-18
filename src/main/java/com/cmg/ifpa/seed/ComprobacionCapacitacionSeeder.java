package com.cmg.ifpa.seed;

import com.cmg.ifpa.model.AccionCapacitacion;
import com.cmg.ifpa.model.Artesano;
import com.cmg.ifpa.model.ComprobacionCapacitacion;
import com.cmg.ifpa.repository.AccionCapacitacionRepository;
import com.cmg.ifpa.repository.ArtesanoRepository;
import com.cmg.ifpa.repository.ComprobacionCapacitacionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
@Order(19)
public class ComprobacionCapacitacionSeeder implements CommandLineRunner {

    private final ComprobacionCapacitacionRepository comprobacionCapacitacionRepository;
    private final ArtesanoRepository artesanoRepository;
    private final AccionCapacitacionRepository accionCapacitacionRepository;

    public ComprobacionCapacitacionSeeder(ComprobacionCapacitacionRepository comprobacionCapacitacionRepository,
                                           ArtesanoRepository artesanoRepository,
                                           AccionCapacitacionRepository accionCapacitacionRepository) {
        this.comprobacionCapacitacionRepository = comprobacionCapacitacionRepository;
        this.artesanoRepository = artesanoRepository;
        this.accionCapacitacionRepository = accionCapacitacionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedComprobaciones();
    }

    private void seedComprobaciones() {
        if (comprobacionCapacitacionRepository.count() == 0) {
            log.info("Seeding initial comprobaciones de capacitación...");

            List<Artesano> artesanos = artesanoRepository.findAll();
            List<AccionCapacitacion> acciones = accionCapacitacionRepository.findAll();

            if (artesanos.isEmpty() || acciones.isEmpty()) {
                log.warn("Cannot seed ComprobacionCapacitacion: Artesano or AccionCapacitacion tables are empty.");
                return;
            }

            Random random = new Random();
            List<ComprobacionCapacitacion> comprobaciones = new ArrayList<>();

            for (int i = 0; i < 5000; i++) {
                ComprobacionCapacitacion comprobacion = new ComprobacionCapacitacion();
                comprobacion.setMonto(500.0 + (random.nextDouble() * 1500.0));
                comprobacion.setEstatus("A");
                
                // Select a random artesano and a random action
                comprobacion.setArtesano(artesanos.get(random.nextInt(artesanos.size())));
                comprobacion.setAccionCapacitacion(acciones.get(random.nextInt(acciones.size())));

                comprobaciones.add(comprobacion);
            }

            comprobacionCapacitacionRepository.saveAll(comprobaciones);
            log.info("Successfully seeded {} comprobaciones de capacitación.", comprobaciones.size());
        } else {
            log.info("ComprobacionCapacitacion table already has data. Skipping seeding.");
        }
    }
}
