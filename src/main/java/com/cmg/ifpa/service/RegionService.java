package com.cmg.ifpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import com.cmg.ifpa.model.Region;

public interface RegionService {
    public Page<Region> findAll(Pageable pageable);

    public Region findById(Long id);

    public Region save(Region model);

    public List<Region> findByEstatus(String estatus);

    public void delete(Long id);
    Page<Region> buscarPorNombre(String nombre, Pageable pageable);
}
