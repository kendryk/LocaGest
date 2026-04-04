package com.locagest.appartement.infrastructure.persistence;

import com.locagest.appartement.domain.model.Appartement;
import com.locagest.appartement.domain.model.StatutBien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Repository pour l'accès aux données Appartement.
 */
@Repository
public interface AppartementRepository extends JpaRepository<Appartement, Long> {

    Optional<Appartement> findByReference(String reference);

    List<Appartement> findByStatut(StatutBien statut);

    List<Appartement> findByVille(String ville);

    List<Appartement> findByProprietaireId(Long proprietaireId);

    long countByStatut(StatutBien statut);

    @Query("SELECT a FROM Appartement a WHERE a.nombrePieces >= :nombrePieces AND a.statut = 'LOUE'")
    List<Appartement> findLouesAvecMinPieces(@Param("nombrePieces") Integer nombrePieces);

    @Query("SELECT a FROM Appartement a WHERE a.statut = 'LIBRE' AND a.loyerMensuel BETWEEN :loyerMin AND :loyerMax ORDER BY a.loyerMensuel")
    List<Appartement> findLibresByLoyerRange(
        @Param("loyerMin") BigDecimal loyerMin,
        @Param("loyerMax") BigDecimal loyerMax
    );

    boolean existsByReference(String reference);
}

