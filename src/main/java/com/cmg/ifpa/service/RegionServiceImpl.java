package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cmg.ifpa.model.Region;
import com.cmg.ifpa.repository.RegionRepository;

@Service
@Transactional
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Page<Region> findAll(Pageable pageable) {
        try {
            return regionRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener regiones: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Region findById(Long id) {
        try {
            return regionRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar región con id " + id + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public Region save(Region model) {
        try {
            return regionRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar región: " + e.getMessage());
        }
    }

    @Override
    public List<Region> findByEstatus(String estatus) {
        try {
            return regionRepository.findByEstatusNative(estatus);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar regiones por estatus: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Long id) {
        try {
            regionRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar región con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Region> buscarPorNombre(String nombre, Pageable pageable) {
        try {
            return regionRepository.buscarPorNombre(nombre, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar Region por nombre: " + e.getMessage());
        }
    }
}
