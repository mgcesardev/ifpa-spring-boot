package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cmg.ifpa.model.LenguaIndigena;
import com.cmg.ifpa.repository.LenguaIndigenaRepository;

@Service
@Transactional
public class LenguaIndigenaServiceImpl implements LenguaIndigenaService {

    private final LenguaIndigenaRepository lenguaIndigenaRepository;

    public LenguaIndigenaServiceImpl(LenguaIndigenaRepository lenguaIndigenaRepository) {
        this.lenguaIndigenaRepository = lenguaIndigenaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LenguaIndigena> findAll(Pageable pageable) {
        try {
            return lenguaIndigenaRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener lenguas indígenas: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public LenguaIndigena findById(Long id) {
        try {
            return lenguaIndigenaRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar lengua indígena con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    public LenguaIndigena save(LenguaIndigena model) {
        try {
            return lenguaIndigenaRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar lengua indígena: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            lenguaIndigenaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar lengua indígena con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LenguaIndigena> buscarPorNombre(String nombre, Pageable pageable) {
        try {
            return lenguaIndigenaRepository.buscarPorNombre(nombre, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar LenguaIndigena por nombre: " + e.getMessage());
        }
    }
}
