package com.cmg.ifpa.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EntityListeners;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "evento")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;
    private String folio;
    private String descripcionRama;
    private String antiguedad;
    private String nombreTaller;
    private String registroMarca;
    private String terminalVenta;
    private String certificacionOaxaca;
    private String aripoCredencial;
    private String pathCredencial;
    private String pathCarta;
    private String pathFotos;
    private String pathTalleres;
    private String estatus;
    
    @ManyToOne
    @JoinColumn(name = "idRamaArtesanal")
    private RamaArtesanal ramaArtesanal;

    @ManyToOne
    @JoinColumn(name = "idArtesano")
    private Artesano artesano;

    @ManyToOne
    @JoinColumn(name = "idOrganizacion")
    private Organizacion organizacion;

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
