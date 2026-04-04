# 💡 Conseils d'utilisation des assistants IA

Ce document regroupe les bonnes pratiques pour utiliser efficacement les assistants IA (Copilot, Claude, etc.) dans le projet.

---

## Copilot

### Contexte

- `@workspace` : analyse l’ensemble du projet
- `@file` : se concentre sur le fichier courant
- `@selection` : analyse uniquement la sélection

---

### Bonnes pratiques

- Donner le **contexte métier et technique**
- Décrire le **problème observé avant la solution**
- Préciser les **contraintes du projet**
- Demander une explication **pas à pas** si nécessaire

---

### Exemples

```txt
@workspace explique pourquoi mon DeleteDto n'est pas à jour
```

```txt
@file refactor ce service en respectant les bonnes pratiques Spring Boot
```

```txt
@selection propose une version plus claire de cette méthode
```

---

## Claude / Agents

### Rôle de `CLAUDE.md`

- Définit le comportement global de l’IA
- Peut être décliné par dossier selon le contexte
- Cadre le style de réponse, les contraintes et les priorités

---

### Bonnes pratiques

- Donner un **rôle clair**
- Définir la **mission**
- Préciser les **contraintes**
- Indiquer le **format de réponse attendu**
- Mentionner les fichiers ou dossiers à analyser

---

### Exemple

```txt
Tu es un expert Spring Boot.
Analyse ce service, explique le problème et propose un correctif avec code.
Respecte les conventions du projet.
```

---

## Debug avec l'IA

### Informations à fournir

- Le message d’erreur exact
- Les logs utiles
- Le code concerné
- Le comportement attendu
- Le comportement observé

---

### Exemple

```txt
@workspace analyse pourquoi mon endpoint retourne 404.
Voici le contrôleur, la route attendue et les logs de démarrage.
```

---

## Raccourcis utiles

- `@workspace` : vue globale du projet
- `@file` : analyse ciblée
- `@selection` : analyse locale

---

## ✅ Récapitulatif

- Donner du **contexte**
- Être **précis**
- Fournir **code + logs + erreurs**
- Demander des **exemples** ou une **explication pas à pas**