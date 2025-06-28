# AI-Assisted Health Symptom Checker & Doctor Booking System

This is a Spring Boot microservices-based backend system that powers an AI-assisted health platform. It allows users to input symptoms, get AI-generated suggestions, and book appointments with doctors. Doctors can manage their schedules, view appointments, and prescribe medication.

## 🧩 Microservices Overview

- **auth-service**: Handles login for users and doctors via JWT.
- **user-service**: Manages user registration, profiles, appointments, and prescriptions.
- **doctor-service**: Manages doctor profiles, specializations, and availability.
- **booking-service**: Coordinates user-doctor appointments.
- **doctor-portal-service**: Doctor dashboard for viewing appointments and prescribing.
- **symptom-checker-service**: Accepts symptom input and returns AI-generated suggestions (supports OpenAI or Ollama via Spring AI).

## 🛠 Tech Stack

- Java 17 + Spring Boot 3
- Spring Security + JWT
- Spring Cloud (Eureka, Gateway)
- PostgreSQL
- Spring AI + Ollama (or OpenAI API)
- Maven

## 🚀 Features

- 🔐 Secure login for doctors and users with JWT
- 🧠 AI-generated suggestions for symptoms
- 🩺 Book and manage appointments with doctors
- 💊 Doctors can add prescriptions to appointments
- 📑 Centralized doctor portal
- 🧭 Service discovery via Eureka
- 📡 API Gateway routing and filtering

## 📂 Folder Structure
microservice-app/
├── auth-service/
├── user-service/
├── doctor-service/
├── booking-service/
├── doctor-portal-service/
├── symptom-checker-service/
├── api-gateway/
├── eureka-server


## 🧪 Running Locally

1. **Install dependencies**
   - PostgreSQL (set up DBs for each service)
   - Java 17
   - Maven

2. **Start Eureka Server**
   - cd eureka-server
   - mvn spring-boot:run

3. **Start API Gateway**
   - cd api-gateway
   - mvn spring-boot:run

4. Start other microservices one by one from their directories:
   - mvn spring-boot:run

5. Symptom Checker with Ollama (optional)
  - Install Ollama: https://ollama.com
  - Run model: ollama run llama3 or ollama run mistral (check CPU support)

🔑 Authentication
JWT tokens are required for all secure routes.

Role-based access:

USER: Access symptom checker, book appointments.

DOCTOR: View appointments, write prescriptions, Manage Availability.

Token is passed in the Authorization: Bearer <token> header.

🧠 AI Integration
Spring AI is used with Ollama or OpenAI.

Replace OpenAiService with OllamaService in symptom-checker-service for local model.

📬 API Endpoints
| Service               | Endpoint              | Description                      |
| --------------------- | --------------------- | -------------------------------- |
| auth-service          | `/api/auth/login`     | Login for users/doctors          |
| user-service          | `/api/users/**`       | User operations & prescriptions  |
| doctor-service        | `/api/doctors/**`     | Doctor profile management        |
| booking-service       | `/api/bookings/**`    | Book and manage appointments     |
| doctor-portal-service | `/api/portal/**`      | Doctor dashboard functions       |
| symptom-checker       | `/api/symptoms/check` | Submit symptoms and get AI reply |

🧼 Environment Configuration
Each service has its own application.properties with:

spring.application.name=user-service
server.port=8081
eureka.client.service-url.defaultZone=http://localhost:8761/eureka

📖 License
This project is built for learning and demo purposes.









