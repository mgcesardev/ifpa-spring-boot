package com.cmg.ifpa.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cmg.ifpa.model.Artesano;
import com.cmg.ifpa.service.ArtesanoService;
import com.cmg.ifpa.service.CredentialService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/artesanos")
@CrossOrigin(origins = "*")
public class ArtesanoController {

    private final ArtesanoService artesanoService;
    private final CredentialService credentialService;

    public ArtesanoController(ArtesanoService artesanoService, CredentialService credentialService) {
        this.artesanoService = artesanoService;
        this.credentialService = credentialService;
    }

    @GetMapping
    public Page<Artesano> findAll(
            @RequestParam(required = false) String estatus,
            Pageable pageable) {
        return artesanoService.findAll(estatus, pageable);
    }

    @GetMapping("/buscar")
    public Page<Artesano> buscarPorNombre(
            @RequestParam String nombre,
            @RequestParam(required = false) String estatus,
            Pageable pageable) {
        return artesanoService.buscarPorNombre(nombre, estatus, pageable);
    }

    @GetMapping("/{id}")
    public Artesano findById(@PathVariable Long id) {
        return artesanoService.findById(id);
    }

    @GetMapping("/export-excel")
    public ResponseEntity<Resource> exportExcel(@RequestParam(required = false) String estatus) {
        String filename = "artesanos.xlsx";
        ByteArrayInputStream in = artesanoService.exportToExcel(estatus);
        @SuppressWarnings("null")
        InputStreamResource file = new InputStreamResource(in);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(file);
    }

    @PostMapping
    public Artesano save(@RequestBody Artesano artesano) {
        return artesanoService.save(artesano);
    }

    @PutMapping("/{id}")
    public Artesano update(@PathVariable Long id, @RequestBody Artesano artesano) {
        return artesanoService.save(artesano);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        artesanoService.delete(id);
    }

    @SuppressWarnings("null")
    @GetMapping("/credencial/{id}")
    public ResponseEntity<byte[]> getCredencial(@PathVariable Long id) {
        Artesano artesano = artesanoService.findById(id);
        if (artesano == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] image = credentialService.generateCredentialImage(artesano);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + id + ".jpg")
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }
}
