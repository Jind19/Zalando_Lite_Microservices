# 🔐 Auth Service

**Purpose:**  
Handles Google login and securely issues JWT tokens for authenticated users.  
Also provides a way for internal services to programmatically request tokens.

---

## 📌 Responsibilities

- Google OAuth2 login using `spring-boot-starter-oauth2-client`
- Issues **ID Tokens (JWT)** after successful login
- Provides internal token issuance via REST endpoint
- Secures communication between microservices using JWT tokens

---

## 🚀 Features & Endpoints

| Endpoint                  | Method | Auth Required | Description                                |
| `/`                       | GET    | No            | Optional public home page                  |
| `/my_login`               | GET    | No            | Custom login page (Thymeleaf template)     |
| `/dashboard`              | GET    | Yes           | Shows logged-in user info and ID token     |
| `/token`                  | GET    | Yes           | Returns the authenticated user’s ID token  |


---