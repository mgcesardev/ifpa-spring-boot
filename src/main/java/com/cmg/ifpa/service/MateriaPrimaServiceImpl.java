package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cmg.ifpa.model.MateriaPrima;
import com.cmg.ifpa.repository.MateriaPrimaRepository;

@Service
@Transactional
public class MateriaPrimaServiceImpl implements MateriaPrimaService {

    private final MateriaPrimaRepository materiaPrimaRepository;

    public MateriaPrimaServiceImpl(MateriaPrimaRepository materiaPrimaRepository) {
        this.materiaPrimaRepository = materiaPrimaRepository;
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Page<MateriaPrima> findAll(Pageable pageable) {
        try {
            return materiaPrimaRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener materias primas: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public MateriaPrima findById(Long id) {
        try {
            return materiaPrimaRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar materia prima con id " + id + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public MateriaPrima save(MateriaPrima model) {
        try {
            return materiaPrimaRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar materia prima: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Long id) {
        try {
            materiaPrimaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar materia prima con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MateriaPrima> buscarPorNombre(String nombre, Pageable pageable) {
        try {
            return materiaPrimaRepository.buscarPorNombre(nombre, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar MateriaPrima por nombre: " + e.getMessage());
        }
    }
}
