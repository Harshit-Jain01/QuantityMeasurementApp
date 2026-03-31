# 📏 QuantityMeasurementApp

> A Java application built using Test-Driven Development (TDD) to systematically design and enhance a quantity measurement system. The project focuses on step-by-step evolution, clean object-oriented principles, and continuous refactoring to create a scalable and maintainable domain model.

## 📖 Overview

- Structured Java project centered around modelling measurement quantities.
- Developed incrementally through well-defined Use Cases to gradually refine the design.
- Prioritizes readability, consistency, and long-term maintainability as the system expands.

## ✅ Implemented Features

> _Additional features will be documented here as new Use Cases are completed._

### UC1 – Feet Equality
  - Defines value-based equality for feet measurements by overriding the `equals()` method.
  - Establishes consistent object comparison semantics, forming the foundation for future cross-unit comparisons.

### UC2 – Inches Equality 
  - Extends value-based equality comparison to inches measurements using a dedicated `Inches` class.
  - Maintains independent unit validation while reinforcing equality behaviour across measurement types.

### UC3 – Generic Length 
  - Refactors separate unit-specific classes into a single `Length` abstraction using a `LengthUnit` enum  
  - Removes duplicated logic by following the DRY principle and supports equality comparison across different units  

### UC4 – Extended Unit Support
  - Adds Yards and Centimeters to the `LengthUnit` enum with appropriate conversion factors.
  - Demonstrates scalability of the generic design by enabling seamless cross-unit equality without introducing new classes.

### UC5 – Unit-to-Unit Conversion
  - Introduces explicit conversion operations between supported length units using centralized enum conversion factors.
  - Extends the `Length` API to convert measurements across units while preserving mathematical equivalence and precision.

### UC6 – Length Addition Operation
  - Introduces addition between length measurements with automatic unit normalization and conversion.
  - Returns a new immutable `Length` result expressed in the unit of the first operand while preserving mathematical accuracy.

### UC7 – Addition with Target Unit Specification
  - Extends length addition by allowing the caller to explicitly choose the unit of the result  
  - Performs automatic normalization and conversion of operands, returning a new immutable `Length` instance in the specified target unit while preserving mathematical accuracy 

###  UC8 – Standalone Unit Enum Refactoring
  - Refactors the design by extracting `LengthUnit` into a standalone enum responsible for all unit conversion logic  
  - Simplifies the `Length` class to delegate conversions to the unit, improving cohesion, reducing coupling, and enabling scalable support for additional measurement categories while preserving existing functionality 

### UC9 – Weight Measurement Support
  - Introduces a new measurement category for weight with units **Kilogram, Gram, and Pound**, supporting equality checks, unit conversion, and addition operations  
  - Mirrors the design patterns used for length measurements, ensuring category type safety, immutability, and scalable architecture while keeping weight and length as independent, non-comparable domains. 

### UC10 – Generic Quantity Class for Unified Measurement Support
  - Refactors the system to use a single **generic Quantity<U extends IMeasurable> class**, replacing separate classes for each measurement category  
  - Uses a common unit interface to standardize conversion behavior across all unit types  
  - Provides equality comparison, unit conversion, and addition operations for any supported category without code duplication  
  - Preserves domain integrity — quantities from different categories (e.g., length vs weight) cannot be compared or combined  
  - Reduces architectural complexity by consolidating logic into one reusable implementation  
  - Allows new measurement categories to be added by creating a new enum implementing the interface, with no changes to existing code  
  - Promotes maintainability, extensibility, and compliance with core SOLID principles while keeping objects immutable

