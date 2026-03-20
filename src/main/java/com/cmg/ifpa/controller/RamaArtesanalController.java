package com.cmg.ifpa.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.*;
import com.cmg.ifpa.service.RamaArtesanalService;
import com.cmg.ifpa.model.RamaArtesanal;
import com.cmg.ifpa.util.PatchHelper;


@RestController
@RequestMapping("/ramas-artesanales")
@CrossOrigin(origins = "*")
public class RamaArtesanalController {

    private final RamaArtesanalService ramaArtesanalService;

    public RamaArtesanalController(RamaArtesanalService ramaArtesanalService) {
        this.ramaArtesanalService = ramaArtesanalService;
    }

    @GetMapping
    public Page<RamaArtesanal> findAll(Pageable pageable) {
        return ramaArtesanalService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public RamaArtesanal findById(@PathVariable Long id) {
        return ramaArtesanalService.findById(id);
    }

    @PostMapping
    public RamaArtesanal save(@RequestBody RamaArtesanal model) {
        return ramaArtesanalService.save(model);
    }

    @PatchMapping("/{id}")
    public RamaArtesanal update(@PathVariable Long id, @RequestBody RamaArtesanal model) {
        RamaArtesanal existing = ramaArtesanalService.findById(id);
        if (existing != null) {
            PatchHelper.copyNonNullProperties(model, existing);
            return ramaArtesanalService.save(existing);
        }
        return null; // Or throw 404
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ramaArtesanalService.delete(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<RamaArtesanal>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<RamaArtesanal> resultados = ramaArtesanalService.buscarPorNombre(nombre, pageable);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
