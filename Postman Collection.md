# 🛠 Postman Collection – Task Management API

Import the following JSON into Postman:

```json
{
  "info": {
    "name": "Task Management API",
    "description": "Complete API testing collection"
  },
  "item": [
    {
      "name": "Create Task",
      "request": {
        "method": "POST",
        "header": [{"key": "Content-Type", "value": "application/json"}],
        "body": {
          "raw": "{\n  \"title\": \"Complete Assignment\",\n  \"description\": \"Build task management API\",\n  \"status\": \"PENDING\",\n  \"priority\": \"HIGH\",\n  \"dueDate\": \"2024-02-15\"\n}"
        },
        "url": "http://localhost:8080/api/tasks"
      }
    },
    {
      "name": "Get All Tasks",
      "request": {
        "method": "GET",
        "url": "http://localhost:8080/api/tasks"
      }
    },
    {
      "name": "Get Task by ID",
      "request": {
        "method": "GET",
        "url": "http://localhost:8080/api/tasks/1"
      }
    },
    {
      "name": "Update Task",
      "request": {
        "method": "PUT",
        "header": [{"key": "Content-Type", "value": "application/json"}],
        "body": {
          "raw": "{\n  \"title\": \"Updated Task\",\n  \"description\": \"Updated description\",\n  \"status\": \"IN_PROGRESS\",\n  \"priority\": \"MEDIUM\"\n}"
        },
        "url": "http://localhost:8080/api/tasks/1"
      }
    },
    {
      "name": "Delete Task",
      "request": {
        "method": "DELETE",
        "url": "http://localhost:8080/api/tasks/1"
      }
    },
    {
      "name": "Invalid Task (Validation)",
      "request": {
        "method": "POST",
        "header": [{"key": "Content-Type", "value": "application/json"}],
        "body": {
          "raw": "{\n  \"title\": \"Hi\",\n  \"description\": \"\"\n}"
        },
        "url": "http://localhost:8080/api/tasks"
      }
    },
    {
      "name": "Non-existent Task",
      "request": {
        "method": "GET",
        "url": "http://localhost:8080/api/tasks/999"
      }
    }
  ]
}
