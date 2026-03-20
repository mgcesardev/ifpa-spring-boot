package com.cmg.ifpa.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.cmg.ifpa.service.UsuarioService;
import com.cmg.ifpa.model.Usuario;
import com.cmg.ifpa.util.PatchHelper;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Usuario findById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @PostMapping
    public Usuario save(@RequestBody Usuario model) {
        return usuarioService.save(model);
    }

    @PatchMapping("/{id}")
    public Usuario update(@PathVariable Long id, @RequestBody Usuario model) {
        Usuario existing = usuarioService.findById(id);
        if (existing != null) {
            PatchHelper.copyNonNullProperties(model, existing);
            return usuarioService.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usuarioService.delete(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Usuario>> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Usuario> resultados = usuarioService.buscarPorNombre(nombre, pageable);
            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
