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

### 📂 Project Structure

```
 📦 QuantityMeasurementApp
│
├── 📁 src
│   │
│   ├── 📁 main
│   │   │
│   │   ├── 📁 java
│   │   │   └── 📁 com
│   │   │       └── 📁 app
│   │   │           └── 📁 quantitymeasurement
│   │   │
│   │   │               ├── 📁 controller
│   │   │               │   └── 📄 QuantityMeasurementController.java
│   │   │               │
│   │   │               ├── 📁 core
│   │   │               │   ├── 📄 ArithmeticOperation.java
│   │   │               │   └── 📄 SupportsArithmetic.java
│   │   │               │
│   │   │               ├── 📁 entity
│   │   │               │   ├── 📄 QuantityDTO.java
│   │   │               │   ├── 📄 QuantityModel.java
│   │   │               │   └── 📄 QuantityMeasurementEntity.java
│   │   │               │
│   │   │               ├── 📁 exception
│   │   │               │   └── 📄 QuantityMeasurementException.java
│   │   │               │
│   │   │               ├── 📁 repository
│   │   │               │   ├── 📄 H2ConnectionManager.java
│   │   │               │   ├── 📄 IQuantityMeasurementRepository.java
│   │   │               │   └── 📄 QuantityMeasurementH2Repository.java
│   │   │               │
│   │   │               ├── 📁 service
│   │   │               │   ├── 📄 IQuantityMeasurementService.java
│   │   │               │   └── 📄 QuantityMeasurementServiceImpl.java
│   │   │               │
│   │   │               ├── 📁 unit
│   │   │               │   ├── 📄 IMeasurable.java
│   │   │               │   ├── 📄 LengthUnit.java
│   │   │               │   ├── 📄 WeightUnit.java
│   │   │               │   ├── 📄 VolumeUnit.java
│   │   │               │   ├── 📄 TemperatureUnit.java
│   │   │               │   └── 📄 Quantity.java
│   │   │               │
│   │   │               └── 📄 QuantityMeasurementApp.java
│   │
│   │
│   ├── 📁 resources
│   │   └── 📄 schema.sql
│   │
│   └── 📁 test
│       └── 📁 java
│           └── 📁 com
│               └── 📁 app
│                   └── 📁 quantitymeasurement
│                       └── 📄 QuantityMeasurementAppTest.java
│
└── 📘 README.md
```

## ⚙️ Development Approach

> This project adopts a structured and incremental **Test-Driven Development (TDD)** methodology:

- Test cases are created first to clearly define the expected behavior.
- Implementation is written to make the tests pass.
- Each Use Case adds functionality through small, manageable increments.
- Refactoring is performed regularly to improve design without breaking existing behavior.
- The system gradually evolves into a clean, maintainable, and thoroughly tested codebase.
