package com.locagest.appartement.domain.model;

/**
 * Type de bien immobilier.
 */
public enum TypeBien {
    STUDIO("Studio"),
    T1("T1"),
    T2("T2"),
    T3("T3"),
    T4_PLUS("T4+"),
    MAISON("Maison");

    private final String label;

    TypeBien(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

