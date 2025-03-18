# POC 2: Data Migration & ETL Pipeline with Java & Spring Boot

This repository contains a proof-of-concept (POC) for a comprehensive data migration and ETL pipeline built using Java, Spring Boot, and Apache Spark. The workflow includes:

1. **Extracting Data from PostgreSQL (Dockerized):**  
   Export data from a PostgreSQL database running in a Docker container into CSV format.

2. **Uploading to GCP Cloud via MFT:**  
   Transfer the CSV files to Google Cloud Platform (GCP) using a Managed File Transfer (MFT) solution for further processing.

3. **Loading Data into Target PostgreSQL:**  
   Extract the CSV data from the cloud and load it into a target PostgreSQL instance hosted on GCP.

4. **ETL Pipeline Using Apache Spark and Hive:**  
   Utilize Apache Spark for data transformation and processing, and integrate with Apache Hive for data warehousing.

5. **Spring Boot Integration:**  
   Use Spring Boot to provide a REST API endpoint for triggering the ETL process and orchestrating additional business logic.

## Table of Contents
- [Overview](#overview)
- [Project Components](#project-components)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [How to Build and Run](#how-to-build-and-run)
- [Contributing](#contributing)
- [License](#license)

## Overview

This project demonstrates an end-to-end data pipeline that extracts data from a local PostgreSQL database, uploads it to GCP, and finally processes and loads the data into a target PostgreSQL database. The ETL process leverages Apache Spark (using the Java API) for data transformation, integrates with Apache Hive for storing processed data, and utilizes Spring Boot to expose a RESTful interface for orchestration and monitoring.

## Project Components

- **Dockerized PostgreSQL:**  
  Run PostgreSQL in a Docker container, create a database, and export data to CSV using PostgreSQL’s `COPY` command.

- **Managed File Transfer (MFT):**  
  Securely transfer CSV files to a designated staging area on GCP.

- **GCP Cloud PostgreSQL:**  
  Use Google Cloud SQL for PostgreSQL as the target database.

- **ETL Pipeline:**  
  - **Extraction:** Read CSV files using Apache Spark (Java API).
  - **Transformation:** Clean, enrich, and transform data using Spark DataFrames.
  - **Loading:** Write the transformed data into Apache Hive and/or load it into the target PostgreSQL instance via JDBC.

- **Spring Boot Application:**  
  Provide REST endpoints to trigger ETL jobs and handle additional orchestration logic. The Spring Boot service also exposes APIs for monitoring and managing the pipeline.

## Prerequisites

- [Docker](https://www.docker.com/)
- [PostgreSQL Docker image](https://hub.docker.com/_/postgres)
- [Google Cloud Platform (GCP) account](https://cloud.google.com/)
- [Java JDK 11 or higher](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Apache Maven](https://maven.apache.org/) (for building the project)
- [Apache Spark](https://spark.apache.org/) (with Java support)
- [Apache Hive](https://hive.apache.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [PostgreSQL JDBC Driver](https://jdbc.postgresql.org/)

## Setup Instructions

1. **Dockerized PostgreSQL Setup:**  
   - Pull and run the PostgreSQL Docker image:
     ```sh
     docker pull postgres:latest
     docker run --name postgres-poc -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres
     ```
   - Connect to the container and create your database and table:
     ```sh
     docker exec -it postgres-poc psql -U postgres
     ```
   - Export data to CSV using:
     ```sql
     COPY my_table TO '/tmp/my_table.csv' CSV HEADER;
     ```
   - Ensure that the host directory is mounted to access the CSV file outside the container if needed.

2. **MFT Configuration:**  
   - Configure your Managed File Transfer tool to move the CSV file from your local or Docker environment to GCP.

3. **GCP Cloud PostgreSQL Setup:**  
   - Set up a Cloud SQL for PostgreSQL instance on GCP.
   - Configure the appropriate network connectivity and authentication.

4. **ETL Pipeline Setup:**  
   - Configure Apache Spark with Hive support.
   - Create Java classes that use the Spark API to read, transform, and write data.
   - Use the PostgreSQL JDBC driver to load data into the target database.

5. **Spring Boot Application Setup:**  
   - The Spring Boot application exposes REST endpoints to trigger the ETL process.
   - Update the `application.properties` (or `application.yml`) file with necessary configurations like database URLs, Spark settings, etc.
   - Example Maven dependency for Spring Boot:
     ```xml
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-web</artifactId>
     </dependency>
     ```
   - Include dependencies for Spark and JDBC as needed.

## How to Build and Run

### **Build the Application**

Use Maven to build your Spring Boot application:
```sh
mvn clean package
