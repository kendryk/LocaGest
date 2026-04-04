package com.locagest.appartement.application.service;

import com.locagest.appartement.api.dto.AppartementDto;
import com.locagest.appartement.application.mapper.AppartementMapper;
import com.locagest.appartement.domain.exception.ResourceNotFoundException;
import com.locagest.appartement.domain.model.Appartement;
import com.locagest.appartement.domain.model.StatutBien;
import com.locagest.appartement.domain.model.TypeBien;
import com.locagest.appartement.infrastructure.persistence.AppartementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Tests unitaires du service AppartementService.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("AppartementService - Tests unitaires")
class AppartementServiceTest {

    @Mock
    private AppartementRepository repository;

    @Mock
    private AppartementMapper mapper;

    @InjectMocks
    private AppartementService service;

    private AppartementDto testDto;
    private Appartement testEntity;

    @BeforeEach
    void setUp() {
        testDto = AppartementDto.builder()
            .reference("APT-PARIS-001")
            .adresse("123 Rue de Paris")
            .codePostal("75001")
            .ville("Paris")
            .surfaceHabitable(new BigDecimal("50.00"))
            .nombrePieces(1)
            .loyerMensuel(new BigDecimal("800.00"))
            .chargesMensuelles(new BigDecimal("100.00"))
            .typeBien(TypeBien.T1)
            .statut(StatutBien.LIBRE)
            .anneeConstruction(2020)
            .observations("Bien en bon état")
            .build();

        testEntity = Appartement.builder()
            .id(1L)
            .reference("APT-PARIS-001")
            .adresse("123 Rue de Paris")
            .codePostal("75001")
            .ville("Paris")
            .surfaceHabitable(new BigDecimal("50.00"))
            .nombrePieces(1)
            .loyerMensuel(new BigDecimal("800.00"))
            .chargesMensuelles(new BigDecimal("100.00"))
            .typeBien(TypeBien.T1)
            .statut(StatutBien.LIBRE)
            .anneeConstruction(2020)
            .observations("Bien en bon état")
            .version(1L)
            .build();
    }

    @Test
    @DisplayName("Création d'un appartement - cas nominal")
    void testCreate_Success() {
        when(repository.existsByReference(testDto.getReference())).thenReturn(false);
        when(mapper.toEntity(testDto)).thenReturn(testEntity);
        when(repository.save(any(Appartement.class))).thenReturn(testEntity);
        when(mapper.toDto(testEntity)).thenReturn(testDto);

        AppartementDto result = service.create(testDto);

        assertNotNull(result);
        assertEquals(testDto.getReference(), result.getReference());
        verify(repository).existsByReference(testDto.getReference());
        verify(repository).save(any(Appartement.class));
    }

    @Test
    @DisplayName("Création d'un appartement - référence dupliquée")
    void testCreate_DuplicateReference() {
        when(repository.existsByReference(testDto.getReference())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> service.create(testDto));
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Récupération par ID - cas nominal")
    void testGetById_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(testEntity));
        when(mapper.toDto(testEntity)).thenReturn(testDto);

        AppartementDto result = service.getById(1L);

        assertNotNull(result);
        assertEquals(testDto.getReference(), result.getReference());
    }

    @Test
    @DisplayName("Récupération par ID - non trouvé")
    void testGetById_NotFound() {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getById(999L));
    }

    @Test
    @DisplayName("Changement de statut - cas nominal")
    void testChangeStatut_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(testEntity));
        testEntity.setStatut(StatutBien.LOUE);
        when(repository.save(any(Appartement.class))).thenReturn(testEntity);
        testDto.setStatut(StatutBien.LOUE);
        when(mapper.toDto(testEntity)).thenReturn(testDto);

        AppartementDto result = service.changeStatut(1L, StatutBien.LOUE);

        assertNotNull(result);
        assertEquals(StatutBien.LOUE, result.getStatut());
    }

    @Test
    @DisplayName("Suppression d'un appartement - cas nominal")
    void testDelete_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(testEntity));

        service.delete(1L);

        verify(repository).findById(1L);
        verify(repository).delete(testEntity);
    }
}

