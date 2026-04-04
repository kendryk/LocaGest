package com.locagest.appartement.api.advice;

import com.locagest.appartement.domain.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Gestionnaire global des exceptions pour les contrôleurs REST.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(
        ResourceNotFoundException ex,
        WebRequest request) {

        log.warn("Ressource non trouvée: {}", ex.getMessage());

        Map<String, Object> body = buildErrorResponse(
            HttpStatus.NOT_FOUND,
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
        MethodArgumentNotValidException ex,
        WebRequest request) {

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult()
            .getFieldErrors()
            .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

        log.warn("Erreurs de validation: {}", fieldErrors);

        Map<String, Object> body = buildErrorResponse(
            HttpStatus.BAD_REQUEST,
            "Validation échouée",
            request.getDescription(false).replace("uri=", "")
        );
        body.put("fieldErrors", fieldErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(
        IllegalArgumentException ex,
        WebRequest request) {

        log.warn("Erreur logique métier: {}", ex.getMessage());

        Map<String, Object> body = buildErrorResponse(
            HttpStatus.CONFLICT,
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(
        Exception ex,
        WebRequest request) {

        log.error("Erreur serveur non gérée", ex);

        Map<String, Object> body = buildErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Une erreur serveur s'est produite",
            request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    private Map<String, Object> buildErrorResponse(
        HttpStatus status,
        String message,
        String path) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", path);

        return body;
    }
}

