# Prompt : Créer un nouveau module backend

## Quand l'utiliser

Quand on démarre un nouveau module ou domaine métier dans le backend.

## Agent recommandé

`architecte-projet` avec contexte `backend`

## Prompt à copier

> Je dois créer un nouveau module backend pour le domaine **[NOM_DOMAINE]**.
>
> Contexte :
> - Ce module gère [DESCRIPTION COURTE DU DOMAINE]
> - Stack : [LANGAGE / FRAMEWORK — ex: Java 17 / Spring Boot]
> - Build : [OUTIL BUILD — ex: Maven, Gradle]
> - Il référence des données d'autres modules : [OUI/NON, lesquelles]
> - Permissions nécessaires : [LISTE DES PERMISSIONS ou N/A]
> - Entités principales : [LISTE DES ENTITÉS ET ATTRIBUTS CLÉS]
>
> Génère :
> 1. La structure complète des dossiers et packages
> 2. Les fichiers de build (pom.xml ou build.gradle)
> 3. Un exemple d'entity + repository + service + controller
> 4. Le DTO associé et le converter
> 5. La migration de schéma initiale
> 6. Un test unitaire du service

## Informations à préparer avant

- Nom du domaine métier
- Entités principales et leurs attributs
- Relations avec les autres modules
- Permissions requises (si applicable)
- Port ou configuration spécifique (si applicable)

## Exemple rempli

> Je dois créer un nouveau module backend pour le domaine **orders**.
>
> Contexte :
> - Ce module gère les commandes clients
> - Stack : Java 17 / Spring Boot 3
> - Build : Maven
> - Il référence des données d'autres modules : OUI (users, products)
> - Permissions nécessaires : ROLE_ORDER_READ, ROLE_ORDER_WRITE
> - Entités principales : Order (id, userId, status, totalAmount, createdAt)
>
> Génère la structure complète.

