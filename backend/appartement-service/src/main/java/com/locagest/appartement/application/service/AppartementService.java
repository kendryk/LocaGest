package com.locagest.appartement.application.service;

import com.locagest.appartement.api.dto.AppartementDto;
import com.locagest.appartement.application.mapper.AppartementMapper;
import com.locagest.appartement.domain.exception.ResourceNotFoundException;
import com.locagest.appartement.domain.model.Appartement;
import com.locagest.appartement.domain.model.StatutBien;
import com.locagest.appartement.infrastructure.persistence.AppartementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service métier pour la gestion des appartements.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AppartementService {

    private final AppartementRepository repository;
    private final AppartementMapper mapper;

    @Transactional
    public AppartementDto create(AppartementDto dto) {
        log.info("Création d'un nouvel appartement avec référence: {}", dto.getReference());

        if (repository.existsByReference(dto.getReference())) {
            log.warn("Tentative de création avec une référence déjà existante: {}", dto.getReference());
            throw new IllegalArgumentException(
                "Un appartement avec la référence '" + dto.getReference() + "' existe déjà"
            );
        }

        if (dto.getLoyerMensuel().signum() <= 0) {
            throw new IllegalArgumentException("Le loyer mensuel doit être strictement positif");
        }

        Appartement appartement = mapper.toEntity(dto);
        Appartement saved = repository.save(appartement);

        log.info("Appartement créé avec succès: ID={}, référence={}", saved.getId(), saved.getReference());
        return mapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public AppartementDto getById(Long id) {
        log.debug("Récupération de l'appartement: ID={}", id);

        Appartement appartement = repository.findById(id)
            .orElseThrow(() -> {
                log.warn("Appartement non trouvé: ID={}", id);
                return new ResourceNotFoundException("Appartement non trouvé: ID=" + id);
            });

        return mapper.toDto(appartement);
    }

    @Transactional(readOnly = true)
    public AppartementDto getByReference(String reference) {
        log.debug("Recherche de l'appartement par référence: {}", reference);

        Appartement appartement = repository.findByReference(reference)
            .orElseThrow(() -> {
                log.warn("Appartement non trouvé: référence={}", reference);
                return new ResourceNotFoundException("Appartement non trouvé: référence=" + reference);
            });

        return mapper.toDto(appartement);
    }

    @Transactional(readOnly = true)
    public Page<AppartementDto> listAll(Pageable pageable) {
        log.debug("Listing des appartements avec pagination");
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<AppartementDto> listByStatut(StatutBien statut) {
        log.debug("Listing des appartements par statut: {}", statut);
        return repository.findByStatut(statut).stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AppartementDto> listByVille(String ville) {
        log.debug("Listing des appartements par ville: {}", ville);
        return repository.findByVille(ville).stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AppartementDto> searchLibresByPriceRange(BigDecimal loyerMin, BigDecimal loyerMax) {
        log.debug("Recherche d'appartements libres dans la plage [{}, {}]", loyerMin, loyerMax);

        if (loyerMin.signum() < 0 || loyerMax.signum() < 0) {
            throw new IllegalArgumentException("Les montants de loyer ne peuvent pas être négatifs");
        }

        if (loyerMin.compareTo(loyerMax) > 0) {
            throw new IllegalArgumentException("Le loyer minimum ne peut pas dépasser le maximum");
        }

        return repository.findLibresByLoyerRange(loyerMin, loyerMax).stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    @Transactional
    public AppartementDto update(Long id, AppartementDto dto) {
        log.info("Mise à jour de l'appartement: ID={}", id);

        Appartement appartement = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Appartement non trouvé: ID=" + id));

        if (dto.getLoyerMensuel().signum() <= 0) {
            throw new IllegalArgumentException("Le loyer mensuel doit être strictement positif");
        }

        mapper.updateEntityFromDto(dto, appartement);
        Appartement updated = repository.save(appartement);

        log.info("Appartement mis à jour avec succès: ID={}", id);
        return mapper.toDto(updated);
    }

    @Transactional
    public AppartementDto changeStatut(Long id, StatutBien nouveauStatut) {
        log.info("Changement de statut pour l'appartement: ID={}, nouveau statut={}", id, nouveauStatut);

        Appartement appartement = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Appartement non trouvé: ID=" + id));

        StatutBien ancienStatut = appartement.getStatut();
        appartement.setStatut(nouveauStatut);
        Appartement updated = repository.save(appartement);

        log.info("Statut changé avec succès: ID={}, {} → {}", id, ancienStatut, nouveauStatut);
        return mapper.toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Suppression de l'appartement: ID={}", id);

        Appartement appartement = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Appartement non trouvé: ID=" + id));

        repository.delete(appartement);
        log.info("Appartement supprimé avec succès: ID={}", id);
    }

    @Transactional(readOnly = true)
    public long countByStatut(StatutBien statut) {
        log.debug("Comptage des appartements avec statut: {}", statut);
        return repository.countByStatut(statut);
    }

    @Transactional(readOnly = true)
    public List<AppartementDto> listByProprietaire(Long proprietaireId) {
        log.debug("Listing des appartements pour le propriétaire: ID={}", proprietaireId);
        return repository.findByProprietaireId(proprietaireId).stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }
}

