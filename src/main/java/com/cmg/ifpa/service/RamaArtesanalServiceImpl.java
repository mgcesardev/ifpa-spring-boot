package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cmg.ifpa.model.RamaArtesanal;
import com.cmg.ifpa.repository.RamaArtesanalRepository;

@Service
@Transactional
public class RamaArtesanalServiceImpl implements RamaArtesanalService {

    private final RamaArtesanalRepository ramaArtesanalRepository;

    public RamaArtesanalServiceImpl(RamaArtesanalRepository ramaArtesanalRepository) {
        this.ramaArtesanalRepository = ramaArtesanalRepository;
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Page<RamaArtesanal> findAll(Pageable pageable) {
        try {
            return ramaArtesanalRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener ramas artesanales: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public RamaArtesanal findById(Long id) {
        try {
            return ramaArtesanalRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar rama artesanal con id " + id + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public RamaArtesanal save(RamaArtesanal model) {
        try {
            return ramaArtesanalRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar rama artesanal: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Long id) {
        try {
            ramaArtesanalRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar rama artesanal con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RamaArtesanal> buscarPorNombre(String nombre, Pageable pageable) {
        try {
            return ramaArtesanalRepository.buscarPorNombre(nombre, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar RamaArtesanal por nombre: " + e.getMessage());
        }
    }
}
