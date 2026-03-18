package com.cmg.ifpa.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmg.ifpa.model.Artesano;
import com.cmg.ifpa.repository.*;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArtesanoServiceImpl implements ArtesanoService {

    private final ArtesanoRepository artesanoRepository;
    private final RegionRepository regionRepository;
    private final DistritoRepository distritoRepository;
    private final MunicipioRepository municipioRepository;
    private final LocalidadRepository localidadRepository;
    private final RamaArtesanalRepository ramaArtesanalRepository;
    private final TecnicaRepository tecnicaRepository;

    public ArtesanoServiceImpl(
            ArtesanoRepository artesanoRepository,
            RegionRepository regionRepository,
            DistritoRepository distritoRepository,
            MunicipioRepository municipioRepository,
            LocalidadRepository localidadRepository,
            RamaArtesanalRepository ramaArtesanalRepository,
            TecnicaRepository tecnicaRepository) {
        this.artesanoRepository = artesanoRepository;
        this.regionRepository = regionRepository;
        this.distritoRepository = distritoRepository;
        this.municipioRepository = municipioRepository;
        this.localidadRepository = localidadRepository;
        this.ramaArtesanalRepository = ramaArtesanalRepository;
        this.tecnicaRepository = tecnicaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Artesano> findAll(String estatus, Pageable pageable) {
        try {
            if (estatus != null && !estatus.isEmpty()) {
                return artesanoRepository.findByEstatus(estatus, pageable);
            }
            return artesanoRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener artesanos: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Artesano> buscarPorNombre(String nombre, String estatus, Pageable pageable) {
        try {
            if (estatus != null && !estatus.isEmpty()) {
                return artesanoRepository.buscarPorNombreCompletoAndEstatus(nombre, estatus, pageable);
            }
            return artesanoRepository.buscarPorNombreCompleto(nombre, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar artesanos por nombre: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Artesano findById(Long id) {
        try {
            return artesanoRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar artesano con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    public Artesano save(Artesano artesano) {
        try {
            return artesanoRepository.save(artesano);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar artesano: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            artesanoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar artesano con id " + id + ": " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ByteArrayInputStream exportToExcel(String estatus) {
        String[] columns = {
            "ID", "Clave IFPA", "Nombre Completo" ,"Nombre", "Primer Apellido", "Segundo Apellido", "Sexo", "Fecha Nacimiento",
            "Estado Civil", "CURP", "Clave INE", "RFC", "Calle", "Num Ext", "C.P.", "Tel Fijo", 
            "Tel Móvil", "Tel Recados", "Email", "Redes Sociales", "Escolaridad", "Estatus",
            "Fecha Creación", "Fecha Actualización",
             "Región", "Municipio", "Localidad", "Rama", "Técnica",
            "Fecha Entrega Credencial", "Comentarios", "Proveedor", "Materia Prima", "Tipo Comprador", "Grupo Étnico", "Lengua Indígena",
            "Organización","Region - ORG", "Distrito - ORG", "Municipio - ORG", "Localidad - ORG", "Rama - ORG", "Técnica - ORG",
        };

        try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            workbook.getProperties().getCoreProperties().setCreator("ING. César Melchor García");
            Sheet sheet = workbook.createSheet("Artesanos");

            // Build lookup maps for Organization foreign keys (stored as String IDs)
            Map<String, String> regionMap = regionRepository.findAll().stream()
                .collect(Collectors.toMap(r -> r.getIdRegion().toString(), r -> r.getRegion(), (v1, v2) -> v1));
            Map<String, String> distritoMap = distritoRepository.findAll().stream()
                .collect(Collectors.toMap(d -> d.getIdDistrito().toString(), d -> d.getDistrito(), (v1, v2) -> v1));
            Map<String, String> municipioMap = municipioRepository.findAll().stream()
                .collect(Collectors.toMap(m -> m.getIdMunicipio().toString(), m -> m.getMunicipio(), (v1, v2) -> v1));
            Map<String, String> localidadMap = localidadRepository.findAll().stream()
                .collect(Collectors.toMap(l -> l.getIdLocalidad().toString(), l -> l.getLocalidad(), (v1, v2) -> v1));
            Map<String, String> ramaMap = ramaArtesanalRepository.findAll().stream()
                .collect(Collectors.toMap(r -> r.getIdRamaArtesanal().toString(), r -> r.getNombreRama(), (v1, v2) -> v1));
            Map<String, String> tecnicaMap = tecnicaRepository.findAll().stream()
                .collect(Collectors.toMap(t -> t.getIdTecnica().toString(), t -> t.getNombreTecnica(), (v1, v2) -> v1));

            // Header Font
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());

            // Header Style
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

            // Create Header Row
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < columns.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(columns[col]);
                cell.setCellStyle(headerCellStyle);
            }

            // Get Data
            List<Artesano> artesanos;
            if (estatus != null && !estatus.isEmpty()) {
                artesanos = artesanoRepository.findByEstatus(estatus);
            } else {
                artesanos = artesanoRepository.findAll();
            }

            // Create Data Rows
            int rowIdx = 1;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for (Artesano a : artesanos) {
                Row row = sheet.createRow(rowIdx++);

                String nombreCompleto = (a.getNombre() != null ? a.getNombre() : "") + " " +
                                       (a.getPrimerApellido() != null ? a.getPrimerApellido() : "") + " " +
                                       (a.getSegundoApellido() != null ? a.getSegundoApellido() : "");

                row.createCell(0).setCellValue(a.getIdArtesano() != null ? a.getIdArtesano() : 0);
                row.createCell(1).setCellValue(a.getClaveIFPA() != null ? a.getClaveIFPA() : "");
                row.createCell(2).setCellValue(nombreCompleto.trim());
                row.createCell(3).setCellValue(a.getNombre() != null ? a.getNombre() : "");
                row.createCell(4).setCellValue(a.getPrimerApellido() != null ? a.getPrimerApellido() : "");
                row.createCell(5).setCellValue(a.getSegundoApellido() != null ? a.getSegundoApellido() : "");
                row.createCell(6).setCellValue(String.valueOf(a.getSexo()));
                row.createCell(7).setCellValue(a.getFechaNacimiento() != null ? a.getFechaNacimiento().format(formatter) : "");
                row.createCell(8).setCellValue(String.valueOf(a.getEstadoCivil()));
                row.createCell(9).setCellValue(a.getCurp() != null ? a.getCurp() : "");
                row.createCell(10).setCellValue(a.getClaveINE() != null ? a.getClaveINE() : "");
                row.createCell(11).setCellValue(a.getRfc() != null ? a.getRfc() : "");
                row.createCell(12).setCellValue(a.getCalle() != null ? a.getCalle() : "");
                row.createCell(13).setCellValue(a.getNumeroExterior() != null ? a.getNumeroExterior() : 0);
                row.createCell(14).setCellValue(a.getCodigoPostal() != null ? a.getCodigoPostal() : 0);
                row.createCell(15).setCellValue(a.getTelefonoFijo() != null ? a.getTelefonoFijo() : "");
                row.createCell(16).setCellValue(a.getTelefonoMovil() != null ? a.getTelefonoMovil() : "");
                row.createCell(17).setCellValue(a.getTelefonoRecados() != null ? a.getTelefonoRecados() : "");
                row.createCell(18).setCellValue(a.getCorreoElectronico() != null ? a.getCorreoElectronico() : "");
                row.createCell(19).setCellValue(a.getRedesSociales() != null ? a.getRedesSociales() : "");
                row.createCell(20).setCellValue(a.getEscolaridad() != null ? a.getEscolaridad() : "");
                
                // Status Mapping
                String estatusLabel = "Inactivo";
                if (a.getEstatus() != null && a.getEstatus().equalsIgnoreCase("A")) {
                    estatusLabel = "Activo";
                }
                row.createCell(21).setCellValue(estatusLabel);

                row.createCell(22).setCellValue(a.getCreatedAt() != null ? a.getCreatedAt().toString() : "");
                row.createCell(23).setCellValue(a.getUpdatedAt() != null ? a.getUpdatedAt().toString() : "");
                
                // Geografía y Especialidad Artesano
                row.createCell(24).setCellValue(a.getRegion() != null ? a.getRegion().getRegion() : "");
                row.createCell(25).setCellValue(a.getMunicipio() != null ? a.getMunicipio().getMunicipio() : "");
                row.createCell(26).setCellValue(a.getLocalidad() != null ? a.getLocalidad().getLocalidad() : "");
                row.createCell(27).setCellValue(a.getRamaArtesanal() != null ? a.getRamaArtesanal().getNombreRama() : "");
                row.createCell(28).setCellValue(a.getTecnica() != null ? a.getTecnica().getNombreTecnica() : "");

                // Otros campos Artesano
                row.createCell(29).setCellValue(a.getFechaEntregaCredencial() != null ? a.getFechaEntregaCredencial() : "");
                row.createCell(30).setCellValue(a.getComentarios() != null ? a.getComentarios() : "");
                row.createCell(31).setCellValue(String.valueOf(a.getProveedor()));
                row.createCell(32).setCellValue(a.getMateriaPrima() != null ? a.getMateriaPrima().getNombre() : "");
                row.createCell(33).setCellValue(a.getTipoComprador() != null ? a.getTipoComprador().getNombre() : "");
                row.createCell(34).setCellValue(a.getGrupoEtnico() != null ? a.getGrupoEtnico().getNombreEtnia() : "");
                row.createCell(35).setCellValue(a.getLenguaIndigena() != null ? a.getLenguaIndigena().getLenguaIndigena() : "");
                
                // Organización
                row.createCell(36).setCellValue(a.getOrganizacion() != null ? a.getOrganizacion().getNombreOrganizacion() : "");
                if (a.getOrganizacion() != null) {
                    row.createCell(37).setCellValue(regionMap.getOrDefault(a.getOrganizacion().getIdRegion(), ""));
                    row.createCell(38).setCellValue(distritoMap.getOrDefault(a.getOrganizacion().getIdDistrito(), ""));
                    row.createCell(39).setCellValue(municipioMap.getOrDefault(a.getOrganizacion().getIdMunicipio(), ""));
                    row.createCell(40).setCellValue(localidadMap.getOrDefault(a.getOrganizacion().getIdLocalidad(), ""));
                    row.createCell(41).setCellValue(ramaMap.getOrDefault(a.getOrganizacion().getIdRamaArtesanal(), ""));
                    row.createCell(42).setCellValue(tecnicaMap.getOrDefault(a.getOrganizacion().getIdTecnica(), ""));
                } else {
                    for (int i = 37; i <= 42; i++) row.createCell(i).setCellValue("");
                }
            }

            // Auto-size columns
            for (int col = 0; col < columns.length; col++) {
                sheet.autoSizeColumn(col);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Error al generar el archivo Excel: " + e.getMessage());
        }
    }

}
