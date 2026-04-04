# 📐 Guide - Création de nouveaux services

Ce guide explique comment créer de nouveaux services Spring Boot dans l'architecture LocaGest.

---

## 🎯 Principes

1. **Autonomie** - Chaque service est un projet Maven autonome
2. **Isolation** - Package distinct: `com.locagest.{service-name}`
3. **Cohérence** - Même structure pour tous les services
4. **Modularité** - Services découplés, communicants par API REST

---

## 📋 Template - Structure d'un service

```
backend/{service-name}/
│
├── pom.xml
├── .gitignore
├── README.md
├── DEPLOYMENT.md
│
└── src/
    ├── main/
    │   ├── java/com/locagest/{service-name}/
    │   │   ├── {ServiceName}Application.java
    │   │   │
    │   │   ├── api/                          ← Couche API
    │   │   │   ├── controller/
    │   │   │   │   └── {Entity}Controller.java
    │   │   │   ├── dto/
    │   │   │   │   └── {Entity}Dto.java
    │   │   │   └── advice/
    │   │   │       └── GlobalExceptionHandler.java
    │   │   │
    │   │   ├── application/                  ← Couche Application
    │   │   │   ├── service/
    │   │   │   │   └── {Entity}Service.java
    │   │   │   └── mapper/
    │   │   │       └── {Entity}Mapper.java
    │   │   │
    │   │   ├── domain/                       ← Couche Domain
    │   │   │   ├── model/
    │   │   │   │   ├── {Entity}.java
    │   │   │   │   └── {Enum}.java
    │   │   │   └── exception/
    │   │   │       └── ResourceNotFoundException.java
    │   │   │
    │   │   └── infrastructure/               ← Couche Infrastructure
    │   │       └── persistence/
    │   │           └── {Entity}Repository.java
    │   │
    │   └── resources/
    │       ├── application.properties
    │       └── db/migration/
    │           └── V001__{description}.sql
    │
    └── test/
        ├── java/com/locagest/{service-name}/
        │   └── application/service/
        │       └── {Entity}ServiceTest.java
        └── resources/
            └── application.properties
```

---

## ✅ Checklist de création

### 1. Créer la structure Maven

```bash
cd backend
mkdir {service-name}
cd {service-name}
```

### 2. pom.xml - Configuration Maven

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="...">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.locagest</groupId>
    <artifactId>{service-name}</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>LocaGest - {Service Name}</name>
    <description>Microservice {description}</description>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
    </parent>

    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- Utilities -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-core</artifactId>
        </dependency>

        <!-- Tests -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### 3. Classe principale - {Service}Application.java

```java
package com.locagest.{service-name};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class {ServiceName}Application {
    public static void main(String[] args) {
        SpringApplication.run({ServiceName}Application.class, args);
    }
}
```

### 4. application.properties

```properties
spring.application.name={service-name}
server.port=808{x}  # 8081 pour Appartement, 8082 pour Utilisateur, etc.

# JPA
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false

# Database
spring.datasource.url=jdbc:h2:mem:{service-name}-db
spring.datasource.username=sa
spring.h2.console.enabled=true

# Flyway
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration

# Logging
logging.level.com.locagest=INFO
```

### 5. Migration SQL - V001__{name}.sql

```sql
CREATE TABLE {entities} (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    -- Colonnes métier
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    version BIGINT DEFAULT 1
);
```

### 6. Première entité JPA

```java
package com.locagest.{service-name}.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "{entities}")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class {Entity} {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Colonnes métier
    // ...

    @Version
    private Long version;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
```

### 7. DTO avec validations

```java
package com.locagest.{service-name}.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class {Entity}Dto {
    private Long id;

    @NotBlank(message = "Champ obligatoire")
    @Size(min = 3, max = 100)
    private String name;

    // Autres champs avec validations
    // ...
}
```

### 8. Repository Spring Data JPA

```java
package com.locagest.{service-name}.infrastructure.persistence;

import com.locagest.{service-name}.domain.model.{Entity};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface {Entity}Repository extends JpaRepository<{Entity}, Long> {
    // Requêtes métier
}
```

### 9. Service métier

```java
package com.locagest.{service-name}.application.service;

import com.locagest.{service-name}.api.dto.{Entity}Dto;
import com.locagest.{service-name}.application.mapper.{Entity}Mapper;
import com.locagest.{service-name}.domain.exception.ResourceNotFoundException;
import com.locagest.{service-name}.infrastructure.persistence.{Entity}Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class {Entity}Service {
    private final {Entity}Repository repository;
    private final {Entity}Mapper mapper;

    @Transactional
    public {Entity}Dto create({Entity}Dto dto) {
        log.info("Création de {entity}: {}", dto);
        // Logique métier
        var entity = mapper.toEntity(dto);
        var saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public {Entity}Dto getById(Long id) {
        log.debug("Récupération de {entity}: ID={}", id);
        return repository.findById(id)
            .map(mapper::toDto)
            .orElseThrow(() -> new ResourceNotFoundException("Non trouvé"));
    }

    // Autres CRUD...
}
```

### 10. Controller REST

```java
package com.locagest.{service-name}.api.controller;

import com.locagest.{service-name}.api.dto.{Entity}Dto;
import com.locagest.{service-name}.application.service.{Entity}Service;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/{entities}")
@RequiredArgsConstructor
public class {Entity}Controller {
    private final {Entity}Service service;

    @PostMapping
    public ResponseEntity<{Entity}Dto> create(@Valid @RequestBody {Entity}Dto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<{Entity}Dto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // Autres endpoints...
}
```

### 11. Tests unitaires

```java
package com.locagest.{service-name}.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class {Entity}ServiceTest {
    @Mock
    private {Entity}Repository repository;

    @InjectMocks
    private {Entity}Service service;

    @Test
    void testCreate() {
        // Test
    }
}
```

---

## 🚀 Port allocation

```
Backend Services:
- 8081: Appartement
- 8082: Utilisateur
- 8083: (Réservé)
- 8084: (Réservé)

Infrastructure:
- 8080: API Gateway (à créer)
- 5432: PostgreSQL (à créer)

Frontend:
- 4200: Angular (à créer)
```

---

## 📚 Exemples existants

Le service **Appartement** `backend/appartement-service/` est un exemple complet à suivre.

Voir:
- `backend/appartement-service/src/main/java/`
- `backend/appartement-service/pom.xml`
- `backend/appartement-service/README.md`

---

## 🎯 Ordre recommandé

1. ✅ **Appartement** (COMPLET)
2. ⏳ **Utilisateur** (auth, roles)
3. ⏳ **Services métier** (Bail, Paiement, etc.)
4. ⏳ **Infrastructure** (API Gateway, DB, messaging)
5. ⏳ **Frontend** (Angular)

---

## 🔧 Commands utiles

```bash
# Créer un service
mkdir backend/{service-name}

# Compiler
mvn clean install

# Tester
mvn test

# Lancer
mvn spring-boot:run

# Générer JAR
mvn clean package
```

---

**Pour créer un nouveau service, dupliquez `backend/appartement-service/` et adaptez les noms.** 🚀

