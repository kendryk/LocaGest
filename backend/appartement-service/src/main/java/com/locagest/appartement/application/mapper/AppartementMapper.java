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

        appartement.setAdresse(dto.getAdresse());
        appartement.setCodePostal(dto.getCodePostal());
        appartement.setVille(dto.getVille());
        appartement.setSurfaceHabitable(dto.getSurfaceHabitable());
        appartement.setNombrePieces(dto.getNombrePieces());
        appartement.setLoyerMensuel(dto.getLoyerMensuel());
        appartement.setChargesMensuelles(dto.getChargesMensuelles());
        appartement.setTypeBien(dto.getTypeBien());
        appartement.setStatut(dto.getStatut());
        appartement.setAnneeConstruction(dto.getAnneeConstruction());
        appartement.setObservations(dto.getObservations());
        appartement.setProprietaireId(dto.getProprietaireId());
    }
}

