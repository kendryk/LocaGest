# Workflow Git

Workflow Git recommandé pour les projets qui utilisent ce socle.

Ce document propose une base. Il n'impose pas une seule stratégie.

## Choisir une stratégie simple

### Option A : `main` + branches courtes

À privilégier si :
- tu travailles seul
- l'équipe est petite
- la cadence est rapide

### Option B : `main` + `develop`

À utiliser si :
- l'équipe veut une branche d'intégration dédiée
- les cycles de livraison sont plus formalisés
- la CI et la review sont déjà bien installées

Ne force pas `develop` si cela n'apporte rien.

## Convention de branches

- `feature/` : nouvelle fonctionnalité
- `fix/` : correction de bug
- `refactor/` : refactoring sans changement fonctionnel
- `docs/` : documentation
- `chore/` : maintenance
- `ci/` : pipeline et automatisation

Exemples :

- `feature/user-authentication`
- `fix/login-validation`
- `refactor/split-user-service`
- `docs/api-authentication`

## Workflow conseillé

### 1. Créer une branche de travail

```bash
git checkout main
git pull origin main
git checkout -b feature/ma-fonctionnalite
```

Si ton équipe utilise `develop`, pars de `develop` au lieu de `main`.

### 2. Commits réguliers et lisibles

Format recommandé :

```text
type(scope): description
```

Types courants :
- `feat`
- `fix`
- `refactor`
- `docs`
- `test`
- `chore`
- `ci`

Exemples :

```text
feat(auth): ajouter la validation JWT
fix(user): corriger la validation email
refactor(service): découper le service utilisateur
docs(readme): clarifier le démarrage
```

### 3. Se resynchroniser avant la PR

```bash
git fetch origin
git rebase origin/main
```

Si ton équipe préfère les merges explicites, utilise `git merge origin/main`.

### 4. Ouvrir une PR

La PR doit expliquer :
- le contexte
- les changements
- l'impact
- les tests effectués
- les points d'attention

### 5. Faire relire

Règles recommandées selon le contexte :

- solo : au moins une auto-relecture sérieuse avant merge
- petite équipe : 1 reviewer
- équipe structurée : 2 reviewers si le changement est sensible

### 6. Merger proprement

Le squash merge est souvent un bon défaut si la branche contient de nombreux commits intermédiaires.

## Avant de merger

- les tests passent
- la CI est verte
- le code est lisible
- les logs de debug ont été retirés
- aucun secret n'est commité
- la branche est à jour

## Hotfix

En cas de correction urgente :

```bash
git checkout main
git pull origin main
git checkout -b fix/hotfix-description
```

Puis :
- corrige
- teste
- ouvre une PR vers la branche de référence
- reporte la correction sur les autres branches utiles si nécessaire

## Commandes à privilégier

- `git revert <commit>` pour annuler un commit déjà partagé
- `git restore <fichier>` pour annuler une modification locale ciblée
- `git reflog` pour retrouver un état perdu

## Commandes dangereuses

`git reset --hard` est destructif.

Ne l'utilise que si :
- tu comprends exactement l'impact
- tu es certain de ne pas supprimer un travail utile
- la situation ne peut pas être réglée par `revert`, `restore` ou un nouveau commit

## Protection recommandée

Sur une branche critique comme `main` :
- push direct limité
- PR obligatoire si possible
- CI obligatoire
- branche à jour avant merge

## Règles simples à retenir

1. branches courtes
2. commits lisibles
3. PR claires
4. annulation par `revert` avant d'envisager une commande destructive
