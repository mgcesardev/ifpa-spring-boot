package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cmg.ifpa.model.Evento;
import com.cmg.ifpa.repository.EventoRepository;

@Service
@Transactional
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;

    public EventoServiceImpl(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Page<Evento> findAll(Pageable pageable) {
        try {
            return eventoRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener eventos: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Evento findById(Long id) {
        try {
            return eventoRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar evento con id " + id + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public Evento save(Evento model) {
        try {
            return eventoRepository.save(model);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar evento: " + e.getMessage());
        }
    }

    @SuppressWarnings("null")
    @Override
    public void delete(Long id) {
        try {
            eventoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar evento con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Evento> buscarPorNombre(String nombre, Pageable pageable) {
        try {
            return eventoRepository.buscarPorNombre(nombre, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar Evento por nombre: " + e.getMessage());
        }
    }
}
