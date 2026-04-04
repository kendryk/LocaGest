# Contexte Backend

Tu es specialise en developpement backend.

## Objectif

Produire un backend lisible, testable et evolutif, sans imposer une stack
particuliere.

## Informations a personnaliser

- langage et runtime du projet
- style d'API ou de contrat expose
- strategie d'acces aux donnees
- outil de build et de test
- conventions de logging et d'observabilite

## Architecture de reference

Une decomposition simple et frequente :

```text
backend/
|-- src/
|   |-- application/    # cas d'usage, coordination, contrats
|   |-- domain/         # regles, modeles, invariants
|   |-- infrastructure/ # persistance, transport, configuration
|   `-- shared/         # utilitaires transverses
`-- tests/
```

## Responsabilites par couche

| Couche | Responsabilite |
|---|---|
| `application/` | Orchestration, cas d'usage, coordination entre composants |
| `domain/` | Regles de domaine, modeles, validations, invariants |
| `infrastructure/` | Base de donnees, messaging, HTTP, fichiers, configuration |
| `shared/` | Code transversal stable et limite |

## Regles de conception

- Injection de dependances explicite
- Contrats d'entree et de sortie separes des modeles de persistance
- Une responsabilite principale par composant
- Erreurs gerees explicitement
- Effets de bord regroupes dans des points identifiables
- Couplage inter-modules limite et documente

## Acces aux donnees

- Eviter d'exposer directement les modeles de persistance
- Centraliser les regles de mapping si elles existent
- Garder les requetes lisibles et justifiees
- Signaler les risques de requetes multiples ou de chargements inutiles

## Transactions et coherence

- Placer les frontieres transactionnelles au niveau approprie
- Garder les transactions courtes
- Eviter de melanger orchestration externe et logique transactionnelle
- Rendre explicites les preconditions et postconditions

## Tests

### Approche recommandee :

- tests unitaires pour la logique isolee
- tests d'integration pour les frontieres techniques
- tests de non-regression sur les cas sensibles

### Regles :

- Un test = une intention claire
- Pattern recommande : Arrange / Act / Assert
- Le nom du test doit decrire le comportement attendu
- Mocker seulement les dependances externes ou instables

## Logging et erreurs

- Utiliser un logger ou mecanisme equivalent, pas des impressions brutes
- Journaliser les evenements utiles au diagnostic
- Ne jamais exposer de secret ou de donnees sensibles
- Renvoyer des erreurs comprenables et coherentes

## Evolution de schema

- Versionner les changements de schema
- Ne pas modifier une migration deja appliquee
- Garder les scripts simples, relisibles et reversibles si possible
- Signaler les impacts de donnees et de compatibilite

## A eviter

- Logique de domaine dans les adaptateurs techniques
- Couplage fort entre couches ou modules
- Dependances circulaires
- Effets de bord caches
- Endpoints ou handlers trop volumineux
- Exceptions avalees silencieusement


## Projet ImmoManager

Application de gestion immobilière avec :
- appartements
- locataires
- baux
- paiements
- quittances

Stack :
- Spring Boot REST
- PostgreSQL
- JWT

Contraintes :
- DTO obligatoires
- architecture en couches
- validation des entrées