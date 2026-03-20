package com.cmg.ifpa.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCrypt;
import com.cmg.ifpa.repository.UsuarioRepository;

@DataJpaTest
public class UsuarioEncryptionTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void testPasswordEncryptionOnPersist() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Test");
        usuario.setCorreoElectronico("test@example.com");
        usuario.setContrasena("plainpassword");
        usuario.setRol('U');

        Usuario savedUsuario = usuarioRepository.save(usuario);
        entityManager.flush();

        assertNotNull(savedUsuario.getContrasena());
        assertTrue(savedUsuario.getContrasena().startsWith("$2a$"), "Password should be encrypted with BCrypt");
        assertTrue(BCrypt.checkpw("plainpassword", savedUsuario.getContrasena()), "Password should match original plain text");
    }

    @Test
    void testPasswordEncryptionOnUpdate() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Test");
        usuario.setCorreoElectronico("test2@example.com");
        usuario.setContrasena("plainpassword");
        usuario.setRol('U');

        Usuario savedUsuario = usuarioRepository.save(usuario);
        entityManager.flush();
        
        String firstHash = savedUsuario.getContrasena();
        
        // Update password
        savedUsuario.setContrasena("newpassword");
        Usuario updatedUsuario = usuarioRepository.save(savedUsuario);
        entityManager.flush();
        
        String secondHash = updatedUsuario.getContrasena();
        
        assertNotEquals(firstHash, secondHash);
        assertTrue(secondHash.startsWith("$2a$"));
        assertTrue(BCrypt.checkpw("newpassword", secondHash));
    }

    @Test
    void testNoDoubleEncryption() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Test");
        usuario.setCorreoElectronico("test3@example.com");
        String existingHash = BCrypt.hashpw("plainpassword", BCrypt.gensalt());
        usuario.setContrasena(existingHash);
        usuario.setRol('U');

        Usuario savedUsuario = usuarioRepository.save(usuario);
        entityManager.flush();

        assertEquals(existingHash, savedUsuario.getContrasena(), "Should not re-encrypt an already encrypted password");
    }
}
