package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cmg.ifpa.model.GrupoEtnico;
import com.cmg.ifpa.repository.GrupoEtnicoRepository;

@Service
@Transactional
public class GrupoEtnicoServiceImpl implements GrupoEtnicoService {

    private final GrupoEtnicoRepository grupoEtnicoRepository;

    public GrupoEtnicoServiceImpl(GrupoEtnicoRepository grupoEtnicoRepository) {
        this.grupoEtnicoRepository = grupoEtnicoRepository;
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Page<GrupoEtnico> findAll(Pageable pageable) {
        try {
            return grupoEtnicoRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener grupos étnicos: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public GrupoEtnico findById(Long id) {
        try {
            return grupoEtnicoRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar grupo étnico con id " + id + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public GrupoEtnico save(GrupoEtnico model) {
        try {
            return grupoEtnicoRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar grupo étnico: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Long id) {
        try {
            grupoEtnicoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar grupo étnico con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GrupoEtnico> buscarPorNombre(String nombre, Pageable pageable) {
        try {
            return grupoEtnicoRepository.buscarPorNombre(nombre, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar GrupoEtnico por nombre: " + e.getMessage());
        }
    }
}
