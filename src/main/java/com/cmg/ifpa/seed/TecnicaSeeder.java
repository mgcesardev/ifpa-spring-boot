package com.cmg.ifpa.seed;

import com.cmg.ifpa.model.RamaArtesanal;
import com.cmg.ifpa.model.Tecnica;
import com.cmg.ifpa.repository.RamaArtesanalRepository;
import com.cmg.ifpa.repository.TecnicaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@Order(13)
public class TecnicaSeeder implements CommandLineRunner {

    private final TecnicaRepository tecnicaRepository;
    private final RamaArtesanalRepository ramaArtesanalRepository;

    public TecnicaSeeder(TecnicaRepository tecnicaRepository, RamaArtesanalRepository ramaArtesanalRepository) {
        this.tecnicaRepository = tecnicaRepository;
        this.ramaArtesanalRepository = ramaArtesanalRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedTecnicas();
    }

    @SuppressWarnings("null")
private void seedTecnicas() {
        if (tecnicaRepository.count() == 0) {
            log.info("Seeding initial técnicas...");

            // Fetch some RamaArtesanal IDs to associate with techniques
            List<RamaArtesanal> ramas = ramaArtesanalRepository.findAll();
            if (ramas.isEmpty()) {
                log.warn("No RamaArtesanal found. Cannot seed techniques accurately.");
                return;
            }

            Long idTextiles = findRamaIdByName(ramas, "Textiles");
            Long idAlfareria = findRamaIdByName(ramas, "Alfarería y Cerámica");
            Long idMadera = findRamaIdByName(ramas, "Madera");
            Long idOrfebreria = findRamaIdByName(ramas, "Orfebrería y Joyería");

            List<Tecnica> tecnicas = Arrays.asList(
                    new Tecnica(null, "TEXT-BORD-01", "Bordado a mano", "Bordado tradicional con aguja",
                            "Varios colores", idTextiles, "A", null, null),
                    new Tecnica(null, "TEXT-TELAR-02", "Telar de cintura", "Tejido en telar tradicional",
                            "Piedras y tintes", idTextiles, "A", null, null),
                    new Tecnica(null, "ALFA-BARRO-03", "Barro Negro", "Quema en horno bajo tierra", "Brillante/Mate",
                            idAlfareria, "A", null, null),
                    new Tecnica(null, "ALFA-BARRO-04", "Barro Rojo", "Modelado a mano y quema a cielo abierto",
                            "Natural", idAlfareria, "A", null, null),
                    new Tecnica(null, "MADE-ALB-05", "Talla de Alebrijes", "Tallado en madera de copal", "Policromado",
                            idMadera, "A", null, null),
                    new Tecnica(null, "MADE-MASC-06", "Talla de Máscaras", "Máscaras para danzas tradicionales",
                            "Madera de cedro", idMadera, "A", null, null),
                    new Tecnica(null, "ORFE-FILI-07", "Filigrana", "Hilos de oro o plata entrelazados", "Oro/Plata",
                            idOrfebreria, "A", null, null),
                    new Tecnica(null, "TEXT-TEJ-08", "Tejido en gancho", "Tejido manual con gancho", "Estambre",
                            idTextiles, "A", null, null),
                    new Tecnica(null, "ALFA-VIDR-09", "Barro Vidriado", "Recubrimiento de sílice", "Verde/Ámbar",
                            idAlfareria, "A", null, null),
                    new Tecnica(null, "MADE-MUEB-10", "Carpintería tradicional", "Muebles con ensambles", "Rústico",
                            idMadera, "A", null, null),
                    new Tecnica(null, "TEXT-PED-11", "Bordado con Pedrería", "Incrustación de chaquira", "Elegante",
                            idTextiles, "A", null, null),
                    new Tecnica(null, "ALFA-PAST-12", "Pastillaje", "Adorno de figuras sobrepuestas", "Detallado",
                            idAlfareria, "A", null, null),
                    new Tecnica(null, "MADE-TOR-13", "Torneado", "Uso de torno para figuras redondas", "Simétrico",
                            idMadera, "A", null, null),
                    new Tecnica(null, "ORFE-REP-14", "Repujado", "Lámina de metal trabajada por el revés", "Relieve",
                            idOrfebreria, "A", null, null),
                    new Tecnica(null, "TEXT-NAT-15", "Tintes Naturales", "Teñido con grana cochinilla", "Orgánico",
                            idTextiles, "A", null, null));

            tecnicaRepository.saveAll(tecnicas);
            log.info("Successfully seeded {} técnicas.", tecnicas.size());
        } else {
            log.info("Técnicas table already has data. Skipping seeding.");
        }
    }

    private Long findRamaIdByName(List<RamaArtesanal> ramas, String name) {
        return ramas.stream()
                .filter(r -> r.getNombreRama().equalsIgnoreCase(name))
                .map(RamaArtesanal::getIdRamaArtesanal)
                .findFirst()
                .orElse(null);
    }
}
