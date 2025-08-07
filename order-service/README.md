# 🛒 Order Service

**Container Port**: `9091`  
**Description**: 

- Microservice responsible for processing new orders. 
- It validates product stock, applies available discounts, and updates inventory accordingly.  
- Secure and token-aware – forwards JWT tokens to dependent services for authorization.

---

## 📌 Responsibilities

- Receive and process incoming order requests.
- Validate product availability by calling **Inventory Service**.
- Retrieve product details from **Product Service**.
- Check and apply discounts via **Discount Service**.
- Deduct inventory on successful order creation.
- Forward **JWT tokens** to downstream services for secured access.

## 🔄 Service Interactions

```plaintext
[ Client ]
    |
    |--> [ Order Service (8589) ]
              |
              |--> [ Product Service ]
              |--> [ Inventory Service ]
              |--> [ Discount Service ]