package com.cmg.ifpa.seed;

import com.cmg.ifpa.model.AccionCapacitacion;
import com.cmg.ifpa.model.Artesano;
import com.cmg.ifpa.model.InscripcionCapacitacion;
import com.cmg.ifpa.repository.AccionCapacitacionRepository;
import com.cmg.ifpa.repository.ArtesanoRepository;
import com.cmg.ifpa.repository.InscripcionCapacitacionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
@Order(18)
public class InscripcionCapacitacionSeeder implements CommandLineRunner {

    private final InscripcionCapacitacionRepository inscripcionCapacitacionRepository;
    private final ArtesanoRepository artesanoRepository;
    private final AccionCapacitacionRepository accionCapacitacionRepository;

    public InscripcionCapacitacionSeeder(InscripcionCapacitacionRepository inscripcionCapacitacionRepository,
                                          ArtesanoRepository artesanoRepository,
                                          AccionCapacitacionRepository accionCapacitacionRepository) {
        this.inscripcionCapacitacionRepository = inscripcionCapacitacionRepository;
        this.artesanoRepository = artesanoRepository;
        this.accionCapacitacionRepository = accionCapacitacionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedInscripciones();
    }

    private void seedInscripciones() {
        if (inscripcionCapacitacionRepository.count() == 0) {
            log.info("Seeding initial inscripciones de capacitación...");

            List<Artesano> artesanos = artesanoRepository.findAll();
            List<AccionCapacitacion> acciones = accionCapacitacionRepository.findAll();

            if (artesanos.isEmpty() || acciones.isEmpty()) {
                log.warn("Cannot seed InscripcionCapacitacion: Artesano or AccionCapacitacion tables are empty.");
                return;
            }

            Random random = new Random();
            List<InscripcionCapacitacion> inscripciones = new ArrayList<>();

            for (int i = 0; i < 5000; i++) {
                InscripcionCapacitacion inscripcion = new InscripcionCapacitacion();
                inscripcion.setSolicitud("SOL-" + (2024000 + i));
                inscripcion.setObservaciones("Ninguna");
                inscripcion.setAsistencia(random.nextBoolean() ? 'S' : 'N');
                inscripcion.setEstatus("A");
                
                // Select a random artesano and a random action
                inscripcion.setArtesano(artesanos.get(random.nextInt(artesanos.size())));
                inscripcion.setAccionCapacitacion(acciones.get(random.nextInt(acciones.size())));

                inscripciones.add(inscripcion);
            }

            inscripcionCapacitacionRepository.saveAll(inscripciones);
            log.info("Successfully seeded {} inscripciones de capacitación.", inscripciones.size());
        } else {
            log.info("InscripcionCapacitacion table already has data. Skipping seeding.");
        }
    }
}
