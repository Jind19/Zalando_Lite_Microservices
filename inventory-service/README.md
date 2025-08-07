# 📦 Inventory Service

**Purpose**: Handles product stock tracking across the system.  
Backed by MongoDB for document-oriented flexibility and performance.

---
**Container Port**: 8388

## 📌 Responsibilities

- Maintain real-time product inventory.
- Support stock updates during order placement.
- Provide inventory availability on request.
- Secure all endpoints using JWT-based authentication.

---

## 📫 Exposed Endpoints

| Method | Endpoint                        | Description                        | Auth Required |
|--------|----------------------------------|------------------------------------|---------------|
| GET    | `/api/inventory/{productId}`    | Get available quantity for product | ✅ Yes        |
| POST   | `/api/inventory`                | Add or update inventory            | ✅ Yes        |

---

## 🗃️ Example API Usage

### 🔍 GET /api/inventory/{productId}

**Response:**
```json
{
  "productId": 1,
  "quantity": 50
}
