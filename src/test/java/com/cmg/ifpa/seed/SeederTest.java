package com.cmg.ifpa.seed;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cmg.ifpa.repository.*;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SeederTest {

    @Mock private ArtesanoRepository artesanoRepository;
    @Mock private RegionRepository regionRepository;
    @Mock private OrganizacionRepository organizacionRepository;
    @Mock private DistritoRepository distritoRepository;
    @Mock private MunicipioRepository municipioRepository;
    @Mock private LocalidadRepository localidadRepository;
    @Mock private GrupoEtnicoRepository grupoEtnicoRepository;
    @Mock private MateriaPrimaRepository materiaPrimaRepository;
    @Mock private TipoCompradorRepository tipoCompradorRepository;
    @Mock private RamaArtesanalRepository ramaArtesanalRepository;
    @Mock private TecnicaRepository tecnicaRepository;
    @Mock private LenguaIndigenaRepository lenguaIndigenaRepository;
    @Mock private ProgramaCapacitacionRepository programaCapacitacionRepository;
    @Mock private AccionCapacitacionRepository accionCapacitacionRepository;
    @Mock private InscripcionCapacitacionRepository inscripcionCapacitacionRepository;
    @Mock private ComprobacionCapacitacionRepository comprobacionCapacitacionRepository;
    @Mock private EventoRepository eventoRepository;
    @Mock private UsuarioRepository usuarioRepository;
    @Mock private TrimestreCapacitacionRepository trimestreCapacitacionRepository;

    @InjectMocks private ArtesanoSeeder artesanoSeeder;
    @InjectMocks private RegionSeeder regionSeeder;
    @InjectMocks private ProgramaCapacitacionSeeder programaCapacitacionSeeder;
    @InjectMocks private AccionCapacitacionSeeder accionCapacitacionSeeder;
    @InjectMocks private InscripcionCapacitacionSeeder inscripcionCapacitacionSeeder;
    @InjectMocks private ComprobacionCapacitacionSeeder comprobacionCapacitacionSeeder;
    @InjectMocks private EventoSeeder eventoSeeder;
    @InjectMocks private OrganizacionSeeder organizacionSeeder;
    @InjectMocks private UsuarioSeeder usuarioSeeder;
    @InjectMocks private TrimestreCapacitacionSeeder trimestreCapacitacionSeeder;
    @InjectMocks private RamaArtesanalSeeder ramaArtesanalSeeder;
    @InjectMocks private MunicipioSeeder municipioSeeder;
    @InjectMocks private TipoCompradorSeeder tipoCompradorSeeder;
    @InjectMocks private MateriaPrimaSeeder materiaPrimaSeeder;
    @InjectMocks private LocalidadSeeder localidadSeeder;
    @InjectMocks private DistritoSeeder distritoSeeder;
    @InjectMocks private LenguaIndigenaSeeder lenguaIndigenaSeeder;
    @InjectMocks private GrupoEtnicoSeeder grupoEtnicoSeeder;
    @InjectMocks private TecnicaSeeder tecnicaSeeder;

    @Test
    void testArtesanoSeederSkip() throws Exception {
        when(artesanoRepository.count()).thenReturn(100L);
        artesanoSeeder.run();
        verify(artesanoRepository, never()).saveAll(any());
    }

    @Test
    void testArtesanoSeederExecute() throws Exception {
        when(artesanoRepository.count()).thenReturn(0L);
        // Avoid actually running the 15k loop in test if possible, or mock all dependencies
        // But for branch coverage of the 'if', this is enough if we just verify the call.
        // However, seedArtesanos will be called. Let's mock all dependencies for it.
        when(organizacionRepository.findAll()).thenReturn(java.util.List.of());
        when(regionRepository.findAll()).thenReturn(java.util.List.of());
        when(distritoRepository.findAll()).thenReturn(java.util.List.of());
        when(municipioRepository.findAll()).thenReturn(java.util.List.of());
        when(localidadRepository.findAll()).thenReturn(java.util.List.of());
        when(grupoEtnicoRepository.findAll()).thenReturn(java.util.List.of());
        when(materiaPrimaRepository.findAll()).thenReturn(java.util.List.of());
        when(tipoCompradorRepository.findAll()).thenReturn(java.util.List.of());
        when(ramaArtesanalRepository.findAll()).thenReturn(java.util.List.of());
        when(tecnicaRepository.findAll()).thenReturn(java.util.List.of());
        when(lenguaIndigenaRepository.findAll()).thenReturn(java.util.List.of());

        // We can't easily shorten the 15000 count without changing the seeder, 
        // but we can just run it once to get branches.
        // Actually, seedArtesanos(15000) might take too long in a unit test.
        // But since we mock everything, it should be fast enough if it just loops.
        artesanoSeeder.run();
        verify(artesanoRepository, atLeastOnce()).saveAll(anyList());
    }
    
    @Test
    void testRegionSeederSkip() throws Exception {
        when(regionRepository.count()).thenReturn(1L);
        regionSeeder.run();
        verify(regionRepository, never()).saveAll(any());
    }

    @Test
    void testRegionSeederExecute() throws Exception {
        when(regionRepository.count()).thenReturn(0L);
        regionSeeder.run();
        verify(regionRepository, atLeastOnce()).saveAll(anyList());
    }

    @Test
    void testProgramaCapacitacionSeederSkip() throws Exception {
        when(programaCapacitacionRepository.count()).thenReturn(1L);
        programaCapacitacionSeeder.run();
        verify(programaCapacitacionRepository, never()).saveAll(any());
    }

    @Test
    void testProgramaCapacitacionSeederExecute() throws Exception {
        when(programaCapacitacionRepository.count()).thenReturn(0L);
        programaCapacitacionSeeder.run();
        verify(programaCapacitacionRepository, atLeastOnce()).saveAll(anyList());
    }

    @Test
    void testAccionCapacitacionSeederSkip() throws Exception {
        when(accionCapacitacionRepository.count()).thenReturn(1L);
        accionCapacitacionSeeder.run();
        verify(accionCapacitacionRepository, never()).saveAll(any());
    }

    @Test
    void testAccionCapacitacionSeederExecute() throws Exception {
        when(accionCapacitacionRepository.count()).thenReturn(0L);
        when(programaCapacitacionRepository.findAll()).thenReturn(Arrays.asList(new com.cmg.ifpa.model.ProgramaCapacitacion()));
        when(trimestreCapacitacionRepository.findAll()).thenReturn(Arrays.asList(new com.cmg.ifpa.model.TrimestreCapacitacion()));
        accionCapacitacionSeeder.run();
        verify(accionCapacitacionRepository, atLeastOnce()).saveAll(anyList());
    }

    @Test
    void testInscripcionCapacitacionSeederSkip() throws Exception {
        when(inscripcionCapacitacionRepository.count()).thenReturn(1L);
        inscripcionCapacitacionSeeder.run();
        verify(inscripcionCapacitacionRepository, never()).saveAll(any());
    }

    @Test
    void testInscripcionCapacitacionSeederExecute() throws Exception {
        when(inscripcionCapacitacionRepository.count()).thenReturn(0L);
        when(artesanoRepository.findAll()).thenReturn(Arrays.asList(new com.cmg.ifpa.model.Artesano()));
        when(accionCapacitacionRepository.findAll()).thenReturn(Arrays.asList(new com.cmg.ifpa.model.AccionCapacitacion()));
        inscripcionCapacitacionSeeder.run();
        verify(inscripcionCapacitacionRepository, atLeastOnce()).saveAll(anyList());
    }

    @Test
    void testComprobacionCapacitacionSeederSkip() throws Exception {
        when(comprobacionCapacitacionRepository.count()).thenReturn(1L);
        comprobacionCapacitacionSeeder.run();
        verify(comprobacionCapacitacionRepository, never()).saveAll(any());
    }

    @Test
    void testComprobacionCapacitacionSeederExecute() throws Exception {
        when(comprobacionCapacitacionRepository.count()).thenReturn(0L);
        when(artesanoRepository.findAll()).thenReturn(Arrays.asList(new com.cmg.ifpa.model.Artesano()));
        when(accionCapacitacionRepository.findAll()).thenReturn(Arrays.asList(new com.cmg.ifpa.model.AccionCapacitacion()));
        comprobacionCapacitacionSeeder.run();
        verify(comprobacionCapacitacionRepository, atLeastOnce()).saveAll(anyList());
    }

    @Test
    void testEventoSeederSkip() throws Exception {
        when(eventoRepository.count()).thenReturn(1L);
        eventoSeeder.run();
        verify(eventoRepository, never()).saveAll(any());
    }

    @Test
    void testEventoSeederExecute() throws Exception {
        when(eventoRepository.count()).thenReturn(0L);
        eventoSeeder.run();
        verify(eventoRepository, atLeastOnce()).saveAll(anyList());
    }

    @Test
    void testOrganizacionSeederSkip() throws Exception {
        when(organizacionRepository.count()).thenReturn(1L);
        organizacionSeeder.run();
        verify(organizacionRepository, never()).saveAll(any());
    }

    @Test
    void testOrganizacionSeederExecute() throws Exception {
        when(organizacionRepository.count()).thenReturn(0L);
        organizacionSeeder.run();
        verify(organizacionRepository, atLeastOnce()).saveAll(anyList());
    }

    @Test
    void testUsuarioSeederSkip() throws Exception {
        when(usuarioRepository.count()).thenReturn(1L);
        usuarioSeeder.run();
        verify(usuarioRepository, never()).saveAll(any());
    }

    @Test
    void testUsuarioSeederExecute() throws Exception {
        when(usuarioRepository.count()).thenReturn(0L);
        usuarioSeeder.run();
        verify(usuarioRepository, atLeastOnce()).saveAll(anyList());
    }

    @Test
    void testTrimestreCapacitacionSeederSkip() throws Exception {
        when(trimestreCapacitacionRepository.count()).thenReturn(1L);
        trimestreCapacitacionSeeder.run();
        verify(trimestreCapacitacionRepository, never()).saveAll(any());
    }

    @Test
    void testTrimestreCapacitacionSeederExecute() throws Exception {
        when(trimestreCapacitacionRepository.count()).thenReturn(0L);
        trimestreCapacitacionSeeder.run();
        verify(trimestreCapacitacionRepository, atLeastOnce()).saveAll(anyList());
    }

    @Test
    void testRamaArtesanalSeederSkip() throws Exception {
        when(ramaArtesanalRepository.count()).thenReturn(1L);
        ramaArtesanalSeeder.run();
        verify(ramaArtesanalRepository, never()).saveAll(any());
    }

    @Test
    void testRamaArtesanalSeederExecute() throws Exception {
        when(ramaArtesanalRepository.count()).thenReturn(0L);
        ramaArtesanalSeeder.run();
        verify(ramaArtesanalRepository, atLeastOnce()).saveAll(anyList());
    }

    @Test
    void testMunicipioSeederSkip() throws Exception {
        when(municipioRepository.count()).thenReturn(1L);
        municipioSeeder.run();
        verify(municipioRepository, never()).saveAll(any());
    }

    @Test
    void testMunicipioSeederExecute() throws Exception {
        when(municipioRepository.count()).thenReturn(0L);
        municipioSeeder.run();
        verify(municipioRepository, atLeastOnce()).saveAll(anyList());
    }

    @Test
    void testTipoCompradorSeederSkip() throws Exception {
        when(tipoCompradorRepository.count()).thenReturn(1L);
        tipoCompradorSeeder.run();
        verify(tipoCompradorRepository, never()).saveAll(any());
    }

    @Test
    void testTipoCompradorSeederExecute() throws Exception {
        when(tipoCompradorRepository.count()).thenReturn(0L);
        tipoCompradorSeeder.run();
        verify(tipoCompradorRepository, atLeastOnce()).saveAll(anyList());
    }

    @Test
    void testMateriaPrimaSeederSkip() throws Exception {
        when(materiaPrimaRepository.count()).thenReturn(1L);
        materiaPrimaSeeder.run();
        verify(materiaPrimaRepository, never()).saveAll(any());
    }

    @Test
    void testMateriaPrimaSeederExecute() throws Exception {
        when(materiaPrimaRepository.count()).thenReturn(0L);
        materiaPrimaSeeder.run();
        verify(materiaPrimaRepository, atLeastOnce()).saveAll(anyList());
    }

    @Test
    void testLocalidadSeederSkip() throws Exception {
        when(localidadRepository.count()).thenReturn(1L);
        localidadSeeder.run();
        verify(localidadRepository, never()).saveAll(any());
    }

    @Test
    void testLocalidadSeederExecute() throws Exception {
        when(localidadRepository.count()).thenReturn(0L);
        localidadSeeder.run();
        verify(localidadRepository, atLeastOnce()).saveAll(anyList());
    }

    @Test
    void testDistritoSeederSkip() throws Exception {
        when(distritoRepository.count()).thenReturn(1L);
        distritoSeeder.run();
        verify(distritoRepository, never()).saveAll(any());
    }

    @Test
    void testDistritoSeederExecute() throws Exception {
        when(distritoRepository.count()).thenReturn(0L);
        distritoSeeder.run();
        verify(distritoRepository, atLeastOnce()).saveAll(anyList());
    }

    @Test
    void testLenguaIndigenaSeederSkip() throws Exception {
        when(lenguaIndigenaRepository.count()).thenReturn(1L);
        lenguaIndigenaSeeder.run();
        verify(lenguaIndigenaRepository, never()).saveAll(any());
    }

    @Test
    void testLenguaIndigenaSeederExecute() throws Exception {
        when(lenguaIndigenaRepository.count()).thenReturn(0L);
        lenguaIndigenaSeeder.run();
        verify(lenguaIndigenaRepository, atLeastOnce()).saveAll(anyList());
    }

    @Test
    void testGrupoEtnicoSeederSkip() throws Exception {
        when(grupoEtnicoRepository.count()).thenReturn(1L);
        grupoEtnicoSeeder.run();
        verify(grupoEtnicoRepository, never()).saveAll(any());
    }

    @Test
    void testGrupoEtnicoSeederExecute() throws Exception {
        when(grupoEtnicoRepository.count()).thenReturn(0L);
        grupoEtnicoSeeder.run();
        verify(grupoEtnicoRepository, atLeastOnce()).saveAll(anyList());
    }

    @Test
    void testTecnicaSeederSkip() throws Exception {
        when(tecnicaRepository.count()).thenReturn(1L);
        tecnicaSeeder.run();
        verify(tecnicaRepository, never()).saveAll(any());
    }

    @Test
    void testTecnicaSeederExecute() throws Exception {
        when(tecnicaRepository.count()).thenReturn(0L);
        com.cmg.ifpa.model.RamaArtesanal r1 = new com.cmg.ifpa.model.RamaArtesanal(); r1.setIdRamaArtesanal(1L); r1.setNombreRama("Textiles");
        com.cmg.ifpa.model.RamaArtesanal r2 = new com.cmg.ifpa.model.RamaArtesanal(); r2.setIdRamaArtesanal(2L); r2.setNombreRama("Alfarería y Cerámica");
        com.cmg.ifpa.model.RamaArtesanal r3 = new com.cmg.ifpa.model.RamaArtesanal(); r3.setIdRamaArtesanal(3L); r3.setNombreRama("Madera");
        com.cmg.ifpa.model.RamaArtesanal r4 = new com.cmg.ifpa.model.RamaArtesanal(); r4.setIdRamaArtesanal(4L); r4.setNombreRama("Orfebrería y Joyería");
        when(ramaArtesanalRepository.findAll()).thenReturn(Arrays.asList(r1, r2, r3, r4));
        tecnicaSeeder.run();
        verify(tecnicaRepository, atLeastOnce()).saveAll(anyList());
    }
}
