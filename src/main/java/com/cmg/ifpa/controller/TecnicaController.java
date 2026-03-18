package com.cmg.ifpa.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.cmg.ifpa.service.TecnicaService;
import com.cmg.ifpa.model.Tecnica;

@RestController
@RequestMapping("/tecnicas")
@CrossOrigin(origins = "*")
public class TecnicaController {

    private final TecnicaService tecnicaService;

    public TecnicaController(TecnicaService tecnicaService) {
        this.tecnicaService = tecnicaService;
    }

    @GetMapping
    public Page<Tecnica> findAll(Pageable pageable) {
        return tecnicaService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Tecnica findById(@PathVariable Long id) {
        return tecnicaService.findById(id);
    }

    @PostMapping
    public Tecnica save(@RequestBody Tecnica model) {
        return tecnicaService.save(model);
    }

    @PutMapping("/{id}")
    public Tecnica update(@PathVariable Long id, @RequestBody Tecnica model) {
        return tecnicaService.save(model);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tecnicaService.delete(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Tecnica>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Tecnica> resultados = tecnicaService.buscarPorNombre(nombre, pageable);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
