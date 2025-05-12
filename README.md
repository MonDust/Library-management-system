# Library-management-system 📚 
A microservices-based system for managing libraries and their books, developed as part of the Internet Services Architectures course. (RAR archive)

## Overview
- **Library Manager** — Microservice for managing library categories (Spring Boot).
- **Book Manager** — Microservice for managing books within libraries (Spring Boot).
- **Library System Gateway** - connector between those systems (Spring Cloud Gateway) - requests,
- **"NewJsProject"/JSViews** - Angular-based GUI for interacting with the system.
- **Docker Compose**

## Lab45-previousversion
Rar with the pre-Docker containers suitable implementation. To run it, it is necessary to run:
`
npm install
`
To install all the needed modules (in "NewJSProject").

## Lab6/general project in repository
A Docker containers suitable implementation with external database (Docker, Docker Compose).

## Repository Structure

| Path                          | Description                                                       |
|-------------------------------|-------------------------------------------------------------------|
| `Lab45-previousversion/`      | Pre-Docker version of the system (RAR archive).                  |
| `Lab6/`                       | Production-ready Dockerized version with external databases.     |
| `LibraryManager/`             | Spring Boot microservice for managing libraries.                 |
| `BookManager/`                | Spring Boot microservice for managing books.                     |
| `Gateway/`                    | Spring Cloud Gateway application for routing.                   |
| `NewJsProject/` or `JSViews/` | Angular application providing a web-based user interface.        |

  
