package com.cmg.ifpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.cmg.ifpa.model.Usuario;

@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @SuppressWarnings("null")
    void testSaveAndFindById() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Admin");
        usuario.setCorreoElectronico("admin@test.com");
        usuario.setEstatus("A");
        
        Usuario saved = usuarioRepository.save(usuario);
        
        assertNotNull(saved.getIdUsuario());
        
        Usuario found = usuarioRepository.findById(saved.getIdUsuario()).orElse(null);
        assertNotNull(found);
        assertEquals("Admin", found.getNombre());
        assertEquals("admin@test.com", found.getCorreoElectronico());
    }
}
