package com.locagest.appartement.application.service;

import com.locagest.appartement.api.dto.LocataireDto;
import com.locagest.appartement.application.mapper.LocataireMapper;
import com.locagest.appartement.domain.exception.ResourceNotFoundException;
import com.locagest.appartement.domain.model.Locataire;
import com.locagest.appartement.infrastructure.persistence.LocataireRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Tests unitaires du service LocataireService.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("LocataireService - Tests unitaires")
class LocataireServiceTest {

    @Mock
    private LocataireRepository repository;

    @Mock
    private LocataireMapper mapper;

    @InjectMocks
    private LocataireService service;

    private LocataireDto testDto;
    private Locataire testEntity;

    @BeforeEach
    void setUp() {
        testDto = LocataireDto.builder()
            .nom("Dupont")
            .prenom("Jean")
            .email("jean.dupont@email.com")
            .telephone("0123456789")
            .dateNaissance(LocalDate.of(1990, 1, 15))
            .actif(true)
            .build();

        testEntity = Locataire.builder()
            .id(1L)
            .nom("Dupont")
            .prenom("Jean")
            .email("jean.dupont@email.com")
            .telephone("0123456789")
            .dateNaissance(LocalDate.of(1990, 1, 15))
            .actif(true)
            .version(1L)
            .build();
    }

    @Test
    @DisplayName("Création d'un locataire - cas nominal")
    void testCreate_Success() {
        when(repository.existsByEmail(testDto.getEmail())).thenReturn(false);
        when(repository.existsByTelephone(testDto.getTelephone())).thenReturn(false);
        when(mapper.toEntity(testDto)).thenReturn(testEntity);
        when(repository.save(any(Locataire.class))).thenReturn(testEntity);
        when(mapper.toDto(testEntity)).thenReturn(testDto);

        LocataireDto result = service.create(testDto);

        assertNotNull(result);
        assertEquals(testDto.getEmail(), result.getEmail());
        verify(repository).existsByEmail(testDto.getEmail());
        verify(repository).existsByTelephone(testDto.getTelephone());
        verify(repository).save(any(Locataire.class));
    }

    @Test
    @DisplayName("Création d'un locataire - email dupliqué")
    void testCreate_DuplicateEmail() {
        when(repository.existsByEmail(testDto.getEmail())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> service.create(testDto));
        verify(repository).existsByEmail(testDto.getEmail());
        verify(repository, never()).save(any(Locataire.class));
    }

    @Test
    @DisplayName("Création d'un locataire - téléphone dupliqué")
    void testCreate_DuplicateTelephone() {
        when(repository.existsByEmail(testDto.getEmail())).thenReturn(false);
        when(repository.existsByTelephone(testDto.getTelephone())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> service.create(testDto));
        verify(repository).existsByTelephone(testDto.getTelephone());
        verify(repository, never()).save(any(Locataire.class));
    }

    @Test
    @DisplayName("Création d'un locataire - mineur")
    void testCreate_Minor() {
        LocataireDto minorDto = LocataireDto.builder()
            .nom("Dupont")
            .prenom("Jean")
            .email("jean.dupont@email.com")
            .telephone("0123456789")
            .dateNaissance(LocalDate.now().minusYears(16)) // Mineur
            .actif(true)
            .build();

        assertThrows(IllegalArgumentException.class, () -> service.create(minorDto));
    }

    @Test
    @DisplayName("Récupération par ID - cas nominal")
    void testGetById_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(testEntity));
        when(mapper.toDto(testEntity)).thenReturn(testDto);

        LocataireDto result = service.getById(1L);

        assertNotNull(result);
        assertEquals(testDto.getEmail(), result.getEmail());
        verify(repository).findById(1L);
    }

    @Test
    @DisplayName("Récupération par ID - non trouvé")
    void testGetById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getById(1L));
        verify(repository).findById(1L);
    }

    @Test
    @DisplayName("Récupération par email - cas nominal")
    void testGetByEmail_Success() {
        when(repository.findByEmail("jean.dupont@email.com")).thenReturn(Optional.of(testEntity));
        when(mapper.toDto(testEntity)).thenReturn(testDto);

        LocataireDto result = service.getByEmail("jean.dupont@email.com");

        assertNotNull(result);
        assertEquals(testDto.getEmail(), result.getEmail());
        verify(repository).findByEmail("jean.dupont@email.com");
    }

    @Test
    @DisplayName("Listing avec pagination")
    void testListAll() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Locataire> entityPage = new PageImpl<>(List.of(testEntity), pageable, 1);
        Page<LocataireDto> dtoPage = new PageImpl<>(List.of(testDto), pageable, 1);

        when(repository.findAll(pageable)).thenReturn(entityPage);
        when(mapper.toDto(testEntity)).thenReturn(testDto);

        Page<LocataireDto> result = service.listAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(repository).findAll(pageable);
    }

    @Test
    @DisplayName("Mise à jour - cas nominal")
    void testUpdate_Success() {
        LocataireDto updateDto = LocataireDto.builder()
            .nom("Dupont")
            .prenom("Jean-Marie")
            .email("jean.dupont@email.com")
            .telephone("0123456789")
            .dateNaissance(LocalDate.of(1990, 1, 15))
            .actif(true)
            .build();

        when(repository.findById(1L)).thenReturn(Optional.of(testEntity));
        when(repository.save(any(Locataire.class))).thenReturn(testEntity);
        when(mapper.toDto(testEntity)).thenReturn(updateDto);

        LocataireDto result = service.update(1L, updateDto);

        assertNotNull(result);
        assertEquals("Jean-Marie", result.getPrenom());
        verify(repository).findById(1L);
        verify(repository).save(any(Locataire.class));
    }

    @Test
    @DisplayName("Mise à jour - email déjà utilisé par un autre locataire")
    void testUpdate_EmailAlreadyUsed() {
        LocataireDto updateDto = LocataireDto.builder()
            .nom("Dupont")
            .prenom("Jean")
            .email("nouveau.email@email.com") // Email différent
            .telephone("0123456789")
            .dateNaissance(LocalDate.of(1990, 1, 15))
            .actif(true)
            .build();

        when(repository.findById(1L)).thenReturn(Optional.of(testEntity));
        when(repository.existsByEmail("nouveau.email@email.com")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> service.update(1L, updateDto));
    }

    @Test
    @DisplayName("Changement de statut actif")
    void testChangeActifStatus() {
        when(repository.findById(1L)).thenReturn(Optional.of(testEntity));
        when(repository.save(any(Locataire.class))).thenReturn(testEntity);
        when(mapper.toDto(testEntity)).thenReturn(testDto);

        LocataireDto result = service.changeActifStatus(1L, false);

        assertNotNull(result);
        verify(repository).findById(1L);
        verify(repository).save(any(Locataire.class));
    }

    @Test
    @DisplayName("Suppression - cas nominal")
    void testDelete_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(testEntity));

        assertDoesNotThrow(() -> service.delete(1L));
        verify(repository).findById(1L);
        verify(repository).delete(testEntity);
    }

    @Test
    @DisplayName("Comptage par statut actif")
    void testCountByActif() {
        when(repository.countByActif(true)).thenReturn(5L);

        long result = service.countByActif(true);

        assertEquals(5L, result);
        verify(repository).countByActif(true);
    }
}
