package com.locagest.appartement.api.dto;

import com.locagest.appartement.domain.model.StatutBien;
import com.locagest.appartement.domain.model.TypeBien;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO pour l'exposition des données d'Appartement.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppartementDto {

    private Long id;

    @NotBlank(message = "La référence est obligatoire")
    @Size(min = 3, max = 50, message = "La référence doit contenir entre 3 et 50 caractères")
    @Pattern(regexp = "^[A-Z0-9\\-]+$", message = "La référence doit contenir uniquement des majuscules, chiffres et tirets")
    private String reference;

    @NotBlank(message = "L'adresse est obligatoire")
    @Size(max = 255, message = "L'adresse ne doit pas dépasser 255 caractères")
    private String adresse;

    @NotBlank(message = "Le code postal est obligatoire")
    @Pattern(regexp = "^[0-9]{5}$", message = "Le code postal doit être composé de 5 chiffres")
    private String codePostal;

    @NotBlank(message = "La ville est obligatoire")
    @Size(max = 100, message = "La ville ne doit pas dépasser 100 caractères")
    private String ville;

    @NotNull(message = "La surface habitable est obligatoire")
    @DecimalMin(value = "1.0", inclusive = true, message = "La surface doit être supérieure à 0")
    @DecimalMax(value = "9999.99", message = "La surface ne doit pas dépasser 9999,99 m²")
    private BigDecimal surfaceHabitable;

    @NotNull(message = "Le nombre de pièces est obligatoire")
    @Min(value = 0, message = "Le nombre de pièces ne peut pas être négatif")
    @Max(value = 20, message = "Le nombre de pièces ne doit pas dépasser 20")
    private Integer nombrePieces;

    @NotNull(message = "Le loyer mensuel est obligatoire")
    @DecimalMin(value = "0.01", message = "Le loyer doit être supérieur à 0")
    @DecimalMax(value = "99999.99", message = "Le loyer ne doit pas dépasser 99 999,99 €")
    private BigDecimal loyerMensuel;

    @NotNull(message = "Les charges mensuelles sont obligatoires")
    @DecimalMin(value = "0.00", message = "Les charges ne peuvent pas être négatives")
    @DecimalMax(value = "99999.99", message = "Les charges ne doivent pas dépasser 99 999,99 €")
    private BigDecimal chargesMensuelles;

    @NotNull(message = "Le type de bien est obligatoire")
    private TypeBien typeBien;

    @NotNull(message = "Le statut du bien est obligatoire")
    private StatutBien statut;

    @Min(value = 1800, message = "L'année de construction doit être supérieure à 1800")
    @Max(value = 2100, message = "L'année de construction ne doit pas dépasser 2100")
    private Integer anneeConstruction;

    @Size(max = 2000, message = "Les observations ne doivent pas dépasser 2000 caractères")
    private String observations;

    private Long proprietaireId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private Long version;
}

