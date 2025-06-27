

# 🧠 ForoHub - BackEnd API

This is the backend REST API for **ForoHub**, a learning forum inspired by Alura's discussion platform. The API was developed as part of a backend challenge to create a forum system with user authentication and topic management using **Spring Boot**.

---

## 🚀 Features

✅ Secure REST API with JWT authentication
✅ Role-based access control (e.g., USER)
✅ Full CRUD for forum topics:

* Create a new topic
* List all topics (with filters and pagination)
* View a specific topic
* Update topic details
* Delete topic

✅ Topic filtering by course name and creation year

✅ Pagination support (10 items per page by default)

✅ Validation of requests

✅ Swagger UI enabled for testing the endpoints

---

## 🛠️ Tech Stack

* Java 21
* Spring Boot 3.3.0
* Spring Security
* Spring Data JPA
* MySQL + Flyway
* JWT (jjwt 0.11.5)
* Lombok
* Swagger (springdoc-openapi 2.5.0)

---

## 🔐 Authentication

All protected endpoints require a valid **JWT Bearer token**.
Use `/auth/login` to obtain your token by providing valid credentials.

Example:

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

---

## 📄 API Endpoints

| Method | Endpoint         | Description                    | Auth Required |
| ------ | ---------------- | ------------------------------ | ------------- |
| POST   | `/auth/register` | Register a new user            | ❌             |
| POST   | `/auth/login`    | Authenticate and get JWT token | ❌             |
| POST   | `/topic/new`     | Create a new topic             | ✅             |
| GET    | `/topic`         | List topics (with filters)     | ✅             |
| GET    | `/topic/{id}`    | Get topic by ID                | ✅             |
| PUT    | `/topic/{id}`    | Update topic                   | ✅             |
| DELETE | `/topic/{id}`    | Delete topic                   | ✅             |

---

## 🔎 Swagger Docs

After starting the app, you can explore and test the API via Swagger UI:

**→ [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**


## 📌 Challenge Context

This project was developed as part of the **One Back End Challenge** from Alura.
The goal was to implement a secure, database-driven forum API in Java using best practices in RESTful design, validation, and authentication.

---
