# 🌍 [DisasterRelief]


## 📝 Project Overview
Natural and man-made disasters are an ongoing problem of the current world.
People are left on their own often without supplies or medical help.
Our project and API aim to resolve this issue by providing methods for
sending out support vehicles and monitoring the progress of the disaster's relief.

---

## ⚙️ Tech Stack & Prerequisites
To run this application locally, ensure your machine meets the minimum version constraints outlined below:

*   **Language & Runtime:** [Java]
*   **Web Framework:** [Spring Boot]
*   **Database:** [PostgreSQL]
*   **Production Dependencies:**
    *   `spring-boot-starter-data-jpa` - Communication with the database.
    *   `spring-boot-starter-webmvc` - Managing endpoints.

---

## 🚀 Installation & Setup
Follow these precise steps to provision your local development environment:

1. **Clone** the remote repository from GitHub:
   ```bash
   git clone https://github.com/TheBigBrand/DisasterRelief
   cd DisasterRelief
   ```

2. **Configure** your environment variables by copying the template file:
   ```bash
   cp .env.example .env
   ```
   *(Open the newly created `.env` file and insert your local database credentials).*

5. **Start** the application (through an IDE).

---

## 📖 API Reference Guide

## TO DO

All request payloads and response bodies must strictly communicate via valid JSON.

| HTTP Method | URL Path | Required JSON Payload Keys | Expected Status | Description / Purpose |
| :--- | :--- | :--- | :---: | :--- |
| `GET` | `/api/v1/resources` | None | `200 OK` | Retrieves a filtered list of all environmental data points. |
| `POST` | `/api/v1/resources` | `{"title": "string", "quantity": "number"}` | `21 Created` | Creates a new record in the ecosystem tracker. |
| `PUT` | `/api/v1/resources/:id` | `{"quantity": "number"}` | `200 OK` | Updates specific metrics of an existing issue tracker entry. |
| `DELETE` | `/api/v1/resources/:id` | None | `204 No Content` | Removes a corrupted or duplicate entry from the live database. |

### 🛑 Standard Error Handling Response

## TO DO

When data validation fails or unexpected edge cases occur, the API guarantees a unified response schema:

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "Field 'quantity' must be a positive integer value.",
  "timestamp": "2026-10-07T14:02:00Z"
}
```


