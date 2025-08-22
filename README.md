
# Directors API

Spring Boot API Consumption Project. 
This is a technical test for ERON International.
A small Spring Boot project that consumes a remote Movies API to analyze directors.  
The service fetches movies from the API and returns a list of directors who have directed more than a given number of movies since 2010.

---

## Table of Contents

- [Overview](#overview)  
- [Technologies](#technologies)  
- [Project Structure](#project-structure)  
- [Getting Started](#getting-started)  
- [Usage](#usage)  
- [Testing](#testing)
- [Postman Collection](#postman-collection)

---

## Overview

This API provides a single endpoint:

```
GET /api/directors?threshold={number}
```

It returns a sorted list of directors who have directed more movies than the given threshold.  
Only movies from 2010 onwards are considered.

For example, if `threshold=2`, only directors with more than 2 movies since 2010 will be returned.

The project is designed to be **easy to understand, maintain, and extend**, with a clear separation of concerns:

- **Controller**: Handles HTTP requests (`DirectorsController`)  
- **Service**: Implements business logic (`DirectorService`)  
- **Client**: Fetches data from the external API (`MovieClient`)  
- **Models**: Maps API responses to Java objects (`Movie`, `MoviePageResponse`)  
- **Exception**: Custom exception for API errors (`RemoteApiException`)  

---

## Technologies

- Java 17  
- Spring Boot 3.5.5  
- Spring Web  
- Spring Validation  
- Jackson (for JSON mapping)  
- JUnit 5 + Mockito (for unit testing)  
- Maven (build tool)

---

## Project Structure

```
directors/
├─ pom.xml
├─ src/
│ ├─ main/
│ │ ├─ java/
│ │ │ └─ com/movies/directors/
│ │ │    ├─ DirectorsApplication.java
│ │ │    ├─ config/RestClientConfig.java
│ │ │    ├─ controller/DirectorsController.java
│ │ │    ├─ service/DirectorService.java
│ │ │    ├─ client/MovieClient.java
│ │ │    ├─ model/Movie.java
│ │ │    ├─ model/MoviePageResponse.java
│ │ │    └─ exception/RemoteApiException.java
│ │ └─ resources/
│ │    └─ application.properties
│ └─ test/
│    └─ java/
│       └─ com/movies/directors/service/DirectorServiceTest.java
└─ README.md
│
└─ directors-test.postman_collection.json
```

---

## Getting Started

**Run the application**:

The application will start on **http://localhost:8080** by default.  
You can change the API base URL in `application.properties`:

```properties
movies.api.base-url=https://wiremock.dev.eroninternational.com
```

---

## Usage

### Endpoint

```
GET /api/directors?threshold={number}
```

- `threshold` (required): Minimum number of movies a director must have directed (0 or higher).  


> The response is sorted alphabetically, ignoring case.

---

## Testing

Unit tests are implemented with JUnit 5 and Mockito.  
The `DirectorServiceTest` verifies that the filtering, counting, and sorting of directors works correctly.

---

## Postman Collection

A Postman collection has been created to test the API endpoints.  
You can import the collection into Postman to run the requests without configuring them manually.

**Instructions to use the Postman collection:**

1. Open Postman.
2. Go to **File → Import → Choose File**.
3. Select the provided `directors-test.postman_collection.json`.
4. Run the request(s) to see the API responses.
3. **Send the request**. You should see a JSON response like:

```json
{
    "directors": [
        "Martin Scorsese",
        "Woody Allen"
    ]
}
```
---
## Notes

- Only movies **from 2010 onwards** are considered.  
- API errors are wrapped in a custom `RemoteApiException`.  
- The project is designed with **separation of concerns** and easy maintainability in mind.  

---

## Author

Fernando Guzmán Mendoza