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
import com.cmg.ifpa.service.DistritoService;
import com.cmg.ifpa.model.Distrito;
import com.cmg.ifpa.util.PatchHelper;


@RestController
@RequestMapping("/distritos")
@CrossOrigin(origins = "*")
public class DistritoController {

    private final DistritoService distritoService;

    public DistritoController(DistritoService distritoService) {
        this.distritoService = distritoService;
    }

    @GetMapping
    public Page<Distrito> findAll(Pageable pageable) {
        return distritoService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Distrito findById(@PathVariable Long id) {
        return distritoService.findById(id);
    }

    @PostMapping
    public Distrito save(@RequestBody Distrito model) {
        return distritoService.save(model);
    }

    @PatchMapping("/{id}")
    public Distrito update(@PathVariable Long id, @RequestBody Distrito model) {
        Distrito existing = distritoService.findById(id);
        if (existing != null) {
            PatchHelper.copyNonNullProperties(model, existing);
            return distritoService.save(existing);
        }
        return null; // Or throw 404
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        distritoService.delete(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Distrito>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Distrito> resultados = distritoService.buscarPorNombre(nombre, pageable);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
