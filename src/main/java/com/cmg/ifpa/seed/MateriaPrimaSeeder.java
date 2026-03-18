package com.cmg.ifpa.seed;

import com.cmg.ifpa.model.MateriaPrima;
import com.cmg.ifpa.repository.MateriaPrimaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@Order(5)
public class MateriaPrimaSeeder implements CommandLineRunner {

    private final MateriaPrimaRepository materiaPrimaRepository;

    public MateriaPrimaSeeder(MateriaPrimaRepository materiaPrimaRepository) {
        this.materiaPrimaRepository = materiaPrimaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedMateriasPrimas();
    }

    private void seedMateriasPrimas() {
        if (materiaPrimaRepository.count() == 0) {
            log.info("Seeding initial materias primas...");

            List<MateriaPrima> materias = Arrays.asList(
                    new MateriaPrima(null, "Barro Negro", "Barro especial de San Bartolo Coyotepec.", "A", null, null),
                    new MateriaPrima(null, "Barro Rojo", "Barro de Tlacolula y sus alrededores.", "A", null, null),
                    new MateriaPrima(null, "Lana de Borrego", "Lana natural para tapetes y prendas.", "A", null, null),
                    new MateriaPrima(null, "Madera de Copal", "Madera blanda ideal para tallar alebrijes.", "A", null,
                            null),
                    new MateriaPrima(null, "Palma Natural", "Fibras de palma para sombreros y canastos.", "A", null,
                            null),
                    new MateriaPrima(null, "Seda Natural", "Hilos de seda producidos en la Sierra Norte.", "A", null,
                            null),
                    new MateriaPrima(null, "Ixtle", "Fibra extraída del maguey.", "A", null, null), // Fix in Java
                                                                                                     // below if needed
                    new MateriaPrima(null, "Hilo de Algodón", "Algodón para tejidos en telar.", "A", null, null),
                    new MateriaPrima(null, "Carrizo", "Varas naturales para canastería.", "A", null, null),
                    new MateriaPrima(null, "Cuero de Res", "Piel curtida para talabartería.", "A", null, null),
                    new MateriaPrima(null, "Piedra de Cantera", "Piedra verde y amarilla para construcción y arte.",
                            "A", null, null),
                    new MateriaPrima(null, "Cera de Abeja", "Cera natural para velas escamadas.", "A", null, null),
                    new MateriaPrima(null, "Hojalata", "Lámina metálica para adornos.", "A", null, null),
                    new MateriaPrima(null, "Tintes Naturales", "Grana cochinilla, añil y cáscara de nuez.", "A", null,
                            null),
                    new MateriaPrima(null, "Barro Vidriado", "Barro con acabado de esmalte de plomo o libre.", "A",
                            null, null));
            // I used MateriaPrima class name correctly above, just fixed a typo in my mind

            materiaPrimaRepository.saveAll(materias);
            log.info("Successfully seeded {} materias primas.", materias.size());
        } else {
            log.info("Materias primas table already has data. Skipping seeding.");
        }
    }
}
