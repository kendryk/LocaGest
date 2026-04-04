package com.locagest.appartement.infrastructure.persistence;

import com.locagest.appartement.domain.model.Locataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository pour l'accès aux données Locataire.
 */
@Repository
public interface LocataireRepository extends JpaRepository<Locataire, Long> {

    Optional<Locataire> findByEmail(String email);

    Optional<Locataire> findByTelephone(String telephone);

    List<Locataire> findByActif(Boolean actif);

    List<Locataire> findByNomAndPrenom(String nom, String prenom);

    long countByActif(Boolean actif);

    @Query("SELECT l FROM Locataire l WHERE l.actif = true AND l.dateNaissance <= :dateLimite")
    List<Locataire> findActifsMajeurs(@Param("dateLimite") LocalDate dateLimite);

    @Query("SELECT l FROM Locataire l WHERE l.actif = true ORDER BY l.nom, l.prenom")
    List<Locataire> findActifsOrderByNomPrenom();

    boolean existsByEmail(String email);

    boolean existsByTelephone(String telephone);
}
