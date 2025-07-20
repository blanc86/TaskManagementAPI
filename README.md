# Task Management API

A simple RESTful Task Management API built with Spring Boot 3.5.3.

## Features
- Create, read, update, and delete tasks
- Task validation
- Error handling
- H2 in-memory database
- Unit and integration tests

## Setup Instructions

1. **Prerequisites**
   - Java 17 or higher
   - Maven 3.6 or higher

2. **Running the Application**
   ```bash
   mvn spring-boot:run
   ```

3. **Access H2 Console**
   - URL: http://localhost:8080/h2-console
   - JDBC URL: jdbc:h2:mem:testdb
   - Username: sa
   - Password: (leave empty)

## API Endpoints

### 1. Get All Tasks
```
GET /api/tasks
```

### 2. Get Task by ID
```
GET /api/tasks/{id}
```

### 3. Create Task
```
POST /api/tasks
Content-Type: application/json

{
  "title": "Complete Spring Boot Assignment",
  "description": "Build a task management API",
  "status": "PENDING",
  "priority": "HIGH",
  "dueDate": "2024-02-15"
}
```

### 4. Update Task
```
PUT /api/tasks/{id}
Content-Type: application/json

{
  "title": "Updated Title",
  "description": "Updated description",
  "status": "IN_PROGRESS",
  "priority": "MEDIUM",
  "dueDate": "2024-02-20"
}
```

### 5. Delete Task
```
DELETE /api/tasks/{id}
```

## Sample Test Cases

### Test Case 1: Create Valid Task
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Complete Assignment",
    "description": "Build task management API",
    "status": "PENDING",
    "priority": "HIGH",
    "dueDate": "2024-02-15"
  }'
```
**Expected**: 201 Created

### Test Case 2: Invalid Data
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Hi",
    "description": ""
  }'
```
**Expected**: 400 Bad Request with validation errors

### Test Case 3: Get Non-existent Task
```bash
curl -X GET http://localhost:8080/api/tasks/999
```
**Expected**: 404 Not Found

## Running Tests
```bash
mvn test
```

## Enums
- **TaskStatus**: PENDING, IN_PROGRESS, COMPLETED
- **Priority**: LOW, MEDIUM, HIGH
