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
import com.cmg.ifpa.service.LocalidadService;
import com.cmg.ifpa.model.Localidad;
import com.cmg.ifpa.util.PatchHelper;


@RestController
@RequestMapping("/localidades")
@CrossOrigin(origins = "*")
public class LocalidadController {

    private final LocalidadService localidadService;

    public LocalidadController(LocalidadService localidadService) {
        this.localidadService = localidadService;
    }

    @GetMapping
    public Page<Localidad> findAll(Pageable pageable) {
        return localidadService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Localidad findById(@PathVariable Long id) {
        return localidadService.findById(id);
    }

    @PostMapping
    public Localidad save(@RequestBody Localidad model) {
        return localidadService.save(model);
    }

    @PatchMapping("/{id}")
    public Localidad update(@PathVariable Long id, @RequestBody Localidad model) {
        Localidad existing = localidadService.findById(id);
        if (existing != null) {
            PatchHelper.copyNonNullProperties(model, existing);
            return localidadService.save(existing);
        }
        return null;
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        localidadService.delete(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Localidad>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Localidad> resultados = localidadService.buscarPorNombre(nombre, pageable);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
