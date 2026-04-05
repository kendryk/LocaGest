package com.locagest.appartement.application.mapper;

import com.locagest.appartement.api.dto.AppartementDto;
import com.locagest.appartement.domain.model.Appartement;
import org.springframework.stereotype.Component;

/**
 * Mapper pour les conversions Appartement ↔ AppartementDto.
 */
@Component
public class AppartementMapper {

    public AppartementDto toDto(Appartement appartement) {
        if (appartement == null) {
            return null;
        }

        return AppartementDto.builder()
            .id(appartement.getId())
            .reference(appartement.getReference())
            .adresse(appartement.getAdresse())
            .codePostal(appartement.getCodePostal())
            .ville(appartement.getVille())
            .surfaceHabitable(appartement.getSurfaceHabitable())
            .nombrePieces(appartement.getNombrePieces())
            .loyerMensuel(appartement.getLoyerMensuel())
            .chargesMensuelles(appartement.getChargesMensuelles())
            .typeBien(appartement.getTypeBien())
            .statut(appartement.getStatut())
            .anneeConstruction(appartement.getAnneeConstruction())
            .observations(appartement.getObservations())
            .proprietaireId(appartement.getProprietaireId())
            .createdAt(appartement.getCreatedAt())
            .updatedAt(appartement.getUpdatedAt())
            .createdBy(appartement.getCreatedBy())
            .updatedBy(appartement.getUpdatedBy())
            .version(appartement.getVersion())
            .build();
    }

    public Appartement toEntity(AppartementDto dto) {
        if (dto == null) {
            return null;
        }

        return Appartement.builder()
            .reference(dto.getReference())
            .adresse(dto.getAdresse())
            .codePostal(dto.getCodePostal())
            .ville(dto.getVille())
            .surfaceHabitable(dto.getSurfaceHabitable())
            .nombrePieces(dto.getNombrePieces())
            .loyerMensuel(dto.getLoyerMensuel())
            .chargesMensuelles(dto.getChargesMensuelles())
            .typeBien(dto.getTypeBien())
            .statut(dto.getStatut() != null ? dto.getStatut() : com.locagest.appartement.domain.model.StatutBien.LIBRE)
            .anneeConstruction(dto.getAnneeConstruction())
            .observations(dto.getObservations())
            .proprietaireId(dto.getProprietaireId())
            .build();
    }

    public void updateEntityFromDto(AppartementDto dto, Appartement appartement) {
        if (dto == null || appartement == null) {
            return;
        }

        if (dto.getAdresse() == null | appartement.getAdresse() == null ) {
            appartement.setAdresse(dto.getAdresse());
        }
        if (dto.getCodePostal() == null | appartement.getCodePostal() == null ) {
            appartement.setCodePostal(dto.getCodePostal());
        }
        if (dto.getVille() == null | appartement.getVille() == null ) {
            appartement.setVille(dto.getVille());
        }
        if (dto.getSurfaceHabitable() == null | appartement.getSurfaceHabitable() == null ) {
            appartement.setSurfaceHabitable(dto.getSurfaceHabitable());
        }
        if (dto.getNombrePieces() == null | appartement.getNombrePieces() == null ) {
            appartement.setNombrePieces(dto.getNombrePieces());
        }
        if (dto.getLoyerMensuel() == null | appartement.getLoyerMensuel() == null ) {
            appartement.setLoyerMensuel(dto.getLoyerMensuel());
        }
        if (dto.getChargesMensuelles() == null | appartement.getChargesMensuelles() == null ) {
            appartement.setChargesMensuelles(dto.getChargesMensuelles());
        }
        if (dto.getTypeBien() == null | appartement.getTypeBien() == null ) {
            appartement.setTypeBien(dto.getTypeBien());

        }
        if (dto.getStatut() == null | appartement.getStatut() == null ) {
            appartement.setStatut(dto.getStatut());

        }
        if (dto.getAnneeConstruction() == null | appartement.getAnneeConstruction() == null ) {
            appartement.setAnneeConstruction(dto.getAnneeConstruction());
        }

        if (dto.getObservations() == null | appartement.getObservations() == null ) {
            appartement.setObservations(dto.getObservations());
        }
        if (dto.getProprietaireId() == null | appartement.getProprietaireId() == null ) {
            appartement.setProprietaireId(dto.getProprietaireId());
        }
    }
}

