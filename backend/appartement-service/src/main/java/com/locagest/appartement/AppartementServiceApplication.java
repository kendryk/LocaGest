package com.locagest.appartement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale du service Appartement LocaGest.
 *
 * Service responsable de:
 * - Gestion des appartements
 * - Gestion des baux
 * - Gestion des locataires
 * - Gestion des paiements
 * - Génération des quittances
 *
 * Port par défaut: 8081
 * Base URL API: http://localhost:8081/api/v1
 */
@SpringBootApplication
public class AppartementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppartementServiceApplication.class, args);
    }
}

