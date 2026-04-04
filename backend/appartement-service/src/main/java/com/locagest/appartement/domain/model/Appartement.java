package com.locagest.appartement.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entité JPA représentant un appartement.
 */
@Entity
@Table(name = "appartements", indexes = {
    @Index(name = "idx_reference", columnList = "reference", unique = true),
    @Index(name = "idx_statut", columnList = "statut"),
    @Index(name = "idx_ville", columnList = "ville"),
    @Index(name = "idx_loyer_mensuel", columnList = "loyer_mensuel"),
    @Index(name = "idx_statut_loyer", columnList = "statut, loyer_mensuel")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appartement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String reference;

    @Column(nullable = false, length = 255)
    private String adresse;

    @Column(nullable = false, length = 10)
    private String codePostal;

    @Column(nullable = false, length = 100)
    private String ville;

    @Column(name = "surface_habitable", nullable = false)
    private BigDecimal surfaceHabitable;

    @Column(name = "nombre_pieces", nullable = false)
    private Integer nombrePieces;

    @Column(nullable = false)
    private BigDecimal loyerMensuel;

    @Column(name = "charges_mensuelles", nullable = false)
    private BigDecimal chargesMensuelles;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private TypeBien typeBien;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private StatutBien statut;

    @Column(name = "annee_construction")
    private Integer anneeConstruction;

    @Column(columnDefinition = "TEXT")
    private String observations;

    @Column(name = "proprietaire_id")
    private Long proprietaireId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;

    @Version
    @Column(name = "version")
    private Long version;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.statut == null) {
            this.statut = StatutBien.LIBRE;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

