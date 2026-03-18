package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cmg.ifpa.model.AccionCapacitacion;
import com.cmg.ifpa.repository.AccionCapacitacionRepository;

@Service
@Transactional
public class AccionCapacitacionServiceImpl implements AccionCapacitacionService {

    private final AccionCapacitacionRepository accionCapacitacionRepository;

    public AccionCapacitacionServiceImpl(AccionCapacitacionRepository accionCapacitacionRepository) {
        this.accionCapacitacionRepository = accionCapacitacionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AccionCapacitacion> findAll(Pageable pageable) {
        try {
            return accionCapacitacionRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener acciones de capacitación: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public AccionCapacitacion findById(Long id) {
        try {
            return accionCapacitacionRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al encontrar acción de capacitación con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    public AccionCapacitacion save(AccionCapacitacion model) {
        try {
            return accionCapacitacionRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar acción de capacitación: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            accionCapacitacionRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar acción de capacitación con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AccionCapacitacion> buscarPorNombre(String nombre, Pageable pageable) {
        try {
            return accionCapacitacionRepository.buscarPorNombre(nombre, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar AccionCapacitacion por nombre: " + e.getMessage());
        }
    }
}
