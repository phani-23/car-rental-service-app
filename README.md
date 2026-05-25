# 🚗 Car Rental Service (Spring Boot)

A **Car Rental Management System** built with **Spring Boot, Spring Data JPA, and MySQL**, providing RESTful APIs for managing cars, customers, and rental bookings. Tested thoroughly using **Postman** collections.

---

## ✨ Features
- **Car Management**
  - Add, update, view, and delete cars
  - Fetch available cars
- **Customer Management**
  - Register new customers
  - Update customer profiles
  - View customer details
- **Rental Management**
  - Book cars for customers
  - Calculate rental costs
  - View rental history
  - Cancel bookings

---

## 🛠️ Tech Stack
- **Backend:** Spring Boot, Spring MVC, Spring Data JPA
- **Database:** MySQL
- **Testing:** Postman
- **Documentation:** Swagger/OpenAPI

---

## 📌 API Endpoints (Sample)
### Car APIs
- `POST /api/cars` → Add a new car  
- `GET /api/cars/available` → Fetch available cars  

### Customer APIs
- `POST /api/customers` → Register a customer  
- `GET /api/customers/{id}` → View customer details  

### Rental APIs
- `POST /api/rentals/book` → Book a car  
- `GET /api/rentals/{id}` → View rental details  
- `DELETE /api/rentals/{id}` → Cancel booking  

---

## 🔍 Postman Testing
- Created **Postman collections** to validate all endpoints.
- Example request/response:

**Request (Book Car):**
```json
POST /api/rentals/book
{
  "customerId": 1,
  "carId": 2,
  "days": 3
}
