-- V002__create_locataires_table.sql
-- Crée la table des locataires
-- Compatible H2 (version locale) et PostgreSQL (production)

CREATE TABLE IF NOT EXISTS locataires (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    -- Informations personnelles
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,

    -- Coordonnées
    email VARCHAR(255) NOT NULL UNIQUE,
    telephone VARCHAR(20) NOT NULL UNIQUE,

    -- Informations complémentaires
    date_naissance DATE NOT NULL,
    actif BOOLEAN NOT NULL DEFAULT TRUE,

    -- Métadonnées
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    version BIGINT DEFAULT 1
);

-- Indexes créés séparément (compatible H2 et PostgreSQL)
CREATE INDEX idx_email ON locataires (email);
CREATE INDEX idx_telephone ON locataires (telephone);
CREATE INDEX idx_actif ON locataires (actif);
CREATE INDEX idx_nom_prenom ON locataires (nom, prenom);
