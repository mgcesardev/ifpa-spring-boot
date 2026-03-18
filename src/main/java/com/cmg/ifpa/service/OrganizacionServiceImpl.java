package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cmg.ifpa.model.Organizacion;
import com.cmg.ifpa.repository.OrganizacionRepository;

@Service
@Transactional
public class OrganizacionServiceImpl implements OrganizacionService {

    private final OrganizacionRepository organizacionRepository;

    public OrganizacionServiceImpl(OrganizacionRepository organizacionRepository) {
        this.organizacionRepository = organizacionRepository;
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Page<Organizacion> findAll(Pageable pageable) {
        try {
            return organizacionRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener organizaciones: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Organizacion findById(Long id) {
        try {
            return organizacionRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar organización con id " + id + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public Organizacion save(Organizacion model) {
        try {
            return organizacionRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar organización: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Long id) {
        try {
            organizacionRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar organización con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Organizacion> buscarPorNombre(String nombre, Pageable pageable) {
        try {
            return organizacionRepository.buscarPorNombre(nombre, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar Organizacion por nombre: " + e.getMessage());
        }
    }
}
