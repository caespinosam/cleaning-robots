# Cleaning robots

## Author
Cesar Espinosa

## Overview
An implementation of the Cleaning robots challenge using Hexagonal architecture and DDD. 
This implementation was built in the suggested timeframe for this exercise which is up to 8 hours.

## Prerequisites for development

- [Git](https://git-scm.com/downloads)
- [JDK 17](https://adoptium.net/)
- [Maven](https://maven.apache.org/download.cgi)

## Technologies Used

- `Kotlin`: It's concise, less code and improves readability. 
- `Spring boot`: It facilitates creating a CLI-based application by using a fat jar that already contains all dependencies. It offers Dependency Injection for loose coupling of components. 
- `Maven`: It is used to build the application as a multi-module project where each module is isolated and defines its own set of dependencies. It helps separate each layer of the hexagon.
- `Mockk`: For testing and mocking. 

## High level Architecture

The architecture used is based on the book "Get Your Hands Dirty on Clean Architecture" by Tom Hombergs:

![alt text](https://www.happycoders.eu/wp-content/uploads/2023/06/hexagonal-architecture-java.v4-944x709.png)

There is an `application` module which contains all business logic/rules and main entities. It is the core of the architecture and is isolated from infrastructure details.
It defines interfaces or `ports` to communicate with the outside world – both to be controlled (by an API, by a user interface, by other applications) and to control (the database, external interfaces, and other infrastructure).

There are `adapters` to receive commands from outside world or to send data to external services. Adapters are connected to one port. For example, a user interface adapter and a REST adapter can both be connected to the same port to control the application.

Fot this exercise, only a port to receive a command is used. No ports to persist or send data externally was detected during analysis. 

## Assumptions

- Each robot operates sequentially and its final position does not interfere with the execution of the next robot. Overlapping positions are not a problem.
- The input (moves) is read from a file that is in the classpath. This was implemented as an adapter, so the application can easily evolve to support more adapters, e.g Rest endpoints.
- It is assumed that the input file is provided by a client, and that the client waits for a synchronous response.
- No information has to be persisted or sent to an external system (no outbound ports).
- Floors can be generated using factories. For this exercise a factory is used to generate a floor with no obstacles.
- The response generated is displayed in the Console logger. The application can easily evolve to change the format used.


## Internal structure

### Application

Contains the domain model, ports, application service and domain services that implement the use case.
It is a Maven module.

#### - Model
Contains entities, value objects and a domain service. This submodule does not reference any framework and does not reference classes in other modules.

The main entities are:
- `Robot`: It contains the business rules for a robot  to rotate and move forward. It also verifies the consistency of the requested move. Main entity (Aggregate root).
- `Floor`: It contains information about the grid's limits the robots will operate in. It can easily evolve to support obstacles.

A domain service is used:
- `RobotDomainService`: Contains the main logic to know when exactly a robot should rotate to left or right, or move forward. It handles only one robot at a time.

It is a Maven submodule.

#### - Service
It contains the inbound port in form of interface and a default implementation. It also contains an application service that implement the business use cases by receiving a command from adapters and then calling the domain service in the `model` submodule.

For this exercise, the following use case (inbound port) is declared:
- `RobotsMoveUseCase`: It receives the input as plain text, then it parses the text to extract each robot configuration and their moves.  Then, it calls an implementation of `RobotDomainService` in the `model` submodule to process robot by robot sequentially.

It also contains a factory `FloorFactory` to build floors.

It is a Maven submodule.
### Adapter

Contains an adapter `ClassPathFileReaderAdapter` that loads the input data as text automatically once the application is started. 

For this exercise, the adapter reads a file from the classpath and then passes it to an implementation of `RobotsMoveUseCase` in the `service` submodule. It then prints the result in the Console logger.

It is a Maven module.
### Bootstrap

Instantiates adapters and domain and application services, and starts the CLI-based application by running automatically `ClassPathFileReaderAdapter`.
It leverages Spring Boot framework to inject all services implementations.

It is a Maven module.
## Test strategy

An integration test was added:

- in `service` submodule: It helps test the `RobotsMoveService` implementation, which can be seen as the application entry point. It tests some edge cases and happy paths.

A unit test was added:
- in `adapter` module: It tests that `ClassPathFileReaderAdapter` reads the file from the classpath. 

In order to execute all tests run:
```
./mvnw clean test
```

## How to execute the application

First compile and package the application as fat jar:
```
./mvnw clean package
```
Then, execute it:
```
java -jar ./bootstrap/target/bootstrap-0.0.1-SNAPSHOT.jar
```

It executes `ClassPathFileReaderAdapter` which processes the default input file that is in the classpath. See `src/main/resources/data/robot_input.txt`.

The content of the input file and the generated response is displayed in the Console logger:

```
INFO 24164 --- [           main] c.v.r.a.in.ClassPathFileReaderAdapter    : INPUT:
 5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
INFO 24164 --- [           main] r.a.i.RobotsResponseProcessorConsoleImpl : OUTPUT:
1 3 N
5 1 E

```


## How to modify the input?
Please just modify the file `./bootstrap/src/main/resources/data/robot_input.txt` and re-execute the commands described in `How to execute the application`. 

