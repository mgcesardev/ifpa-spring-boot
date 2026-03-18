package com.cmg.ifpa.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cmg.ifpa.model.*;
import com.cmg.ifpa.repository.*;

@ExtendWith(MockitoExtension.class)
public class CommonCrudServicesTest {

    @Mock private DistritoRepository distritoRepository;
    @Mock private EventoRepository eventoRepository;
    @Mock private GrupoEtnicoRepository grupoEtnicoRepository;
    @Mock private LenguaIndigenaRepository lenguaIndigenaRepository;
    @Mock private LocalidadRepository localidadRepository;
    @Mock private MateriaPrimaRepository materiaPrimaRepository;
    @Mock private TecnicaRepository tecnicaRepository;
    @Mock private TipoCompradorRepository tipoCompradorRepository;

    private DistritoServiceImpl distritoService;
    private EventoServiceImpl eventoService;
    private GrupoEtnicoServiceImpl grupoEtnicoService;
    private LenguaIndigenaServiceImpl lenguaIndigenaService;
    private LocalidadServiceImpl localidadService;
    private MateriaPrimaServiceImpl materiaPrimaService;
    private TecnicaServiceImpl tecnicaService;
    private TipoCompradorServiceImpl tipoCompradorService;

    @BeforeEach
    void setUp() {
        distritoService = new DistritoServiceImpl(distritoRepository);
        eventoService = new EventoServiceImpl(eventoRepository);
        grupoEtnicoService = new GrupoEtnicoServiceImpl(grupoEtnicoRepository);
        lenguaIndigenaService = new LenguaIndigenaServiceImpl(lenguaIndigenaRepository);
        localidadService = new LocalidadServiceImpl(localidadRepository);
        materiaPrimaService = new MateriaPrimaServiceImpl(materiaPrimaRepository);
        tecnicaService = new TecnicaServiceImpl(tecnicaRepository);
        tipoCompradorService = new TipoCompradorServiceImpl(tipoCompradorRepository);
    }

    @Test
    void testDistritoService() {
        Distrito d = new Distrito();
        d.setIdDistrito(1L);
        Page<Distrito> page = new PageImpl<>(Arrays.asList(d));
        when(distritoRepository.findAll(any(Pageable.class))).thenReturn(page);
        assertNotNull(distritoService.findAll(PageRequest.of(0, 10)));
        
        when(distritoRepository.findById(1L)).thenReturn(Optional.of(d));
        assertEquals(d, distritoService.findById(1L));
        
        when(distritoRepository.save(any(Distrito.class))).thenReturn(d);
        assertEquals(d, distritoService.save(d));
        
        doNothing().when(distritoRepository).deleteById(anyLong());
        distritoService.delete(1L);
    }

    @Test
    void testEventoService() {
        Evento e = new Evento();
        e.setIdEvento(1L);
        Page<Evento> page = new PageImpl<>(Arrays.asList(e));
        when(eventoRepository.findAll(any(Pageable.class))).thenReturn(page);
        assertNotNull(eventoService.findAll(PageRequest.of(0, 10)));
        
        when(eventoRepository.save(any(Evento.class))).thenReturn(e);
        assertEquals(e, eventoService.save(e));
    }

    @Test
    void testGrupoEtnicoService() {
        GrupoEtnico ge = new GrupoEtnico();
        ge.setIdGrupoEtnico(1L);
        Page<GrupoEtnico> page = new PageImpl<>(Arrays.asList(ge));
        when(grupoEtnicoRepository.findAll(any(Pageable.class))).thenReturn(page);
        assertNotNull(grupoEtnicoService.findAll(PageRequest.of(0, 10)));
        
        when(grupoEtnicoRepository.save(any(GrupoEtnico.class))).thenReturn(ge);
        assertEquals(ge, grupoEtnicoService.save(ge));
    }

    @Test
    void testLenguaIndigenaService() {
        LenguaIndigena li = new LenguaIndigena();
        li.setIdLenguaIndigena(1L);
        Page<LenguaIndigena> page = new PageImpl<>(Arrays.asList(li));
        when(lenguaIndigenaRepository.findAll(any(Pageable.class))).thenReturn(page);
        assertNotNull(lenguaIndigenaService.findAll(PageRequest.of(0, 10)));
    }

    @Test
    void testLocalidadService() {
        Localidad l = new Localidad();
        l.setIdLocalidad(1L);
        Page<Localidad> page = new PageImpl<>(Arrays.asList(l));
        when(localidadRepository.findAll(any(Pageable.class))).thenReturn(page);
        assertNotNull(localidadService.findAll(PageRequest.of(0, 10)));
    }

    @Test
    void testMateriaPrimaService() {
        MateriaPrima mp = new MateriaPrima();
        mp.setIdMateriaPrima(1L);
        Page<MateriaPrima> page = new PageImpl<>(Arrays.asList(mp));
        when(materiaPrimaRepository.findAll(any(Pageable.class))).thenReturn(page);
        assertNotNull(materiaPrimaService.findAll(PageRequest.of(0, 10)));
    }

    @Test
    void testTecnicaService() {
        Tecnica t = new Tecnica();
        t.setIdTecnica(1L);
        Page<Tecnica> page = new PageImpl<>(Arrays.asList(t));
        when(tecnicaRepository.findAll(any(Pageable.class))).thenReturn(page);
        assertNotNull(tecnicaService.findAll(PageRequest.of(0, 10)));
    }

    @Test
    void testTipoCompradorService() {
        TipoComprador tc = new TipoComprador();
        tc.setIdTipoComprador(1L);
        Page<TipoComprador> page = new PageImpl<>(Arrays.asList(tc));
        when(tipoCompradorRepository.findAll(any(Pageable.class))).thenReturn(page);
        assertNotNull(tipoCompradorService.findAll(PageRequest.of(0, 10)));
    }
}
