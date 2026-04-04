# Appartement Service - Documentation

## 📋 Vue d'ensemble

Service Spring Boot autonome pour la gestion des appartements dans LocaGest.

**Port:** 8081  
**Base URL API:** `http://localhost:8081/api/v1`

## 📁 Structure du projet

```
backend/appartement-service/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/locagest/appartement/
│   │   │   ├── AppartementServiceApplication.java    [Main]
│   │   │   ├── api/
│   │   │   │   ├── controller/AppartementController.java
│   │   │   │   ├── dto/AppartementDto.java
│   │   │   │   └── advice/GlobalExceptionHandler.java
│   │   │   ├── application/
│   │   │   │   ├── service/AppartementService.java
│   │   │   │   └── mapper/AppartementMapper.java
│   │   │   ├── domain/
│   │   │   │   ├── model/
│   │   │   │   │   ├── Appartement.java
│   │   │   │   │   ├── TypeBien.java
│   │   │   │   │   └── StatutBien.java
│   │   │   │   └── exception/ResourceNotFoundException.java
│   │   │   └── infrastructure/
│   │   │       └── persistence/AppartementRepository.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migration/
│   │           └── V001__create_appartements_table.sql
│   └── test/
│       ├── java/com/locagest/appartement/
│       │   └── application/service/AppartementServiceTest.java
│       └── resources/
│           └── application.properties
└── README.md
```

## 🚀 Démarrage rapide

### 1. Build

```bash
cd backend/appartement-service
mvn clean install
```

### 2. Lancer le service

```bash
mvn spring-boot:run
```

Ou avec :

```bash
java -jar target/appartement-service-1.0.0-SNAPSHOT.jar
```

### 3. Vérifier

```bash
curl http://localhost:8081/api/v1/appartements
```

## 📊 Endpoints REST

### Créer un appartement

```http
POST /api/v1/appartements
Content-Type: application/json

{
  "reference": "APT-PARIS-001",
  "adresse": "123 Rue de Rivoli",
  "codePostal": "75004",
  "ville": "Paris",
  "surfaceHabitable": 50.50,
  "nombrePieces": 1,
  "loyerMensuel": 850.00,
  "chargesMensuelles": 120.00,
  "typeBien": "T1",
  "statut": "LIBRE"
}
```

### Récupérer par ID

```http
GET /api/v1/appartements/1
```

### Lister avec pagination

```http
GET /api/v1/appartements?page=0&size=20&sort=reference,asc
```

### Filtrer par statut

```http
GET /api/v1/appartements/filter/statut?statut=LIBRE
```

### Recherche par plage de loyer

```http
GET /api/v1/appartements/search/price-range?min=500&max=1500
```

### Changer le statut

```http
PATCH /api/v1/appartements/1/statut?statut=LOUE
```

### Mettre à jour

```http
PUT /api/v1/appartements/1
{...données...}
```

### Supprimer

```http
DELETE /api/v1/appartements/1
```

## 🧪 Tests

### Lancer les tests

```bash
mvn clean test
```

### Test spécifique

```bash
mvn test -Dtest=AppartementServiceTest
```

## 🏗️ Architecture

### Couches

1. **API** - Controllers, DTOs, Exception Handlers
2. **Application** - Services, Mappers
3. **Domain** - Entités, Énumérations, Exceptions métier
4. **Infrastructure** - Repositories, Persistence

### Principes

- ✅ Architecture en couches
- ✅ Injection par constructeur
- ✅ DTOs séparés des entités
- ✅ Validations Bean Validation
- ✅ Transactions explicites
- ✅ Logging SLF4J
- ✅ SOLID principles

## 📦 Dépendances principales

- Spring Boot 3.2
- Spring Data JPA
- Lombok
- H2 Database
- Flyway
- JUnit 5 + Mockito

## 📝 Configuration

### application.properties

```properties
server.port=8081
spring.datasource.url=jdbc:h2:mem:appartement-db
spring.jpa.hibernate.ddl-auto=validate
```

## 🔄 Prochaines étapes

- [ ] Implémenter Locataire
- [ ] Implémenter Bail
- [ ] Implémenter Paiement
- [ ] Implémenter Quittance
- [ ] Ajouter authentification
- [ ] Ajouter permissions

## 📞 Support

Voir la documentation technique dans `docs/BRIQUE_APPARTEMENT.md`

