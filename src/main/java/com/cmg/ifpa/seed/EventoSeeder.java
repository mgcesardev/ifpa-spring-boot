package com.cmg.ifpa.seed;

import com.cmg.ifpa.model.Evento;
import com.cmg.ifpa.model.RamaArtesanal;
import com.cmg.ifpa.repository.EventoRepository;
import com.cmg.ifpa.repository.RamaArtesanalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@Order(9)
public class EventoSeeder implements CommandLineRunner {

    private final EventoRepository eventoRepository;
    private final RamaArtesanalRepository ramaArtesanalRepository;

    public EventoSeeder(EventoRepository eventoRepository, RamaArtesanalRepository ramaArtesanalRepository) {
        this.eventoRepository = eventoRepository;
        this.ramaArtesanalRepository = ramaArtesanalRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedEventos();
    }

    private void seedEventos() {
        if (eventoRepository.count() == 0) {
            log.info("Seeding initial eventos...");

            List<Evento> eventos = new ArrayList<>();
            Optional<RamaArtesanal> rama1 = ramaArtesanalRepository.findById(1L);
            Optional<RamaArtesanal> rama2 = ramaArtesanalRepository.findById(2L);
            Optional<RamaArtesanal> rama3 = ramaArtesanalRepository.findById(3L);

            for (int i = 1; i <= 15; i++) {
                Evento evento = new Evento();
                evento.setFolio("EV-2024-" + String.format("%03d", i));
                evento.setDescripcionRama("Feria Artesanal " + i);
                evento.setAntiguedad(i + " años");
                evento.setNombreTaller("Taller Artesanal " + i);
                evento.setRegistroMarca(i % 2 == 0 ? "Sí" : "No");
                evento.setTerminalVenta(i % 3 == 0 ? "Sí" : "No");
                evento.setCertificacionOaxaca("Certificado IFPA");
                evento.setAripoCredencial("CRED-" + i);
                evento.setEstatus("A");

                // Assign some ramas
                if (i <= 5 && rama1.isPresent())
                    evento.setRamaArtesanal(rama1.get());
                else if (i <= 10 && rama2.isPresent())
                    evento.setRamaArtesanal(rama2.get());
                else if (rama3.isPresent())
                    evento.setRamaArtesanal(rama3.get());

                eventos.add(evento);
            }

            eventoRepository.saveAll(eventos);
            log.info("Successfully seeded {} eventos.", eventos.size());
        } else {
            log.info("Eventos table already has data. Skipping seeding.");
        }
    }
}
