# Java Code Review Assistant

An AI-powered code review platform built with **Spring Boot**, **JavaParser**, **LangChain4j**, and **Google Gemini**.

The application performs both **static code analysis** and **AI-driven code review**, providing developers with actionable feedback on Java code quality, maintainability, complexity, and design.

---

## Features

### Static Code Analysis

* Detects methods with excessive parameters
* Detects long methods
* Detects high-complexity methods
* Extensible analyzer architecture
* JavaParser-based AST analysis

### AI-Powered Review

* Google Gemini integration via LangChain4j
* Architectural and maintainability insights
* Design recommendations
* Readability improvements
* Security and code smell detection
* Structured JSON findings

### REST APIs

* Spring Boot REST endpoints
* Swagger/OpenAPI documentation
* Request validation
* Global exception handling

---

## Architecture

```text
Client
   |
   v
CodeReviewController
   |
   +--------------------+
   |                    |
   v                    v
ReviewService      AiReviewService
   |                    |
   v                    v
ReviewEngine       Google Gemini
   |
   v
Analyzers
   |
   +--> TooManyParametersAnalyzer
   +--> LongMethodAnalyzer
   +--> ComplexityAnalyzer
```

---

## Tech Stack

| Technology        | Purpose                       |
| ----------------- | ----------------------------- |
| Java 24           | Core Language                 |
| Spring Boot       | REST API Framework            |
| JavaParser        | AST Parsing & Static Analysis |
| LangChain4j       | LLM Integration               |
| Google Gemini     | AI Code Review                |
| OpenAPI / Swagger | API Documentation             |
| JUnit 5           | Unit Testing                  |
| Maven             | Build Tool                    |

---

## API Endpoints

### Static Review

```http
POST /review/staticreview
```

Performs rule-based static code analysis.

---

### AI Review

```http
POST /review/aireview
```

Performs AI-powered code review using Gemini.

---

### Combined Review

```http
POST /review
```

Returns both static analysis findings and AI-generated review findings.

---

## Sample Request

```json
{
  "code": "public class EmployeeService { public void createEmployee(String firstName,String lastName,String email,String phone,String city,String state,String country){ if(firstName!=null){ if(email!=null){ if(phone!=null){ System.out.println(firstName+email+phone); }}}}}"
}
```

---

## Sample Response

```json
{
  "staticFindings": [
    {
      "rule": "TOO_MANY_PARAMETERS",
      "severity": "WARNING",
      "message": "Method createEmployee exceeds the recommended number of parameters (7 parameters)"
    }
  ],
  "aiFindings": [
    {
      "severity": "HIGH",
      "category": "Maintainability",
      "message": "Method 'createEmployee' has too many parameters. Consider encapsulating related parameters into a dedicated DTO or object."
    },
    {
      "severity": "MEDIUM",
      "category": "Readability",
      "message": "Deeply nested if statements reduce readability."
    }
  ]
}
```

---

## Running Locally

### Clone Repository

```bash
git clone <repository-url>
```

### Configure Gemini API Key

Create an environment variable:

```bash
GEMINI_API_KEY=your_api_key
```

### Run Application

```bash
mvn spring-boot:run
```

### Swagger UI

```text
http://localhost:8080/swagger-ui.html
```

---

## Future Enhancements

* AI-generated code fixes
* AI-generated unit tests
* Architecture review mode
* Performance optimization for AI requests
* Custom exception hierarchy
* Kafka integration
* Review history and persistence

---

## Learning Objectives

This project was built to explore:

* Static code analysis using AST parsing
* Spring Boot application architecture
* AI integration using LangChain4j
* Prompt engineering for structured AI responses
* Combining deterministic and LLM-based review techniques
* Designing extensible analyzer frameworks

```
```
