package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cmg.ifpa.model.TipoComprador;
import com.cmg.ifpa.repository.TipoCompradorRepository;

@Service
@Transactional
public class TipoCompradorServiceImpl implements TipoCompradorService {

    private final TipoCompradorRepository tipoCompradorRepository;

    public TipoCompradorServiceImpl(TipoCompradorRepository tipoCompradorRepository) {
        this.tipoCompradorRepository = tipoCompradorRepository;
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Page<TipoComprador> findAll(Pageable pageable) {
        try {
            return tipoCompradorRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener tipos de comprador: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public TipoComprador findById(Long id) {
        try {
            return tipoCompradorRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar tipo de comprador con id " + id + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public TipoComprador save(TipoComprador model) {
        try {
            return tipoCompradorRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar tipo de comprador: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Long id) {
        try {
            tipoCompradorRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar tipo de comprador con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TipoComprador> buscarPorNombre(String nombre, Pageable pageable) {
        try {
            return tipoCompradorRepository.buscarPorNombre(nombre, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar TipoComprador por nombre: " + e.getMessage());
        }
    }
}