### UC11 – Volume Measurement Support (Litre, Millilitre, Gallon)
  - Introduces a new measurement category for volume with units **Litre (L), Millilitre (mL), and Gallon (gal)** using litre as the base unit  
  - Supports equality comparison, unit conversion, and addition operations through the existing generic `Quantity<U extends IMeasurable>` class  
  - Validates that volume measurements in different units are equivalent when representing the same quantity (e.g., 1 L = 1000 mL ≈ 0.264172 gal)  
  - Maintains strict category isolation — volume measurements are independent and non-comparable with length or weight measurements  
  - Requires only a new `VolumeUnit` enum implementing `IMeasurable`; no changes to core classes or application logic  
  - Demonstrates seamless scalability of the UC10 architecture to additional measurement domains  
  - Preserves immutability, type safety, and consistent behavior across all supported categories  

 ### UC12 – Subtraction and Division Operations on Quantity Measurements
  - Extends the generic `Quantity<U extends IMeasurable>` class with **subtraction** and **division** operations for comprehensive arithmetic support  
  - Subtraction computes the difference between two quantities of the same category and returns a new immutable `Quantity<U>` result  
  - Division computes the ratio between two quantities and returns a **dimensionless scalar (double)** value  
  - Supports cross-unit arithmetic within the same category through automatic conversion to a common base unit  
  - Allows implicit result unit (first operand’s unit) or explicit target unit specification for subtraction  
  - Preserves strict category isolation — operations across different domains (e.g., length vs weight) are prevented  
  - Maintains immutability, validation, and consistent error handling (null checks, finite values, division by zero)  
  - Demonstrates scalability of the generic design by adding new operations without modifying existing architecture  

### UC13: Add temperature measurement support with selective arithmetic and IMeasurable refactoring

  - Introduced TemperatureUnit (Celsius, Fahrenheit, Kelvin) with accurate non-linear conversions  
  - Refactored IMeasurable to support optional arithmetic via default methods  
  - Added SupportsArithmetic functional interface with lambda-based capability checks  
  - Disabled arithmetic operations for temperature (add, subtract, divide) with clear exceptions  
  - Updated Quantity<U> to validate operation support before execution  
  - Preserved full arithmetic support for length, weight, and volume units  
  - Ensured strict cross-category type safety and backward compatibility (UC1–UC13)  
  - Added demonstration cases and comprehensive tests for temperature equality, conversion, and error handling  
 
### UC14 – Temperature Measurement with Selective Arithmetic Support
- Adds **temperature units (Celsius, Fahrenheit, Kelvin)** with equality and conversion support only  
- Refactors `IMeasurable` to make arithmetic operations optional via default methods  
- Uses precise non-linear formulas for accurate cross-unit conversion  
- Disables arithmetic on absolute temperatures (throws `UnsupportedOperationException`)  
- Ensures type safety and keeps temperature separate from other measurement categories

## UC15 – N-Tier Architecture Refactoring

UC15 refactors the **Quantity Measurement Application** from a monolithic structure into a **professional N-Tier architecture** to improve maintainability, scalability, and separation of concerns.

### Architecture Layers

The application is divided into the following layers:
```
Application Layer
↓
Controller Layer
↓
Service Layer
↓
Repository Layer
↓
Entity / Model Layer

```

### Layers Description

**Application Layer**
- Entry point of the application (`QuantityMeasurementApp`)
- Initializes controller, service, and repository.

**Controller Layer**
- Handles user requests and delegates operations to the service layer.
- Implemented by `QuantityMeasurementController`.

**Service Layer**
- Contains business logic for comparison, conversion, and arithmetic operations.
- Implemented by `IQuantityMeasurementService` and `QuantityMeasurementServiceImpl`.

**Repository Layer**
- Handles persistence of measurement operations.
- Implemented by `IQuantityMeasurementRepository` and `QuantityMeasurementCacheRepository`.

**Entity / Model Layer**
- Defines data structures used across the application.
- Includes:
  - `QuantityDTO`
  - `QuantityModel`
  - `QuantityMeasurementEntity`

### Design Principles Used

- **SOLID Principles**
- **Dependency Injection**
- **Interface Segregation**
- **Separation of Concerns**

### Design Patterns Used

- **Singleton Pattern** – Repository
- **Factory Pattern** – Object creation
- **Facade Pattern** – Controller interface

