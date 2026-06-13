# Java Code Review Assistant

## Overview

A Spring Boot application that performs static code analysis on Java source code using JavaParser.

## Features

- Too Many Parameters Detection
- Long Method Detection
- Cyclomatic Complexity Analysis
- REST API
- OpenAPI / Swagger Support
- Request Validation
- Global Exception Handling

## Tech Stack

- Java 24
- Spring Boot
- JavaParser
- OpenAPI
- JUnit 5
- Mockito

## Architecture

Controller
↓
Service
↓
Review Engine
↓
Analyzers

## API

POST /review

### Request

...

### Response

...

## Run

mvn spring-boot:run