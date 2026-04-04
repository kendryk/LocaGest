# AI Project Template

Template pour structurer l’usage de l’IA dans un projet logiciel de manière cohérente, réutilisable et maintenable.

> Penser le système d’instructions comme une architecture légère, pas comme une accumulation de prompts isolés.

---

## 🎯 Objectif

Ce dépôt fournit un cadre pour :

* structurer l’utilisation d’assistants IA (Copilot, Claude, ChatGPT)
* améliorer la cohérence des réponses
* capitaliser les bonnes pratiques
* accélérer le développement sans perdre le contrôle technique

⚠️ Ce dépôt n’est pas une application exécutable, mais un **socle d’organisation**.

---

## 🧱 Structure du dépôt

```text
ai-project-template/
|-- CLAUDE.md
|-- .github/
|   `-- agents/
|-- contexts/
|-- docs/
|-- examples/
```

### Détail

* **agents/** → rôles spécialisés (dev, archi, debug…)
* **contexts/** → règles techniques et conventions
* **docs/prompts/** → prompts réutilisables
* **examples/** → cas concrets

---

## 🤖 Agents disponibles

| Agent              | Rôle           | Usage             |
| ------------------ | -------------- | ----------------- |
| assistant-dev      | Point d’entrée | Cadrage initial   |
| architecte-projet  | Architecture   | Design technique  |
| analyste-metier    | Fonctionnel    | Traduction métier |
| reviewer-code      | Qualité        | Review / risques  |
| debugger-technique | Debug          | Logs / erreurs    |
| redacteur-doc      | Documentation  | MR / README       |

---

## 🧠 Contextes

Les contextes enrichissent les réponses avec des règles spécifiques :

* backend → Spring / API / BDD
* frontend → Angular / UI
* infra → Docker / CI / déploiement
* domain → logique métier

👉 Ils évitent de répéter les mêmes instructions à chaque prompt.

---

## 🚀 Exemple concret (ImmoManager)

Ce template peut être utilisé pour un projet réel comme :

**ImmoManager** – application de gestion d’appartements

Fonctionnalités :

* gestion des appartements
* locataires
* baux
* paiements
* quittances

Stack :

* Spring Boot
* Angular
* PostgreSQL
* JWT
* Docker

👉 Dans ce cas :

* le contexte backend décrit Spring
* le contexte domain décrit bail / locataire / quittance
* les agents pilotent les tâches

---

## ⚡ Utilisation

### Cas 1 – Nouveau projet

```bash
git clone <repo> mon-projet
cd mon-projet
```

Puis :

1. Adapter `CLAUDE.md`
2. Garder uniquement les contextes utiles
3. Ajouter ton contexte métier
4. Adapter les agents si besoin

---

### Cas 2 – Projet existant

Copier uniquement :

* `CLAUDE.md`
* `.github/agents/`
* contextes utiles
* prompts utiles

---

## 🔄 Workflow recommandé

Pour une feature :

1. assistant-dev → cadrage
2. analyste-metier → analyse
3. architecte-projet → design
4. contexte adapté → implémentation
5. debugger-technique → debug
6. reviewer-code → review
7. redacteur-doc → documentation

---

## 🧪 Prompts

Les prompts sont dans :

```text
docs/prompts/
```

Exemples :

* créer un module backend
* analyser un bug
* rédiger une MR

👉 Ajouter tes propres prompts métier (ex : gestion locataire)

---

## ✅ Bonnes pratiques

* Toujours donner du contexte
* Fournir du code réel
* Garder les agents simples
* Versionner les prompts utiles
* Adapter les contextes au projet

---

## ⚠️ Limites

* Pas d’exécution directe
* Nécessite adaptation
* Ne remplace pas l’expertise technique

---

## 📚 Documentation

* docs/getting-started.md
* docs/utilisation-agents.md
* docs/workflow-git.md
* docs/prompts/README.md

---

## 🤝 Contribution

* Ouvrir une issue avant gros changement
* PR ciblées
* Markdown propre et relu

---

## 📄 Licence

MIT
