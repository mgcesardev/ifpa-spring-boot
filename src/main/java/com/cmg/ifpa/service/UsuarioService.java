package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cmg.ifpa.model.Usuario;

public interface UsuarioService {
    public Page<Usuario> findAll(Pageable pageable);

    public Usuario findById(Long id);

    public Usuario save(Usuario model);

    public void delete(Long id);
    Page<Usuario> buscarPorNombre(String nombre, Pageable pageable);
}
