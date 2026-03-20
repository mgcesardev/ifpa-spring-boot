package com.cmg.ifpa.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cmg.ifpa.service.MateriaPrimaService;
import com.cmg.ifpa.model.MateriaPrima;
import com.cmg.ifpa.util.PatchHelper;

@RestController
@RequestMapping("/materias-primas")
@CrossOrigin(origins = "*")
public class MateriaPrimaController {

    private final MateriaPrimaService materiaPrimaService;

    public MateriaPrimaController(MateriaPrimaService materiaPrimaService) {
        this.materiaPrimaService = materiaPrimaService;
    }

    @GetMapping
    public Page<MateriaPrima> findAll(Pageable pageable) {
        return materiaPrimaService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public MateriaPrima findById(@PathVariable Long id) {
        return materiaPrimaService.findById(id);
    }

    @PostMapping
    public MateriaPrima save(@RequestBody MateriaPrima model) {
        return materiaPrimaService.save(model);
    }

    @PatchMapping("/{id}")
    public MateriaPrima update(@PathVariable Long id, @RequestBody MateriaPrima model) {
        MateriaPrima existing = materiaPrimaService.findById(id);
        if (existing != null) {
            PatchHelper.copyNonNullProperties(model, existing);
            return materiaPrimaService.save(existing);
        }
        return null; // Or throw 404
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        materiaPrimaService.delete(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<MateriaPrima>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<MateriaPrima> resultados = materiaPrimaService.buscarPorNombre(nombre, pageable);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
