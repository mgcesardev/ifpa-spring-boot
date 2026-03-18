package com.cmg.ifpa.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.cmg.ifpa.service.EventoService;
import com.cmg.ifpa.model.Evento;

@RestController
@RequestMapping("/eventos")
@CrossOrigin(origins = "*")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public Page<Evento> findAll(Pageable pageable) {
        return eventoService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Evento findById(@PathVariable Long id) {
        return eventoService.findById(id);
    }

    @PostMapping
    public Evento save(@RequestBody Evento model) {
        return eventoService.save(model);
    }

    @PutMapping("/{id}")
    public Evento update(@PathVariable Long id, @RequestBody Evento model) {
        return eventoService.save(model);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eventoService.delete(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Evento>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Evento> resultados = eventoService.buscarPorNombre(nombre, pageable);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
