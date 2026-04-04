# Brique Appartement - Documentation Technique

## Vue d'ensemble

La brique **Appartement** gère le cycle de vie complet des bien immobiliers dans LocaGest.

```
Responsabilités principales:
├── Gestion des appartements (CRUD)
├── Recherche et filtrage (statut, ville, prix)
├── Changement de statut (LIBRE → LOUE → MAINTENANCE)
├── Métadonnées (audit, versioning)
└── Exposition REST sécurisée
```

## Architecture en couches

### 1. **Domain** (Cœur métier)

**Package:** `com.locagest.domain.entity`

- `Appartement.java` - Entité JPA avec validations métier
- `TypeBien.java` - Énumération (STUDIO, T1, T2, T3, T4+, MAISON)
- `StatutBien.java` - Énumération (LIBRE, LOUE, MAINTENANCE, RETIRE_DU_MARCHE)

**Caractéristiques:**
- Pas de logique métier, uniquement persistance
- Hooks JPA (`@PrePersist`, `@PreUpdate`) pour les métadonnées
- Optimistic locking via `@Version`
- Indices DB pour les recherches fréquentes

### 2. **Application** (Orchestration)

**Package:** `com.locagest.application.service`

- `AppartementService.java` - Logique métier, transactions, coordin métier

**Validations métier:**
- Référence unique (checked avant persist)
- Loyer strictement positif
- Charges non-négatives
- Montants cohérents

**Package:** `com.locagest.application.mapper`

- `AppartementMapper.java` - Conversion Entity ↔ DTO (manuel)

**Mapping:**
- Asymétrique: ID et métadonnées ne remontent pas à la création
- Reference immuable après création
- Statut par défaut: LIBRE

**Package:** `com.locagest.application.dto`

- `AppartementDto.java` - Contrat d'exposition (validations Bean Validation)

**Validations:**
- `@NotBlank`, `@Size`, `@Pattern` pour les strings
- `@DecimalMin`, `@DecimalMax` pour les montants
- Messages français à l'utilisateur
- Format: APT-UPPERCASE-DIGITS

**Package:** `com.locagest.application.exception`

- `ResourceNotFoundException.java` - Exception 404

### 3. **Infrastructure** (Techniques)

**Package:** `com.locagest.infrastructure.persistence`

- `AppartementRepository.java` - Spring Data JPA

**Requêtes:**
- Dérivées simples: `findByVille`, `findByStatut`
- `@Query` pour requêtes complexes: plages de loyer, jointures
- Aucun N+1 query par défaut (pas de lazy loading forcé)

**Package:** `com.locagest.infrastructure.web`

- `AppartementController.java` - Endpoints REST
- `GlobalExceptionHandler.java` - Gestion centralisée des erreurs

**Endpoints:**
```
POST   /api/v1/appartements                    → Créer
GET    /api/v1/appartements                    → Lister (pagination)
GET    /api/v1/appartements/{id}               → Récupérer
GET    /api/v1/appartements/reference/{ref}   → Par référence
PUT    /api/v1/appartements/{id}               → Mettre à jour
PATCH  /api/v1/appartements/{id}/statut       → Changer statut
DELETE /api/v1/appartements/{id}               → Supprimer
GET    /api/v1/appartements/filter/statut     → Filtrer par statut
GET    /api/v1/appartements/filter/ville      → Filtrer par ville
GET    /api/v1/appartements/search/price-range → Recherche loyer
GET    /api/v1/appartements/stats/count       → Statistiques
GET    /api/v1/appartements/proprietaire/{id} → Par propriétaire
```

**Package:** `com.locagest.infrastructure.config`

- `AppartementConfiguration.java` - Autowiring Spring Data JPA

## Choix techniques

### Pourquoi un Mapper manuel ?

| Approche | Avantages | Inconvénients |
|----------|-----------|---------------|
| **Mapper manuel (choix)** | Simple pour 1-2 entités, facile à debugger, zéro dépendance | Boilerplate si 10+ entités |
| **MapStruct** | Moins de boilerplate, performance | Dépendance supplémentaire, courbe apprentissage |
| **Dozer/Orika** | Automatique | Trop compliqué pour des cas simples |

**Décision:** Mapper manuel. **Migration:** Passer à MapStruct si > 5 entités complexes.

### Pourquoi injection par constructeur ?

```java
// ✅ SOLID: dépendances explicites
@RequiredArgsConstructor
public class AppartementService {
    private final AppartementRepository repository;
    private final AppartementMapper mapper;
}

// ❌ À éviter: injection par champ
@Autowired
private AppartementRepository repository;
```

