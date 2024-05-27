# Review Service

The Review Service is a service that provides the functionality to create, read, update, and delete reviews.

## Description

The service provides the following functionalities:
- Create a review
- Read a review
- Update a review
- Delete a review
- Get all reviews of an item
- Get all reviews of a member
- Get the average rating of an item (To-do)
- Get the average rating of a member (To-do)
- Get the number of reviews of an item (To-do)
- Get the number of reviews of a member (To-do)


## Inter-service communication

The service communicates with the following services via gRPC:
- Member Service
- Item Service
- Order Service

## Setup


### Install the dependencies

```bash
gradle build

docker image build -t review-service .
```


### Run the service

```bash
docker run -p 8000:8000 review-service
```

## Requirements

- Java 17
- Gradle 7.2
- MySQL 8.0.26
- gRPC
- Docker
- Zipkin
