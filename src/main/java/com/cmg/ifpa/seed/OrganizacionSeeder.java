package com.cmg.ifpa.seed;

import com.cmg.ifpa.model.Organizacion;
import com.cmg.ifpa.repository.OrganizacionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@Order(16)
public class OrganizacionSeeder implements CommandLineRunner {

    private final OrganizacionRepository organizacionRepository;

    public OrganizacionSeeder(OrganizacionRepository organizacionRepository) {
        this.organizacionRepository = organizacionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedOrganizaciones();
    }

    private void seedOrganizaciones() {
        if (organizacionRepository.count() == 0) {
            log.info("Seeding initial organizaciones...");

            List<Organizacion> organizaciones = Arrays.asList(
                    new Organizacion(null, "ORG-001", "Cooperativa Manos Mágicas", "Juana Pérez", "Cooperativa Manos Mágicas S.C.", "MMM120101ABC", "Calle Real 10", 10, null, 68000, "9511234567", "9519876543", null, "coopmanosmagicas@gmail.com", "20", "15", "5", "Tejedoras de lana fina", "COOPERATIVA", "ARTESANAL", "1", "1", "1", "1", "1", "1", "A", null, null),
                    new Organizacion(null, "ORG-002", "Artesanos del Valle", "Mario Ruíz", "Artesanos del Valle A.C.", "AVM150505XYZ", "Av. Central 5", 5, "B", 71230, "9512345678", "9518765432", null, "artesanosvalle@hotmail.com", "15", "8", "7", "Productores de barro negro", "ASOCIACION", "ARTESANAL", "8", "1", "385", "8821", "8", "1", "A", null, null),
                    new Organizacion(null, "ORG-003", "Textiles la Sierra", "Elena Castro", "Textiles la Sierra de Juárez S.C. de R.L.", "TSJ100808DEF", "Calle de los Pinos 2", 2, null, 68740, "9513456789", "9517654321", null, "textilessierra@live.com", "25", "20", "5", "Bordados tradicionales", "SOCIEDAD COOPERATIVA", "ARTESANAL", "7", "24", "551", "12704", "1", "3", "A", null, null),
                    new Organizacion(null, "ORG-004", "Alfareros Unidos", "Roberto Gómez", "Alfareros Unidos del Sur", "AUS181212GHI", "Privada del Sol 14", 14, null, 71500, "9514567890", "9516543210", null, "alfarerosunidos@yahoo.com", "12", "5", "7", "Taller colectivo de cerámica", "GRUPO INFORMAL", "ARTESANAL", "3", "10", "198", "5075", "8", "1", "A", null, null),
                    new Organizacion(null, "ORG-005", "Bordados con Alma", "Luisa Mendez", "Bordados con Alma S.A. de C.V.", "BCA200120JKL", "Calle Libertad 100", 100, null, 68450, "2871234567", "2879876543", null, "bordadosalma@gmail.com", "30", "30", "0", "Huipiles de alta calidad", "SOCIEDAD ANONIMA", "ARTESANAL", "5", "26", "184", "4556", "1", "18", "A", null, null),
                    new Organizacion(null, "ORG-006", "Palma y Corazón", "Miguel Á. Sosa", "Palma y Corazón del Istmo", "PCI140303MNO", "Calle Juarez 45", 45, null, 70137, "9711234567", "9719876543", null, "palmacorazon@outlook.com", "18", "10", "8", "Tejido de palma y cestería", "ASOCIACION", "ARTESANAL", "3", "10", "130", "3592", "2", "10", "A", null, null),
                    new Organizacion(null, "ORG-007", "Alebrijes del Futuro", "Silvia Arango", "Alebrijes del Futuro S.C.", "ADF111111PQR", "Calle de los Artesanos 12", 12, null, 71230, "9515678901", "9515432109", null, "alebrijes@arteweb.mx", "10", "4", "6", "Tallado de madera y pintura", "COOPERATIVA", "ARTESANAL", "8", "1", "385", "8821", "7", "25", "A", null, null),
                    new Organizacion(null, "ORG-008", "Mujeres Tejiendo Sueños", "Rosa Martínez", "Mujeres Tejiendo Sueños Mixteca", "MTS090909STU", "Calle Reforma 8", 8, "A", 69000, "9531234567", "9539876543", null, "tejiendosuenos@gmail.com", "40", "40", "0", "Telares de cintura", "GRUPO TRABAJO", "ARTESANAL", "4", "7", "39", "1176", "1", "3", "A", null, null),
                    new Organizacion(null, "ORG-009", "Cinceladores del Istmo", "Juan Carlos Gil", "Cinceladores del Istmo", "CIST130404VWX", "Av. Morelos 1", 1, null, 70000, "9712345678", "9718765432", null, "cinceladores@prodigy.net.mx", "8", "2", "6", "Orfebrería en plata", "TALLER COLECTIVO", "ARTESANAL", "3", "10", "43", "1393", "11", "3", "A", null, null),
                    new Organizacion(null, "ORG-010", "Tapetes Ancestrales", "Fernando Ruíz", "Tapetes Ancestrales Teotitlán", "TAT150606YZ1", "Km 15 Carr. Istmo", 15, null, 70420, "9516789012", "9514321098", null, "tapetes_ancestrales@gmail.com", "22", "11", "11", "Tapetes de lana teñidos", "ASOCIACION", "ARTESANAL", "8", "24", "298", "7052", "1", "3", "A", null, null),
                    new Organizacion(null, "ORG-011", "Arte y Barro", "Catalina Díaz", "Arte y Barro Atzompa", "ABA101010A2B", "Av. Ferrocarril 3", 3, null, 71220, "9513214567", "9517894561", null, "arteybarro@live.com", "14", "9", "5", "Cerámica vidriada", "COOPERATIVA", "ARTESANAL", "8", "1", "399", "9176", "8", "7", "A", null, null),
                    new Organizacion(null, "ORG-012", "Hilos de Tradición", "Margarita López", "Hilos de Tradición Tlahuitoltepec", "HTT120505C3D", "Centro Loc. Tlahuitoltepec", 0, "S/N", 70250, "9514561234", "9516549871", null, "hilostradicion@outlook.es", "35", "32", "3", "Bordados en máquina", "SOCIEDAD CIVIL", "ARTESANAL", "7", "18", "447", "10348", "1", "18", "A", null, null),
                    new Organizacion(null, "ORG-013", "Maestros del Mezcal Art.", "Jorge Santiago", "Asoc. Maestros Mezcaleros Art.", "AMM110111E4F", "Callejón de la Noche 14", 14, null, 68470, "2873456789", "2877654321", null, "mezcalartesanal@gmail.com", "15", "3", "12", "Embotellado y diseño de botellas", "ASOCIACION", "HIBRIDO", "5", "26", "232", "5798", "11", "12", "A", null, null),
                    new Organizacion(null, "ORG-014", "Piedra y Fuego", "Arturo Méndez", "Piedra y Fuego S.C. de R.L.", "PIF130202G5H", "Duranzo 21", 21, null, 70780, "9715678901", "9714567890", null, "piedrayfuego@prodigy.net", "11", "2", "9", "Labrado de piedra cantera", "COOPERATIVA", "ARTESANAL", "3", "21", "248", "6073", "1", "17", "A", null, null),
                    new Organizacion(null, "ORG-015", "Viento y Música", "Bernardo Jiménez", "Instrumentos Viento y Música", "VYM180303I6J", "Calle Real 55", 55, null, 68000, "9516781234", "9513459876", null, "vientoymusica@gmail.com", "6", "0", "6", "Fabricación de instrumentos", "GRUPO PRODUCCION", "ARTESANAL", "1", "1", "1", "1", "1", "1", "A", null, null)
            );

            organizacionRepository.saveAll(organizaciones);
            log.info("Successfully seeded {} organizaciones.", organizaciones.size());
        } else {
            log.info("Organizaciones table already has data. Skipping seeding.");
        }
    }
}
