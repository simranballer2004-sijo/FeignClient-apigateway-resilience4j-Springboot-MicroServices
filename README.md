To start mysql and phpmyAdmin locally : Phpmyadmin command : phpMyAdmin % php -S localhost:8080 For mysql : /usr/local/mysql/bin/mysqladmin -u root -p status
I have created a config service : which is related to the config repo (in order to get all the config related details for the micro services) I have created a Eureka discovery service : basically registers the micro services
There are 2 micro services : Customers and Orders it has normal packages like entity, repo -> implementing the JPARepo , services and controller Customer -> mysql DB Orders -> inbuilt h2 Db
Order service is used for getting the customer information as well (i.e., using feign client) for this we will need to add a model for customer and also a service interface to get the mappings for the required data
We have handled multiple calls failure using Resilience Circuit Breaker @Circuitbreaker + fallback: it is basically written on the order Micro service only and it has different application properties that give us the desired things
While handling exceptions we also learnt about @bean -> ErrorDecoder which helps to store error messages thrown by another Microservice in this case -> Controller
We also learnt how @RestControllerAdvice removes the need of try catch blocks and helps in making the code look clean: the key need used in this is Problem detail class which is used to store the respective error messages and all.
We have also impelled api gateway : which is the entry point for all the microservices. With the help of adding the predicates , it can basically route to the desired microservices.
