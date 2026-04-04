# Contribution

Merci de contribuer à ce socle IA.

## Comment contribuer

### Signaler un problème

Ouvre une issue en décrivant :
- le contexte
- le comportement observé
- le comportement attendu

### Proposer un changement

1. Fork le dépôt
2. Crée une branche : `feature/ma-contribution` ou `fix/mon-correctif`
3. Fais tes modifications
4. Vérifie que le Markdown est propre et lisible
5. Ouvre une Pull Request avec une description claire

### Règles

- Une PR = un sujet
- Le Markdown doit rendre correctement sur GitHub
- Les agents doivent rester cohérents avec le format existant (frontmatter + sections)
- Les placeholders utilisent le format `[NOM_EN_MAJUSCULES]`
- Les exemples de code utilisent des noms neutres (`com.example`, `OrderService`, etc.)
- Pas de contenu spécifique à un projet dans le template de base
  (utiliser `examples/` pour les cas concrets)

## Structure à respecter

### Agents

Fichier : `.github/agents/nom-agent.agent.md`

```markdown
---
name: nom-agent
description: Description courte de l'agent
---

# Nom de l'agent

## Mission
## Posture
## Format de réponse
## Exemples
## Ce qu'il faut éviter
```

### Contextes

Fichier : `contexts/nom/CLAUDE.md`

Structure libre, mais doit contenir au minimum :
- la stack de référence
- les principes et conventions
- les exemples de code
- les anti-patterns

### Prompts

Fichier : `docs/prompts/nom-prompt.md`

```markdown
# Prompt : Titre

## Quand l'utiliser
## Agent recommandé
## Prompt à copier
## Informations à préparer avant
```

