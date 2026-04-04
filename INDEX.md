# 📚 Index de la documentation - LocaGest

Bienvenue dans LocaGest ! Voici où trouver l'information.

---

## 🚀 Je veux...

### ... Démarrer le service Appartement
👉 **`RESUME_REORGANISATION.md`** - Instructions de démarrage rapide
```bash
cd backend/appartement-service && mvn spring-boot:run
```

### ... Comprendre l'architecture du projet
👉 **`ARCHITECTURE.md`** - Vue globale de la structure
👉 **`README_MAIN.md`** - Documentation complète

### ... Créer un nouveau service (Utilisateur, etc.)
👉 **`GUIDE_NOUVEAUX_SERVICES.md`** - Template étape par étape

### ... Comprendre ce qui a changé lors du nettoyage
👉 **`RAPPORT_FINAL.md`** - Rapport complet d'exécution
👉 **`REORGANIZATION_COMPLETE.md`** - Historique du nettoyage

### ... Utiliser les agents IA
👉 **`.github/agents/`** - Agents spécialisés
👉 **`contexts/`** - Contextes techniques
👉 **`CLAUDE.md`** - Instructions principales

### ... Tester l'API Appartement
👉 **`backend/appartement-service/README.md`** - Endpoints disponibles

---

## 📂 Organisation des fichiers

```
LocaGest/
│
├── 🤖 SYSTÈME D'INSTRUCTIONS IA (PRÉSERVÉ)
│   ├── .github/agents/          ← Agents spécialisés
│   ├── contexts/                ← Conventions techniques
│   ├── docs/prompts/            ← Prompts réutilisables
│   └── CLAUDE.md                ← Instructions principales
│
├── 🏗️ ARCHITECTURE & GUIDE
│   ├── ARCHITECTURE.md          ← Vue globale multi-services
│   ├── README_MAIN.md           ← Documentation complète
│   ├── GUIDE_NOUVEAUX_SERVICES.md ← Template création services
│   └── RESUME_REORGANISATION.md ← Guide démarrage rapide
│
├── 📊 RAPPORTS & HISTORIQUE
│   ├── RAPPORT_FINAL.md         ← Réorganisation complète
│   └── REORGANIZATION_COMPLETE.md ← Détails du nettoyage
│
├── 🎯 CODE MÉTIER
│   └── backend/
│       ├── appartement-service/ ← ✅ Service autonome (PORT 8081)
│       │   ├── README.md        ← Documentation du service
│       │   ├── pom.xml          ← Maven autonome
│       │   └── src/             ← Code source
│       └── utilisateur-service/ ← ⏳ À créer (PORT 8082)
│
├── 🎨 FRONTEND
│   └── locagest-web/            ← ⏳ À créer (PORT 4200)
│
└── 📝 MÉTADONNÉES
    ├── README.md                ← (ancien - voir README_MAIN.md)
    ├── LICENSE
    ├── CONTRIBUTING.md
    └── .gitignore
```

---

## 🗺️ Chemins recommandés

### Pour un nouvel arrivant

1. Lisez **`RESUME_REORGANISATION.md`** (5 min)
2. Lancez le service (**`cd backend/appartement-service && mvn spring-boot:run`**)
3. Lisez **`README_MAIN.md`** (20 min)
4. Explorez le code du service

### Pour implémenter une feature

1. Consultez **`ARCHITECTURE.md`** pour comprendre
2. Allez à **`backend/appartement-service/README.md`** pour voir l'exemple
3. Demandez un agent IA via `CLAUDE.md`

### Pour créer un nouveau service

1. Lisez **`GUIDE_NOUVEAUX_SERVICES.md`** (checklist complète)
2. Suivez le template fourni
3. Utilisez `backend/appartement-service/` comme référence

### Pour contribuer avec IA

1. Consultez **`CLAUDE.md`** (instructions générales)
2. Explorez **`contexts/`** pour conventions
3. Demandez un agent spécialisé (`.github/agents/`)

---

## 📖 Lecture recommandée par rôle

