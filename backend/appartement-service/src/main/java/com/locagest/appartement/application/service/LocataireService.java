package com.locagest.appartement.application.service;

import com.locagest.appartement.api.dto.LocataireDto;
import com.locagest.appartement.application.mapper.LocataireMapper;
import com.locagest.appartement.domain.exception.ResourceNotFoundException;
import com.locagest.appartement.domain.model.Locataire;
import com.locagest.appartement.infrastructure.persistence.LocataireRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service métier pour la gestion des locataires.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LocataireService {

    private final LocataireRepository repository;
    private final LocataireMapper mapper;

    @Transactional
    public LocataireDto create(LocataireDto dto) {
        log.info("Création d'un nouveau locataire: {} {}", dto.getPrenom(), dto.getNom());

        if (repository.existsByEmail(dto.getEmail())) {
            log.warn("Tentative de création avec un email déjà existant: {}", dto.getEmail());
            throw new IllegalArgumentException(
                "Un locataire avec l'email '" + dto.getEmail() + "' existe déjà"
            );
        }

        if (repository.existsByTelephone(dto.getTelephone())) {
            log.warn("Tentative de création avec un téléphone déjà existant: {}", dto.getTelephone());
            throw new IllegalArgumentException(
                "Un locataire avec le téléphone '" + dto.getTelephone() + "' existe déjà"
            );
        }

        // Validation de l'âge minimum (18 ans)
        LocalDate dateMajorite = LocalDate.now().minusYears(18);
        if (dto.getDateNaissance().isAfter(dateMajorite)) {
            throw new IllegalArgumentException("Le locataire doit être majeur (18 ans minimum)");
        }

        Locataire locataire = this.toNewEntity(dto); //  mapper.toEntity(dto);
        Locataire saved = repository.save(locataire);

        log.info("Locataire créé avec succès: ID={}, nom={} {}", saved.getId(), saved.getPrenom(), saved.getNom());
        return mapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public LocataireDto getById(Long id) {
        log.debug("Récupération du locataire: ID={}", id);

        Locataire locataire = repository.findById(id)
            .orElseThrow(() -> {
                log.warn("Locataire non trouvé: ID={}", id);
                return new ResourceNotFoundException("Locataire non trouvé: ID=" + id);
            });

        return mapper.toDto(locataire);
    }

    @Transactional(readOnly = true)
    public LocataireDto getByEmail(String email) {
        log.debug("Recherche du locataire par email: {}", email);

        Locataire locataire = repository.findByEmail(email)
            .orElseThrow(() -> {
                log.warn("Locataire non trouvé: email={}", email);
                return new ResourceNotFoundException("Locataire non trouvé: email=" + email);
            });

        return mapper.toDto(locataire);
    }

    @Transactional(readOnly = true)
    public Page<LocataireDto> listAll(Pageable pageable) {
        log.debug("Listing des locataires avec pagination");
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<LocataireDto> listByActif(Boolean actif) {
        log.debug("Listing des locataires par statut actif: {}", actif);
        return repository.findByActif(actif).stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LocataireDto> listActifsOrderByNomPrenom() {
        log.debug("Listing des locataires actifs triés par nom/prénom");
        return repository.findActifsOrderByNomPrenom().stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    @Transactional
    public LocataireDto update(Long id, LocataireDto dto) {
        log.info("Mise à jour du locataire: ID={}", id);

        Locataire locataire = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Locataire non trouvé: ID=" + id));

        // Vérifier que l'email n'est pas déjà utilisé par un autre locataire
        if (!locataire.getEmail().equals(dto.getEmail()) && repository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException(
                "Un autre locataire utilise déjà l'email '" + dto.getEmail() + "'"
            );
        }

        // Vérifier que le téléphone n'est pas déjà utilisé par un autre locataire
        if (!locataire.getTelephone().equals(dto.getTelephone()) && repository.existsByTelephone(dto.getTelephone())) {
            throw new IllegalArgumentException(
                "Un autre locataire utilise déjà le téléphone '" + dto.getTelephone() + "'"
            );
        }

        // Validation de l'âge minimum (18 ans)
        LocalDate dateMajorite = LocalDate.now().minusYears(18);
        if (dto.getDateNaissance().isAfter(dateMajorite)) {
            throw new IllegalArgumentException("Le locataire doit être majeur (18 ans minimum)");
        }

        mapper.updateEntityFromDto(dto, locataire);
        Locataire updated = repository.save(locataire);

        log.info("Locataire mis à jour avec succès: ID={}", id);
        return mapper.toDto(updated);
    }

    @Transactional
    public LocataireDto changeActifStatus(Long id, Boolean actif) {
        log.info("Changement de statut actif pour le locataire: ID={}, nouveau statut={}", id, actif);

        Locataire locataire = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Locataire non trouvé: ID=" + id));

        Boolean ancienStatut = locataire.getActif();
        locataire.setActif(actif);
        Locataire updated = repository.save(locataire);

        log.info("Statut actif changé avec succès: ID={}, {} → {}", id, ancienStatut, actif);
        return mapper.toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Suppression du locataire: ID={}", id);

        Locataire locataire = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Locataire non trouvé: ID=" + id));

        repository.delete(locataire);
        log.info("Locataire supprimé avec succès: ID={}", id);
    }

    @Transactional(readOnly = true)
    public long countByActif(Boolean actif) {
        log.debug("Comptage des locataires avec statut actif: {}", actif);
        return repository.countByActif(actif);
    }

    public Locataire toNewEntity(LocataireDto dto) {
        Locataire locataire = mapper.toEntity(dto);
        if (locataire.getActif() == null) {
            locataire.setActif(true);
        }
        return locataire;
    }
}
