package com.locagest.appartement.api.controller;

import com.locagest.appartement.api.dto.LocataireDto;
import com.locagest.appartement.application.service.LocataireService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour la gestion des locataires.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/locataires")
@RequiredArgsConstructor
public class LocataireController {

    private final LocataireService service;

    @PostMapping
    public ResponseEntity<LocataireDto> create(@Valid @RequestBody LocataireDto dto) {
        log.info("POST /api/v1/locataires - Création: {} {}", dto.getPrenom(), dto.getNom());
        LocataireDto created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocataireDto> getById(@PathVariable Long id) {
        log.info("GET /api/v1/locataires/{}", id);
        LocataireDto locataire = service.getById(id);
        return ResponseEntity.ok(locataire);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<LocataireDto> getByEmail(@PathVariable String email) {
        log.info("GET /api/v1/locataires/email/{}", email);
        LocataireDto locataire = service.getByEmail(email);
        return ResponseEntity.ok(locataire);
    }

    @GetMapping
    public ResponseEntity<Page<LocataireDto>> listAll(Pageable pageable) {
        log.info("GET /api/v1/locataires - Listing avec pagination");
        Page<LocataireDto> locataires = service.listAll(pageable);
        return ResponseEntity.ok(locataires);
    }

    @GetMapping("/filter/actif")
    public ResponseEntity<List<LocataireDto>> listByActif(@RequestParam Boolean actif) {
        log.info("GET /api/v1/locataires/filter/actif?actif={}", actif);
        List<LocataireDto> locataires = service.listByActif(actif);
        return ResponseEntity.ok(locataires);
    }

    @GetMapping("/actifs")
    public ResponseEntity<List<LocataireDto>> listActifsOrderByNomPrenom() {
        log.info("GET /api/v1/locataires/actifs - Listing des actifs triés par nom/prénom");
        List<LocataireDto> locataires = service.listActifsOrderByNomPrenom();
        return ResponseEntity.ok(locataires);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocataireDto> update(
        @PathVariable Long id,
        @Valid @RequestBody LocataireDto dto) {
        log.info("PUT /api/v1/locataires/{}", id);
        LocataireDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/actif")
    public ResponseEntity<LocataireDto> changeActifStatus(
        @PathVariable Long id,
        @RequestParam Boolean actif) {
        log.info("PATCH /api/v1/locataires/{}/actif?actif={}", id, actif);
        LocataireDto updated = service.changeActifStatus(id, actif);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /api/v1/locataires/{}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats/count")
    public ResponseEntity<Long> countByActif(@RequestParam Boolean actif) {
        log.info("GET /api/v1/locataires/stats/count?actif={}", actif);
        long count = service.countByActif(actif);
        return ResponseEntity.ok(count);
    }
}
