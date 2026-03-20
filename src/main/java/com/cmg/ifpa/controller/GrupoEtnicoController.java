package com.cmg.ifpa.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PatchMapping;
import com.cmg.ifpa.service.GrupoEtnicoService;
import com.cmg.ifpa.model.GrupoEtnico;
import com.cmg.ifpa.util.PatchHelper;


@RestController
@RequestMapping("/grupos-etnicos")
@CrossOrigin(origins = "*")
public class GrupoEtnicoController {

    private final GrupoEtnicoService grupoEtnicoService;

    public GrupoEtnicoController(GrupoEtnicoService grupoEtnicoService) {
        this.grupoEtnicoService = grupoEtnicoService;
    }

    @GetMapping
    public Page<GrupoEtnico> findAll(Pageable pageable) {
        return grupoEtnicoService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public GrupoEtnico findById(@PathVariable Long id) {
        return grupoEtnicoService.findById(id);
    }

    @PostMapping
    public GrupoEtnico save(@RequestBody GrupoEtnico model) {
        return grupoEtnicoService.save(model);
    }

    @PatchMapping("/{id}")
    public GrupoEtnico update(@PathVariable Long id, @RequestBody GrupoEtnico model) {
        GrupoEtnico existing = grupoEtnicoService.findById(id);
        if (existing != null) {
            PatchHelper.copyNonNullProperties(model, existing);
            return grupoEtnicoService.save(existing);
        }
        return null;
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        grupoEtnicoService.delete(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<GrupoEtnico>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<GrupoEtnico> resultados = grupoEtnicoService.buscarPorNombre(nombre, pageable);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
