package com.cmg.ifpa.seed;

import com.cmg.ifpa.model.RamaArtesanal;
import com.cmg.ifpa.repository.RamaArtesanalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@Order(4)
public class RamaArtesanalSeeder implements CommandLineRunner {

    private final RamaArtesanalRepository ramaArtesanalRepository;

    public RamaArtesanalSeeder(RamaArtesanalRepository ramaArtesanalRepository) {
        this.ramaArtesanalRepository = ramaArtesanalRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedRamasArtesanales();
    }

    private void seedRamasArtesanales() {
        if (ramaArtesanalRepository.count() == 0) {
            log.info("Seeding initial ramas artesanales...");

            List<RamaArtesanal> ramas = Arrays.asList(
                    new RamaArtesanal(null, "TEXT-01", "Textiles", "Tejidos y bordados tradicionales", "A", null, null),
                    new RamaArtesanal(null, "ALFA-02", "Alfarería y Cerámica", "Barro negro, vidriado y natural", "A",
                            null, null),
                    new RamaArtesanal(null, "MADER-03", "Madera", "Talla de alebrijes y máscaras", "A", null, null),
                    new RamaArtesanal(null, "ORFE-04", "Orfebrería y Joyería", "Filigrana en oro y plata", "A", null,
                            null),
                    new RamaArtesanal(null, "TALAB-05", "Talabartería y Peletería", "Trabajos en cuero y piel", "A",
                            null, null),
                    new RamaArtesanal(null, "FIBR-06", "Fibras Vegetales", "Cestería de palma y carrizo", "A", null,
                            null),
                    new RamaArtesanal(null, "CANT-07", "Cantería y Lapidaria", "Tallado en piedra y mármol", "A", null,
                            null),
                    new RamaArtesanal(null, "CERER-08", "Cerería", "Velas decorativas y tradicionales", "A", null,
                            null),
                    new RamaArtesanal(null, "METAL-09", "Metales y Herrería", "Trabajos en hierro forjado", "A", null,
                            null),
                    new RamaArtesanal(null, "VIDRI-10", "Vidrio", "Vidrio soplado y decorado", "A", null, null),
                    new RamaArtesanal(null, "PAPEL-11", "Papel y Cartonería", "Papel picado y figuras de cartón", "A",
                            null, null),
                    new RamaArtesanal(null, "CONCH-12", "Concha y Caracol", "Artesanías marinas", "A", null, null),
                    new RamaArtesanal(null, "HUESO-13", "Hueso y Cuerno", "Tallado en materiales orgánicos", "A", null,
                            null),
                    new RamaArtesanal(null, "PLARI-14", "Platería", "Joyas de plata lina", "A", null, null),
                    new RamaArtesanal(null, "MINIA-15", "Miniatura", "Artesanías en pequeña escala", "A", null, null));

            ramaArtesanalRepository.saveAll(ramas);
            log.info("Successfully seeded {} ramas artesanales.", ramas.size());
        } else {
            log.info("Ramas artesanales table already has data. Skipping seeding.");
        }
    }
}
