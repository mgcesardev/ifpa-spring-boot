package com.cmg.ifpa.seed;

import com.cmg.ifpa.model.Distrito;
import com.cmg.ifpa.repository.DistritoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@Order(10)
public class DistritoSeeder implements CommandLineRunner {

    private final DistritoRepository distritoRepository;

    public DistritoSeeder(DistritoRepository distritoRepository) {
        this.distritoRepository = distritoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDistritos();
    }

    private void seedDistritos() {
        if (distritoRepository.count() == 0) {
            log.info("Seeding initial distritos...");

            List<Distrito> distritos = Arrays.asList(
                    new Distrito(null, "Centro", "Valles Centrales", "A", null, null),
                    new Distrito(null, "Juchitán", "Istmo", "A", null, null),
                    new Distrito(null, "Juxtlahuaca", "Mixteca", "A", null, null),
                    new Distrito(null, "Miahuatlán", "Sierra Sur", "A", null, null),
                    new Distrito(null, "Nochixtlán", "Mixteca", "A", null, null),
                    new Distrito(null, "Ocotlán", "Valles Centrales", "A", null, null),
                    new Distrito(null, "Pochutla", "Costa", "A", null, null),
                    new Distrito(null, "Putla", "Sierra Sur", "A", null, null),
                    new Distrito(null, "Silacayoápam", "Mixteca", "A", null, null),
                    new Distrito(null, "Tlacolula", "Valles Centrales", "A", null, null),
                    new Distrito(null, "Tlaxiaco", "Mixteca", "A", null, null),
                    new Distrito(null, "Tuxtepec", "Papaloapan", "A", null, null),
                    new Distrito(null, "Villa Alta", "Sierra de Juárez", "A", null, null),
                    new Distrito(null, "Yautepec", "Sierra Sur", "A", null, null),
                    new Distrito(null, "Zimatlán", "Valles Centrales", "A", null, null));

            distritoRepository.saveAll(distritos);
            log.info("Successfully seeded {} distritos.", distritos.size());
        } else {
            log.info("Distritos table already has data. Skipping seeding.");
        }
    }
}
