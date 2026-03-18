package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cmg.ifpa.model.TrimestreCapacitacion;
import com.cmg.ifpa.repository.TrimestreCapacitacionRepository;

@Service
@Transactional
public class TrimestreCapacitacionServiceImpl implements TrimestreCapacitacionService {

    private final TrimestreCapacitacionRepository trimestreCapacitacionRepository;

    public TrimestreCapacitacionServiceImpl(TrimestreCapacitacionRepository trimestreCapacitacionRepository) {
        this.trimestreCapacitacionRepository = trimestreCapacitacionRepository;
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Page<TrimestreCapacitacion> findAll(Pageable pageable) {
        try {
            return trimestreCapacitacionRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener trimestres de capacitación: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public TrimestreCapacitacion findById(Long id) {
        try {
            return trimestreCapacitacionRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al encontrar trimestre de capacitación con id " + id + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public TrimestreCapacitacion save(TrimestreCapacitacion model) {
        try {
            return trimestreCapacitacionRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar trimestre de capacitación: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Long id) {
        try {
            trimestreCapacitacionRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al eliminar trimestre de capacitación con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TrimestreCapacitacion> buscarPorNombre(String nombre, Pageable pageable) {
        try {
            return trimestreCapacitacionRepository.buscarPorNombre(nombre, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar TrimestreCapacitacion por nombre: " + e.getMessage());
        }
    }
}
