package com.cmg.ifpa.service;

import com.cmg.ifpa.model.Artesano;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class CredentialService {

    public byte[] generateCredentialImage(Artesano artesano) {
        try {
            // Load base image
            ClassPathResource imageResource = new ClassPathResource("static/credencial.jpg");
            BufferedImage img;
            try (InputStream is = imageResource.getInputStream()) {
                img = ImageIO.read(is);
            }

            if (img == null) {
                throw new RuntimeException("Base image not found");
            }

            Graphics2D g2d = img.createGraphics();
            
            // Enable antialiasing for text
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            Color white = Color.BLACK; // PHP code uses (0,0,0) for $white variable
            Color red = new Color(157, 36, 73);

            // Load Font
            Font baseFont = loadFont(7);
            Font largeFont = loadFont(13);
            Font tinyFont = loadFont(6);

            String id = artesano.getIdArtesano().toString();
            String nombre = artesano.getNombre() != null ? artesano.getNombre() : "";
            String apellidos = (artesano.getPrimerApellido() != null ? artesano.getPrimerApellido() : "") + " " +
                              (artesano.getSegundoApellido() != null ? artesano.getSegundoApellido() : "");
            
            String texto = "PERSONA ARTESANA";
            String persona = (artesano.getSexo() == 'H') ? "PRODUCTOR DE " : "PRODUCTORA DE ";
            String rama = (artesano.getRamaArtesanal() != null) ? artesano.getRamaArtesanal().getNombreRama() : "";
            
            String tipoInscripcion = artesano.getGrupoPertenencia() != null ? artesano.getGrupoPertenencia() : "INDEPENDIENTE";
            String idOrganizacion = (artesano.getOrganizacion() != null && artesano.getOrganizacion().getIdOrganizacion() != null) 
                                    ? artesano.getOrganizacion().getIdOrganizacion().toString() : "";
            String domicilio = (artesano.getCalle() != null ? artesano.getCalle() : "") + " " +
                               (artesano.getNumeroExterior() != null ? artesano.getNumeroExterior() : "");
            String localidad = (artesano.getLocalidad() != null) ? artesano.getLocalidad().getLocalidad() : "";
            String municipio = (artesano.getMunicipio() != null) ? artesano.getMunicipio().getMunicipio() : "";
            String region = (artesano.getRegion() != null) ? artesano.getRegion().getRegion() : "";
            String cp = (artesano.getCodigoPostal() != null) ? artesano.getCodigoPostal().toString() : "";
            String telefono = (artesano.getTelefonoMovil() != null) ? artesano.getTelefonoMovil() : "";
            String ine = (artesano.getClaveINE() != null) ? artesano.getClaveINE() : "";
            String curp = (artesano.getCurp() != null) ? artesano.getCurp() : "";
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fechaRegistro = (artesano.getCreatedAt() != null) ? artesano.getCreatedAt().format(formatter) : "";

            // Common fields
            g2d.setColor(white);
            g2d.setFont(baseFont);
            g2d.drawString(id, 330, 55);

            g2d.setColor(red);
            g2d.setFont(largeFont);
            g2d.drawString(nombre, 85, 214);
            g2d.drawString(apellidos, 50, 231);

            g2d.setColor(white);
            g2d.setFont(tinyFont);
            g2d.drawString(texto, 61, 246);
            g2d.drawString(persona + rama, 61, 256);

            g2d.setFont(baseFont);
            if (!"INDEPENDIENTE".equalsIgnoreCase(tipoInscripcion)) {
                // PHP Condition: if ($tipo_inscripcion != 'INDEPENDIENTE') 
                // Wait, PHP code has two branches that are almost identical but swapped label values and positions.
                // Let's look closely at the PHP logic provided.
                
                // Branch 1: $tipo_inscripcion != 'INDEPENDIENTE'
                g2d.drawString("TIPO DE INSCRIPCIÓN: ", 280, 105);
                g2d.drawString(tipoInscripcion, 380, 105);
                g2d.drawString("ID ORGANIZACIÓN: ", 280, 117);
                g2d.drawString(idOrganizacion, 360, 117);
                g2d.drawString("DOMICILIO: ", 280, 129);
                g2d.drawString(domicilio, 335, 129);
                g2d.drawString("LOCALIDAD: ", 280, 141);
                g2d.drawString(localidad, 335, 141);
                g2d.drawString("MUNICIPIO: ", 280, 153);
                g2d.drawString(municipio, 335, 153);
                g2d.drawString("REGIÓN: ", 280, 165);
                g2d.drawString(region, 320, 165);
                g2d.drawString("CP: ", 280, 177);
                g2d.drawString(cp, 300, 177);
                g2d.drawString("TELÉFONO: ", 280, 189);
                g2d.drawString(telefono, 330, 189);
                g2d.drawString("NÚMERO DE INE: ", 280, 201);
                g2d.drawString(ine, 353, 201);
                g2d.drawString("CURP: ", 280, 213);
                g2d.drawString(curp, 310, 213);
                g2d.drawString("FECHA DE REGISTRO: ", 280, 225);
                g2d.drawString(fechaRegistro, 373, 225);
                g2d.drawString("VIGENTE HASTA: 2028", 280, 237);
            } else {
                // PHP Branch 2: else (INDEPENDIENTE)
                g2d.drawString("TIPO DE INSCRIPCIÓN: ", 280, 105);
                g2d.drawString(tipoInscripcion, 380, 105);
                g2d.drawString("DOMICILIO: ", 280, 117);
                g2d.drawString(domicilio, 335, 117);
                g2d.drawString("LOCALIDAD: ", 280, 129);
                g2d.drawString(localidad, 335, 129);
                g2d.drawString("MUNICIPIO: ", 280, 141);
                g2d.drawString(municipio, 335, 141);
                g2d.drawString("REGIÓN: ", 280, 153);
                g2d.drawString(region, 320, 153);
                g2d.drawString("CP: ", 280, 165);
                g2d.drawString(cp, 300, 165);
                g2d.drawString("TELÉFONO: ", 280, 177);
                g2d.drawString(telefono, 330, 177);
                g2d.drawString("NÚMERO DE INE: ", 280, 189);
                g2d.drawString(ine, 353, 189);
                g2d.drawString("CURP: ", 280, 201);
                g2d.drawString(curp, 310, 201);
                g2d.drawString("FECHA DE REGISTRO: ", 280, 213);
                g2d.drawString(fechaRegistro, 373, 213);
                g2d.drawString("VIGENTE HASTA: 2028", 280, 225);
            }

            g2d.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", baos);
            return baos.toByteArray();

        } catch (Exception e) {
            log.error("Error generating credential image", e);
            throw new RuntimeException("Error al generar la imagen de la credencial", e);
        }
    }

    private Font loadFont(float size) {
        try {
            ClassPathResource fontResource = new ClassPathResource("fonts/Roboto-Black.ttf");
            if (fontResource.exists()) {
                try (InputStream is = fontResource.getInputStream()) {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, is);
                    return font.deriveFont(size);
                }
            }
        } catch (Exception e) {
            log.warn("Could not load Roboto-Black.ttf, falling back to Serif", e);
        }
        return new Font("Serif", Font.BOLD, (int) size);
    }
}
