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
import com.cmg.ifpa.service.MunicipioService;
import com.cmg.ifpa.model.Municipio;
import com.cmg.ifpa.util.PatchHelper;

@RestController
@RequestMapping("/municipios")
@CrossOrigin(origins = "*")
public class MunicipioController {

    private final MunicipioService municipioService;

    public MunicipioController(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @GetMapping
    public Page<Municipio> findAll(Pageable pageable) {
        return municipioService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Municipio findById(@PathVariable Long id) {
        return municipioService.findById(id);
    }

    @PostMapping
    public Municipio save(@RequestBody Municipio model) {
        return municipioService.save(model);
    }

    @PatchMapping("/{id}")
    public Municipio update(@PathVariable Long id, @RequestBody Municipio model) {
        Municipio existing = municipioService.findById(id);
        if (existing != null) {
            PatchHelper.copyNonNullProperties(model, existing);
            return municipioService.save(existing);
        }
        return null; // Or throw 404
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        municipioService.delete(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Municipio>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Municipio> resultados = municipioService.buscarPorNombre(nombre, pageable);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
