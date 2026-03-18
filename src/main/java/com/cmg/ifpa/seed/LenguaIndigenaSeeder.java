package com.cmg.ifpa.seed;

import com.cmg.ifpa.model.LenguaIndigena;
import com.cmg.ifpa.repository.LenguaIndigenaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@Order(3)
public class LenguaIndigenaSeeder implements CommandLineRunner {

    private final LenguaIndigenaRepository lenguaIndigenaRepository;

    public LenguaIndigenaSeeder(LenguaIndigenaRepository lenguaIndigenaRepository) {
        this.lenguaIndigenaRepository = lenguaIndigenaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedLenguasIndigenas();
    }

    private void seedLenguasIndigenas() {
        if (lenguaIndigenaRepository.count() == 0) {
            log.info("Seeding initial lenguas indígenas...");

            List<LenguaIndigena> lenguas = Arrays.asList(
                    new LenguaIndigena(null, "Zapoteco de la Sierra Sur", "A", null, null),
                    new LenguaIndigena(null, "Mixteco de la Costa", "A", null, null),
                    new LenguaIndigena(null, "Mazateco del Norte", "A", null, null),
                    new LenguaIndigena(null, "Mixe Alto", "A", null, null),
                    new LenguaIndigena(null, "Chinanteco de Tuxtepec", "A", null, null),
                    new LenguaIndigena(null, "Chatino de Jahuiche", "A", null, null),
                    new LenguaIndigena(null, "Triqui de Copala", "A", null, null),
                    new LenguaIndigena(null, "Huave de San Mateo", "A", null, null),
                    new LenguaIndigena(null, "Cuicateco del Centro", "A", null, null),
                    new LenguaIndigena(null, "Zoque de Chimalapas", "A", null, null),
                    new LenguaIndigena(null, "Amuzgo de Ipalapa", "A", null, null),
                    new LenguaIndigena(null, "Chocholteco de Nativitas", "A", null, null),
                    new LenguaIndigena(null, "Tacuate de Ixtayutla", "A", null, null),
                    new LenguaIndigena(null, "Nahua del Sur", "A", null, null),
                    new LenguaIndigena(null, "Ixcateco de Santa María", "A", null, null));

            lenguaIndigenaRepository.saveAll(lenguas);
            log.info("Successfully seeded {} lenguas indígenas.", lenguas.size());
        } else {
            log.info("Lenguas indígenas table already has data. Skipping seeding.");
        }
    }
}
