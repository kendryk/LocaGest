# Prompts réutilisables

Catalogue de prompts prêts à l'emploi pour les scénarios récurrents.

## Mode d'emploi

1. Identifier le scénario dans la liste ci-dessous
2. Ouvrir le fichier correspondant
3. Copier le prompt, remplacer les `[PLACEHOLDERS]`
4. Choisir l'agent et le contexte recommandés
5. Coller dans la conversation

## Catalogue

| Fichier | Scénario | Agent | Contexte |
|---|---|---|---|
| `creer-module-backend.md` | Nouveau module backend | `architecte-projet` | `backend` |
| `analyser-bug-production.md` | Debug d'une erreur en production | `debugger-technique` | `backend` ou `infra` |
| `rediger-mr.md` | Description de MR/PR | `redacteur-doc` | — |

### Prompts spécifiques par projet

Des prompts spécialisés sont disponibles dans le dossier `examples/` de chaque projet.
Par exemple : `examples/projet/prompts/`.

## Principes

- Un prompt = un scénario concret
- Toujours indiquer l'agent et le contexte à utiliser
- Utiliser des placeholders clairs : `[NOM]`, `[DESCRIPTION]`
- Maintenir ce catalogue à jour quand un nouveau scénario récurrent émerge
