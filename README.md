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
  │   │                   └── 📄 QuantityLength.java
  │   │                   └── 📄 QuantityMeasurementApp.java
  │   │
  │   └── 📁 test
  │       └── 📁 java
  │           └── 📁 com
  │               └── 📁 app
  │                   └── 📁 quantitymeasurement
  │                    └── 📄 LengthMeasurementTesting.java 
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
