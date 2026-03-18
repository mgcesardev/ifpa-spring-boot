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
@Table(name = "comprobacion_capacitacion")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComprobacionCapacitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComprobacionCapacitacion;
    private double monto;
    private String estatus;

    @ManyToOne
    @JoinColumn(name = "idArtesano")
    private Artesano artesano;  

    @ManyToOne
    @JoinColumn(name = "idAccionCapacitacion")
    private AccionCapacitacion accionCapacitacion;

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
