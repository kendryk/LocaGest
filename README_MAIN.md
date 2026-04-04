# LocaGest

**Application de gestion immobilière modulaire et scalable**

> Mono-repo combinant **starter kit IA** + **services backend autonomes** + **frontend moderne**

![Status](https://img.shields.io/badge/status-in%20development-yellow)
![Java](https://img.shields.io/badge/java-17+-blue)
![Spring Boot](https://img.shields.io/badge/spring%20boot-3.2+-green)

---

## 📋 Vue d'ensemble

LocaGest est une solution complète pour :
- 🏠 **Gestion des appartements** (service: 8081)
- 👤 **Gestion des utilisateurs** (service: 8082 - *À créer*)
- 📝 **Gestion des baux** (feature: *À créer*)
- 💰 **Gestion des paiements** (feature: *À créer*)
- 📄 **Génération de quittances** (feature: *À créer*)
- 🎨 **Interface web** (frontend: 4200 - *À créer*)

---

## 🏗️ Architecture

```
LocaGest/
│
├── .github/agents/              ← Agents IA (instructions)
├── contexts/                    ← Contextes techniques (conventions)
├── docs/                        ← Documentation starter kit
│
├── backend/
│   ├── appartement-service/     ✅ Service Appartement (8081)
│   └── utilisateur-service/     ⏳ Service Utilisateur (8082)
│
├── frontend/
│   └── locagest-web/            ⏳ Angular (4200)
│
├── ARCHITECTURE.md              ← Guide d'architecture
├── REORGANIZATION_COMPLETE.md   ← Historique du nettoyage
└── README.md                    ← Ce fichier
```

---

## 🚀 Démarrage rapide

### Prérequis
- Java 17+
- Maven 3.8+
- Node.js 18+ (pour frontend)
- Git

### Service Appartement

```bash
cd backend/appartement-service

# Compiler et installer
mvn clean install

# Lancer les tests
mvn test

# Démarrer le service
mvn spring-boot:run
```

**Accès:** http://localhost:8081/api/v1/appartements

### Endpoints disponibles

| Méthode | URL | Description |
|---------|-----|-------------|
| POST | `/api/v1/appartements` | Créer un appartement |
| GET | `/api/v1/appartements` | Lister (pagination) |
| GET | `/api/v1/appartements/{id}` | Récupérer par ID |
| PUT | `/api/v1/appartements/{id}` | Mettre à jour |
| PATCH | `/api/v1/appartements/{id}/statut` | Changer le statut |
| DELETE | `/api/v1/appartements/{id}` | Supprimer |
| GET | `/api/v1/appartements/filter/statut` | Filtrer par statut |
| GET | `/api/v1/appartements/search/price-range` | Recherche par prix |

---

## 📁 Structure des services

Chaque service Spring Boot suit le pattern :

```
service-name/
├── pom.xml                                 ← Maven autonome
├── src/
│   ├── main/
│   │   ├── java/com/locagest/{service}/
│   │   │   ├── {Service}Application.java   ← Classe principale
│   │   │   ├── api/                        ← Controllers, DTOs, Handlers
│   │   │   ├── application/                ← Services, Mappers
│   │   │   ├── domain/                     ← Entités, Énumérations, Exceptions
│   │   │   └── infrastructure/             ← Repositories, Persistence
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migration/              ← Flyway
│   └── test/
│       ├── java/...                        ← Tests unitaires (JUnit 5 + Mockito)
│       └── resources/
└── README.md
```

### Conventions

- **Package root:** `com.locagest.{service-name}`
- **Architecture:** API → Application → Domain → Infrastructure
- **Injection:** Constructeur (jamais champ)
- **DTOs:** Toujours séparés des entités JPA
- **Transactions:** `@Transactional` au Service level
- **Logging:** SLF4J/Logback (jamais `System.out`)
- **Tests:** JUnit 5 + Mockito (pas `@SpringBootTest` pour unitaires)
- **Migration:** Flyway `V{number}__{description}.sql`

---

## 🧠 Starter Kit IA

Le dépôt contient un **système d'instructions IA** pour guider le développement :

### Agents spécialisés (`.github/agents/`)

- **assistant-dev** - Clarification et cadrage
- **architecte-projet** - Design et architecture
- **analyste-metier** - Traduction fonctionnelle
- **reviewer-code** - Qualité et risques
- **debugger-technique** - Diagnostic d'erreurs
- **expert-immo** - Domaine immobilier
- **redacteur-doc** - Documentation

### Contextes (`.contexts/`)

- **backend/** - Conventions Spring Boot
- **domain/** - Règles métier
- **frontend/** - Conventions Angular
- **infra/** - CI/CD et déploiement

### Prompts réutilisables (`.docs/prompts/`)

- `creer-module-backend.md`
- `analyser-bug-production.md`
- etc.

---

## 📊 Statut des services

| Service | Status | Port | Description |
|---------|--------|------|-------------|
| Appartement | ✅ Complété | 8081 | CRUD complet, 12 endpoints |
| Utilisateur | ⏳ À créer | 8082 | Auth, Roles, Permissions |
| Frontend | ⏳ À créer | 4200 | Angular, UI/UX |
| API Gateway | ⏳ À créer | 8080 | Spring Cloud Gateway |
| Database | ⏳ À créer | 5432 | PostgreSQL |

---

## 🔧 Configuration

### Variables d'environnement

```bash
# Base de données
DATABASE_URL=jdbc:postgresql://localhost:5432/locagest
DATABASE_USER=postgres
DATABASE_PASSWORD=secret

# Services
APPARTEMENT_SERVICE_PORT=8081
UTILISATEUR_SERVICE_PORT=8082

# Frontend
FRONTEND_PORT=4200
API_URL=http://localhost:8080
```

### Profils Spring

```bash
# Développement (H2 en mémoire)
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"

# Production (PostgreSQL)
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"

# Tests
mvn test -Dspring-boot.run.arguments="--spring.profiles.active=test"
```

---

## 📚 Documentation

| Document | Contenu |
|----------|---------|
| `ARCHITECTURE.md` | Vue globale de la structure |
| `REORGANIZATION_COMPLETE.md` | Historique du nettoyage |
| `backend/appartement-service/README.md` | Guide du service Appartement |
| `backend/appartement-service/DEPLOYMENT.md` | Guide de déploiement |
| `.github/CLAUDE.md` | Instructions IA principales |
| `contexts/backend/CLAUDE.md` | Conventions backend |
| `contexts/domain/CLAUDE.md` | Règles métier |

---

## 🧪 Tests

### Lancer les tests d'un service

```bash
cd backend/appartement-service

# Tous les tests
mvn test

# Test spécifique
mvn test -Dtest=AppartementServiceTest

# Avec coverage
mvn test jacoco:report
# Rapport: target/site/jacoco/index.html
```

### Test d'API (curl)

```bash
# Créer un appartement
curl -X POST http://localhost:8081/api/v1/appartements \
  -H "Content-Type: application/json" \
  -d '{
    "reference": "APT-PARIS-001",
    "adresse": "123 Rue de Rivoli",
    "codePostal": "75004",
    "ville": "Paris",
    "surfaceHabitable": 50.5,
    "nombrePieces": 1,
    "loyerMensuel": 850.00,
    "chargesMensuelles": 120.00,
    "typeBien": "T1",
    "statut": "LIBRE"
  }'

# Lister
curl http://localhost:8081/api/v1/appartements

# Filtrer
curl "http://localhost:8081/api/v1/appartements/filter/statut?statut=LIBRE"
```

---

## 🐳 Docker & Déploiement

### Docker Compose (À créer)

```yaml
version: '3.8'
services:
  # Services backend
  appartement-service:
    build: ./backend/appartement-service
    ports:
      - "8081:8081"
    environment:
      DATABASE_URL: jdbc:postgresql://db:5432/locagest
  
  utilisateur-service:
    build: ./backend/utilisateur-service
    ports:
      - "8082:8082"
  
  # Database
  db:
    image: postgres:15
    environment:
      POSTGRES_DB: locagest
      POSTGRES_PASSWORD: secret
    ports:
      - "5432:5432"
  
  # Frontend
  frontend:
    build: ./frontend/locagest-web
    ports:
      - "4200:4200"
```

### Lancer avec Docker

```bash
docker-compose up -d
```

---

## 🤝 Contribution

Voir `CONTRIBUTING.md` pour les directives de contribution.

### Workflow de développement

1. Créer une branche: `git checkout -b feature/mon-feature`
2. Implémenter avec les agents IA
3. Tester: `mvn test`
4. Commiter: `git commit -m "feat: description"`
5. Pousser: `git push origin feature/mon-feature`
6. Pull Request avec description

---

## 📝 Licence

Voir `LICENSE` file.

---

## 📞 Support

- **Documentation:** Voir `docs/`
- **Contextes IA:** Voir `contexts/`
- **Agents IA:** Voir `.github/agents/`
- **Exemples:** Voir `backend/appartement-service/README.md`

---

## 🗺️ Roadmap

### Phase 1 : Services backend (EN COURS ✅)
- [x] Service Appartement (CRUD, migrations, tests)
- [ ] Service Utilisateur (Auth, Roles)
- [ ] Intégration des services

### Phase 2 : Frontend (À VENIR)
- [ ] Setup Angular
- [ ] Composants Appartement
- [ ] Intégration API

### Phase 3 : Infrastructure (À VENIR)
- [ ] Docker Compose
- [ ] API Gateway
- [ ] Database PostgreSQL
- [ ] CI/CD (GitHub Actions)

### Phase 4 : Avancé (À VENIR)
- [ ] Monitoring (Prometheus, Grafana)
- [ ] Tracing (Jaeger)
- [ ] Messaging (RabbitMQ)
- [ ] Cache (Redis)

---

## ✨ Highlights

✅ Service Appartement complet avec 12 endpoints  
✅ Tests unitaires fournis (JUnit 5 + Mockito)  
✅ Migrations SQL avec Flyway  
✅ Documentation claire et détaillée  
✅ Starter kit IA pour guidance  
✅ Architecture modulaire et scalable  
✅ Conformité SOLID et best practices  
✅ Prêt pour production (H2/PostgreSQL)  

---

**Dernière mise à jour:** 2026-04-04  
**Version:** 1.0.0-SNAPSHOT  
**Auteur:** Équipe LocaGest + GitHub Copilot

