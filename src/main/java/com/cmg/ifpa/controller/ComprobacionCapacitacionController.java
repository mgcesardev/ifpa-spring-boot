package com.cmg.ifpa.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.cmg.ifpa.service.ComprobacionCapacitacionService;
import com.cmg.ifpa.model.ComprobacionCapacitacion;
import com.cmg.ifpa.util.PatchHelper;

@RestController
@RequestMapping("/comprobaciones-capacitaciones")
@CrossOrigin(origins = "*")
public class ComprobacionCapacitacionController {

    private final ComprobacionCapacitacionService comprobacionCapacitacionService;

    public ComprobacionCapacitacionController(ComprobacionCapacitacionService comprobacionCapacitacionService) {
        this.comprobacionCapacitacionService = comprobacionCapacitacionService;
    }

    @GetMapping
    public Page<ComprobacionCapacitacion> findAll(Pageable pageable) {
        return comprobacionCapacitacionService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ComprobacionCapacitacion findById(@PathVariable Long id) {
        return comprobacionCapacitacionService.findById(id);
    }

    @PostMapping
    public ComprobacionCapacitacion save(@RequestBody ComprobacionCapacitacion model) {
        return comprobacionCapacitacionService.save(model);
    }

    @PatchMapping("/{id}")
    public ComprobacionCapacitacion update(@PathVariable Long id, @RequestBody ComprobacionCapacitacion model) {
        ComprobacionCapacitacion existing = comprobacionCapacitacionService.findById(id);
        if (existing != null) {
            PatchHelper.copyNonNullProperties(model, existing);
            return comprobacionCapacitacionService.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        comprobacionCapacitacionService.delete(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<ComprobacionCapacitacion>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ComprobacionCapacitacion> resultados = comprobacionCapacitacionService.buscarPorNombre(nombre, pageable);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
