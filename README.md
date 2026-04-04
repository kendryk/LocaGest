# ai-project-template

Template pour structurer l'usage de l'IA dans un projet logiciel de maniere
generique, reusable et maintenable.

> Pense le systeme d'instructions comme une architecture legere, pas comme une
> accumulation de prompts isoles.

## Pourquoi ce template

Sans structure, les assistants IA repondent souvent de facon incoherente.

Ce depot fournit un socle pour :

- definir des roles clairs via des agents
- activer les bons contextes selon le sujet
- centraliser les conventions utiles
- produire des reponses plus coherentes dans la duree

Ce depot n'est pas une application executable. C'est un cadre de travail.

## Structure du depot

```text
ai-project-template/
|-- CLAUDE.md
|-- .github/
|   `-- agents/
|       |-- assistant-dev.agent.md
|       |-- architecte-projet.agent.md
|       |-- analyste-metier.agent.md
|       |-- reviewer-code.agent.md
|       |-- debugger-technique.agent.md
|       `-- redacteur-doc.agent.md
|-- contexts/
|   |-- backend/CLAUDE.md
|   |-- frontend/CLAUDE.md
|   |-- infra/CLAUDE.md
|   `-- domain/CLAUDE.md
|-- docs/
|   |-- getting-started.md
|   |-- utilisation-agents.md
|   |-- workflow-git.md
|   `-- prompts/
|       |-- README.md
|       |-- analyser-bug-production.md
|       |-- creer-module-backend.md
|       `-- rediger-mr.md
`-- examples/
    `-- complex-domain-example/
```

## Agents fournis

| Agent | Role | Quand l'utiliser |
|---|---|---|
| `assistant-dev` | Point d'entree, cadrage | Besoin flou, premiere orientation |
| `architecte-projet` | Architecture et structure | Nouveau projet, evolution d'architecture |
| `analyste-metier` | Analyse fonctionnelle vers technique | Impacts, tickets, cas de test |
| `reviewer-code` | Review et risques | Relecture de code, regressions, tests manquants |
| `debugger-technique` | Diagnostic cible | Logs, build, runtime, configuration |
| `redacteur-doc` | Documentation | README, PR/MR, docs API, syntheses |

## Contextes techniques et de domaine

Les contextes ne remplacent pas les agents. Ils completent les instructions avec
des conventions ciblees :

- `contexts/backend/CLAUDE.md` : architecture serveur, contrats, tests, donnees
- `contexts/frontend/CLAUDE.md` : organisation UI, etat, services, accessibilite
- `contexts/infra/CLAUDE.md` : containerisation, CI/CD, deploiement, observabilite
- `contexts/domain/CLAUDE.md` : vocabulaire, regles de domaine, interactions

## Trois usages possibles

### 1. Utiliser le depot comme template complet

Tu clones le depot puis tu adaptes le socle a ton projet.

### 2. Integrer seulement une partie dans un projet existant

Tu copies uniquement :

- `CLAUDE.md`
- `.github/agents/`
- les contextes utiles
- les documents et prompts utiles

### 3. T'en inspirer comme bibliotheque de conventions

Tu réutilises les formulations, la structure et les exemples pedagogiques.

## Demarrage rapide

### Personnalisation minimale

Apres clonage, adapte au minimum :

- `CLAUDE.md`
- les contextes utiles a ton projet
- les agents specialises si necessaire
- les prompts recurrents

### Nouveau projet

```bash
git clone [URL_DU_DEPOT] mon-projet
cd mon-projet
```

Ensuite :

1. Personnaliser `CLAUDE.md`
2. Garder uniquement les contextes utiles
3. Completer `contexts/domain/CLAUDE.md` si ton projet a un vocabulaire fonctionnel riche
4. Ajouter des agents specialises si le projet le justifie
5. Lire `docs/getting-started.md`

### Projet existant

Copie seulement les elements necessaires dans le depot cible :

- le `CLAUDE.md` global adapte
- les agents utiles dans `.github/agents/`
- les contextes pertinents
- les prompts recurrents

## Workflow recommande

Pour une nouvelle fonctionnalite :

1. `assistant-dev` pour cadrer
2. `analyste-metier` pour les impacts
3. `architecte-projet` pour la structure
4. le contexte adapte pour l'implementation
5. `debugger-technique` en cas d'erreur
6. `reviewer-code` pour la relecture
7. `redacteur-doc` pour documenter

## Exemples

Le dossier `examples/` contient des exemples pedagogiques complets.

- `examples/complex-domain-example/` : exemple anonymise et simplifie pour un
  projet multi-modules avec backend, frontend, permissions et interactions de
  domaine

Ces exemples sont volontairement generiques. Ils servent de base d'adaptation,
pas de modele à recopier tel quel.

## Bonnes pratiques

- Donner le contexte utile avec la demande
- Montrer le code, la configuration ou l'erreur exacte quand c'est possible
- Garder les agents simples et bien separes
- Faire vivre les contextes au meme rythme que le projet
- Eviter d'imposer une stack dans les documents globaux

## Limites

- Le depot ne fournit pas d'application executable
- Les exemples restent des supports pedagogiques
- Les choix finaux dependent du projet, de l'equipe et des contraintes reelles

## Documentation

- [Guide de demarrage](docs/getting-started.md)
- [Utilisation des agents](docs/utilisation-agents.md)
- [Workflow Git](docs/workflow-git.md)
- [Prompts reutilisables](docs/prompts/README.md)

## Contribution

Les contributions sont les bienvenues.

Pour les changements importants :

- ouvrir une issue ou une discussion d'abord
- garder les PR ciblees
- relire les snippets et le rendu Markdown avant envoi

## Licence

Ce projet est distribue sous licence MIT. Voir `LICENSE`.
