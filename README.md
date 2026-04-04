# 🚀 Quantity Measurement Microservices System

## 📏 TDD | Microservices | Clean Architecture

---

## 🧠 Project Overview

The **Quantity Measurement Application** handles:

* Unit comparison
* Unit conversion
* Arithmetic operations

Built using:

* Test-Driven Development (TDD)
* Microservices Architecture
* Clean Code Practices
* JWT Authentication

---

## 🧠 Tech Stack

| Layer            | Technology           |
| ---------------- | -------------------- |
| Backend          | Spring Boot          |
| Microservices    | Spring Cloud         |
| Service Registry | Eureka Server        |
| API Gateway      | Spring Cloud Gateway |
| Security         | JWT                  |
| Communication    | REST APIs            |

---

## 🏗️ Architecture

```
        API Gateway (8081)
               │
     ┌─────────┴─────────┐
     ▼                   ▼
Auth Server (8083)   QMA Service (8082)
               │
        Eureka Server (8761)
```

---

## ⚙️ Services & Ports

| Service       | Port | Role                 |
| ------------- | ---- | -------------------- |
| Eureka Server | 8761 | Service Registry     |
| API Gateway   | 8081 | Routing + Auth check |
| Auth Server   | 8083 | JWT Authentication   |
| QMA Service   | 8082 | Business Logic       |

---

## 🔄 Flow

```
Client → API Gateway → Service → Response
```

---

## 🚀 Run Order

```bash
cd eureka-server && mvn spring-boot:run
cd api-gateway && mvn spring-boot:run
cd auth-server && mvn spring-boot:run
cd qma-service && mvn spring-boot:run
```

---

## 🌐 URLs

* Eureka: http://localhost:8761
* Gateway: http://localhost:8081
* Auth: http://localhost:8083
* QMA: http://localhost:8082

---
