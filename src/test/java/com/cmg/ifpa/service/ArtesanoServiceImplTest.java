package com.cmg.ifpa.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cmg.ifpa.model.Artesano;
import com.cmg.ifpa.repository.ArtesanoRepository;
import com.cmg.ifpa.repository.RegionRepository;
import com.cmg.ifpa.repository.DistritoRepository;
import com.cmg.ifpa.repository.MunicipioRepository;
import com.cmg.ifpa.repository.LocalidadRepository;
import com.cmg.ifpa.repository.RamaArtesanalRepository;
import com.cmg.ifpa.repository.TecnicaRepository;

@ExtendWith(MockitoExtension.class)
public class ArtesanoServiceImplTest {

    @Mock
    private ArtesanoRepository artesanoRepository;
    @Mock
    private RegionRepository regionRepository;
    @Mock
    private DistritoRepository distritoRepository;
    @Mock
    private MunicipioRepository municipioRepository;
    @Mock
    private LocalidadRepository localidadRepository;
    @Mock
    private RamaArtesanalRepository ramaArtesanalRepository;
    @Mock
    private TecnicaRepository tecnicaRepository;

    @InjectMocks
    private ArtesanoServiceImpl artesanoService;

    private Artesano artesano;

    @BeforeEach
    void setUp() {
        artesano = new Artesano();
        artesano.setIdArtesano(1L);
        artesano.setNombre("Juan");
        artesano.setPrimerApellido("Perez");
    }

    @Test
    @SuppressWarnings("null")
    void testFindAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Artesano> page = new PageImpl<>(Arrays.asList(artesano));
        when(artesanoRepository.findAll(pageable)).thenReturn(page);
        
        Page<Artesano> result = artesanoService.findAll(null, pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(artesanoRepository).findAll(pageable);
    }

    @Test
    @SuppressWarnings("null")
    void testFindAllWithEstatus() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Artesano> page = new PageImpl<>(Arrays.asList(artesano));
        when(artesanoRepository.findByEstatus("A", pageable)).thenReturn(page);
        
        Page<Artesano> result = artesanoService.findAll("A", pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(artesanoRepository).findByEstatus("A", pageable);
    }

    @Test
    @SuppressWarnings("null")
    void testFindAllWithEmptyEstatus() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Artesano> page = new PageImpl<>(Arrays.asList(artesano));
        when(artesanoRepository.findAll(pageable)).thenReturn(page);
        
        Page<Artesano> result = artesanoService.findAll("", pageable);
        
