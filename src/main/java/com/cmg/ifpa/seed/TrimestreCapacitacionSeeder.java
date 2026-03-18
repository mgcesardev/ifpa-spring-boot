package com.cmg.ifpa.seed;

import com.cmg.ifpa.model.TrimestreCapacitacion;
import com.cmg.ifpa.repository.TrimestreCapacitacionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@Order(7)
public class TrimestreCapacitacionSeeder implements CommandLineRunner {

    private final TrimestreCapacitacionRepository trimestreCapacitacionRepository;

    public TrimestreCapacitacionSeeder(TrimestreCapacitacionRepository trimestreCapacitacionRepository) {
        this.trimestreCapacitacionRepository = trimestreCapacitacionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedTrimestres();
    }

    private void seedTrimestres() {
        if (trimestreCapacitacionRepository.count() == 0) {
            log.info("Seeding initial trimestres de capacitación...");

            List<TrimestreCapacitacion> trimestres = Arrays.asList(
                    new TrimestreCapacitacion(null, "Enero", "Marzo", "2023", '1', "Ene, Feb, Mar", "A", null, null),
                    new TrimestreCapacitacion(null, "Abril", "Junio", "2023", '0', "Abr, May, Jun", "A", null, null),
                    new TrimestreCapacitacion(null, "Julio", "Septiembre", "2023", '0', "Jul, Ago, Sep", "A", null,
                            null),
                    new TrimestreCapacitacion(null, "Octubre", "Diciembre", "2023", '0', "Oct, Nov, Dic", "A", null,
                            null),
                    new TrimestreCapacitacion(null, "Enero", "Marzo", "2024", '1', "Ene, Feb, Mar", "A", null, null),
                    new TrimestreCapacitacion(null, "Abril", "Junio", "2024", '0', "Abr, May, Jun", "A", null, null),
                    new TrimestreCapacitacion(null, "Julio", "Septiembre", "2024", '0', "Jul, Ago, Sep", "A", null,
                            null),
                    new TrimestreCapacitacion(null, "Octubre", "Diciembre", "2024", '0', "Oct, Nov, Dic", "A", null,
                            null),
                    new TrimestreCapacitacion(null, "Enero", "Marzo", "2025", '1', "Ene, Feb, Mar", "A", null, null),
                    new TrimestreCapacitacion(null, "Abril", "Junio", "2025", '0', "Abr, May, Jun", "A", null, null),
                    new TrimestreCapacitacion(null, "Julio", "Septiembre", "2025", '0', "Jul, Ago, Sep", "A", null,
                            null),
                    new TrimestreCapacitacion(null, "Octubre", "Diciembre", "2025", '0', "Oct, Nov, Dic", "A", null,
                            null),
                    new TrimestreCapacitacion(null, "Enero", "Marzo", "2026", '1', "Ene, Feb, Mar", "A", null, null),
                    new TrimestreCapacitacion(null, "Abril", "Junio", "2026", '0', "Abr, May, Jun", "A", null, null),
                    new TrimestreCapacitacion(null, "Julio", "Septiembre", "2026", '0', "Jul, Ago, Sep", "A", null,
                            null));

            trimestreCapacitacionRepository.saveAll(trimestres);
            log.info("Successfully seeded {} trimestres.", trimestres.size());
        } else {
            log.info("Trimestres table already has data. Skipping seeding.");
        }
    }
}
