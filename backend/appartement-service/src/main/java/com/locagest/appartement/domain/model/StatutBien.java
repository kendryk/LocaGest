package com.locagest.appartement.domain.model;

/**
 * Statut d'un appartement.
 */
public enum StatutBien {
    LIBRE("Libre"),
    LOUE("Loué"),
    MAINTENANCE("En maintenance"),
    RETIRE_DU_MARCHE("Retiré du marché");

    private final String label;

    StatutBien(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

