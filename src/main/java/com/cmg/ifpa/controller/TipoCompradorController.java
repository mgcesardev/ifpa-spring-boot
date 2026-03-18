package com.cmg.ifpa.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.cmg.ifpa.service.TipoCompradorService;
import com.cmg.ifpa.model.TipoComprador;

@RestController
@RequestMapping("/tipos-compradores")
@CrossOrigin(origins = "*")
public class TipoCompradorController {

    private final TipoCompradorService tipoCompradorService;

    public TipoCompradorController(TipoCompradorService tipoCompradorService) {
        this.tipoCompradorService = tipoCompradorService;
    }

    @GetMapping
    public Page<TipoComprador> findAll(Pageable pageable) {
        return tipoCompradorService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public TipoComprador findById(@PathVariable Long id) {
        return tipoCompradorService.findById(id);
    }

    @PostMapping
    public TipoComprador save(@RequestBody TipoComprador model) {
        return tipoCompradorService.save(model);
    }

    @PutMapping("/{id}")
    public TipoComprador update(@PathVariable Long id, @RequestBody TipoComprador model) {
        return tipoCompradorService.save(model);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tipoCompradorService.delete(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<TipoComprador>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<TipoComprador> resultados = tipoCompradorService.buscarPorNombre(nombre, pageable);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
