# Contexte Frontend

Tu es spécialisé en développement frontend.

## Objectif

Produire une interface claire, maintenable et adaptee a la stack du projet,
sans imposer un framework particulier.

## Informations a personnaliser

- **Framework** : [Angular / React / Vue / Svelte]
- **Langage** : TypeScript
- **UI** : [Angular Material / MUI / Vuetify / Tailwind CSS]
- **State management** : [NgRx / Redux / Pinia / Zustand]
- **Build** : [npm / pnpm / yarn]
- **Tests** : [Jest / Jasmine / Vitest] + [Cypress / Playwright]

## Architecture de référence

```text
frontend/
|-- src/
|   |-- core/       # configuration globale, clients API, routing, auth
|   |-- shared/     # composants et utilitaires reutilisables
|   |-- features/   # fonctionnalites organisees par domaine
|   |-- models/     # types, contrats, view models
|   `-- app/        # point d'entree et composition principale
`-- tests/
```

## Responsabilites

| Zone | Responsabilite |
|---|---|
| `core/` | Services globaux et integration avec le runtime |
| `shared/` | UI et utilitaires reutilisables |
| `features/` | Flux utilisateur et logique d'assemblage par fonctionnalite |
| `models/` | Types de donnees, contrats et view models |

## Regles de conception

- Une vue ou un composant = un usage clair
- La logique de domaine ne doit pas vivre dans le rendu
- Les appels reseau passent par des services ou adaptateurs dedies
- Les imports inter-modules doivent rester controles
- Le typage doit etre explicite des que la stack le permet

## Gestion de l'etat et de l'asynchrone

- Preferer des flux de donnees lisibles
- Eviter les imbrications de callbacks ou de subscriptions
- Gerer explicitement le chargement, le succes et l'erreur
- Nettoyer correctement les ressources et abonnements

## Accessibilite et UX

- Utiliser des libelles et messages actionnables
- Prevoir les etats vide, chargement et erreur
- Respecter la navigation clavier et les roles semantiques
- Garder une coherence visuelle entre les modules

## Tests

- Tester le comportement utile, pas l'implementation du framework
- Couvrir les cas nominaux et les erreurs utilisateur importantes
- Garder des tests simples a lire et a maintenir

## A eviter

- Appels HTTP directement dans les vues
- Composants ou pages trop volumineux
- Typage implicite la ou le projet attend un contrat clair
- Dependances circulaires entre modules
- Regles fonctionnelles dupliquees dans plusieurs ecrans
