# users-ms
service in charge management of users  

## How to Run

This application is packaged as a jar which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository https://github.com/jaguilar30/users-ms
* Make sure you are using JDK 1.17, Maven 3.6.3 and spring boot 3.3.4
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar -Dspring.profiles.active=test target/users-ms-1.0-SNAPSHOT.jar
or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=default"
```
* Check the stdout or boot_example.log file to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2017-08-29 17:31:23.091  INFO 19387 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8090 (http)
2017-08-29 17:31:23.097  INFO 19387 --- [           main] com.khoubyari.example.Application        : Started Application in 22.285 seconds (JVM running for 23.032)
```
## How to Create database objects

run sql follow sql script: 

```
CREATE TABLE users (
    id UUID DEFAULT random_uuid() PRIMARY KEY,
    name varchar(100),
    email varchar(100), 
    password varchar(500),
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    last_login TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    token varchar(2000),
    active BOOLEAN
);  

ALTER TABLE users
ADD UNIQUE (email); 
```

```
CREATE TABLE phones (
    id UUID DEFAULT random_uuid() PRIMARY KEY,
    number varchar(100),
    city_code varchar(50), 
    country_code varchar(50),
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES users(id)
);  
```
 
## About the Service

Here are some endpoints you can call: 

### Get information about system health, configurations, etc.

``` 
http://localhost:8081/health
http://localhost:8081/info
http://localhost:8081/metrics
```

### Create a user resource

```
POST /api/v1/users
Accept: application/json
Content-Type: application/json

{
    "name": "Juan Rodriguez",
    "email": "juan@gmail.cl",
    "password": "Syc@juluaga2016",
    "phones": [
        {
            "number": "1234567",
            "city_code": "1",
            "country_code": "57"
        }
    ]
}

RESPONSE: HTTP 201 (Created) 

{
    "message": "User created",
    "data": {
        "id": "482a1a6c-ff3c-4818-a4a3-6e30c0c8088c",
        "name": "Juan Rodriguez",
        "email": "juan@gmail.cl",
        "password": "$2a$10$ZUNdOmxrZ8vKKi13dGGAE.N63LhseYzEJh2vslqHNCkSaPgW6hpSe",
        "phones": [
            {
                "number": "1234567",
                "city_code": "1",
                "country_code": "57"
            }
        ],
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c3VhcmlvMTIzIiwiaWF0IjoxNzU1Nzg4MTE2LCJleHAiOjE3NTU3OTUzMTZ9.OWjChsRnYSjGqcCwiEIseb--qbzZMWUJcFP6zyMaHNA",
        "active": true,
        "create_at": "2025-08-21T14:55:16.530+00:00",
        "update_at": "2025-08-21T14:55:16.530+00:00",
        "last_login": "2025-08-21T14:55:16.530+00:00"
    }
}

```

### To view Swagger 2 API docs

Run the server and browse to http://localhost:8080/api/v1/swagger-ui/index.html

# About Spring Boot

Spring Boot is an "opinionated" application bootstrapping framework that makes it easy to create new RESTful services (among other types of applications). It provides many of the usual Spring facilities that can be configured easily usually without any XML. In addition to easy set up of Spring Controllers, Spring Data, etc. Spring Boot comes with the Actuator module that gives the application the following endpoints helpful in monitoring and operating the service:

**/metrics** Shows “metrics” information for the current application.

**/health** Shows application health information.

**/info** Displays arbitrary application info.

**/configprops** Displays a collated list of all @ConfigurationProperties.

**/mappings** Displays a collated list of all @RequestMapping paths.

**/beans** Displays a complete list of all the Spring Beans in your application.

**/env** Exposes properties from Spring’s ConfigurableEnvironment.

**/trace** Displays trace information (by default the last few HTTP requests).
 
# Questions and Comments: juanaguilargarcia20@gmail.com