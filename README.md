# Peter Oziegbe

## A demostration of a SpringBoot Data JPA application with Booking service as a case study.

This application levarage on Spring Data JPA to perform a booking for a client of the application.


## Implentation detail

The implementation leverages on a typical conponents of an enterprise of an application that interacts with each other. The are as follows.

A controller application that handles a request from a client of the application

A service that perform service level logic on the request such as mapping a request object to a dto and mapping an entity to a dto.

An entity, in this example is a Booking entity that represent the domain object.

A flyway migration script for creating a booking entity in the db. 

A repository that provide access to data stored in persistent storage to save a booking entity.

This application also as an h2 in-memory database db for our data storage, so no need to configure a database to run this application.

This application also leverage on WebMvcTest, JUnit and Mockito to perform unit and integration tests on all the components that makes up the application.

## Running the appication

mvn spring-boot:run
