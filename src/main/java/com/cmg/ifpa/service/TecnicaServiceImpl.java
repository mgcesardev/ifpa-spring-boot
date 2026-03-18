package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cmg.ifpa.model.Tecnica;
import com.cmg.ifpa.repository.TecnicaRepository;

@Service
@Transactional
public class TecnicaServiceImpl implements TecnicaService {

    private final TecnicaRepository tecnicaRepository;

    public TecnicaServiceImpl(TecnicaRepository tecnicaRepository) {
        this.tecnicaRepository = tecnicaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Tecnica> findAll(Pageable pageable) {
        try {
            return tecnicaRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener técnicas: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Tecnica findById(Long id) {
        try {
            return tecnicaRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar técnica con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    public Tecnica save(Tecnica model) {
        try {
            return tecnicaRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar técnica: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            tecnicaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar técnica con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Tecnica> buscarPorNombre(String nombre, Pageable pageable) {
        try {
            return tecnicaRepository.buscarPorNombre(nombre, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar Tecnica por nombre: " + e.getMessage());
        }
    }
}
