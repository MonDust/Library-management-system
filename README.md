# Library-management-system 📚 
A microservices-based system for managing libraries and their books, developed as part of the Internet Services Architectures course. (RAR archive)

## Overview
- **Library Manager** — Microservice for managing library categories (Spring Boot).
- **Book Manager** — Microservice for managing books within libraries (Spring Boot).
- **API Gateway** - connector between those systems (Spring Cloud Gateway) - requests,
- **Frontend Client** - Angular-based GUI for interacting with the system.
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
| `Lab6/`                       | Dockerized version with external databases.     |
| `LibraryManager/`             | Spring Boot microservice for managing libraries.                 |
| `BookManager/`                | Spring Boot microservice for managing books.                     |
| `Gateway/`                    | Spring Cloud Gateway application for routing.                   |
| `NewJsProject/` or `JSViews/` | Angular application providing a web-based user interface.        |

  
## Technologies Used 🛠️
- Java SE
- Spring Boot (with Spring MVC and Spring Data)
- PostgreSQL (external databases | Lab6)
- H2 Database (Lab 45)
- Angular Framework
- Maven
- Docker & Docker Compose
- RESTful APIs
- Project Lombok

## How to Run 🚀

### Pre-Docker Version (Lab 4–5)

#### Backend
1. Open the project folders:
   - `library-management/`
   - `book-management/`
   - `gateway/`
2. Ensure **Java** and **Maven** are installed.
3. Run the projects.
   ```bash
   mvn spring-boot:run

#### Frontend
1. Navigate to `Lab45-previousversion/NewJsProject`.
2. Install frontend dependencies:
   ```bash
   npm install
3. Run it:
   ```bash
   ng serve --proxy-config proxy.conf.json
4. Open your browser and go to: [http://localhost:4200](http://localhost:4200)
#### ⚠️ Make sure all backend services are running before starting the frontend.

## Dockerized Version (Lab6)
1. Ensure Docker and Docker Compose are installed.
2. Navigate to the Lab6 root folder containing docker-compose.yml.
3. Start the full system:
   ```bash
   docker-compose up --build
4. Open your browser and go to: [http://localhost:4200](http://localhost:4200)


## Features Implemented in Angular Frontend
- Library List View – Display and remove library.
- Add Library View – Form to add a new library.
- Edit Library View – Pre-filled form to update a library.
- Library Details View – Details of a library and its books; includes remove option.
- Add Book View – Add a new book to library.
- Edit Book View – Edit an existing book (pre-filled form).
- Book Details View – Display all details of a specific book.
#### ⚠️ Screenshots of views are available in 'Screenshots' folder.

## Author
O. Paszkiewicz (MonDust)
2025
