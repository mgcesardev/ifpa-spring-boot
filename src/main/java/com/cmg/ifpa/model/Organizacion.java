package com.cmg.ifpa.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "organizacion")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrganizacion;
    @Column(unique = true)
    private String claveOrganizacion;
    private String nombre;
    private String representate;
    private String nombreOrganizacion;
    private String rfc;
    private String calle;
    private Integer numeroExterior;
    private String numeroInterior;
    private Integer codigoPostal;
    private String telefonoFijo;
    private String telefonoMovil;
    private String telefonoRecados;
    private String correoElectronico;
    private String numeroIntegrantes;
    private String numeroMujeres;
    private String numeroHombres;
    private String descripcion;
    private String tipoOrganizacion;
    private String tipo;
    private String idRegion;
    private String idDistrito;
    private String idMunicipio;
    private String idLocalidad;
    private String idRamaArtesanal;
    private String idTecnica;
    private String estatus;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (this.estatus == null) {
            this.estatus = "A";
        }
    }
}
