package com.cmg.ifpa.seed;

import com.cmg.ifpa.model.*;
import com.cmg.ifpa.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
@Order(17) // Ensure it runs after all reference data seeders
public class ArtesanoSeeder implements CommandLineRunner {

    private final ArtesanoRepository artesanoRepository;
    private final OrganizacionRepository organizacionRepository;
    private final RegionRepository regionRepository;
    private final DistritoRepository distritoRepository;
    private final MunicipioRepository municipioRepository;
    private final LocalidadRepository localidadRepository;
    private final GrupoEtnicoRepository grupoEtnicoRepository;
    private final MateriaPrimaRepository materiaPrimaRepository;
    private final TipoCompradorRepository tipoCompradorRepository;
    private final RamaArtesanalRepository ramaArtesanalRepository;
    private final TecnicaRepository tecnicaRepository;
    private final LenguaIndigenaRepository lenguaIndigenaRepository;

    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        if (artesanoRepository.count() < 100) { // Seed if there's very little data or empty
            log.info("Seeding 1000 test artesano records...");
            seedArtesanos(15000);
        } else {
            log.info("Artesano table already has sufficient data. Skipping seeding.");
        }
    }

    private void seedArtesanos(int count) {
        List<Organizacion> organizaciones = organizacionRepository.findAll();
        List<Region> regiones = regionRepository.findAll();
        List<Distrito> distritos = distritoRepository.findAll();
        List<Municipio> municipios = municipioRepository.findAll();
        List<Localidad> localidades = localidadRepository.findAll();
        List<GrupoEtnico> gruposEtnicos = grupoEtnicoRepository.findAll();
        List<MateriaPrima> materiasPrimas = materiaPrimaRepository.findAll();
        List<TipoComprador> tiposComprador = tipoCompradorRepository.findAll();
        List<RamaArtesanal> ramas = ramaArtesanalRepository.findAll();
        List<Tecnica> tecnicas = tecnicaRepository.findAll();
        List<LenguaIndigena> lenguas = lenguaIndigenaRepository.findAll();

        String[] nombres = {
                // A
                "ALEJANDRO", "ALBERTO", "ANTONIO", "ARMANDO", "ARTURO", "ANA", "ADRIANA", "ALICIA", "ANGELA", "AMELIA",
                // B
                "BERNARDO", "BENITO", "BENJAMIN", "BRAULIO", "BRUNO", "BEATRIZ", "BLANCA", "BELEN", "BARBARA", "BERTA",
                // C
                "CARLOS", "CESAR", "CLAUDIO", "CRISTIAN", "CUAUHTEMOC", "CARMEN", "CECILIA", "CLAUDIA", "CRISTINA",
                "CATALINA",
                // D
                "DANIEL", "DAVID", "DIEGO", "DIONISIO", "DOMINGO", "DIANA", "DOLORES", "DANIELA", "DORA", "DULCE",
                // E
                "EDUARDO", "ENRIQUE", "ESTEBAN", "EUGENIO", "ELOY", "ELENA", "ELSA", "ELVIRA", "ESPERANZA", "ESTELA",
                // F
                "FRANCISCO", "FERNANDO", "FELIPE", "FELIX", "FEDERICO", "FABIOLA", "FATIMA", "FELICIA", "FRANCISCA",
                "FLORA",
                // G
                "GABRIEL", "GERARDO", "GERMAN", "GONZALO", "GUILLERMO", "GABRIELA", "GLORIA", "GRACIELA", "GUADALUPE",
                "GERTRUDIS",
                // H
                "HECTOR", "HUGO", "HUMBERTO", "HIPOLITO", "HORACIO", "HILDA", "HORTENSIA", "HERMINIA", "HONORIA",
                "HELGA",
                // I
                "IGNACIO", "ISMAEL", "IVAN", "ISAAC", "ISIDRO", "ISABEL", "IRENE", "INES", "IRMA", "ITZEL",
                // J
                "JUAN", "JOSE", "JAVIER", "JORGE", "JESUS", "JUANA", "JOSEFINA", "JULIA", "JESSICA", "JIMENA",
                // K
                "KEVIN", "KURT", "KENJI", "KARL", "KALEB", "KARLA", "KAREN", "KESIA", "KIMBERLY", "KRISTEL",
                // L
                "LUIS", "LUCAS", "LORENZO", "LEONARDO", "LUCIO", "LAURA", "LUCIA", "LETICIA", "LUISA", "LORENA",
                // M
                "MANUEL", "MIGUEL", "MARIO", "MARCOS", "MARTIN", "MARIA", "MARTHA", "MARGARITA", "MONICA", "MARCELA",
                // N
                "NICOLAS", "NOE", "NESTOR", "NARCISO", "NOEL", "NOEMI", "NATALIA", "NELLY", "NORMA", "NORA",
                // O
                "OSCAR", "OCTAVIO", "OMAR", "ORLANDO", "ONOFRE", "OLGA", "OLIVIA", "OFELIA", "ODILA", "OTILIA",
                // P
                "PEDRO", "PABLO", "PATRICIO", "PASCUAL", "PANFILO", "PATRICIA", "PAULA", "PETRA", "PILAR", "PRUDENCIA",
                // Q
                "QUETZALCOATL", "QUINTERO", "QUIRINO", "QUINTIN", "QUEVEDO", "QUETA", "QUERUBINA", "QUIANA", "QUIRINA",
                "QUITERIA",
                // R
                "RICARDO", "ROBERTO", "RAUL", "RAMON", "RODRIGO", "ROSA", "RAQUEL", "REBECA", "RITA", "RAMONA",
                // S
                "SERGIO", "SANTIAGO", "SEBASTIAN", "SAMUEL", "SILVERIO", "SILVIA", "SARA", "SONIA", "SUSANA", "SOCORRO",
                // T
                "TOMAS", "TEODORO", "TIMOTEO", "TIBURCIO", "TRISTAN", "TERESA", "TATIANA", "TANIA", "TEOFILA",
                "TRANSITO",
                // U
                "ULISES", "URBANO", "URIEL", "UBALDO", "USIEL", "URSULA", "URANIA", "URBANA", "UXIA", "UZMA",
                // V
                "VICENTE", "VICTOR", "VALENTE", "VALENTIN", "VENANCIO", "VERONICA", "VIRGINIA", "VICTORIA", "VALERIA",
                "VANESSA",
                // W
                "WALTER", "WILFREDO", "WALDO", "WENCESLAO", "WILLIAM", "WANDA", "WENDY", "WINONA", "WHITNEY", "WILMA",
                // X
                "XAVIER", "XERXES", "XICOTENCATL", "XIMENO", "XANDRES", "XIMENA", "XIOMARA", "XOCHITL", "XENIA", "XUAN",
                // Y
                "YAMIL", "YOSEF", "YURI", "YAIR", "YONATHAN", "YOLANDA", "YADIRA", "YESENIA", "YVONNE", "YANET",
                // Z
                "ZACARIAS", "ZENON", "ZEFERINO", "ZAID", "ZIGOR", "ZULEMA", "ZORAIDA", "ZENOBIA", "ZITA", "ZOE"
        };
        String[] nivelesEscolaridad = {
            "PREESCOLAR", "PRIMARIA", "SECUNDARIA", "BACHILLERATO", "LICENCIATURA", "MAESTRIA", "DOCTORADO"
        };
        String[] apellidos = {
                // A
                "AGUILAR", "ALVAREZ", "ARROYO", "AVILA", "ALONSO", "ALCANTARA", "ACOSTA", "ALEMAN", "ANDRADE",
                "ARTEAGA",
                "AGUIRRE", "ACEVEDO", "ALBAN", "ALCANTAR", "ALVARADO", "AMEZCUA", "ANAYA", "ANGULO", "APARICIO",
                "AREVALO",
                // B
                "BUSTAMANTE", "BARRIENTOS", "BLANCO", "BORJA", "BAEZ", "BAUTISTA", "BERMUDEZ", "BOLAÑOS", "BRACAMONTES",
                "BUSTILLO",
                "BARAJAS", "BARRERA", "BARRIOS", "BASTIDA", "BECERRA", "BELTRAN", "BENAVIDES", "BERNAL", "BRAVO",
                "BURGOS",
                // C
                "CRUZ", "CASTILLO", "CORTES", "COTO", "CARRILLO", "CASTAÑEDA", "CEBALLOS", "CHAVEZ", "CORONADO",
                "CORTAZAR",
                "CABALLERO", "CABRERA", "CALDERON", "CALLEJAS", "CAMACHO", "CAMPOS", "CANO", "CANTU", "CARDENAS",
                "CARMONA",
                // D
                "DIAZ", "DOMINGUEZ", "DELGADO", "DURAN", "DE LA CRUZ", "DE LA ROSA", "DURAZNO", "DURON", "DURANGO",
                "DURVAL",
                "DAVILA", "DE LA FUENTE", "DE LEON", "DEL RIO", "DEL VALLE", "DELGADILLO", "DIEZ", "DORANTES", "DUARTE",
                "DUEÑAS",
                // E
                "ESPINOZA", "ESTRADA", "ELIZALDE", "ESQUIVEL", "ESPINOSA", "ESTEBAN", "ECHEVERRIA", "ELIAS", "ENRIQUEZ",
                "ERAZO",
                "EGBERT", "ELIZONDO", "ENCISO", "ESCALANTE", "ESCAMILLA", "ESCOBAR", "ESCOBEDO", "ESCUDERO", "ESPARZA",
                "ESPEJO",
                // F
                "FLORES", "FUENTES", "FERNANDEZ", "FIGUEROA", "FRANCO", "FONSECA", "FAJARDO", "FARFAN", "FARÍAS",
                "FAUSTO",
                "FELICIANO", "FELIX", "FERMIN", "FERRER", "FIERRO", "FLORENTINO", "FRAGOSO", "FRIAS", "FROST", "FRUTOS",
                // G
                "GARCIA", "GONZALEZ", "GOMEZ", "GUTIERREZ", "GUERRERO", "GALEANA", "GALLEGOS", "GALVAN", "GAMBOA",
                "GAONA",
                "GARAY", "GARZA", "GASCA", "GAYTAN", "GIL", "GIRON", "GODINEZ", "GODOY", "GRANADOS", "GUZMAN",
                // H
                "HERNANDEZ", "HERRERA", "HUERTA", "HURTADO", "HINOJOSA", "HEREDIA", "HERNANDES", "HIDALGO", "HIGUERA",
                "HINOJOS",
                "HIPOLITO", "HOLGUIN", "HORTA", "HOYOS", "HUERTA", "HUITRON", "HUMARA", "HURTADO", "HURTADOREA",
                "HYSLOP",
                // I
                "ISLAS", "IZQUIERDO", "IBARRA", "IGLESIAS", "INFANTE", "IBANEZ", "IBARGOYEN", "ICAZA", "IDALGO",
                "IDROGO",
                "IELES", "IGLECIAS", "IGNACIO", "IGUARAN", "ILDEFONSO", "ILLESCAS", "IMERY", "INCLAN", "INDALECIO",
                "IÑIGUEZ",
                // J
                "JIMENEZ", "JUAREZ", "JURADO", "JAIME", "JAIMES", "JAQUEZ", "JARAMILLO", "JARQUIN", "JASSO", "JAUREGUI",
                "JAVIER", "JERONIMO", "JESUS", "JIJIMENEZ", "JIL", "JINES", "JOYA", "JUAN", "JUAREZ", "JUSTO",
                // K
                "KURI", "KRAUSE", "KAISER", "KAUFMAN", "KELLER", "KEMPIS", "KENT", "KERK", "KIDD", "KING",
                "KIRCHNER", "KLEIN", "KNIGHT", "KOCH", "KONIG", "KOPPEL", "KRAUSS", "KREBS", "KRUEGER", "KUNZ",
                // L
                "LOPEZ", "LOZANO", "LUNA", "LARA", "LARIOS", "LAGUNES", "LAMAS", "LANDA", "LANDEROS", "LANDIN",
                "LANZA", "LARA", "LAZCANO", "LEAL", "LEDESMA", "LEON", "LEYVA", "LIMA", "LINARES", "LIRA",
                // M
                "MARTINEZ", "MENDOZA", "MORALES", "MONTERO", "MUNOZ", "MACIAS", "MADERA", "MADRID", "MADRIGAL",
                "MAGAÑA",
                "MALDONADO", "MANCERA", "MANZANO", "MARIN", "MARQUEZ", "MATIAS", "MAYA", "MEDINA", "MEJIA", "MENDEZ",
                // N
                "NAVARRO", "NUNEZ", "NIETO", "NAVA", "NAVARRETE", "NEGRETE", "NERI", "NICOLAS", "NIEVES", "NINO",
                "NOBLE", "NOGUERA", "NOLASCO", "NORIEGA", "NOVA", "NOVELO", "NOYOLA", "NUÑEZ", "NYBERG", "NYE",
                // O
                "ORTIZ", "OSORIO", "OLVERA", "OCHOA", "OCAMPO", "OCON", "OJEDA", "OLAREZ", "OLGUIN", "OLIVA",
                "OLIVAREZ", "OLIVAS", "OLIVARES", "OLMEDO", "OLMOS", "ONTIVEROS", "OROZCO", "ORTEGA", "ORTIZ", "OSORIO",
                // P
                "PEREZ", "PACHECO", "PADILLA", "PAEZ", "PALACIOS", "PALAFOX", "PALMA", "PALOMINO", "PALOMO", "PANTOJA",
                "PARRA", "PARTIDA", "PASILLAS", "PASTOR", "PATIÑO", "PAVON", "PEDRAZA", "PEDROZA", "PELAEZ", "PEÑA",
                // Q
                "QUINTANA", "QUEZADA", "QUERO", "QUETZAL", "QUEVEDO", "QUIJADA", "QUIJANO", "QUINONES", "QUINTANAR",
                "QUINTERO",
                "QUINTO", "QUINTOR", "QUIRARTE", "QUIRINO", "QUIROZ", "QUISTIAN", "QUITERIO", "QUITO", "QUITOZ", "QUIZ",
                // R
                "RODRIGUEZ", "RAMIREZ", "RIVERA", "ROJAS", "ROBLES", "RAMOS", "RANGEL", "RAYA", "RAYGOZA", "RAYMUNDO",
                "REBOLLAR", "RECILLAS", "REGALADO", "REINA", "RENDON", "RENTERIA", "RESENDIZ", "REYES", "REYNA",
                "REYNOSO",
                // S
                "SANCHEZ", "SOTO", "SILVA", "SALAZAR", "SALAS", "SALCEDO", "SALDAÑA", "SALGADO", "SALINAS", "SALMERON",
                "SAMANIEGO", "SAMPERIO", "SAN JUAN", "SAN MARTIN", "SAN VICENTE", "SANABRIA", "SANDOVAL", "SANTANA",
                "SANTIAGO", "SANTILLAN",
                // T
                "TORRES", "TABOADA", "TALAMANTES", "TALAVERA", "TAMAYO", "TAPIA", "TEJEDA", "TELLEZ", "TENORIO",
                "TERAN",
                "TERRAZAS", "TIBURCIO", "TIJERINA", "TINOCO", "TIRADO", "TOVAR", "TREJO", "TREVIÑO", "TRUJILLO",
                "TURRUBIATES",
                // U
                "URBINA", "UGALDE", "ULLOA", "UMANA", "UNZAGA", "URBANO", "URBIETA", "URDIALES", "URENDA", "UREÑA",
                "URIBE", "URIETA", "URIAS", "URQUIETA", "URQUIZA", "URRUTIA", "URZUA", "USCANDA", "USIEL", "USO",
                // V
                "VASQUEZ", "VILLANUEVA", "VILLALOBOS", "VACA", "VALADEZ", "VALDES", "VALDEZ", "VALDIVIA", "VALDOR",
                "VALENCIA",
                "VALENZUELA", "VALERA", "VALERO", "VALERIO", "VALLES", "VALLE", "VARA", "VARGAS", "VAZQUEZ", "VELASCO",
                // W
                "WONG", "WAGNER", "WALKER", "WALTER", "WARD", "WARNER", "WARREN", "WATSON", "WEBER", "WEBSTER",
                "WEIDNER", "WEINSTEIN", "WEISS", "WELCH", "WELLS", "WEST", "WHEATLEY", "WHEELER", "WHITE", "WILSON",
                // X
                "XICOTENCATL", "XAVIER", "XIMENA", "XOCHICALE", "XOCHIMILCO", "XOCHIQUIQUITZAL", "XOCHITLE", "XOLALPA",
                "XOLO", "XONTACOTA",
                "XUAREZ", "XUL", "XURY", "XUT", "XYL", "XAN", "XEN", "XIN", "XON", "XUN",
                // Y
                "YANEZ", "YBARRA", "YBARGOYEN", "YEPEZ", "YERENA", "YOCUPICIO", "YON", "YOUNG", "YOUPICI", "YU",
                "YUCUPICIO", "YUEN", "YULTI", "YUN", "YUP", "YUR", "YUS", "YUT", "YVON", "YAM",
                // Z
                "ZAMORA", "ZABALA", "ZAGAL", "ZAID", "ZALDIVAR", "ZAMBRANO", "ZAMUDIO", "ZAPATA", "ZARAGOZA", "ZARATE",
                "ZAVALA", "ZAYAS", "ZEMPOALTECA", "ZENDEJAS", "ZEPEDA", "ZERMEÑO", "ZERTUCHE", "ZUÑIGA", "ZURITA",
                "ZURUTUZA"
        };

        List<Artesano> artesanos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Artesano a = new Artesano();
            String name = getRandom(nombres);
            String lastName1 = getRandom(apellidos);
            String lastName2 = getRandom(apellidos);

            a.setNombre(name);
            a.setPrimerApellido(lastName1);
            a.setSegundoApellido(lastName2);
            a.setSexo(random.nextBoolean() ? 'H' : 'M');
            int year = 1940 + random.nextInt(45); // Approx 20 to 85 years old
            int month = 1 + random.nextInt(12);
            int day = 1 + random.nextInt(28); // Safe day for all months
            a.setFechaNacimiento(LocalDate.of(year, month, day));
            a.setEstadoCivil(random.nextBoolean() ? 'S' : 'C');

            String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            a.setClaveIFPA("ART-" + uniqueSuffix);
            a.setCurp(
                    lastName1.substring(0, 2) + lastName2.substring(0, 1) + name.substring(0, 1) + "XX" + uniqueSuffix);
            a.setClaveINE("INE-" + uniqueSuffix);
            a.setRfc(lastName1.substring(0, 2) + lastName2.substring(0, 1) + name.substring(0, 1) + uniqueSuffix);

            a.setCalle("CALLE " + random.nextInt(100));
            a.setNumeroExterior(random.nextInt(1000));
            a.setCodigoPostal(68000 + random.nextInt(1000));
            a.setTelefonoFijo("951" + (1000000 + random.nextInt(8999999)));
            a.setTelefonoMovil("951" + (1000000 + random.nextInt(8999999)));
            a.setTelefonoRecados("951" + (1000000 + random.nextInt(8999999)));

            // Social Media as JSON-like string (simplified for this task)
            String fb = name.toLowerCase() + "." + lastName1.toLowerCase();
            String ig = name.toLowerCase() + "_" + lastName1.toLowerCase();
            a.setRedesSociales(String.format(
                    "{\"facebook\": \"https://facebook.com/%s\", \"instagram\": \"https://instagram.com/%s\"}", fb,
                    ig));

            a.setCorreoElectronico(name.toLowerCase() + "." + lastName1.toLowerCase() + i + "@example.com");
            a.setEscolaridad(getRandom(nivelesEscolaridad));
            a.setGrupoPertenencia("INDIVIDUAL");
            a.setEstatus(random.nextBoolean() ? "A" : "I");
            a.setProveedor('0');

            // Randomly assign foreign keys if available
            if (!organizaciones.isEmpty())
                a.setOrganizacion(getRandom(organizaciones));
            if (!regiones.isEmpty())
                a.setRegion(getRandom(regiones));
            if (!distritos.isEmpty())
                a.setDistrito(getRandom(distritos));
            if (!municipios.isEmpty())
                a.setMunicipio(getRandom(municipios));
            if (!localidades.isEmpty())
                a.setLocalidad(getRandom(localidades));
            if (!gruposEtnicos.isEmpty())
                a.setGrupoEtnico(getRandom(gruposEtnicos));
            if (!materiasPrimas.isEmpty())
                a.setMateriaPrima(getRandom(materiasPrimas));
            if (!tiposComprador.isEmpty())
                a.setTipoComprador(getRandom(tiposComprador));
            if (!ramas.isEmpty())
                a.setRamaArtesanal(getRandom(ramas));
            if (!tecnicas.isEmpty())
                a.setTecnica(getRandom(tecnicas));
            if (!lenguas.isEmpty())
                a.setLenguaIndigena(getRandom(lenguas));

            artesanos.add(a);

            // Save in batches of 100 to avoid memory issues if count is larger
            if (artesanos.size() >= 100) {
                artesanoRepository.saveAll(artesanos);
                artesanos.clear();
            }
        }

        if (!artesanos.isEmpty()) {
            artesanoRepository.saveAll(artesanos);
        }

        log.info("Successfully seeded {} artesano records.", count);
    }

    private <T> T getRandom(T[] array) {
        return array[random.nextInt(array.length)];
    }

    private <T> T getRandom(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }
}
