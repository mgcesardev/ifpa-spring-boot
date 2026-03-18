package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cmg.ifpa.model.InscripcionCapacitacion;
import com.cmg.ifpa.repository.InscripcionCapacitacionRepository;

@Service
@Transactional
public class InscripcionCapacitacionServiceImpl implements InscripcionCapacitacionService {

    private final InscripcionCapacitacionRepository inscripcionCapacitacionRepository;

    public InscripcionCapacitacionServiceImpl(InscripcionCapacitacionRepository inscripcionCapacitacionRepository) {
        this.inscripcionCapacitacionRepository = inscripcionCapacitacionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InscripcionCapacitacion> findAll(Pageable pageable) {
        try {
            return inscripcionCapacitacionRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener inscripciones de capacitación: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public InscripcionCapacitacion findById(Long id) {
        try {
            return inscripcionCapacitacionRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al encontrar inscripción de capacitación con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    public InscripcionCapacitacion save(InscripcionCapacitacion model) {
        try {
            return inscripcionCapacitacionRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar inscripción de capacitación: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            inscripcionCapacitacionRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al eliminar inscripción de capacitación con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InscripcionCapacitacion> buscarPorNombre(String nombre, Pageable pageable) {
        try {
            return inscripcionCapacitacionRepository.buscarPorNombre(nombre, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar InscripcionCapacitacion por nombre: " + e.getMessage());
        }
    }
}
