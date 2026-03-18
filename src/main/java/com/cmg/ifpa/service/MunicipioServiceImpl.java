package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cmg.ifpa.model.Municipio;
import com.cmg.ifpa.repository.MunicipioRepository;

@Service
@Transactional
public class MunicipioServiceImpl implements MunicipioService {

    private final MunicipioRepository municipioRepository;

    public MunicipioServiceImpl(MunicipioRepository municipioRepository) {
        this.municipioRepository = municipioRepository;
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Page<Municipio> findAll(Pageable pageable) {
        try {
            return municipioRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener municipios: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Municipio findById(Long id) {
        try {
            return municipioRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar municipio con id " + id + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public Municipio save(Municipio model) {
        try {
            return municipioRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar municipio: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Long id) {
        try {
            municipioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar municipio con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Municipio> buscarPorNombre(String nombre, Pageable pageable) {
        try {
            return municipioRepository.buscarPorNombre(nombre, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar Municipio por nombre: " + e.getMessage());
        }
    }
}
