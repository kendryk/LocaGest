package com.locagest.appartement.application.mapper;

import com.locagest.appartement.api.dto.LocataireDto;
import com.locagest.appartement.domain.model.Locataire;
import org.springframework.stereotype.Component;

/**
 * Mapper pour les conversions Locataire ↔ LocataireDto.
 */
@Component
public class LocataireMapper {

    public LocataireDto toDto(Locataire locataire) {
        if (locataire == null) {
            return null;
        }

        return LocataireDto.builder()
            .id(locataire.getId())
            .nom(locataire.getNom())
            .prenom(locataire.getPrenom())
            .email(locataire.getEmail())
            .telephone(locataire.getTelephone())
            .dateNaissance(locataire.getDateNaissance())
            .actif(locataire.getActif())
            .createdAt(locataire.getCreatedAt())
            .updatedAt(locataire.getUpdatedAt())
            .createdBy(locataire.getCreatedBy())
            .updatedBy(locataire.getUpdatedBy())
            .version(locataire.getVersion())
            .build();
    }

    public Locataire toEntity(LocataireDto dto) {
        if (dto == null) {
            return null;
        }

        return Locataire.builder()
            .nom(dto.getNom())
            .prenom(dto.getPrenom())
            .email(dto.getEmail())
            .telephone(dto.getTelephone())
            .dateNaissance(dto.getDateNaissance())
            .actif(dto.getActif() != null ? dto.getActif() : true)
            .build();
    }

    public void updateEntityFromDto(LocataireDto dto, Locataire locataire) {
        if (dto == null || locataire == null) {
            return;
        }

        locataire.setNom(dto.getNom());
        locataire.setPrenom(dto.getPrenom());
        locataire.setEmail(dto.getEmail());
        locataire.setTelephone(dto.getTelephone());
        locataire.setDateNaissance(dto.getDateNaissance());
        locataire.setActif(dto.getActif());
    }
}
