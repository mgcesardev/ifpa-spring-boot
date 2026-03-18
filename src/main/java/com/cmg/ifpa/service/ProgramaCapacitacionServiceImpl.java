package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cmg.ifpa.model.ProgramaCapacitacion;
import com.cmg.ifpa.repository.ProgramaCapacitacionRepository;

@Service
@Transactional
public class ProgramaCapacitacionServiceImpl implements ProgramaCapacitacionService {

    private final ProgramaCapacitacionRepository programaCapacitacionRepository;

    public ProgramaCapacitacionServiceImpl(ProgramaCapacitacionRepository programaCapacitacionRepository) {
        this.programaCapacitacionRepository = programaCapacitacionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProgramaCapacitacion> findAll(Pageable pageable) {
        try {
            return programaCapacitacionRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener programas de capacitación: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ProgramaCapacitacion findById(Long id) {
        try {
            return programaCapacitacionRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al encontrar programa de capacitación con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    public ProgramaCapacitacion save(ProgramaCapacitacion model) {
        try {
            return programaCapacitacionRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar programa de capacitación: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            programaCapacitacionRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al eliminar programa de capacitación con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProgramaCapacitacion> buscarPorNombre(String nombre, Pageable pageable) {
        try {
            return programaCapacitacionRepository.buscarPorNombre(nombre, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar ProgramaCapacitacion por nombre: " + e.getMessage());
        }
    }
}
