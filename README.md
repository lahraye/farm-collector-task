# Farm Collector API
This service allows the organisation to collect and manage Farmers' Farm field information
### Farm Collector API ###

* Name: Farm Collector Service
* Version: 1.0.0

## Application Requirements
- The full overview of the requirement can be found [click here](Coding-task.docx).

## Architechtural Choice
This project is built using the ### Layered architecture ### [Layered Architecture](layered-architecture.webp)

### Technologies used ###

The technologies used for developing this application includes:
* Java 17
* Spring boot 3.3.4
* Flyway (For database migrations)
* Postgres Database
* JOOQ (for the persistence layer and dynamic query). see documentation [here](https://www.jooq.org/)
* docker compose for running in local environment
* github for version control

### To set up on local environment ###

* Ensure docker is installed and running on your machine [docker](https://www.docker.com/)
* [Git](https://git-scm.com/)
* [OpenJDK 17](https://adoptium.net/temurin/releases)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) or any other suitable IDE of choice
* configure the environment variables like the following to ovveride the defaults set in the application.yaml file
    - FARMCOLLECTOR_SERVICE_DATASOURCE_URL=jdbc:postgresql://localhost:5432/farmcollector
    - FARMCOLLECTOR_SERVICE_DATASOURCE_USERNAME=postgres
    - FARMCOLLECTOR_SERVICE_DATASOURCE_PASSWORD=postgres

### Running locally ####
The code contains a docker compose file [docker-compose](compose.yaml). This quickly builds the service and spin up a postgres database to run the application locally
To run the app, do the following:
- navigate to the directory of the project
- run `docker compose up`

### Testing Locally ###
The code contains a [api-docs.json](./api-docs.json) to test locally. you can also visit [swagger page](http://localhost:8080/swagger-ui.html)


### Sample API Requests ###

##### Operation: Collect Information from Farmer #####
```
URL: localhost:8080/api/v1/farm-collections
Method: POST
Request Payload:

{
    "farmFieldId": 1,
    "year": 2024,
    "farmFieldDetails": [
        {
            "cropId": 1,
            "cropFarmingDetails": [
                {
                    "season": "SPRING",
                    "plantingAreaInAcres": 10,
                    "amountOfExpectedProduct": 12.0
                },
                {
                    "season": "FALL",
                    "actualAmountOfProductHarvested": 10.0
                }
            ]
        }
    ]
}

Response Payload: 
{
    "farmId": 1,
    "farmFieldId": 1
}
```


##### Operation: Collect Information from Farmer #####
```
URL: localhost:8080/api/v1/farm-collections
Method: POST
Request Payload:

{
    "farmFieldId": 1,
    "year": 2024,
    "farmFieldDetails": [
        {
            "cropId": 1,
            "cropFarmingDetails": [
                {
                    "season": "SPRING",
                    "plantingAreaInAcres": 10,
                    "amountOfExpectedProduct": 12.0
                },
                {
                    "season": "FALL",
                    "actualAmountOfProductHarvested": 10.0
                }
            ]
        }
    ]
}

Response Payload: 
{
    "farmId": 1,
    "farmFieldId": 1
}

### Who do I talk to? ###

* [Showemimo Olarewaju](lahraye@gmail.com)
