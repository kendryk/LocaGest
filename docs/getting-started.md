# Guide de démarrage

Comment mettre en place le socle IA sur ton projet.

## Prérequis

- Un IDE compatible GitHub Copilot (VS Code, JetBrains)
- GitHub Copilot activé avec support des agents (`@agent`)
- Un projet existant ou un nouveau projet à structurer

## Étape 1 — Cloner ou copier le template

### Option A : nouveau projet

```bash
git clone [URL_DU_DEPOT] mon-projet
cd mon-projet
# ⚠️ Supprime l'historique Git du template
rm -rf .git
git init
```

### Option B : projet existant

Copier les fichiers utiles dans ton dépôt :

```bash
cp CLAUDE.md /chemin/vers/mon-projet/
cp -r .github/agents/ /chemin/vers/mon-projet/.github/agents/
cp -r contexts/ /chemin/vers/mon-projet/contexts/
cp -r docs/ /chemin/vers/mon-projet/docs/
```

## Étape 2 — Personnaliser le `CLAUDE.md`

Ouvrir `CLAUDE.md` et remplacer tous les `[PLACEHOLDERS]` :

1. `[NOM_PROJET]` → le nom de ton projet
2. `[TYPE D'APPLICATION]` → description courte
3. `[COMPOSANT X]` → les composants de ton application
4. La table des technologies → ta stack réelle
5. Les structures d'architecture → tes dossiers réels

Supprimer le bandeau `⚠️ Ce fichier est un template` une fois personnalisé.

## Étape 3 — Adapter les contextes

### Garder ou supprimer

- `contexts/backend/` → garder si tu as un backend
- `contexts/frontend/` → garder si tu as un frontend
- `contexts/infra/` → garder si tu fais du DevOps

### Personnaliser

Ouvrir chaque contexte conservé et adapter la section
« Stack de référence » à ta stack réelle.

### Ajouter un contexte métier (optionnel)

Si ton projet a un domaine métier riche, créer `contexts/metier/CLAUDE.md` avec :

```markdown
# Contexte Métier — [NOM_PROJET]

## Glossaire

| Terme | Définition |
|---|---|
| … | … |

## Règles métier principales

- …
- …

## Modules et interactions

- …
```

Voir `examples/projet/contexts/metier/CLAUDE.md` pour un exemple complet.

## Étape 4 — Ajouter des agents spécialisés (optionnel)

Les 6 agents de base couvrent la plupart des besoins.

Si ton projet a des patterns spécifiques récurrents, ajouter un agent dans
`.github/agents/` :

```markdown
---
name: nom-agent
description: Description courte
---

# Nom de l'agent

## Mission
[Ce que fait l'agent]

## Patterns du projet
[Les patterns spécifiques à vérifier/implémenter]

## Ce qu'il faut éviter
[Les anti-patterns]
```

Voir `examples/projet/agents/` pour des exemples d'agents spécialisés.

## Étape 5 — Tester

1. Ouvrir le projet dans l'IDE
2. Lancer une conversation avec `@assistant-dev`
3. Poser une question simple liée au projet
4. Vérifier que les conventions du `CLAUDE.md` sont respectées dans la réponse
5. Tester un autre agent (`@reviewer-code`, `@debugger-technique`…)

## Étape 6 — Itérer

- Affiner les contextes après quelques utilisations
- Ajouter des prompts réutilisables dans `docs/prompts/`
- Partager le socle avec l'équipe
- Faire évoluer les agents en fonction des retours

## Résumé

| Étape | Action | Temps estimé |
|---|---|---|
| 1 | Cloner ou copier | 2 min |
| 2 | Personnaliser `CLAUDE.md` | 15 min |
| 3 | Adapter les contextes | 10 min |
| 4 | Agents spécialisés (optionnel) | 15 min |
| 5 | Tester | 5 min |
| **Total** | | **~45 min** |

