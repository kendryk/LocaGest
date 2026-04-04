# Brique Appartement - Exemples d'utilisation

## Base

- **URL base:** `http://localhost:8080/api/v1/appartements`
- **Content-Type:** `application/json`
- **Charset:** UTF-8

---

## 1. Créer un appartement

### Requête

```http
POST /api/v1/appartements
Content-Type: application/json

{
  "reference": "APT-PARIS-001",
  "adresse": "123 Rue de Rivoli",
  "codePostal": "75004",
  "ville": "Paris",
  "surfaceHabitable": 50.50,
  "nombrePieces": 1,
  "loyerMensuel": 850.00,
  "chargesMensuelles": 120.00,
  "typeBien": "T1",
  "statut": "LIBRE",
  "anneeConstruction": 2015,
  "observations": "Bien rénové, vue sur la Seine",
  "proprietaireId": 42
}
```

### Réponse (201 Created)

```json
{
  "id": 1,
  "reference": "APT-PARIS-001",
  "adresse": "123 Rue de Rivoli",
  "codePostal": "75004",
  "ville": "Paris",
  "surfaceHabitable": 50.50,
  "nombrePieces": 1,
  "loyerMensuel": 850.00,
  "chargesMensuelles": 120.00,
  "typeBien": "T1",
  "statut": "LIBRE",
  "anneeConstruction": 2015,
  "observations": "Bien rénové, vue sur la Seine",
  "proprietaireId": 42,
  "createdAt": "2026-04-04T10:30:00",
  "updatedAt": "2026-04-04T10:30:00",
  "createdBy": null,
  "updatedBy": null,
  "version": 1
}
```

### Cas d'erreur

#### Référence dupliquée (409 Conflict)

```json
{
  "timestamp": "2026-04-04T10:31:00",
  "status": 409,
  "error": "Conflict",
  "message": "Un appartement avec la référence 'APT-PARIS-001' existe déjà",
  "path": "/api/v1/appartements"
}
```

#### Validation échouée (400 Bad Request)

```http
POST /api/v1/appartements
Content-Type: application/json

{
  "reference": "invalid ref",     // ❌ Caractères spéciaux
  "adresse": "",                  // ❌ Vide
  "codePostal": "750",            // ❌ Pas 5 chiffres
  "ville": "Paris",
  "surfaceHabitable": -10,        // ❌ Négatif
  "nombrePieces": 1,
  "loyerMensuel": 0,              // ❌ Doit être > 0
  "chargesMensuelles": 100,
  "typeBien": "T1",
  "statut": "LIBRE"
}
```

**Réponse (400 Bad Request):**

```json
{
  "timestamp": "2026-04-04T10:32:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation échouée",
  "fieldErrors": {
    "reference": "La référence doit contenir uniquement des majuscules, chiffres et tirets",
    "adresse": "L'adresse est obligatoire",
    "codePostal": "Le code postal doit être composé de 5 chiffres",
    "surfaceHabitable": "La surface doit être supérieure à 0",
    "loyerMensuel": "Le loyer doit être supérieur à 0"
  },
  "path": "/api/v1/appartements"
}
```

---

## 2. Récupérer par ID

### Requête

```http
GET /api/v1/appartements/1
```

### Réponse (200 OK)

```json
{
  "id": 1,
  "reference": "APT-PARIS-001",
  "adresse": "123 Rue de Rivoli",
  "codePostal": "75004",
  "ville": "Paris",
  "surfaceHabitable": 50.50,
  "nombrePieces": 1,
  "loyerMensuel": 850.00,
  "chargesMensuelles": 120.00,
  "typeBien": "T1",
  "statut": "LIBRE",
  "anneeConstruction": 2015,
  "observations": "Bien rénové, vue sur la Seine",
  "proprietaireId": 42,
  "createdAt": "2026-04-04T10:30:00",
  "updatedAt": "2026-04-04T10:30:00",
  "createdBy": null,
  "updatedBy": null,
  "version": 1
}
```

### Non trouvé (404 Not Found)

```http
GET /api/v1/appartements/999
```

**Réponse:**

```json
{
  "timestamp": "2026-04-04T10:33:00",
  "status": 404,
  "error": "Not Found",
  "message": "Appartement non trouvé: ID=999",
  "path": "/api/v1/appartements/999"
}
```

---

## 3. Récupérer par référence

### Requête

```http
GET /api/v1/appartements/reference/APT-PARIS-001
```

### Réponse (200 OK)

Même structure que récupérer par ID.

---

## 4. Lister tous les appartements (pagination)

