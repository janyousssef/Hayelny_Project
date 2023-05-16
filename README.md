# Hayelny.AI
Hayelny.AI is a disease detection platform powered by Deep Learning. 
This repository contains the source code for the backend app of my graduation project.

## Table of Contents
 - [General info](#general-info)
 - [Installation](#installation)  
 - [Usage](#usage)
 - [Technologies used](#technologies-used)

## General info
This is a backend app for a disease detection platform. It is built using Spring Boot and Java 19.  
It exposes REST APIs to the frontend APP. It also talks to a Deep Learning model.  
The Deep Learning model is trained using a dataset of chest X-ray images.  
Data is stored in a PostgresSQL database.  
### Domain Model
The domain contains the following entities:
- **Doctor:** Represents a doctor, can have many patients
- **Patient:** Represents a patient, can have many images/diagnoses
- **Diagnosis:** Represents a diagnosis, corresponds to one image


## Installation
To install this project, follow these steps:

- Clone this repository.
- Install Java 19.
- Install Maven.
- Run `bash ./scripts/buildrun.sh`.

## Usage
To use this project, follow these steps:  
Run `bash ./scripts/run.sh`  
Open your browser and navigate to http://localhost:8080.

## Technologies used
- Java 19
- Spring Boot 3 (Data JPA, Web, Security, HATEOAS, Actuator)
- Lombok
- Maven
- PostgresSQL
