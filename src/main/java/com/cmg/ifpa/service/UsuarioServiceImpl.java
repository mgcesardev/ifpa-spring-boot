package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cmg.ifpa.model.Usuario;
import com.cmg.ifpa.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Page<Usuario> findAll(Pageable pageable) {
        try {
            return usuarioRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener usuarios: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        try {
            return usuarioRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar usuario con id " + id + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public Usuario save(Usuario model) {
        try {
            return usuarioRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar usuario: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar usuario con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Usuario> buscarPorNombre(String nombre, Pageable pageable) {
        try {
            return usuarioRepository.buscarPorNombre(nombre, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar Usuario por nombre: " + e.getMessage());
        }
    }
}
