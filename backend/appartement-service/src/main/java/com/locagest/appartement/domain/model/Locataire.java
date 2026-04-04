package com.locagest.appartement.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entité JPA représentant un locataire.
 */
@Entity
@Table(name = "locataires", indexes = {
    @Index(name = "idx_email", columnList = "email", unique = true),
    @Index(name = "idx_telephone", columnList = "telephone"),
    @Index(name = "idx_actif", columnList = "actif"),
    @Index(name = "idx_nom_prenom", columnList = "nom, prenom")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Locataire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 100)
    private String prenom;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 20)
    private String telephone;

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @Column(nullable = false)
    private Boolean actif;

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
        if (this.actif == null) {
            this.actif = true;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
