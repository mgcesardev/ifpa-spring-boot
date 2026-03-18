package com.cmg.ifpa.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.cmg.ifpa.service.AccionCapacitacionService;
import com.cmg.ifpa.model.AccionCapacitacion;

@RestController
@RequestMapping("/acciones-capacitaciones")
@CrossOrigin(origins = "*")
public class AccionCapacitacionController {

    private final AccionCapacitacionService accionCapacitacionService;

    public AccionCapacitacionController(AccionCapacitacionService accionCapacitacionService) {
        this.accionCapacitacionService = accionCapacitacionService;
    }

    @GetMapping
    public Page<AccionCapacitacion> findAll(Pageable pageable) {
        return accionCapacitacionService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public AccionCapacitacion findById(@PathVariable Long id) {
        return accionCapacitacionService.findById(id);
    }

    @PostMapping
    public AccionCapacitacion save(@RequestBody AccionCapacitacion model) {
        return accionCapacitacionService.save(model);
    }

    @PutMapping("/{id}")
    public AccionCapacitacion update(@PathVariable Long id, @RequestBody AccionCapacitacion model) {
        return accionCapacitacionService.save(model);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        accionCapacitacionService.delete(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<AccionCapacitacion>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<AccionCapacitacion> resultados = accionCapacitacionService.buscarPorNombre(nombre, pageable);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
