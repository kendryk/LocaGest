package com.locagest.appartement.api.controller;

import com.locagest.appartement.api.dto.AppartementDto;
import com.locagest.appartement.application.service.AppartementService;
import com.locagest.appartement.domain.model.StatutBien;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Controller REST pour la gestion des appartements.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/appartements")
@RequiredArgsConstructor
public class AppartementController {

    private final AppartementService service;

    @PostMapping
    public ResponseEntity<AppartementDto> create(@Valid @RequestBody AppartementDto dto) {
        log.info("POST /api/v1/appartements - Création avec référence: {}", dto.getReference());
        AppartementDto created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppartementDto> getById(@PathVariable Long id) {
        log.info("GET /api/v1/appartements/{}", id);
        AppartementDto appartement = service.getById(id);
        return ResponseEntity.ok(appartement);
    }

    @GetMapping("/reference/{reference}")
    public ResponseEntity<AppartementDto> getByReference(@PathVariable String reference) {
        log.info("GET /api/v1/appartements/reference/{}", reference);
        AppartementDto appartement = service.getByReference(reference);
        return ResponseEntity.ok(appartement);
    }

    @GetMapping
    public ResponseEntity<Page<AppartementDto>> listAll(Pageable pageable) {
        log.info("GET /api/v1/appartements - Listing avec pagination");
        Page<AppartementDto> appartements = service.listAll(pageable);
        return ResponseEntity.ok(appartements);
    }

    @GetMapping("/filter/statut")
    public ResponseEntity<List<AppartementDto>> listByStatut(@RequestParam StatutBien statut) {
        log.info("GET /api/v1/appartements/filter/statut?statut={}", statut);
        List<AppartementDto> appartements = service.listByStatut(statut);
        return ResponseEntity.ok(appartements);
    }

    @GetMapping("/filter/ville")
    public ResponseEntity<List<AppartementDto>> listByVille(@RequestParam String ville) {
        log.info("GET /api/v1/appartements/filter/ville?ville={}", ville);
        List<AppartementDto> appartements = service.listByVille(ville);
        return ResponseEntity.ok(appartements);
    }

    @GetMapping("/search/price-range")
    public ResponseEntity<List<AppartementDto>> searchByPriceRange(
        @RequestParam BigDecimal min,
        @RequestParam BigDecimal max) {
        log.info("GET /api/v1/appartements/search/price-range?min={}&max={}", min, max);
        List<AppartementDto> appartements = service.searchLibresByPriceRange(min, max);
        return ResponseEntity.ok(appartements);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppartementDto> update(
        @PathVariable Long id,
        @Valid @RequestBody AppartementDto dto) {
        log.info("PUT /api/v1/appartements/{}", id);
        AppartementDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/statut")
    public ResponseEntity<AppartementDto> changeStatut(
        @PathVariable Long id,
        @RequestParam StatutBien statut) {
        log.info("PATCH /api/v1/appartements/{}/statut?statut={}", id, statut);
        AppartementDto updated = service.changeStatut(id, statut);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /api/v1/appartements/{}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats/count")
    public ResponseEntity<Long> countByStatut(@RequestParam StatutBien statut) {
        log.info("GET /api/v1/appartements/stats/count?statut={}", statut);
        long count = service.countByStatut(statut);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/proprietaire/{proprietaireId}")
    public ResponseEntity<List<AppartementDto>> listByProprietaire(@PathVariable Long proprietaireId) {
        log.info("GET /api/v1/appartements/proprietaire/{}", proprietaireId);
        List<AppartementDto> appartements = service.listByProprietaire(proprietaireId);
        return ResponseEntity.ok(appartements);
    }
}