## UC16 – JDBC-Based Database Integration
  - Enhances the N-Tier architecture with persistent storage using JDBC, replacing the in-memory repository with `QuantityMeasurementDatabaseRepository` for durable data across restarts.
  - Introduces configuration and performance utilities like `ApplicationConfig` (for environment-based settings) and `ConnectionPool` (for efficient connection reuse).

- ⚙️ **Improved Repository & Error Handling**
  - Extends repository capabilities with filtering and management methods (`getMeasurementsByType`, `getTotalCount`, etc.) and uses `PreparedStatement` to prevent SQL injection.
  - Adds structured exception handling via `DatabaseException` and shifts logging to `java.util.logging` (via SLF4J/Logback).

- 🧪 **Configurability, Testing & Best Practices**
  - Supports H2 by default with easy switching to MySQL/PostgreSQL via properties, and runtime repository selection (`database` or `cache`).
  - Includes schema setup, integration/unit tests, and follows best practices like connection pooling, clean resource handling, and layered architecture organization.
 
##  UC17 – Spring Boot REST Architecture with JPA Persistence (NEW)

- Transforms the application into a **Spring Boot-based RESTful system** while retaining the core domain logic and design.
- Introduces `QuantityMeasurementApplication` as the centralized **application entry point**.
- Replaces manual JDBC implementation with **Spring Data JPA (`JpaRepository`)** for simplified and efficient data access.
- Implements **derived query methods** along with **custom JPQL/native queries** in repository interfaces.
- Reorganizes package structure to improve **clarity, modularity, and maintainability**.

### 📦 Data Transfer Layer
- Enhances `QuantityDTO` using validation annotations such as `@NotNull`, `@Min`, etc.
- Introduces:
  - `QuantityInputDTO` for handling API request payloads  
  - `QuantityMeasurementDTO` for structured API responses  

### 🔢 Operation Management
- Adds `OperationType` enum to represent supported operations (ADD, SUBTRACT, CONVERT, COMPARE).
- Ensures **type-safe and extensible operation handling**.

### 🌐 REST API
- Exposes RESTful endpoints for:
  - Quantity operations (conversion, arithmetic, comparison)  
  - Measurement history tracking  
- Built using standard Spring annotations like `@RestController`, `@RequestMapping`, etc.

### 📚 API Documentation
- Integrates **Swagger / OpenAPI** for interactive API documentation and testing.

### ⚠️ Exception Handling
- Implements centralized error handling via `GlobalExceptionHandler` using:
  - `@ControllerAdvice`  
  - `@ExceptionHandler`  
- Provides consistent and structured API error responses.

### 🔐 Security Configuration
- Introduces `SecurityConfig` with a **permissive setup** for development.
- Designed to support future integration with authentication mechanisms (JWT/OAuth).

###  Performance & Database
- Uses **HikariCP** for efficient database connection pooling.
- Enables **JPA auto-DDL** for automatic schema management.

###  Monitoring & Observability
- Integrates **Spring Boot Actuator** to provide:
  - Health checks  
  - Metrics  
  - Application monitoring endpoints  

###  Testing
- Includes comprehensive test coverage:
  - Controller tests  
  - Service layer tests  
  - Repository tests  
  - Integration tests  

### 🏗️ Outcome
- Marks the transition from a traditional layered system to a **modern, scalable, and enterprise-ready Spring Boot architecture**.

##  UC18 – Spring Security with JWT & OAuth2 Authentication (Google & GitHub)

- Enhances the application with **robust security using Spring Security**, integrating JWT-based authentication along with Google and GitHub OAuth2 login.
- Secures REST APIs with **role-based authorization** and follows industry-standard security practices.

### 🔐 Security Architecture
- Introduces a dedicated `security` package containing:
  - `JwtTokenProvider`
  - `JwtAuthenticationFilter`
  - `JwtAuthenticationEntryPoint`
  - `JwtAccessDeniedHandler`
  - `CustomUserDetailsService`
  - `UserPrincipal`
  - `CustomOAuth2UserService`
  - `OAuth2AuthenticationSuccessHandler`
  - `OAuth2AuthenticationFailureHandler`

