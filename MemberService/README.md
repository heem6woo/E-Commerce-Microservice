
# Member Service

This service is responsible for managing the members of the organization. It provides the following functionalities:

- Register a new member
- Delete a member
- Change a password
- Read/Write/Change member(customer/seller) details
- Get an Access Token
- Login (get an Access Token and refresh token)
- Logout (delete the refresh token)


## Description

JWT is used for authentication and authorization. The service provides an access token and a refresh token.
The access token is used to authenticate the user and the refresh token is used to get a new access token when the access token expires.
Refresh tokens are stored in the database(redis) and are deleted when the user logs out.

## Inter-service communication

The service communicates with the following services via gRPC:
- Order Service
- Item Service
- Review Service

Mainly, the service communicates with other services to provide the id of the member who is making a request 
because the other service cannot identify the id of member with username.


## Setup


### Install the dependencies

```bash
gradle build

docker image build -t member-service .


```


### Run the service
    
```bash
docker run -p 6379:6379 redis

docker run -p 8080:8080 member-service
```



## Requirements

- Java 17
- Gradle 7.2
- Redis 6.2.5
- MySQL 8.0.26
- gRPC
- Docker



## API Endpoints

### Register a new member

```http
POST api/auth/register
```

### Login
```http
POST api/auth/authenticate
```

### Get Access Token
```http
POST api/auth/access-token
```

### Logout
```http
POST api/logout
```

### Read/Write/Update member(customer/seller) details

```http
GET /api/customers/customer-detail
POST /api/customers/customer-detail
PATCH /api/customers/customer-detail
```

```http
GET /api/sellers/customer-detail
POST /api/sellers/customer-detail
PATCH /api/sellers/customer-detail
```

