package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cmg.ifpa.model.Localidad;
import com.cmg.ifpa.repository.LocalidadRepository;

@Service
@Transactional
public class LocalidadServiceImpl implements LocalidadService {

    private final LocalidadRepository localidadRepository;

    public LocalidadServiceImpl(LocalidadRepository localidadRepository) {
        this.localidadRepository = localidadRepository;
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Page<Localidad> findAll(Pageable pageable) {
        try {
            return localidadRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener localidades: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Localidad findById(Long id) {
        try {
            return localidadRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar localidad con id " + id + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public Localidad save(Localidad model) {
        try {
            return localidadRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar localidad: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Long id) {
        try {
            localidadRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar localidad con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Localidad> buscarPorNombre(String nombre, Pageable pageable) {
        try {
            return localidadRepository.buscarPorNombre(nombre, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar Localidad por nombre: " + e.getMessage());
        }
    }
}
