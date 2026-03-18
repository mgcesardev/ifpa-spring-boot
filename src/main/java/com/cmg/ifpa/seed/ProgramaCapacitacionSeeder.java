package com.cmg.ifpa.seed;

import com.cmg.ifpa.model.ProgramaCapacitacion;
import com.cmg.ifpa.repository.ProgramaCapacitacionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
@Order(14)
public class ProgramaCapacitacionSeeder implements CommandLineRunner {

    private final ProgramaCapacitacionRepository programaCapacitacionRepository;

    @Override
    public void run(String... args) throws Exception {
        if (programaCapacitacionRepository.count() == 0) {
            log.info("Seeding 500 ProgramaCapacitacion records...");
            seedProgramas(500);
        } else {
            log.info("ProgramaCapacitacion table already has data. Skipping seeding.");
        }
    }

    private void seedProgramas(int count) {
        List<ProgramaCapacitacion> programas = new ArrayList<>();
        String[] bases = {"Técnica", "Administración", "Diseño", "Comercialización", "Innovación", "Preservación", "Calidad", "Exportación"};
        
        for (int i = 1; i <= count; i++) {
            ProgramaCapacitacion p = new ProgramaCapacitacion();
            p.setNombrePrograma("Programa de " + bases[i % bases.length] + " Artesanal " + i);
            p.setEstatus("A");
            programas.add(p);
            
            if (programas.size() >= 100) {
                programaCapacitacionRepository.saveAll(programas);
                programas.clear();
            }
        }
        
        if (!programas.isEmpty()) {
            programaCapacitacionRepository.saveAll(programas);
        }
        
        log.info("Successfully seeded {} ProgramaCapacitacion records.", count);
    }
}
