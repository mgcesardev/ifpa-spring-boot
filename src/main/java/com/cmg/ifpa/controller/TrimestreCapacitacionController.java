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
import com.cmg.ifpa.service.TrimestreCapacitacionService;
import com.cmg.ifpa.model.TrimestreCapacitacion;
import com.cmg.ifpa.util.PatchHelper;


@RestController
@RequestMapping("/trimestres-capacitaciones")
@CrossOrigin(origins = "*")
public class TrimestreCapacitacionController {

    private final TrimestreCapacitacionService trimestreCapacitacionService;

    public TrimestreCapacitacionController(TrimestreCapacitacionService trimestreCapacitacionService) {
        this.trimestreCapacitacionService = trimestreCapacitacionService;
    }

    @GetMapping
    public Page<TrimestreCapacitacion> findAll(Pageable pageable) {
        return trimestreCapacitacionService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public TrimestreCapacitacion findById(@PathVariable Long id) {
        return trimestreCapacitacionService.findById(id);
    }

    @PostMapping
    public TrimestreCapacitacion save(@RequestBody TrimestreCapacitacion model) {
        return trimestreCapacitacionService.save(model);
    }

    @PatchMapping("/{id}")
    public TrimestreCapacitacion update(@PathVariable Long id, @RequestBody TrimestreCapacitacion model) {
        TrimestreCapacitacion existing = trimestreCapacitacionService.findById(id);
        if (existing != null) {
            PatchHelper.copyNonNullProperties(model, existing);
            return trimestreCapacitacionService.save(existing);
        }
        return null; // Or throw 404
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        trimestreCapacitacionService.delete(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<TrimestreCapacitacion>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<TrimestreCapacitacion> resultados = trimestreCapacitacionService.buscarPorNombre(nombre, pageable);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
