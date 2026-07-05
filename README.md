# Diet App Backend

Spring Boot + Java learning project for backend interviews.

Current module:

- Calorie target calculation using Strategy Pattern
- Plain Java strategy interfaces for BMR and goal adjustment
- Spring Boot only for dependency injection and REST API wiring

## Run Tests

```powershell
mvn test
```

## Run App

```powershell
mvn spring-boot:run
```

## Sample API

```http
POST /api/calories/target
Content-Type: application/json

{
  "age": 28,
  "weightKg": 80,
  "heightCm": 175,
  "gender": "MALE",
  "activityLevel": "MODERATE",
  "goalType": "CUT",
  "bodyFatPercentage": 20,
  "medicalConditions": ["PCOS"]
}
```