        assertNotNull(result);
        verify(artesanoRepository).findAll(pageable);
    }

    @Test
    void testFindById() {
        when(artesanoRepository.findById(1L)).thenReturn(Optional.of(artesano));
        
        Artesano result = artesanoService.findById(1L);
        
        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(artesanoRepository).findById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testSave() {
        when(artesanoRepository.save(any(Artesano.class))).thenReturn(artesano);
        
        Artesano result = artesanoService.save(artesano);
        
        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(artesanoRepository).save(artesano);
    }

    @Test
    void testDelete() {
        doNothing().when(artesanoRepository).deleteById(1L);
        
        artesanoService.delete(1L);
        
        verify(artesanoRepository).deleteById(1L);
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombre() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Artesano> page = new PageImpl<>(Arrays.asList(artesano));
        when(artesanoRepository.buscarPorNombreCompleto("Juan", pageable)).thenReturn(page);
        
        Page<Artesano> result = artesanoService.buscarPorNombre("Juan", null, pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(artesanoRepository).buscarPorNombreCompleto("Juan", pageable);
    }
    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombreWithEstatus() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Artesano> page = new PageImpl<>(Arrays.asList(artesano));
        when(artesanoRepository.buscarPorNombreCompletoAndEstatus("Juan", "A", pageable)).thenReturn(page);
        
        Page<Artesano> result = artesanoService.buscarPorNombre("Juan", "A", pageable);
        
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(artesanoRepository).buscarPorNombreCompletoAndEstatus("Juan", "A", pageable);
    }

    @Test
    @SuppressWarnings("null")
    void testBuscarPorNombreWithEmptyEstatus() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Artesano> page = new PageImpl<>(Arrays.asList(artesano));
        when(artesanoRepository.buscarPorNombreCompleto("Juan", pageable)).thenReturn(page);
        
        Page<Artesano> result = artesanoService.buscarPorNombre("Juan", "", pageable);
        
        assertNotNull(result);
        verify(artesanoRepository).buscarPorNombreCompleto("Juan", pageable);
    }

    @Test
    void testExportToExcel() throws Exception {
        when(artesanoRepository.findAll()).thenReturn(Arrays.asList(artesano));
        when(regionRepository.findAll()).thenReturn(Arrays.asList());
        when(distritoRepository.findAll()).thenReturn(Arrays.asList());
        when(municipioRepository.findAll()).thenReturn(Arrays.asList());
        when(localidadRepository.findAll()).thenReturn(Arrays.asList());
        when(ramaArtesanalRepository.findAll()).thenReturn(Arrays.asList());
        when(tecnicaRepository.findAll()).thenReturn(Arrays.asList());
        
        java.io.ByteArrayInputStream bis = artesanoService.exportToExcel(null);
        assertNotNull(bis);
    }

    @Test
    void testExportToExcelWithEstatus() throws Exception {
        when(artesanoRepository.findByEstatus("A")).thenReturn(Arrays.asList(artesano));
        when(regionRepository.findAll()).thenReturn(Arrays.asList());
        when(distritoRepository.findAll()).thenReturn(Arrays.asList());
        when(municipioRepository.findAll()).thenReturn(Arrays.asList());
        when(localidadRepository.findAll()).thenReturn(Arrays.asList());
        when(ramaArtesanalRepository.findAll()).thenReturn(Arrays.asList());
        when(tecnicaRepository.findAll()).thenReturn(Arrays.asList());
        
        java.io.ByteArrayInputStream bis = artesanoService.exportToExcel("A");
        assertNotNull(bis);
    }

    @Test
    void testExportToExcelComprehensive() throws Exception {
        // Setup a fully populated artesan with Organization
        artesano.setEstatus("A");
        artesano.setNumeroExterior(100);
        artesano.setCodigoPostal(68000);
        artesano.setSexo('H');
        artesano.setProveedor('S');
        
        com.cmg.ifpa.model.Organizacion org = new com.cmg.ifpa.model.Organizacion();
        org.setIdOrganizacion(10L);
        org.setNombreOrganizacion("ORG");
        org.setIdRegion("1");
        org.setIdDistrito("1");
        org.setIdMunicipio("1");
        org.setIdLocalidad("1");
        org.setIdRamaArtesanal("1");
        org.setIdTecnica("1");
        artesano.setOrganizacion(org);
        
        com.cmg.ifpa.model.Region reg = new com.cmg.ifpa.model.Region();
        reg.setIdRegion(1L);
        reg.setRegion("REG");
        when(regionRepository.findAll()).thenReturn(Arrays.asList(reg));
        
        com.cmg.ifpa.model.Distrito dist = new com.cmg.ifpa.model.Distrito();
        dist.setIdDistrito(1L);
        dist.setDistrito("DIST");
        when(distritoRepository.findAll()).thenReturn(Arrays.asList(dist));
        
        com.cmg.ifpa.model.Municipio mun = new com.cmg.ifpa.model.Municipio();
        mun.setIdMunicipio(1L);
        mun.setMunicipio("MUN");
        when(municipioRepository.findAll()).thenReturn(Arrays.asList(mun));
        
        com.cmg.ifpa.model.Localidad loc = new com.cmg.ifpa.model.Localidad();
        loc.setIdLocalidad(1L);
        loc.setLocalidad("LOC");
        when(localidadRepository.findAll()).thenReturn(Arrays.asList(loc));
        
        com.cmg.ifpa.model.RamaArtesanal rama = new com.cmg.ifpa.model.RamaArtesanal();
        rama.setIdRamaArtesanal(1L);
        rama.setNombreRama("RAMA");
        when(ramaArtesanalRepository.findAll()).thenReturn(Arrays.asList(rama));
        
        com.cmg.ifpa.model.Tecnica tec = new com.cmg.ifpa.model.Tecnica();
        tec.setIdTecnica(1L);
        tec.setNombreTecnica("TEC");
        when(tecnicaRepository.findAll()).thenReturn(Arrays.asList(tec));

        when(artesanoRepository.findAll()).thenReturn(Arrays.asList(artesano));
        
        java.io.ByteArrayInputStream bis = artesanoService.exportToExcel(null);
        assertNotNull(bis);
    }

    @Test
    void testExportToExcelEmptyArtesano() throws Exception {
        Artesano emptyArtesano = new Artesano();
        emptyArtesano.setIdArtesano(0L);
        
        when(artesanoRepository.findAll()).thenReturn(Arrays.asList(emptyArtesano));
        when(regionRepository.findAll()).thenReturn(Arrays.asList());
        when(distritoRepository.findAll()).thenReturn(Arrays.asList());
        when(municipioRepository.findAll()).thenReturn(Arrays.asList());
        when(localidadRepository.findAll()).thenReturn(Arrays.asList());
        when(ramaArtesanalRepository.findAll()).thenReturn(Arrays.asList());
        when(tecnicaRepository.findAll()).thenReturn(Arrays.asList());
        
        java.io.ByteArrayInputStream bis = artesanoService.exportToExcel(null);
        assertNotNull(bis);
    }


    @Test
    void testExportToExcelWithOrganizacionNull() throws Exception {
        artesano.setOrganizacion(null);
        when(artesanoRepository.findAll()).thenReturn(Arrays.asList(artesano));
        when(regionRepository.findAll()).thenReturn(Arrays.asList());
        when(distritoRepository.findAll()).thenReturn(Arrays.asList());
        when(municipioRepository.findAll()).thenReturn(Arrays.asList());
        when(localidadRepository.findAll()).thenReturn(Arrays.asList());
        when(ramaArtesanalRepository.findAll()).thenReturn(Arrays.asList());
        when(tecnicaRepository.findAll()).thenReturn(Arrays.asList());
        
        java.io.ByteArrayInputStream bis = artesanoService.exportToExcel(null);
        assertNotNull(bis);
    }

    @Test
    void testExportToExcelWithPartialArtesanoData() throws Exception {
        Artesano partialArtesano = new Artesano();
        partialArtesano.setIdArtesano(2L);
        // Fields left null to trigger ternary operators/null checks
        partialArtesano.setNombre(null);
        partialArtesano.setPrimerApellido(null);
        partialArtesano.setSegundoApellido(null);
        partialArtesano.setEstatus("A");
        
        when(artesanoRepository.findAll()).thenReturn(Arrays.asList(partialArtesano));
        when(regionRepository.findAll()).thenReturn(Arrays.asList());
        when(distritoRepository.findAll()).thenReturn(Arrays.asList());
        when(municipioRepository.findAll()).thenReturn(Arrays.asList());
        when(localidadRepository.findAll()).thenReturn(Arrays.asList());
        when(ramaArtesanalRepository.findAll()).thenReturn(Arrays.asList());
        when(tecnicaRepository.findAll()).thenReturn(Arrays.asList());
        
        java.io.ByteArrayInputStream bis = artesanoService.exportToExcel(null);
        assertNotNull(bis);
    }

    @Test
    void testExportToExcelEmptyList() throws Exception {
        when(artesanoRepository.findAll()).thenReturn(Arrays.asList());
        when(regionRepository.findAll()).thenReturn(Arrays.asList());
        when(distritoRepository.findAll()).thenReturn(Arrays.asList());
        when(municipioRepository.findAll()).thenReturn(Arrays.asList());
        when(localidadRepository.findAll()).thenReturn(Arrays.asList());
        when(ramaArtesanalRepository.findAll()).thenReturn(Arrays.asList());
        when(tecnicaRepository.findAll()).thenReturn(Arrays.asList());
        
        java.io.ByteArrayInputStream bis = artesanoService.exportToExcel(null);
        assertNotNull(bis);
    }

    @Test
    void testExportToExcelExhaustiveBranches() throws Exception {
        // Create an artesano with mixed null/non-null fields to hit more branches
        Artesano mixedA = new Artesano();
        mixedA.setIdArtesano(3L);
        mixedA.setNombre("Test");
        mixedA.setPrimerApellido(null);
        mixedA.setSegundoApellido("LastName2");
        mixedA.setSexo('M');
        mixedA.setEstatus("I"); // Hits the "Inactivo" branch
        mixedA.setOrganizacion(null);
        mixedA.setRegion(null);
        mixedA.setMunicipio(null);
        mixedA.setLocalidad(null);
        mixedA.setRamaArtesanal(null);
        mixedA.setTecnica(null);
        mixedA.setMateriaPrima(null);
        mixedA.setTipoComprador(null);
        mixedA.setGrupoEtnico(null);
        mixedA.setLenguaIndigena(null);
        
        Artesano activeA = new Artesano();
        activeA.setIdArtesano(4L);
        activeA.setEstatus("A"); // Hits the "Activo" branch
        activeA.setNombre("Active");
        activeA.setPrimerApellido("One");
        activeA.setSegundoApellido(null);
        
        when(artesanoRepository.findAll()).thenReturn(Arrays.asList(mixedA, activeA));
        when(regionRepository.findAll()).thenReturn(Arrays.asList());
        when(distritoRepository.findAll()).thenReturn(Arrays.asList());
        when(municipioRepository.findAll()).thenReturn(Arrays.asList());
        when(localidadRepository.findAll()).thenReturn(Arrays.asList());
        when(ramaArtesanalRepository.findAll()).thenReturn(Arrays.asList());
        when(tecnicaRepository.findAll()).thenReturn(Arrays.asList());
        
        java.io.ByteArrayInputStream bis = artesanoService.exportToExcel(null);
        assertNotNull(bis);
    }

    @Test
    void testFindAllError() {
        when(artesanoRepository.findAll(any(Pageable.class))).thenThrow(new RuntimeException("DB Error"));
        assertThrows(RuntimeException.class, () -> artesanoService.findAll(null, PageRequest.of(0, 10)));
    }

    @Test
    void testFindByIdError() {
        when(artesanoRepository.findById(anyLong())).thenThrow(new RuntimeException("DB Error"));
        assertThrows(RuntimeException.class, () -> artesanoService.findById(1L));
    }

    @Test
    void testSaveError() {
        when(artesanoRepository.save(any(Artesano.class))).thenThrow(new RuntimeException("DB Error"));
        assertThrows(RuntimeException.class, () -> artesanoService.save(artesano));
    }

    @Test
    void testDeleteError() {
        doThrow(new RuntimeException("DB Error")).when(artesanoRepository).deleteById(anyLong());
        assertThrows(RuntimeException.class, () -> artesanoService.delete(1L));
    }

    @Test
    void testBuscarPorNombreError() {
        when(artesanoRepository.buscarPorNombreCompleto(anyString(), any())).thenThrow(new RuntimeException("DB Error"));
        assertThrows(RuntimeException.class, () -> artesanoService.buscarPorNombre("Test", null, PageRequest.of(0, 10)));
    }

    @Test
    void testExportToExcelFullCoverage() throws Exception {
        // Trigger the merge function in Collectors.toMap for all maps
        com.cmg.ifpa.model.Region r1 = new com.cmg.ifpa.model.Region(); r1.setIdRegion(1L); r1.setRegion("R1");
        com.cmg.ifpa.model.Region r2 = new com.cmg.ifpa.model.Region(); r2.setIdRegion(1L); r2.setRegion("R2");
        when(regionRepository.findAll()).thenReturn(Arrays.asList(r1, r2));

        com.cmg.ifpa.model.Distrito d1 = new com.cmg.ifpa.model.Distrito(); d1.setIdDistrito(1L); d1.setDistrito("D1");
        com.cmg.ifpa.model.Distrito d2 = new com.cmg.ifpa.model.Distrito(); d2.setIdDistrito(1L); d2.setDistrito("D2");
        when(distritoRepository.findAll()).thenReturn(Arrays.asList(d1, d2));

        com.cmg.ifpa.model.Municipio m1 = new com.cmg.ifpa.model.Municipio(); m1.setIdMunicipio(1L); m1.setMunicipio("M1");
        com.cmg.ifpa.model.Municipio m2 = new com.cmg.ifpa.model.Municipio(); m2.setIdMunicipio(1L); m2.setMunicipio("M2");
        when(municipioRepository.findAll()).thenReturn(Arrays.asList(m1, m2));

        com.cmg.ifpa.model.Localidad l1 = new com.cmg.ifpa.model.Localidad(); l1.setIdLocalidad(1L); l1.setLocalidad("L1");
        com.cmg.ifpa.model.Localidad l2 = new com.cmg.ifpa.model.Localidad(); l2.setIdLocalidad(1L); l2.setLocalidad("L2");
        when(localidadRepository.findAll()).thenReturn(Arrays.asList(l1, l2));

        com.cmg.ifpa.model.RamaArtesanal ra1 = new com.cmg.ifpa.model.RamaArtesanal(); ra1.setIdRamaArtesanal(1L); ra1.setNombreRama("RA1");
        com.cmg.ifpa.model.RamaArtesanal ra2 = new com.cmg.ifpa.model.RamaArtesanal(); ra2.setIdRamaArtesanal(1L); ra2.setNombreRama("RA2");
        when(ramaArtesanalRepository.findAll()).thenReturn(Arrays.asList(ra1, ra2));

        com.cmg.ifpa.model.Tecnica t1 = new com.cmg.ifpa.model.Tecnica(); t1.setIdTecnica(1L); t1.setNombreTecnica("T1");
        com.cmg.ifpa.model.Tecnica t2 = new com.cmg.ifpa.model.Tecnica(); t2.setIdTecnica(1L); t2.setNombreTecnica("T2");
        when(tecnicaRepository.findAll()).thenReturn(Arrays.asList(t1, t2));

        // Two artisans: one with full data, one with mostly nulls
        Artesano a1 = new Artesano();
        a1.setIdArtesano(1L);
        a1.setClaveIFPA("IFPA001");
        a1.setNombre("Juan");
        a1.setPrimerApellido("Perez");
        a1.setSegundoApellido("Garcia");
        a1.setSexo('H');
        a1.setFechaNacimiento(java.time.LocalDate.now());
        a1.setEstadoCivil('S');
        a1.setCurp("CURP001");
        a1.setClaveINE("INE001");
        a1.setRfc("RFC001");
        a1.setCalle("Calle Falsa");
        a1.setNumeroExterior(123);
        a1.setCodigoPostal(12345);
        a1.setTelefonoFijo("5551234");
        a1.setTelefonoMovil("5554321");
        a1.setTelefonoRecados("5550000");
        a1.setCorreoElectronico("test@test.com");
        a1.setRedesSociales("@test");
        a1.setEscolaridad("Licenciatura");
        a1.setEstatus("A");
        a1.setCreatedAt(java.time.LocalDateTime.now());
        a1.setUpdatedAt(java.time.LocalDateTime.now());
        a1.setRegion(new com.cmg.ifpa.model.Region());
        a1.getRegion().setRegion("Region Test");
        a1.setMunicipio(new com.cmg.ifpa.model.Municipio());
        a1.getMunicipio().setMunicipio("Municipio Test");
        a1.setLocalidad(new com.cmg.ifpa.model.Localidad());
        a1.getLocalidad().setLocalidad("Localidad Test");
        a1.setRamaArtesanal(new com.cmg.ifpa.model.RamaArtesanal());
        a1.getRamaArtesanal().setNombreRama("Rama Test");
        a1.setTecnica(new com.cmg.ifpa.model.Tecnica());
        a1.getTecnica().setNombreTecnica("Tecnica Test");
        a1.setFechaEntregaCredencial("2024-01-01");
        a1.setComentarios("Comentario Test");
        a1.setProveedor('S');
        a1.setMateriaPrima(new com.cmg.ifpa.model.MateriaPrima());
        a1.getMateriaPrima().setNombre("Materia Test");
        a1.setTipoComprador(new com.cmg.ifpa.model.TipoComprador());
        a1.getTipoComprador().setNombre("Tipo Test");
        a1.setGrupoEtnico(new com.cmg.ifpa.model.GrupoEtnico());
        a1.getGrupoEtnico().setNombreEtnia("Etnia Test");
        a1.setLenguaIndigena(new com.cmg.ifpa.model.LenguaIndigena());
        a1.getLenguaIndigena().setLenguaIndigena("Lengua Test");
        
        com.cmg.ifpa.model.Organizacion org = new com.cmg.ifpa.model.Organizacion();
        org.setIdOrganizacion(1L);
        org.setNombreOrganizacion("ORG TEST");
        org.setIdRegion("1");
        org.setIdDistrito("1");
        org.setIdMunicipio("1");
        org.setIdLocalidad("1");
        org.setIdRamaArtesanal("1");
        org.setIdTecnica("1");
        a1.setOrganizacion(org);
        
        Artesano a2 = new Artesano();
        a2.setIdArtesano(2L);
        a2.setEstatus("I");
        a2.setOrganizacion(null);
        // a2 fields are mostly null by default
        
        when(artesanoRepository.findAll()).thenReturn(Arrays.asList(a1, a2));
        
        java.io.ByteArrayInputStream bis = artesanoService.exportToExcel(null);
        assertNotNull(bis);
    }
}
