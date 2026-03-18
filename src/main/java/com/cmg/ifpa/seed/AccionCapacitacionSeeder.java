package com.cmg.ifpa.seed;

import com.cmg.ifpa.model.AccionCapacitacion;
import com.cmg.ifpa.model.ProgramaCapacitacion;
import com.cmg.ifpa.model.TrimestreCapacitacion;
import com.cmg.ifpa.repository.AccionCapacitacionRepository;
import com.cmg.ifpa.repository.ProgramaCapacitacionRepository;
import com.cmg.ifpa.repository.TrimestreCapacitacionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
@Order(15)
public class AccionCapacitacionSeeder implements CommandLineRunner {

    private final AccionCapacitacionRepository accionCapacitacionRepository;
    private final ProgramaCapacitacionRepository programaCapacitacionRepository;
    private final TrimestreCapacitacionRepository trimestreCapacitacionRepository;

    public AccionCapacitacionSeeder(AccionCapacitacionRepository accionCapacitacionRepository,
                                     ProgramaCapacitacionRepository programaCapacitacionRepository,
                                     TrimestreCapacitacionRepository trimestreCapacitacionRepository) {
        this.accionCapacitacionRepository = accionCapacitacionRepository;
        this.programaCapacitacionRepository = programaCapacitacionRepository;
        this.trimestreCapacitacionRepository = trimestreCapacitacionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedAcciones();
    }

    private void seedAcciones() {
        if (accionCapacitacionRepository.count() == 0) {
            log.info("Seeding initial acciones de capacitación...");

            List<ProgramaCapacitacion> programas = programaCapacitacionRepository.findAll();
            List<TrimestreCapacitacion> trimestres = trimestreCapacitacionRepository.findAll();

            if (programas.isEmpty() || trimestres.isEmpty()) {
                log.warn("Cannot seed AccionCapacitacion: ProgramaCapacitacion or TrimestreCapacitacion tables are empty.");
                return;
            }

            Random random = new Random();
            List<AccionCapacitacion> acciones = new ArrayList<>();

            String[] areas = {"Textiles", "Alfarería", "Madera", "Fibras Vegetales", "Metalistería", "Joyería"};
            String[] nombres = {
                "Taller de Bordado Tradicional", "Curso de Torneado en Madera", "Seminario de Tintes Naturales",
                "Diplomado en Gestión Cultural", "Taller de Cerámica de Alta Temperatura", "Curso de Joyería de Autor",
                "Taller de Tejido en Telar de Cintura", "Seminario de Comercialización Artesanal",
                "Curso de Curaduría de Arte Popular", "Taller de Cestería con Palma", "Seminario de Diseño y Artesanía",
                "Taller de Talla en Madera", "Curso de Orfebrería", "Seminario de Mercadotecnia Digital",
                "Taller de Curtido de Piel", "Curso de Encuadernación Artesanal", "Seminario de Propiedad Intelectual",
                "Taller de Papel Amate", "Curso de Juguetería Tradicional", "Seminario de Exportación de Artesanías",
                "Taller de Vidrio Soplado", "Curso de Forja Artística", "Seminario de Costos y Precios",
                "Taller de Sombreros de Fibra", "Curso de Pintura en Cerámica", "Seminario de Empaque y Embalaje",
                "Taller de Rebozos", "Curso de Tapetes de Lana", "Seminario de Fotografía de Producto",
                "Taller de Máscaras Tradicionales"
            };

            for (int i = 0; i < 35; i++) {
                AccionCapacitacion accion = new AccionCapacitacion();
                accion.setArea(areas[random.nextInt(areas.length)]);
                accion.setNombre(nombres[i % nombres.length] + " " + (i + 1));
                accion.setTextoConstancia("Por haber participado satisfactoriamente en el curso " + (i + 1));
                accion.setCapacitadores("Capacitador Experto " + (i + 1));
                accion.setObjetivo("Fortalecer las habilidades técnicas en " + accion.getArea());
                accion.setPoblacionObjetivo("Artesanos de la región");
                accion.setDuracion((10 + random.nextInt(40)) + " horas");
                accion.setNivel(random.nextBoolean() ? "Básico" : "Intermedio");
                accion.setAnnio("2024");
                accion.setCargos("Ninguno");
                accion.setEstatus("A");
                accion.setProgramaCapacitacion(programas.get(random.nextInt(programas.size())));
                accion.setTrimestreCapacitacion(trimestres.get(random.nextInt(trimestres.size())));
                
                acciones.add(accion);
            }

            accionCapacitacionRepository.saveAll(acciones);
            log.info("Successfully seeded {} acciones de capacitación.", acciones.size());
        } else {
            log.info("AccionCapacitacion table already has data. Skipping seeding.");
        }
    }
}
