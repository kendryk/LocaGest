# Utilisation des agents

Guide pratique pour utiliser les agents spécialisés du socle IA.

## Vue d'ensemble

Le socle repose sur deux briques différentes :

- les **agents**, qui définissent un rôle de conversation
- les **contextes**, qui apportent des règles métier ou techniques supplémentaires

En pratique :
- on choisit d'abord le bon agent
- puis on active le ou les contextes utiles au sujet

## Les 6 agents

| Agent | Rôle | Quand l'utiliser |
|---|---|---|
| `assistant-dev` | Point d'entrée, orientation | Besoin flou, cadrage initial |
| `architecte-projet` | Architecture, structuration | Nouveau projet, évolution d'architecture |
| `analyste-metier` | Analyse métier → technique | Besoin fonctionnel, tickets, impacts |
| `reviewer-code` | Review, refactoring, risques | Relecture de code, détection de risques |
| `debugger-technique` | Diagnostic d'erreurs | Stacktraces, erreurs de build, logs |
| `redacteur-doc` | Documentation technique | README, MR/PR, documentation API |

> Ton projet peut ajouter des agents spécialisés supplémentaires
> (ex : `expert-spring`, `expert-securite`). Voir `examples/` pour des exemples.

---

### `assistant-dev`

À utiliser pour :
- clarifier un besoin flou
- cadrer un premier échange
- savoir quel agent mobiliser ensuite

Exemple de demande :

```text
Je dois ajouter une authentification 2FA à mon application Spring Boot + Angular.
Par où commencer ?
```

Réponse attendue :
- reformulation du besoin
- quelques questions ciblées
- première piste concrète
- redirection éventuelle vers un autre agent

### `architecte-projet`

À utiliser pour :
- structurer un nouveau projet
- faire évoluer une architecture existante
- choisir une organisation de dossiers ou de couches
- arbitrer entre plusieurs options techniques

Exemple de demande :

```text
Je dois créer une application de gestion de tâches avec Spring Boot + Angular.
Je veux une architecture simple, maintenable et adaptée à une petite équipe.
```

### `analyste-metier`

À utiliser pour :
- analyser un besoin métier exprimé en langage naturel
- identifier les impacts backend / frontend / base de données
- produire des tickets Jira prêts à créer
- rédiger des synthèses pour MR ou Teams
- proposer des cas de tests fonctionnels

Exemple de demande :

```text
On veut pouvoir créer des commandes de type "abonnement".
Aujourd'hui seuls les types "ponctuel" et "récurrent" existent.
Analyse les impacts et propose un découpage en tickets.
```

### `reviewer-code`

À utiliser pour :
- faire une review de code
- détecter des risques ou régressions
- préparer un refactoring

Exemple de demande :

```text
Fais une review de ce service.
Je veux les risques principaux, les améliorations possibles et les tests manquants.
```

### `debugger-technique`

À utiliser pour :
- analyser une stacktrace ou un message d'erreur
- diagnostiquer un échec de build (Gradle, npm, Maven)
- résoudre une erreur Spring Boot, Angular, Flyway ou autre
- comprendre un code HTTP inattendu (400, 401, 403, 500)

Exemple de demande :

```text
J'ai cette erreur au démarrage de mon application :
BeanCreationException: Error creating bean with name 'orderService':
Unsatisfied dependency expressed through constructor parameter 0

Aide-moi à trouver la cause racine.
```

### `redacteur-doc`

À utiliser pour :
- rédiger une description de MR ou PR
- écrire un README
- documenter une API
- expliquer une architecture ou une fonctionnalité

Exemple de demande :

```text
Rédige une description de MR pour cette implémentation d'authentification 2FA.
Je veux une version claire, courte et prête à coller dans GitHub.
```

---

## Les contextes

### `contexts/backend`

À utiliser quand la demande porte sur :
- le code backend (Java, Python, Node.js…)
- les services, controllers, repositories
- les tests backend

### `contexts/frontend`

À utiliser quand la demande porte sur :
- le code frontend (Angular, React, Vue…)
- les composants, formulaires, services HTTP
- le typage TypeScript, RxJS

### `contexts/infra`

À utiliser quand la demande porte sur :
- Docker, Docker Compose
- GitHub Actions, CI/CD
- variables d'environnement
- déploiement et exploitation

### `contexts/metier` (optionnel)

À créer si le projet a un domaine métier riche. À utiliser quand la demande porte sur :
- le glossaire métier
- les règles fonctionnelles
- les mécanismes inter-modules
- les permissions et profils

---

## Workflow conseillé

### Cas d'usage : nouvelle fonctionnalité

1. **Clarifier** avec `assistant-dev`

```text
Je dois ajouter des notifications email quand quelqu'un commente une tâche.
```

2. **Analyser le métier** avec `analyste-metier`

```text
Analyse les impacts de cette fonctionnalité sur les modules existants.
Propose un découpage en tickets Jira.
```

3. **Structurer** avec `architecte-projet`

```text
Comment organiser ça dans mon application sans complexifier l'existant ?
```

4. **Implémenter** avec le bon contexte

```text
Dans le contexte backend, propose la structure du service de notification.
```

5. **Déboguer** avec `debugger-technique` (si erreurs)

```text
J'ai cette erreur au démarrage : [stacktrace]
```

6. **Vérifier** avec `reviewer-code`

```text
Fais une review de cette implémentation.
Je veux les risques principaux et les tests manquants.
```

7. **Documenter** avec `redacteur-doc`

```text
Rédige la MR associée en mettant l'accent sur l'impact et les points de vigilance.
```

---

## Prompts réutilisables

Des prompts prêts à l'emploi sont disponibles dans le dossier `docs/prompts/` :

| Prompt | Scénario |
|---|---|
| `creer-module-backend.md` | Nouveau module backend complet |
| `analyser-bug-production.md` | Debug d'une erreur en production |
| `rediger-mr.md` | Description de MR/PR |

Des prompts spécifiques à un projet sont disponibles dans `examples/`.

---

## Bonnes pratiques

- donne toujours le contexte technique
- colle le code ou l'erreur exacte quand tu veux une réponse sérieuse
- mobilise plusieurs agents si le sujet l'exige
- adapte les contextes à ton projet réel
- reste cohérent avec l'architecture existante

## À éviter

- demandes vagues comme `ça marche pas`
- tout demander au même agent
- utiliser un contexte technique non pertinent
- copier les exemples sans les adapter à la stack du projet
- utiliser `debugger-technique` pour une review (et vice-versa)

## Ressources associées

- [README du socle](../README.md)
- [Workflow Git](./workflow-git.md)
- [Contexte global](../CLAUDE.md)
- [Prompts réutilisables](./prompts/README.md)
