package com.cmg.ifpa.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "artesano")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artesano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArtesano;
    @Column(unique = true)
    private String claveIFPA;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private char sexo;
    private LocalDate fechaNacimiento;
    private char estadoCivil;
    @Column(unique = true)
    private String curp;
    @Column(unique = true)
    private String claveINE;
    @Column(unique = true)
    private String rfc;
    private String calle;
    private Integer numeroExterior;
    private String numeroInterior;
    private Integer codigoPostal;
    private String seccion;
    private String telefonoFijo;
    private String telefonoMovil;
    private String telefonoRecados;
    private String correoElectronico;
    private String redesSociales;
    private String escolaridad;
    private String grupoPertenencia;
    private String fechaEntregaCredencial;
    private String pathFoto;
    private String pathArchivos;
    private String comentarios;
    private char proveedor;
    private String estatus;

    @ManyToOne
    @JoinColumn(name = "idOrganizacion")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Organizacion organizacion;

    @ManyToOne
    @JoinColumn(name = "idRegion")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;

    @ManyToOne
    @JoinColumn(name = "idDistrito")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Distrito distrito;

    @ManyToOne
    @JoinColumn(name = "idMunicipio")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Municipio municipio;

    @ManyToOne
    @JoinColumn(name = "idLocalidad")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Localidad localidad;

    @ManyToOne
    @JoinColumn(name = "idGrupoEtnico")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private GrupoEtnico grupoEtnico;

    @ManyToOne
    @JoinColumn(name = "idMateriaPrima")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MateriaPrima materiaPrima;

    @ManyToOne
    @JoinColumn(name = "idTipoComprador")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TipoComprador tipoComprador;

    @ManyToOne
    @JoinColumn(name = "idRamaArtesanal")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private RamaArtesanal ramaArtesanal;

    @ManyToOne
    @JoinColumn(name = "idTecnica")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tecnica tecnica;

    @ManyToOne
    @JoinColumn(name = "idLenguaIndigena")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private LenguaIndigena lenguaIndigena;


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
