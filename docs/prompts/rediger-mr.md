# Prompt : Rédiger une description de MR/PR

## Quand l'utiliser

Quand on doit rédiger la description d'une Merge Request prête à publier.

## Agent recommandé

`redacteur-doc` (pas de contexte technique spécifique nécessaire)

## Prompt à copier

> Rédige une description de MR pour le changement suivant :
>
> **Titre :** [TITRE COURT DE LA MR]
>
> **Ce qui a été fait :**
> - [CHANGEMENT 1]
> - [CHANGEMENT 2]
> - [CHANGEMENT 3]
>
> **Module(s) touché(s) :** [LISTE DES MODULES]
>
> **Ticket Jira :** [NUMÉRO DU TICKET OU "aucun"]
>
> **Tests effectués :**
> - [TEST 1]
> - [TEST 2]
>
> **Points d'attention :**
> - [RISQUE OU LIMITE CONNUE]
>
> Génère une description claire, concise et prête à coller dans GitHub.

## Informations à préparer avant

- Résumé des changements effectués
- Modules impactés (backend, frontend, les deux)
- Référence Jira si elle existe
- Tests réalisés (manuels ou automatisés)
- Risques connus ou limites de l'implémentation
- Migration Flyway incluse (oui/non)

