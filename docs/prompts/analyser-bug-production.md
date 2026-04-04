# Prompt : Analyser un bug en production

## Quand l'utiliser

Quand une erreur survient en production ou staging et qu'il faut diagnostiquer rapidement.

## Agent recommandé

`debugger-technique` avec contexte `backend` ou `infra` selon la nature de l'erreur

## Prompt à copier

> J'ai une erreur en **[ENVIRONNEMENT : local / staging / production]**.
>
> **Message d'erreur / stacktrace :**
> ```
> [COLLER ICI LA STACKTRACE OU LE MESSAGE EXACT]
> ```
>
> **Contexte :**
> - Module concerné : [NOM_MODULE]
> - Action déclenchante : [CE QUE L'UTILISATEUR FAISAIT]
> - Depuis quand : [NOUVEAU / RÉCURRENT / DEPUIS TEL DÉPLOIEMENT]
> - Changements récents : [DERNIERS COMMITS OU MISES À JOUR]
>
> Aide-moi à identifier la cause racine et propose une correction minimale.

## Informations à préparer avant

- La stacktrace complète (pas juste le message)
- Le contexte d'exécution (quel micro-service, quel écran)
- Les logs pertinents (filtrer sur le timestamp de l'erreur)
- Les derniers changements déployés
- Si le problème est reproductible ou intermittent

## Conseil

Plus la stacktrace est complète, plus le diagnostic sera rapide.
Copier-coller le log brut est toujours mieux qu'un résumé.

