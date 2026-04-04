-- V001__create_appartements_table.sql
-- Crée la table des appartements
-- Compatible H2 (version locale) et PostgreSQL (production)

CREATE TABLE IF NOT EXISTS appartements (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    -- Identifiants
    reference VARCHAR(50) NOT NULL UNIQUE,

    -- Localisation
    adresse VARCHAR(255) NOT NULL,
    code_postal VARCHAR(10) NOT NULL,
    ville VARCHAR(100) NOT NULL,

    -- Caractéristiques
    surface_habitable DECIMAL(7, 2) NOT NULL,
    nombre_pieces INT NOT NULL,
    type_bien VARCHAR(20) NOT NULL,
    annee_construction INT,

    -- Montants
    loyer_mensuel DECIMAL(10, 2) NOT NULL,
    charges_mensuelles DECIMAL(10, 2) NOT NULL,

    -- Relations
    statut VARCHAR(20) NOT NULL DEFAULT 'LIBRE',
    proprietaire_id BIGINT,

    -- Métadonnées
    observations TEXT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    created_by VARCHAR(100),
    updated_by VARCHAR(100),
    version BIGINT DEFAULT 1
);

-- Indexes créés séparément (compatible H2 et PostgreSQL)
CREATE INDEX idx_reference ON appartements (reference);
CREATE INDEX idx_statut ON appartements (statut);
CREATE INDEX idx_ville ON appartements (ville);
CREATE INDEX idx_loyer_mensuel ON appartements (loyer_mensuel);
CREATE INDEX idx_statut_loyer ON appartements (statut, loyer_mensuel);

