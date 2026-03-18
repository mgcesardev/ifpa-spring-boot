package com.cmg.ifpa.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.cmg.ifpa.service.ProgramaCapacitacionService;
import com.cmg.ifpa.model.ProgramaCapacitacion;

@RestController
@RequestMapping("/programas-capacitaciones")
@CrossOrigin(origins = "*")
public class ProgramaCapacitacionController {

    private final ProgramaCapacitacionService programaCapacitacionService;

    public ProgramaCapacitacionController(ProgramaCapacitacionService programaCapacitacionService) {
        this.programaCapacitacionService = programaCapacitacionService;
    }

    @GetMapping
    public Page<ProgramaCapacitacion> findAll(Pageable pageable) {
        return programaCapacitacionService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ProgramaCapacitacion findById(@PathVariable Long id) {
        return programaCapacitacionService.findById(id);
    }

    @PostMapping
    public ProgramaCapacitacion save(@RequestBody ProgramaCapacitacion model) {
        return programaCapacitacionService.save(model);
    }

    @PutMapping("/{id}")
    public ProgramaCapacitacion update(@PathVariable Long id, @RequestBody ProgramaCapacitacion model) {
        return programaCapacitacionService.save(model);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        programaCapacitacionService.delete(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<ProgramaCapacitacion>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ProgramaCapacitacion> resultados = programaCapacitacionService.buscarPorNombre(nombre, pageable);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