### Requête

```http
GET /api/v1/appartements?page=0&size=20&sort=reference,asc
```

### Réponse (200 OK)

```json
{
  "content": [
    {
      "id": 1,
      "reference": "APT-LYON-001",
      "adresse": "456 Cours Lafayette",
      "codePostal": "69003",
      "ville": "Lyon",
      "surfaceHabitable": 65.00,
      "nombrePieces": 2,
      "loyerMensuel": 750.00,
      "chargesMensuelles": 100.00,
      "typeBien": "T2",
      "statut": "LOUE",
      "anneeConstruction": 2018,
      "observations": null,
      "proprietaireId": 10,
      "createdAt": "2026-01-15T08:00:00",
      "updatedAt": "2026-03-20T14:00:00",
      "createdBy": null,
      "updatedBy": null,
      "version": 3
    },
    {
      "id": 2,
      "reference": "APT-PARIS-001",
      "adresse": "123 Rue de Rivoli",
      "codePostal": "75004",
      "ville": "Paris",
      "surfaceHabitable": 50.50,
      "nombrePieces": 1,
      "loyerMensuel": 850.00,
      "chargesMensuelles": 120.00,
      "typeBien": "T1",
      "statut": "LIBRE",
      "anneeConstruction": 2015,
      "observations": "Bien rénové",
      "proprietaireId": 42,
      "createdAt": "2026-04-04T10:30:00",
      "updatedAt": "2026-04-04T10:30:00",
      "createdBy": null,
      "updatedBy": null,
      "version": 1
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "offset": 0,
    "unpaged": false,
    "paged": true
  },
  "totalElements": 2,
  "totalPages": 1,
  "last": true,
  "size": 20,
  "number": 0,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "numberOfElements": 2,
  "first": true,
  "empty": false
}
```

---

## 5. Filtrer par statut

### Requête

```http
GET /api/v1/appartements/filter/statut?statut=LIBRE
```

### Réponse (200 OK)

```json
[
  {
    "id": 2,
    "reference": "APT-PARIS-001",
    "adresse": "123 Rue de Rivoli",
    "codePostal": "75004",
    "ville": "Paris",
    "surfaceHabitable": 50.50,
    "nombrePieces": 1,
    "loyerMensuel": 850.00,
    "chargesMensuelles": 120.00,
    "typeBien": "T1",
    "statut": "LIBRE",
    "anneeConstruction": 2015,
    "observations": "Bien rénové",
    "proprietaireId": 42,
    "createdAt": "2026-04-04T10:30:00",
    "updatedAt": "2026-04-04T10:30:00",
    "createdBy": null,
    "updatedBy": null,
    "version": 1
  }
]
```

---

## 6. Filtrer par ville

### Requête

```http
GET /api/v1/appartements/filter/ville?ville=Paris
```

### Réponse (200 OK)

Même structure que filtrer par statut.

---

## 7. Recherche par plage de loyer

### Requête

```http
GET /api/v1/appartements/search/price-range?min=500&max=1500
```

Retourne tous les appartements LIBRES dont le loyer est entre 500 et 1500 €.

### Réponse (200 OK)

Même structure que filtrer par statut (liste triée par loyer ascendant).

---

## 8. Mettre à jour un appartement (PUT)

### Requête

```http
PUT /api/v1/appartements/1
Content-Type: application/json

{
  "reference": "APT-PARIS-001",
  "adresse": "124 Rue de Rivoli",
  "codePostal": "75004",
  "ville": "Paris",
  "surfaceHabitable": 52.00,
  "nombrePieces": 1,
  "loyerMensuel": 900.00,
  "chargesMensuelles": 130.00,
  "typeBien": "T1",
  "statut": "LIBRE",
  "anneeConstruction": 2015,
  "observations": "Bien rénové, vue sur la Seine, recent update",
  "proprietaireId": 42
}
```

### Réponse (200 OK)

```json
{
  "id": 1,
  "reference": "APT-PARIS-001",
  "adresse": "124 Rue de Rivoli",
  "codePostal": "75004",
  "ville": "Paris",
  "surfaceHabitable": 52.00,
  "nombrePieces": 1,
  "loyerMensuel": 900.00,
  "chargesMensuelles": 130.00,
  "typeBien": "T1",
  "statut": "LIBRE",
  "anneeConstruction": 2015,
  "observations": "Bien rénové, vue sur la Seine, recent update",
  "proprietaireId": 42,
  "createdAt": "2026-04-04T10:30:00",
  "updatedAt": "2026-04-04T11:00:00",
  "createdBy": null,
  "updatedBy": null,
  "version": 2
}
```

