package com.cmg.ifpa.seed;

import com.cmg.ifpa.model.TipoComprador;
import com.cmg.ifpa.repository.TipoCompradorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@Order(6)
public class TipoCompradorSeeder implements CommandLineRunner {

    private final TipoCompradorRepository tipoCompradorRepository;

    public TipoCompradorSeeder(TipoCompradorRepository tipoCompradorRepository) {
        this.tipoCompradorRepository = tipoCompradorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedTiposComprador();
    }

    private void seedTiposComprador() {
        if (tipoCompradorRepository.count() == 0) {
            log.info("Seeding initial tipos de comprador...");

            List<TipoComprador> tipos = Arrays.asList(
                    new TipoComprador(null, "Turista Local", "Personas de la misma región.", "A", null, null),
                    new TipoComprador(null, "Turista Nacional", "Visitantes de otros estados de México.", "A", null,
                            null),
                    new TipoComprador(null, "Turista Extranjero Norteamérica", "Visitantes de USA y Canadá.", "A", null,
                            null),
                    new TipoComprador(null, "Turista Extranjero Europa", "Visitantes de países europeos.", "A", null,
                            null),
                    new TipoComprador(null, "Turista Extranjero Asia", "Visitantes de países asiáticos.", "A", null,
                            null),
                    new TipoComprador(null, "Revendedor Minorista", "Compra para venta en locales pequeños.", "A", null,
                            null),
                    new TipoComprador(null, "Revendedor Mayorista",
                            "Compra en grandes volúmenes para exportación o tiendas grandes.", "A", null, null),
                    new TipoComprador(null, "Coleccionista de Arte", "Busca piezas únicas y de autor.", "A", null,
                            null),
                    new TipoComprador(null, "Institución Gubernamental", "Compras para regalos oficiales o decoración.",
                            "A", null, null),
                    new TipoComprador(null, "Empresa Privada", "Compras corporativas para decoración o incentivos.",
                            "A", null, null),
                    new TipoComprador(null, "Diseñador de Interiores", "Busca piezas para proyectos habitacionales.",
                            "A", null, null),
                    new TipoComprador(null, "Galería de Arte", "Adquiere piezas para exhibición y venta especializada.",
                            "A", null, null),
                    new TipoComprador(null, "Museo", "Piezas para acervos culturales.", "A", null, null),
                    new TipoComprador(null, "Estudiante/Investigador", "Compra con fines académicos o de estudio.", "A",
                            null, null),
                    new TipoComprador(null, "Público en General en Ferias", "Compras casuales en eventos masivos.", "A",
                            null, null));

            tipoCompradorRepository.saveAll(tipos);
            log.info("Successfully seeded {} tipos de comprador.", tipos.size());
        } else {
            log.info("Tipos de comprador table already has data. Skipping seeding.");
        }
    }
}
