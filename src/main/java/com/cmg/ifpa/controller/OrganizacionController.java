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
import com.cmg.ifpa.service.OrganizacionService;
import com.cmg.ifpa.model.Organizacion;
import com.cmg.ifpa.util.PatchHelper;

@RestController
@RequestMapping("/organizaciones")
@CrossOrigin(origins = "*")
public class OrganizacionController {

    private final OrganizacionService organizacionService;

    public OrganizacionController(OrganizacionService organizacionService) {
        this.organizacionService = organizacionService;
    }

    @GetMapping
    public Page<Organizacion> findAll(Pageable pageable) {
        return organizacionService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Organizacion findById(@PathVariable Long id) {
        return organizacionService.findById(id);
    }

    @PostMapping
    public Organizacion save(@RequestBody Organizacion model) {
        return organizacionService.save(model);
    }

    @PatchMapping("/{id}")
    public Organizacion update(@PathVariable Long id, @RequestBody Organizacion model) {
        Organizacion existing = organizacionService.findById(id);
        if (existing != null) {
            PatchHelper.copyNonNullProperties(model, existing);
            return organizacionService.save(existing);
        }
        return null; // Or throw 404
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        organizacionService.delete(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Organizacion>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Organizacion> resultados = organizacionService.buscarPorNombre(nombre, pageable);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
