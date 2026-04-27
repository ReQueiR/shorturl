# ShortUrl

A simple URL shortener REST API built with Kotlin and Spring Boot.

## Features
* Shorten long URLs with a random code or custom alias
* Redirect to original URL via short link
* Click tracking 
* Link expiration date
* Input validation

## Tech Stack

* Kotlin + Spring Boot 4
* PostgreSQL
* Spring Data JPA
* Bean Validation

## Getting Started

### Prerequisites

* Java 24+
* PostgreSQL

### Database setup

```sql
CREATE DATABASE urlshortener;
CREATE USER urluser WITH PASSWORD 'urlpassword';
GRANT ALL PRIVILEGES ON DATABASE urlshortener TO urluser;
GRANT ALL ON SCHEMA public TO urluser;
```

### Configuration

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/urlshortener
spring.datasource.username=urluser
spring.datasource.password=urlpassword
```

### Run

```bash
./gradlew bootRun
```

The app will be available at `http://localhost:8080`.

## API

### Shorten a URL

`POST /shorten`

```json
{
  "url": "https://example.com",
  "alias": "my-link",
  "expiresAt": "2026-12-31T23:59:59"
}
```

### Redirect

`GET /{code}` - redirects to the original URL (HTTP 302)

### Stats

`GET /stats/{code}` - returns link details and click count

## Error codes

| Status | Meaning                   |
|--------|---------------------------|
| 400    | Invalid input             |
| 404    | Link not found or expired |
| 409    | Alias already taken       |