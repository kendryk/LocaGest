# LocaGest - Architecture Multi-Services

## 📐 Structure globale du projet

```
LocaGest/
│
├── .github/agents/                      ← Starter kit IA (PRÉSERVÉ)
├── contexts/                            ← Starter kit IA (PRÉSERVÉ)
├── docs/                                ← Starter kit IA (PRÉSERVÉ)
│   ├── prompts/
│   └── tips/
│
├── backend/                             ← Services Spring Boot
│   ├── appartement-service/             ← ✅ Service Appartement (CRÉÉ)
│   │   ├── pom.xml
│   │   ├── src/
│   │   ├── README.md
│   │   └── DEPLOYMENT.md
│   │
│   ├── utilisateur-service/             ← À créer
│   │   ├── pom.xml
│   │   └── src/
│   │
│   └── [autres-services]/               ← À créer
│
├── frontend/                            ← Applications frontend
│   ├── locagest-web/                    ← À créer (Angular)
│   └── [autres-frontends]/
│
├── docker-compose.yml                   ← Orchestre les services (À créer)
└── README.md                            ← Documentation globale (À mettre à jour)
```

## ✅ Statut actuel

### Créé ✅
- **`backend/appartement-service/`** - Service Spring Boot complet
  - Entité Appartement
  - DTO avec validations
  - Service avec logique métier
  - Controller REST (12 endpoints)
  - Repository Spring Data JPA
  - Tests unitaires
  - Configuration Flyway
  - **Port:** 8081

### À créer ⏳
- **`backend/utilisateur-service/`**
  - Gestion des utilisateurs
  - Authentification
  - Rôles et permissions
  - **Port:** 8082 (proposé)

- **`frontend/locagest-web/`**
  - Application Angular
  - Intégration API REST
  - Interface utilisateur

- **Infrastructure**
  - Docker Compose
  - API Gateway
  - Service Discovery

## 🚀 Démarrer les services

### Service Appartement

```bash
cd backend/appartement-service
mvn clean install
mvn spring-boot:run
```

Accès: `http://localhost:8081/api/v1/appartements`

### Service Utilisateur (quand créé)

```bash
cd backend/utilisateur-service
mvn clean install
mvn spring-boot:run
```

Accès: `http://localhost:8082/api/v1/utilisateurs`

### Frontend (quand créé)

```bash
cd frontend/locagest-web
npm install
ng serve
```

Accès: `http://localhost:4200`

## 📊 Conventions par service

Chaque service Spring Boot suit:
- ✅ Java 17
- ✅ Spring Boot 3.x
- ✅ Architecture en couches (API, Application, Domain, Infrastructure)
- ✅ Injection par constructeur
- ✅ DTOs séparés des entités
- ✅ JUnit 5 + Mockito
- ✅ SLF4J logging
- ✅ Flyway migrations
- ✅ Port distinct (8081, 8082, etc.)

## 🏗️ Roadmap

### Phase 1: Services backend ✅ (EN COURS)
- [x] `appartement-service` - Créé
- [ ] `utilisateur-service` - À créer
- [ ] Bail, Locataire, Paiement, Quittance (features)

### Phase 2: Frontend 🔄
- [ ] `locagest-web` - Angular

### Phase 3: Infrastructure 🔄
- [ ] Docker Compose
- [ ] API Gateway (Spring Cloud Gateway)
- [ ] Service Discovery (Eureka)
- [ ] Configuration Server

### Phase 4: Avancé 🔄
- [ ] Messaging (RabbitMQ, Kafka)
- [ ] Monitoring (Prometheus, Grafana)
- [ ] Tracing (Jaeger)
- [ ] Security (Spring Security, JWT)

## 🎯 Prochains défis

**Créer `backend/utilisateur-service/` ?**

Même structure que `appartement-service`:
- ✅ `pom.xml`
- ✅ Controllers, Services, Repositories
- ✅ Feature User, Role, Permission
- ✅ Tests unitaires
- ✅ Documentation

**Commande sugérée:**
```
Crée backend/utilisateur-service/ avec la même structure que appartement-service
Feature: User (entité, DTO, service, controller)
Port: 8082
```

## 📝 Notes importantes

- ✅ Le **starter kit IA reste intact** à la racine (`.github/`, `contexts/`, `docs/`)
- ✅ Le **code métier vit dans les services** (`backend/`, `frontend/`)
- ✅ Chaque service est **autonome et déployable** indépendamment
- ✅ Architecture **production-ready** (Docker, migrations, logging)
- ✅ Tests **fournis par défaut** (JUnit 5 + Mockito)

## 🔗 Documentation

- **Service Appartement:** `backend/appartement-service/README.md`
- **Déploiement:** `backend/appartement-service/DEPLOYMENT.md`
- **Instructions IA:** `.github/agents/`
- **Contextes:** `contexts/`

---

**LocaGest est maintenant structurée pour une architecture microservices! 🎉**

