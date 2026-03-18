package com.cmg.ifpa.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cmg.ifpa.model.Usuario;
import com.cmg.ifpa.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNombre("Admin");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Usuario> page = new PageImpl<>(Arrays.asList(usuario));
        when(usuarioRepository.findAll(any(Pageable.class))).thenReturn(page);
        
        Page<Usuario> result = usuarioService.findAll(pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(usuarioRepository).findAll(pageable);
    }

    @Test
    void testFindById() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        
        Usuario result = usuarioService.findById(1L);
        
        assertNotNull(result);
        assertEquals("Admin", result.getNombre());
        verify(usuarioRepository).findById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        
        Usuario result = usuarioService.save(usuario);
        
        assertNotNull(result);
        assertEquals("Admin", result.getNombre());
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testDelete() {
        doNothing().when(usuarioRepository).deleteById(1L);
        usuarioService.delete(1L);
        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    void testFindAllError() {
        when(usuarioRepository.findAll(any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> usuarioService.findAll(PageRequest.of(0, 10)));
    }

    @Test
    void testFindByIdError() {
        when(usuarioRepository.findById(1L)).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> usuarioService.findById(1L));
    }

    @Test
    void testSaveError() {
        when(usuarioRepository.save(any())).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> usuarioService.save(usuario));
    }

    @Test
    void testDeleteError() {
        doThrow(new RuntimeException("Error")).when(usuarioRepository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> usuarioService.delete(1L));
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Usuario> page = new PageImpl<>(Arrays.asList(usuario));
        when(usuarioRepository.buscarPorNombre("TEST", pageable)).thenReturn(page);
        Page<Usuario> result = usuarioService.buscarPorNombre("TEST", pageable);
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }

    @Test
    void testBuscarPorNombreError() {
        when(usuarioRepository.buscarPorNombre(anyString(), any(Pageable.class))).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> usuarioService.buscarPorNombre("TEST", PageRequest.of(0, 10)));
    }
}

