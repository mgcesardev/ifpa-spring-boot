package com.cmg.ifpa.seed;

import com.cmg.ifpa.model.Municipio;
import com.cmg.ifpa.repository.MunicipioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@Order(11)
public class MunicipioSeeder implements CommandLineRunner {

    private final MunicipioRepository municipioRepository;

    public MunicipioSeeder(MunicipioRepository municipioRepository) {
        this.municipioRepository = municipioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedMunicipios();
    }

    private void seedMunicipios() {
        if (municipioRepository.count() == 0) {
            log.info("Seeding initial municipios...");

            List<Municipio> municipios = Arrays.asList(
                    new Municipio(null, "Oaxaca de Juárez", "Centro", "A", null, null),
                    new Municipio(null, "Santa Lucía del Camino", "Centro", "A", null, null),
                    new Municipio(null, "Santa Cruz Xoxocotlán", "Centro", "A", null, null),
                    new Municipio(null, "San Bartolo Coyotepec", "Centro", "A", null, null),
                    new Municipio(null, "Santa María Atzompa", "Centro", "A", null, null),
                    new Municipio(null, "San Pedro Pochutla", "Pochutla", "A", null, null),
                    new Municipio(null, "Santa María Huatulco", "Pochutla", "A", null, null),
                    new Municipio(null, "Heroica Ciudad de Tlaxiaco", "Tlaxiaco", "A", null, null),
                    new Municipio(null, "Santiago Juxtlahuaca", "Juxtlahuaca", "A", null, null),
                    new Municipio(null, "San Juan Bautista Tuxtepec", "Tuxtepec", "A", null, null),
                    new Municipio(null, "Santo Domingo Tehuantepec", "Tehuantepec", "A", null, null), // Tehuantepec not
                                                                                                      // in
                                                                                                      // DistritoSeeder
                                                                                                      // but used for
                                                                                                      // realism
                    new Municipio(null, "Heroica Ciudad de Juchitán de Zaragoza", "Juchitán", "A", null, null),
                    new Municipio(null, "Miahuatlán de Porfirio Díaz", "Miahuatlán", "A", null, null),
                    new Municipio(null, "Tlacolula de Matamoros", "Tlacolula", "A", null, null),
                    new Municipio(null, "San Pablo Villa de Mitla", "Tlacolula", "A", null, null));

            municipioRepository.saveAll(municipios);
            log.info("Successfully seeded {} municipios.", municipios.size());
        } else {
            log.info("Municipios table already has data. Skipping seeding.");
        }
    }
}
