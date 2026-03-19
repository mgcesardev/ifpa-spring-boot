package com.cmg.ifpa.seed;

import com.cmg.ifpa.model.Usuario;
import com.cmg.ifpa.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@Order(8)
public class UsuarioSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    public UsuarioSeeder(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedUsuarios();
    }

    @SuppressWarnings("null")
private void seedUsuarios() {
        if (usuarioRepository.count() == 0) {
            log.info("Seeding initial usuarios...");

            List<Usuario> usuarios = Arrays.asList(
                    new Usuario(null, "Admin", "General", "IFPA", "9511234567", "admin@ifpa.gob.mx", "password123", 'A',
                            "A", null, null),
                    new Usuario(null, "Juan", "Pérez", "García", "9510001111", "juan.perez@ifpa.gob.mx", "password123", 'U',
                            "A", null, null),
                    new Usuario(null, "María", "López", "Martínez", "9512223333", "maria.lopez@ifpa.gob.mx", "password123",
                            'U', "A", null, null),
                    new Usuario(null, "Carlos", "Sánchez", "Rodríguez", "9514445555", "carlos.sanchez@ifpa.gob.mx",
                            "password123", 'U', "A", null, null),
                    new Usuario(null, "Ana", "Gómez", "Hernández", "9516667777", "ana.gomez@ifpa.gob.mx", "password123",
                            'U', "A", null, null),
                    new Usuario(null, "Pedro", "Ruiz", "Díaz", "9518889999", "pedro.ruiz@ifpa.gob.mx", "password123", 'U',
                            "A", null, null),
                    new Usuario(null, "Laura", "Jiménez", "Álvarez", "9511112222", "laura.jimenez@ifpa.gob.mx",
                            "password123", 'U', "A", null, null),
                    new Usuario(null, "Jorge", "Castro", "Ortiz", "9513334444", "jorge.count@ifpa.gob.mx", "password123",
                            'U', "A", null, null),
                    new Usuario(null, "Sofía", "Torres", "Vázquez", "9515556666", "sofia.torres@ifpa.gob.mx", "password123",
                            'U', "A", null, null),
                    new Usuario(null, "Luis", "Morales", "Ramos", "9517778888", "luis.morales@ifpa.gob.mx", "password123",
                            'U', "A", null, null),
                    new Usuario(null, "Elena", "Reyes", "Gil", "9519990000", "elena.reyes@ifpa.gob.mx", "password123", 'U',
                            "A", null, null),
                    new Usuario(null, "Ricardo", "Díaz", "Briones", "9510000001", "ricardo.diaz@ifpa.gob.mx", "password123",
                            'U', "A", null, null),
                    new Usuario(null, "Carmen", "Mendoza", "Flores", "9512222222", "carmen.mendoza@ifpa.gob.mx",
                            "password123", 'U', "A", null, null),
                    new Usuario(null, "Roberto", "Sosa", "Nuñez", "9514444444", "roberto.sosa@ifpa.gob.mx", "password123",
                            'U', "A", null, null),
                    new Usuario(null, "Paola", "Chávez", "Luna", "9516666666", "paola.chavez@ifpa.gob.mx", "password123",
                            'U', "A", null, null));

            usuarioRepository.saveAll(usuarios);
            log.info("Successfully seeded {} usuarios.", usuarios.size());
        } else {
            log.info("Usuarios table already has data. Skipping seeding.");
        }
    }
}