---

## 9. Changer le statut (PATCH)

### Requête

```http
PATCH /api/v1/appartements/1/statut?statut=LOUE
```

### Réponse (200 OK)

```json
{
  "id": 1,
  "reference": "APT-PARIS-001",
  "adresse": "124 Rue de Rivoli",
  "codePostal": "75004",
  "ville": "Paris",
  "surfaceHabitable": 52.00,
  "nombrePieces": 1,
  "loyerMensuel": 900.00,
  "chargesMensuelles": 130.00,
  "typeBien": "T1",
  "statut": "LOUE",
  "anneeConstruction": 2015,
  "observations": "Bien rénové, vue sur la Seine, recent update",
  "proprietaireId": 42,
  "createdAt": "2026-04-04T10:30:00",
  "updatedAt": "2026-04-04T11:05:00",
  "createdBy": null,
  "updatedBy": null,
  "version": 3
}
```

---

## 10. Supprimer un appartement

### Requête

```http
DELETE /api/v1/appartements/1
```

### Réponse (204 No Content)

Pas de body.

### Non trouvé (404 Not Found)

```http
DELETE /api/v1/appartements/999
```

Retourne un 404 avec message d'erreur.

---

## 11. Statistiques - Compter par statut

### Requête

```http
GET /api/v1/appartements/stats/count?statut=LIBRE
```

### Réponse (200 OK)

```
5
```

(Simple nombre entier)

---

## 12. Lister par propriétaire

### Requête

```http
GET /api/v1/appartements/proprietaire/42
```

### Réponse (200 OK)

Array d'appartements du propriétaire 42.

---

## cURL pour tester

### Créer

```bash
curl -X POST http://localhost:8080/api/v1/appartements \
  -H "Content-Type: application/json" \
  -d '{
    "reference": "APT-PARIS-001",
    "adresse": "123 Rue de Rivoli",
    "codePostal": "75004",
    "ville": "Paris",
    "surfaceHabitable": 50.50,
    "nombrePieces": 1,
    "loyerMensuel": 850.00,
    "chargesMensuelles": 120.00,
    "typeBien": "T1",
    "statut": "LIBRE",
    "anneeConstruction": 2015,
    "observations": "Bien rénové"
  }'
```

### Récupérer

```bash
curl -X GET http://localhost:8080/api/v1/appartements/1
```

### Lister (pagination)

```bash
curl -X GET 'http://localhost:8080/api/v1/appartements?page=0&size=10&sort=reference,asc'
```

### Filtrer

```bash
curl -X GET 'http://localhost:8080/api/v1/appartements/filter/statut?statut=LIBRE'
```

### Recherche prix

```bash
curl -X GET 'http://localhost:8080/api/v1/appartements/search/price-range?min=500&max=1500'
```

### Changer statut

```bash
curl -X PATCH 'http://localhost:8080/api/v1/appartements/1/statut?statut=LOUE'
```

### Supprimer

```bash
curl -X DELETE http://localhost:8080/api/v1/appartements/1
```

---

## Postman / Insomnia

Importer la collection JSON suivante:

```json
{
  "info": {
    "name": "LocaGest - Appartements",
    "version": "1.0"
  },
  "item": [
    {
      "name": "Créer appartement",
      "request": {
        "method": "POST",
        "url": "{{baseUrl}}/api/v1/appartements",
        "body": {
          "mode": "raw",
          "raw": "{\n  \"reference\": \"APT-PARIS-001\",\n  \"adresse\": \"123 Rue de Rivoli\",\n  \"codePostal\": \"75004\",\n  \"ville\": \"Paris\",\n  \"surfaceHabitable\": 50.50,\n  \"nombrePieces\": 1,\n  \"loyerMensuel\": 850.00,\n  \"chargesMensuelles\": 120.00,\n  \"typeBien\": \"T1\",\n  \"statut\": \"LIBRE\",\n  \"anneeConstruction\": 2015\n}"
        }
      }
    },
    {
      "name": "Lister tous",
      "request": {
        "method": "GET",
        "url": "{{baseUrl}}/api/v1/appartements?page=0&size=20"
      }
    },
    {
      "name": "Filtrer LIBRE",
      "request": {
        "method": "GET",
        "url": "{{baseUrl}}/api/v1/appartements/filter/statut?statut=LIBRE"
      }
    }
  ]
}
```

**Variables Postman:**
- `baseUrl` = `http://localhost:8080`

