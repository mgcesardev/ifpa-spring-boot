package com.cmg.ifpa.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;

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
public class CapacitacionAndUserServicesTest {

    @Mock private AccionCapacitacionRepository accionCapacitacionRepository;
    @Mock private ComprobacionCapacitacionRepository comprobacionCapacitacionRepository;
    @Mock private InscripcionCapacitacionRepository inscripcionCapacitacionRepository;
    @Mock private ProgramaCapacitacionRepository programaCapacitacionRepository;
    @Mock private TrimestreCapacitacionRepository trimestreCapacitacionRepository;
    @Mock private UsuarioRepository usuarioRepository;

    private AccionCapacitacionServiceImpl accionCapacitacionService;
    private ComprobacionCapacitacionServiceImpl comprobacionCapacitacionService;
    private InscripcionCapacitacionServiceImpl inscripcionCapacitacionService;
    private ProgramaCapacitacionServiceImpl programaCapacitacionService;
    private TrimestreCapacitacionServiceImpl trimestreCapacitacionService;
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void setUp() {
        accionCapacitacionService = new AccionCapacitacionServiceImpl(accionCapacitacionRepository);
        comprobacionCapacitacionService = new ComprobacionCapacitacionServiceImpl(comprobacionCapacitacionRepository);
        inscripcionCapacitacionService = new InscripcionCapacitacionServiceImpl(inscripcionCapacitacionRepository);
        programaCapacitacionService = new ProgramaCapacitacionServiceImpl(programaCapacitacionRepository);
        trimestreCapacitacionService = new TrimestreCapacitacionServiceImpl(trimestreCapacitacionRepository);
        usuarioService = new UsuarioServiceImpl(usuarioRepository);
    }

    @Test
    void testAccionCapacitacionService() {
        AccionCapacitacion ac = new AccionCapacitacion();
        ac.setIdAccionCapacitacion(1L);
        Page<AccionCapacitacion> page = new PageImpl<>(Arrays.asList(ac));
        // Use any(Pageable.class) to avoid ambiguity
        when(accionCapacitacionRepository.findAll(any(Pageable.class))).thenReturn(page);
        assertNotNull(accionCapacitacionService.findAll(PageRequest.of(0, 10)));
        
        when(accionCapacitacionRepository.save(any())).thenReturn(ac);
        assertEquals(ac, accionCapacitacionService.save(ac));
    }

    @Test
    void testComprobacionCapacitacionService() {
        ComprobacionCapacitacion cc = new ComprobacionCapacitacion();
        cc.setIdComprobacionCapacitacion(1L);
        Page<ComprobacionCapacitacion> page = new PageImpl<>(Arrays.asList(cc));
        when(comprobacionCapacitacionRepository.findAll(any(Pageable.class))).thenReturn(page);
        assertNotNull(comprobacionCapacitacionService.findAll(PageRequest.of(0, 10)));
    }

    @Test
    void testInscripcionCapacitacionService() {
        InscripcionCapacitacion ic = new InscripcionCapacitacion();
        ic.setIdInscripcionCapacitacion(1L);
        Page<InscripcionCapacitacion> page = new PageImpl<>(Arrays.asList(ic));
        when(inscripcionCapacitacionRepository.findAll(any(Pageable.class))).thenReturn(page);
        assertNotNull(inscripcionCapacitacionService.findAll(PageRequest.of(0, 10)));
    }

    @Test
    void testProgramaCapacitacionService() {
        ProgramaCapacitacion pc = new ProgramaCapacitacion();
        pc.setIdProgramaCapacitacion(1L);
        Page<ProgramaCapacitacion> page = new PageImpl<>(Arrays.asList(pc));
        when(programaCapacitacionRepository.findAll(any(Pageable.class))).thenReturn(page);
        assertNotNull(programaCapacitacionService.findAll(PageRequest.of(0, 10)));
    }

    @Test
    void testTrimestreCapacitacionService() {
        TrimestreCapacitacion tc = new TrimestreCapacitacion();
        tc.setIdTrimestreCapacitacion(1L);
        Page<TrimestreCapacitacion> page = new PageImpl<>(Arrays.asList(tc));
        when(trimestreCapacitacionRepository.findAll(any(Pageable.class))).thenReturn(page);
        assertNotNull(trimestreCapacitacionService.findAll(PageRequest.of(0, 10)));
    }

    @Test
    void testUsuarioService() {
        Usuario u = new Usuario();
        u.setIdUsuario(1L);
        Page<Usuario> page = new PageImpl<>(Arrays.asList(u));
        when(usuarioRepository.findAll(any(Pageable.class))).thenReturn(page);
        assertNotNull(usuarioService.findAll(PageRequest.of(0, 10)));
    }
}