### Pourquoi @Transactional au Service ?

- **Transactions courtes** (une opération = une transaction)
- **readOnly=true** sur les lectures (optimisation DB)
- **Propagation.REQUIRED** par défaut (par couche, pas par méthode)
- Les exceptions de persistance restent du domaine du Service

### Énumérations vs String

```java
// ✅ Type-safe, validable, pas d'erreurs de typage
@Enumerated(EnumType.STRING)
private StatutBien statut;

// ❌ Fragile, pas de validation
@Column(length = 20)
private String statut;
```

### Validations à deux niveaux

```
Client  → (1) Bean Validation (DTO)  → 400 Bad Request
        → (2) Logique métier (Service) → 409 Conflict
```

Exemple:
- Niveau 1: loyer est un nombre positif ✓
- Niveau 2: référence existe déjà → business error

### Optimistic Locking

```java
@Version
private Long version;
```

Protège contre les updates concurrentes. À utiliser si plusieurs utilisateurs modifient le même bien.

### Métadonnées de Audit

```
createdAt  → @PrePersist (auto)
updatedAt  → @PreUpdate (auto)
createdBy  → À remplir par le Service (contexte utilisateur)
updatedBy  → À remplir par le Service (contexte utilisateur)
```

À implémenter quand l'authentification sera en place.

## Relations et dépendances futures

### Propriétaire (relation 1:N)

```java
// Dans Appartement (futur):
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "proprietaire_id")
private Proprietaire proprietaire;
```

### Baux/Locataires (relation 1:N)

```
Apartement ← Bail (1 appartement, plusieurs bails) → Locataire
```

À implémenter ensuite.

## Tests

### Unitaires (JUnit 5 + Mockito)

- Service: logique métier isolée
- Mapper: conversion DTO ↔ Entity
- Controller: **PAS** testé ici (testing Spring Boot coûteux)

### Intégration

À faire après:
- Repository + DB réelle
- Controller + HTTP
- Transactions

### Couverture cible

- Métier: 80%+ (Service)
- Infrastructure: cas critiques seulement
- Controller: acceptance tests à niveau HTTP

## Performance et considérations

### N+1 Query Problem

❌ **Mauvais:**
```java
List<Appartement> apts = repository.findAll();
apts.forEach(a -> a.getProprietaire()); // Query par appartement!
```

✅ **Bon:**
```java
@Query("SELECT a FROM Appartement a JOIN FETCH a.proprietaire")
List<Appartement> findAllWithProprietaire();
```

### Pagination obligatoire

```java
// ❌ À éviter: charge 1M de lignes
List<Appartement> findAll();

// ✅ À utiliser
Page<AppartementDto> listAll(Pageable pageable);
```

### Indices recommandés

```sql
-- Références (recherche exacte)
CREATE UNIQUE INDEX idx_reference ON appartements(reference);

-- Adresse (filtres)
CREATE INDEX idx_adresse ON appartements(adresse_id);

-- Statut + loyer (recherches combinées)
CREATE INDEX idx_statut_loyer ON appartements(statut, loyer_mensuel);
```

## Exécution des tests

```bash
# Tous les tests de la brique
mvn test -Dtest=com.locagest.application.service.*

# Test spécifique
mvn test -Dtest=AppartementServiceTest

# Coverage
mvn clean test jacoco:report
```

## Évolutions probables

| Évolution | Impact | Priorité |
|-----------|--------|----------|
| Propriétaire | Ajouter relation ManyToOne | P1 |
| Baux | Ajouter module distinct | P1 |
| Images/Documents | Ajouter stockage | P2 |
| Historique prix | Ajouter table d'audit | P2 |
| Notifications | Ajouter événements | P3 |
| Permissions | Ajouter contrôle d'accès | P1 |

## Dépendances Maven requises

```xml
<!-- Spring Boot (version 3.x recommandée) -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- Validation -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

<!-- Lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>

<!-- Tests -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

## Configuration Spring Boot

```properties
# application.properties

# JPA
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true

# Logging
logging.level.com.locagest=INFO
logging.level.org.springframework.web=WARN
```

## Prochaines étapes

1. ✅ Entité + DTO + Service + Controller
2. ⏳ Integration tests avec @DataJpaTest
3. ⏳ Intégration avec Propriétaire
4. ⏳ Intégration avec Baux/Locataires
5. ⏳ Permissions (qui peut voir/modifier)
6. ⏳ Filtres avancés (recherche full-text, map)