### 🪪 JWT Authentication
- Implements complete JWT lifecycle:
  - Generates **signed tokens (HS256)** after authentication
  - Extracts user details (email, roles) from token
  - Validates token for every request
- Configurable via:
  - `app.jwt.secret`
  - `app.jwt.expiration-ms`

### 👤 Local Authentication APIs
- Provides REST endpoints under `/api/v1/auth`:
  - `POST /register` → registers user with **BCrypt-encrypted password** and returns JWT  
  - `POST /login` → validates credentials and returns JWT  
  - `GET /me` → returns authenticated user profile  

### 🌐 OAuth2 Authentication

#### Google Login
- Uses Spring Security OAuth2 flow at:
  - `/oauth2/authorization/google`
- Fetches user profile and maps to local user.
- Issues JWT on successful authentication and redirects to frontend.

#### GitHub Login
- Available at:
  - `/oauth2/authorization/github`
- Handles provider-specific attributes:
  - `id` → providerId  
  - `login` → username  
  - `avatar_url` → profile image  
- Validates email availability (rejects login if email is private).
- Requires scopes: `read:user`, `user:email`.

### 🗄️ User Management
- Introduces `User` JPA entity (`app_user` table) with fields:
  - email, name, password  
  - provider (`LOCAL`, `GOOGLE`, `GITHUB`)  
  - providerId, role (`USER`, `ADMIN`)  
  - imageUrl, createdAt  

- Adds `UserRepository` with:
  - `findByEmail()`  
  - `existsByEmail()`  

### 📦 DTO Layer
- Introduces:
  - `AuthRequest`
  - `AuthResponse` (Builder pattern)
  - `RegisterRequest`
- Uses validation annotations:
  - `@NotBlank`, `@Email`, `@Size`

### 🛡️ Authorization Rules
- Enables method-level security using `@EnableMethodSecurity`.
- Access control:
  - Public → Auth, OAuth2, Swagger, Actuator  
  - USER & ADMIN → All quantity operations  
  - ADMIN only → Error history endpoint  

### ⚙️ Security Configuration
- Configures `SecurityConfig`:
  - Registers `DaoAuthenticationProvider` with BCrypt
  - Exposes `AuthenticationManager`
  - Adds `JwtAuthenticationFilter` before Spring security filters  

### 🚫 Stateless Security
- Enforces **stateless session policy**:
  - No HTTP sessions  
  - CSRF disabled  
  - No form login / HTTP Basic  

### 🔧 Configuration Properties
- Uses environment-based configuration:
  - JWT settings (`secret`, `expiration`)
  - OAuth2 client credentials
  - Redirect URI for frontend  

### 🧪 Testing
- Adds comprehensive test coverage:
  - JWT functionality  
  - Authentication flows  
  - Security filters  
  - Controller endpoints  
  - Repository interactions  

### 🏗️ Outcome
- Upgrades the system to a **secure, scalable, and production-ready backend** with modern authentication standards and extensible security design.

### 🧰 Tech Stack

- **Java 17+** — core language and application development  
- **Maven** — build automation and dependency management  
- **JUnit 5** — unit testing framework supporting TDD workflow

### ▶️ Build / Run

 - Build the project:
  
    ```
    mvn clean install
    ```

- Run tests:
    
    ```
    mvn test
    ```
```

## ⚙️ Development Approach

> This project adopts a structured and incremental **Test-Driven Development (TDD)** methodology:

- Test cases are created first to clearly define the expected behavior.
- Implementation is written to make the tests pass.
- Each Use Case adds functionality through small, manageable increments.
- Refactoring is performed regularly to improve design without breaking existing behavior.
- The system gradually evolves into a clean, maintainable, and thoroughly tested codebase.
