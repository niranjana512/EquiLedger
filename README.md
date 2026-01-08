## EquiLedger
High-Precision Expense Orchestration Engine

EquiLedger is a Spring Boot service designed for accurate financial splitting and debt settlement. It focuses on financial integrity and algorithmic efficiency, demonstrating core SDE 2 engineering skills.

## Key Features
Penny Precision: Uses BigDecimal to solve the $0.01 rounding discrepancy.

Debt Simplification: A Greedy Min-Max Heap algorithm to minimize transaction count.

Concurrency: Implements Optimistic Locking to handle simultaneous updates safely.

Strategy Pattern: Clean LLD for Equal, Exact, and Percentage-based splits.

## Tech Stack
Language: Java 21

Framework: Spring Boot 3.x, Spring Data JPA

Database: PostgreSQL / H2

API: Swagger / OpenAPI

## System Design
EquiLedger follows Domain-Driven Design (DDD) and SOLID principles:

Domain: Pure business logic for balance calculation.

Service: Orchestration of debt settlement and persistence.

API: RESTful endpoints with robust DTO validation.

## 🚦 Getting Started
Run: mvn spring-boot:run

API Docs: Access http://localhost:8080/swagger-ui.html

Settle Debts: Use the /api/v1/settle endpoint to view the minimized transaction graph.
