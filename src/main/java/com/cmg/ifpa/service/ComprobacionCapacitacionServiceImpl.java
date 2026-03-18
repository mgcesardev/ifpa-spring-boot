package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cmg.ifpa.model.ComprobacionCapacitacion;
import com.cmg.ifpa.repository.ComprobacionCapacitacionRepository;

@Service
@Transactional
public class ComprobacionCapacitacionServiceImpl implements ComprobacionCapacitacionService {

    private final ComprobacionCapacitacionRepository comprobacionCapacitacionRepository;

    public ComprobacionCapacitacionServiceImpl(ComprobacionCapacitacionRepository comprobacionCapacitacionRepository) {
        this.comprobacionCapacitacionRepository = comprobacionCapacitacionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ComprobacionCapacitacion> findAll(Pageable pageable) {
        try {
            return comprobacionCapacitacionRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener comprobaciones de capacitación: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ComprobacionCapacitacion findById(Long id) {
        try {
            return comprobacionCapacitacionRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al encontrar comprobación de capacitación con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    public ComprobacionCapacitacion save(ComprobacionCapacitacion model) {
        try {
            return comprobacionCapacitacionRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar comprobación de capacitación: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            comprobacionCapacitacionRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al eliminar comprobación de capacitación con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ComprobacionCapacitacion> buscarPorNombre(String nombre, Pageable pageable) {
        try {
            return comprobacionCapacitacionRepository.buscarPorNombre(nombre, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar ComprobacionCapacitacion por nombre: " + e.getMessage());
        }
    }
}
