package com.cmg.ifpa.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.cmg.ifpa.service.RegionService;
import com.cmg.ifpa.model.Region;
import java.util.List;

@RestController
@RequestMapping("/regiones")
@CrossOrigin(origins = "*")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public Page<Region> findAll(Pageable pageable) {
        return regionService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Region findById(@PathVariable Long id) {
        return regionService.findById(id);
    }

    @PostMapping
    public Region save(@RequestBody Region model) {
        return regionService.save(model);
    }

    @GetMapping("/estatus")
    public List<Region> findByEstatus(@RequestParam String estatus) {
        return regionService.findByEstatus(estatus);
    }

    @PutMapping("/{id}")
    public Region update(@PathVariable Long id, @RequestBody Region model) {
        return regionService.save(model);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        regionService.delete(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Region>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Region> resultados = regionService.buscarPorNombre(nombre, pageable);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