### 🛠️ Développeur Backend
1. `RESUME_REORGANISATION.md` (démarrage rapide)
2. `README_MAIN.md` (contexte)
3. `backend/appartement-service/README.md` (exemple)
4. `contexts/backend/CLAUDE.md` (conventions)

### 🏗️ Architecte
1. `ARCHITECTURE.md` (vision globale)
2. `GUIDE_NOUVEAUX_SERVICES.md` (pattern multi-services)
3. `RAPPORT_FINAL.md` (structure décisive)

### 🤖 Utilisateur IA (Copilot, Claude)
1. `CLAUDE.md` (instructions générales)
2. `.github/agents/` (agents spécialisés)
3. `contexts/` (conventions techniques)
4. `docs/prompts/` (exemples de prompts)

### 📝 Documentaliste
1. `RAPPORT_FINAL.md` (ce qui a changé)
2. `README_MAIN.md` (mise à jour)
3. Service docs dans `backend/{service}/`

---

## 🎯 Navigation rapide

| Besoin | Lire | Puis |
|--------|------|------|
| Démarrer | `RESUME_REORGANISATION.md` | `backend/appartement-service/README.md` |
| Architecture | `ARCHITECTURE.md` | `GUIDE_NOUVEAUX_SERVICES.md` |
| Conventions | `contexts/backend/CLAUDE.md` | Code d'exemple |
| Agents IA | `.github/agents/` | `CLAUDE.md` |
| Historique | `RAPPORT_FINAL.md` | (optionnel) |
| Help | `README_MAIN.md` | Contact |

---

## ✨ Ce qui a changé

**Avant:** Code dupliqué à la racine + service = confusion  
**Après:** Starter kit IA séparé + services autonomes = clarté

Voir **`RAPPORT_FINAL.md`** pour détails complets.

---

## 🚀 Commandes utiles

```bash
# Démarrer le service Appartement
cd backend/appartement-service && mvn spring-boot:run

# Tester l'API
curl http://localhost:8081/api/v1/appartements

# Compiler
mvn clean install

# Tests
mvn test

# Créer JAR
mvn clean package -DskipTests

# Afficher cette doc
cat INDEX.md  # (ce fichier)
```

---

## 🎓 Apprentissage progressif

### Niveau 1 : Découverte (30 min)
- Lire `RESUME_REORGANISATION.md`
- Lancer le service
- Faire un curl

### Niveau 2 : Compréhension (1h)
- Lire `README_MAIN.md`
- Explorer `backend/appartement-service/src/`
- Consulter `ARCHITECTURE.md`

### Niveau 3 : Contribution (2h)
- Lire les conventions (`contexts/backend/CLAUDE.md`)
- Comprendre les agents IA (`.github/agents/`)
- Modifier le code Appartement

### Niveau 4 : Autonomie (3h+)
- Suivre `GUIDE_NOUVEAUX_SERVICES.md`
- Créer un nouveau service
- Étendre l'architecture

---

## 💡 Astuces

- **Besoin de clarté ?** → `ARCHITECTURE.md`
- **Besoin d'exemple ?** → `backend/appartement-service/`
- **Besoin d'assistance IA ?** → `CLAUDE.md` + agents
- **Perdu ?** → `README_MAIN.md` (TOC complet)
- **Historique ?** → `RAPPORT_FINAL.md`

---

## 🔗 Liens directs

| Ressource | Chemin |
|-----------|--------|
| Accueil IA | `.github/agents/` |
| Conventions | `contexts/` |
| Documentation | `docs/` |
| Service exemple | `backend/appartement-service/` |
| Prompts | `docs/prompts/` |

---

## ✅ Statut

✅ **Starter kit IA** - Préservé et intact  
✅ **Service Appartement** - Complet et opérationnel (port 8081)  
✅ **Documentation** - Complète et à jour  
✅ **Guides** - Fournis pour évolution future  
⏳ **Service Utilisateur** - À créer (port 8082)  
⏳ **Frontend** - À créer (port 4200)  

---

**Version:** 1.0.0  
**Date:** 2026-04-04  
**Status:** ✅ Production Ready

👉 **Commencez par `RESUME_REORGANISATION.md` !**

