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
  │   ├── 📁 main
  │   │   └── 📁 java
  │   │       └── 📁 com
  │   │           └── 📁 app
  │   │               └── 📁 quantitymeasurement
  │   │                   └── 📄 LengthUnit.java
  │   │                   └── 📄 IMeasurable.java
  |   |                   └── 📄 WeightUnit.java
  |   |                   └── 📄 Quantity.java
  │   │                   └── 📄 QuantityMeasurementApp.java
  |   |               
  │   │
  |   |
  |   |
  │   └── 📁 test
  │       └── 📁 java
  │           └── 📁 com
  │               └── 📁 app
  │                   └── 📁 quantitymeasurement
  │                    └── 📄 QuantityMeasurementAppTest.java 
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
