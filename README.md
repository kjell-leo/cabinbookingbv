# Cabin Booking - Bolagsverket

## Overview

This is a Spring Boot application serving a REST API and a Vue frontend. It is built with Maven and requires Java 23.

## Prerequisites

- Java 23 (JDK 23) - https://www.oracle.com/se/java/technologies/downloads
- Apache Maven - https://maven.apache.org/download.cgi

## Running the Application

### Steps:
1. Clone the Repository:
   ```sh
   git clone https://github.com/kjell-leo/cabinbookingbv.git
   ```
2. Navigate to the project directory:
   ```sh
   cd .\cabinbookingbv
   ```
3. Run the Maven build command to compile and package the project:
   ```sh
   mvn clean install
   ```
4. Navigate to the target directory:
   ```sh
   cd .\target
   ```
5. Run the application:
   ```sh
   java -jar .\cabinbookingbv-0.0.1-SNAPSHOT.jar
   ```

### Accessing the Application

- **Frontend:** [http://localhost:8080](http://localhost:8080)
- **Admin Area:** [http://localhost:8080/admin](http://localhost:8080/admin)
    - **Username:** admin
    - **Password:** admin

