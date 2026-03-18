package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cmg.ifpa.model.Distrito;
import com.cmg.ifpa.repository.DistritoRepository;

@Service
@Transactional
public class DistritoServiceImpl implements DistritoService {

    private final DistritoRepository distritoRepository;

    public DistritoServiceImpl(DistritoRepository distritoRepository) {
        this.distritoRepository = distritoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Distrito> findAll(Pageable pageable) {
        try {
            return distritoRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener distritos: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Distrito findById(Long id) {
        try {
            return distritoRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar distrito con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    public Distrito save(Distrito model) {
        try {
            return distritoRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar distrito: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            distritoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar distrito con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Distrito> buscarPorNombre(String nombre, Pageable pageable) {
        try {
            return distritoRepository.buscarPorNombre(nombre, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar Distrito por nombre: " + e.getMessage());
        }
    }
}
