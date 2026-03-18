package com.cmg.ifpa.seed;

import com.cmg.ifpa.model.GrupoEtnico;
import com.cmg.ifpa.repository.GrupoEtnicoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@Order(2)
public class GrupoEtnicoSeeder implements CommandLineRunner {

    private final GrupoEtnicoRepository grupoEtnicoRepository;

    public GrupoEtnicoSeeder(GrupoEtnicoRepository grupoEtnicoRepository) {
        this.grupoEtnicoRepository = grupoEtnicoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedGruposEtnicos();
    }

    @SuppressWarnings("null")
    private void seedGruposEtnicos() {
        if (grupoEtnicoRepository.count() == 0) {
            log.info("Seeding initial grupos étnicos...");

            List<GrupoEtnico> grupos = Arrays.asList(
                    new GrupoEtnico(null, "Zapoteco", "A", null, null),
                    new GrupoEtnico(null, "Mixteco", "A", null, null),
                    new GrupoEtnico(null, "Mazateco", "A", null, null),
                    new GrupoEtnico(null, "Mixe", "A", null, null),
                    new GrupoEtnico(null, "Chinanteco", "A", null, null),
                    new GrupoEtnico(null, "Chatino", "A", null, null),
                    new GrupoEtnico(null, "Triqui", "A", null, null),
                    new GrupoEtnico(null, "Huave", "A", null, null),
                    new GrupoEtnico(null, "Cuicateco", "A", null, null),
                    new GrupoEtnico(null, "Zoque", "A", null, null),
                    new GrupoEtnico(null, "Amuzgo", "A", null, null),
                    new GrupoEtnico(null, "Chocholteco", "A", null, null),
                    new GrupoEtnico(null, "Tacuate", "A", null, null),
                    new GrupoEtnico(null, "Nahua", "A", null, null),
                    new GrupoEtnico(null, "Ixcateco", "A", null, null));

            grupoEtnicoRepository.saveAll(grupos);
            log.info("Successfully seeded {} grupos étnicos.", grupos.size());
        } else {
            log.info("Grupos étnicos table already has data. Skipping seeding.");
        }
    }
}
