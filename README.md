# EntitleFlow

EntitleFlow is a small REST API for managing customers, hardware assets, customer orders, and software entitlements. It uses a conventional Spring Boot layered architecture with PostgreSQL persistence.

## Technology

- Java 21 and Spring Boot 3
- Maven, Spring Web, Spring Data JPA, Jakarta Bean Validation
- PostgreSQL
- Swagger UI / OpenAPI

## Prerequisites

- JDK 21
- Maven 3.6+
- PostgreSQL 14+ (or a compatible current PostgreSQL release)

## Configure PostgreSQL

Create a database and application user, for example:

```sql
CREATE USER entitleflow WITH PASSWORD 'change-me';
CREATE DATABASE entitleflow OWNER entitleflow;
```

The application reads `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD`. Defaults target `jdbc:postgresql://localhost:5432/entitleflow` with the example username and password above. Override them in your shell; do not commit real credentials. A sample is in `.env.example` (Spring Boot does not load that file automatically).

```powershell
$env:DB_URL = 'jdbc:postgresql://localhost:5432/entitleflow'
$env:DB_USERNAME = 'entitleflow'
$env:DB_PASSWORD = 'your-local-password'
```

Hibernate creates or updates the schema on startup. For production deployments, use a managed schema migration process and set an appropriate DDL mode.

## Run

From the project root:

```bash
mvn spring-boot:run
```

The API listens on `http://localhost:8080` by default. Set `PORT` to change the port.

## API endpoints

All IDs are UUIDs. JSON request fields are validated; errors use a consistent JSON response.

| Method | Endpoint | Purpose |
| --- | --- | --- |
| GET, POST | `/api/customers` | List customers / create customer |
| GET, PUT, DELETE | `/api/customers/{id}` | Read, update, or delete a customer |
| GET, POST | `/api/assets` | List assets / register asset |
| GET | `/api/assets/{id}` | Read an asset |
| GET, POST | `/api/orders` | List orders / create an order |
| GET | `/api/orders/{id}` | Read an order |
| PATCH | `/api/orders/{id}/status` | Change order status |
| GET, POST | `/api/entitlements` | List entitlements / create entitlement |
| GET | `/api/entitlements/{id}` | Read an entitlement |
| POST | `/api/entitlements/{id}/activate` | Activate a revoked entitlement |
| POST | `/api/entitlements/{id}/revoke` | Revoke an active entitlement |

Assets default to `AVAILABLE`; supported statuses are `AVAILABLE`, `RESERVED`, and `ALLOCATED`. Orders begin as `CREATED`; allowed status values are `CREATED`, `PROCESSING`, `COMPLETED`, and `FAILED`. Entitlements begin as `ACTIVE` and support `ACTIVE`, `REVOKED`, and `EXPIRED`. Customer email and asset serial number are unique.

### Example requests

Create a customer:

```json
{"name":"Avery Chen","email":"avery@example.com","company":"Example Networks"}
```

Create an order (use the returned customer UUID):

```json
{"customerId":"00000000-0000-0000-0000-000000000000","productModel":"Router X1","quantity":1}
```

Create an entitlement:

```json
{"customerId":"00000000-0000-0000-0000-000000000000","productModel":"Network Suite","activationDate":"2026-10-18","expiryDate":"2027-10-18"}
```

## API documentation

Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

OpenAPI JSON: `http://localhost:8080/v3/api-docs`
