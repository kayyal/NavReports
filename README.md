# Navigation Report System

## Description

This is a Spring Boot application that provides an API for users to send various types of navigation reports (e.g.,
traffic, accident, etc.). Each report has specific characteristics and a default lifespan that determines how long the
report is active. Some reports need to be reviewed by operators before being published to other users.

The system stores the reports along with the information of the user who sent the report. Active reports are provided to
users who request active reports on the map. When a user takes a route from point A to point B, they send the geometry
of their route to the program, and we provide the active reports along the user's route. The reports along the route are
the reports that are located within a 10-meter radius of the user's route line.

Users can like or dislike reports. Each confirmation should increase the duration of the report being active, and each
non-confirmation should deduct a certain amount (determined in the basic information of the report type, e.g., 2
minutes) from the duration of the report being active.

As mentioned, for the publication of certain types of reports, operators must confirm them, so facilities for operator
login and operation should also be considered.

## Features

- User registration and authentication
- Creating and sending reports with location data
- Operator review and confirmation of reports
- Finding active reports along a user's route
- Liking or disliking reports

## Technologies Used

- Java
- Spring Boot
- Hibernate
- PostGIS

## Setup

### Prerequisites

- Java 8 or higher
- Maven
- PostgreSQL with PostGIS extension

### Installation

1. Clone the repository
   git clone https://github.com/yourusername/yourrepository.git

3. Navigate to the project directory
   cd navReports

5. Build the project
   mvn clean install

4. Run the application
   mvn spring-boot:run

## Usage

### Create a User

 ```bash
curl --location 'http://localhost:8080/signUp/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Sophia",
    "email": "sophia@gmail.com",
    "password": "p@ssw0rd!"
}'

```

### Create a Report

```bash
curl --location 'http://localhost:8080/api/user/create/report'
--header 'Content-Type: application/json'
--header 'Authorization: Basic U29waGlhOnBAc3N3MHJkIQ=='
--data '{ "type": "TRAFFIC", "message": "Traffic is moving slowly around this area.", "location": { "type": "Point", "
coordinates": [59.536471494307136, 36.299506118407525] } }'
```

### Find Reports Near a Route

```bash
curl --location --request GET 'http://localhost:8080/api/user/route'
--header 'Content-Type: application/json'
--header 'Authorization: Basic U29waGlhOnBAc3N3MHJkIQ=='
--data '{ "type": "LineString", "
coordinates": [ [59.5378346524856, 36.300370980492104], [59.536558504403104, 36.29936586959842], [59.53449926545366, 36.300628099803745], [59.53299109044863, 36.30013723492836], [59.532904080352694, 36.3008384695182] ] }'
```

### Find the hour with the most accident report
```bash
curl --location 'http://localhost:8080/reports/accidents/hour' \
--header 'Authorization: Basic RGF2aWQ6bXlzZWNyZXRwYXNzd29yZA==' \
--header 'Cookie: JSESSIONID=2B74679175AA83898F4E3C84A15DA843' \
--data ''
```


