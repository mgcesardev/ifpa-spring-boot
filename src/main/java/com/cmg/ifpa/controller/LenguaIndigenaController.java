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
import com.cmg.ifpa.service.LenguaIndigenaService;
import com.cmg.ifpa.model.LenguaIndigena;
import com.cmg.ifpa.util.PatchHelper;

@RestController
@RequestMapping("/lenguas-indigenas")
@CrossOrigin(origins = "*")
public class LenguaIndigenaController {

    private final LenguaIndigenaService lenguaIndigenaService;

    public LenguaIndigenaController(LenguaIndigenaService lenguaIndigenaService) {
        this.lenguaIndigenaService = lenguaIndigenaService;
    }

    @GetMapping
    public Page<LenguaIndigena> findAll(Pageable pageable) {
        return lenguaIndigenaService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public LenguaIndigena findById(@PathVariable Long id) {
        return lenguaIndigenaService.findById(id);
    }

    @PostMapping
    public LenguaIndigena save(@RequestBody LenguaIndigena model) {
        return lenguaIndigenaService.save(model);
    }

    @PatchMapping("/{id}")
    public LenguaIndigena update(@PathVariable Long id, @RequestBody LenguaIndigena model) {
        LenguaIndigena existing = lenguaIndigenaService.findById(id);
        if (existing != null) {
            PatchHelper.copyNonNullProperties(model, existing);
            return lenguaIndigenaService.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        lenguaIndigenaService.delete(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<LenguaIndigena>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<LenguaIndigena> resultados = lenguaIndigenaService.buscarPorNombre(nombre, pageable);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
