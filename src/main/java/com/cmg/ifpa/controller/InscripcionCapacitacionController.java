package com.cmg.ifpa.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PatchMapping;
import com.cmg.ifpa.service.InscripcionCapacitacionService;
import com.cmg.ifpa.model.InscripcionCapacitacion;
import com.cmg.ifpa.util.PatchHelper;


@RestController
@RequestMapping("/inscripciones-capacitaciones")
@CrossOrigin(origins = "*")
public class InscripcionCapacitacionController {

    private final InscripcionCapacitacionService inscripcionCapacitacionService;

    public InscripcionCapacitacionController(InscripcionCapacitacionService inscripcionCapacitacionService) {
        this.inscripcionCapacitacionService = inscripcionCapacitacionService;
    }

    @GetMapping
    public Page<InscripcionCapacitacion> findAll(Pageable pageable) {
        return inscripcionCapacitacionService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public InscripcionCapacitacion findById(@PathVariable Long id) {
        return inscripcionCapacitacionService.findById(id);
    }

    @PostMapping
    public InscripcionCapacitacion save(@RequestBody InscripcionCapacitacion model) {
        return inscripcionCapacitacionService.save(model);
    }

    @PatchMapping("/{id}")
    public InscripcionCapacitacion update(@PathVariable Long id, @RequestBody InscripcionCapacitacion model) {
        InscripcionCapacitacion existing = inscripcionCapacitacionService.findById(id);
        if (existing != null) {
            PatchHelper.copyNonNullProperties(model, existing);
            return inscripcionCapacitacionService.save(existing);
        }
        return null;
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        inscripcionCapacitacionService.delete(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<InscripcionCapacitacion>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<InscripcionCapacitacion> resultados = inscripcionCapacitacionService.buscarPorNombre(nombre, pageable);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
