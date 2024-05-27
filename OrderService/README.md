# Order Service

The Order Service is a service that provides the functionality to create, read, update, and delete orders.

## Description

The service provides the following functionalities:
- Create an order
- Read an order 

When an order is created, the service sends a message to the Payment Service to create a payment 
and sends a message to the Item Service to update the stock of the item.

If the updating stock and payment are successfully done, the service sends an confirmation email to the customer.(To be implemented)

If the updating stock and payment are not successfully done, the service sends an email to the customer to inform the failure of the order.(To be implemented)

## Inter-service communication

The service communicates with the following services via gRPC:
- Member Service
- Item Service

The service communicates with the following services via Kafka:
- Payment Service
- Item Service

The service uses Kafka to communicate with the Payment Service and the Item Service.
The service sends a message to the Payment Service to create a payment and sends a message to the Item Service to update the stock of the item.

If the updating stock and payment are successfully done, the service sends an confirmation message to each service.

If the updating stock and payment are not successfully done, the service sends an error message to each service for rollback.

The service change the status of the order with the received message from the Payment Service and the Item Service via kafka.
Kafka Stream is used to manage the status of the order.

## Setup


### Install the dependencies

```bash
gradle build

docker image build -t order-service .
```


### Run the service

```bash
docker run -p 8200:8200 order-service
```

## Requirements

- Java 17
- Gradle 7.2
- MySQL 8.0.26
- gRPC
- Kafka
- Kafka Streams
- Docker
- Zipkin
