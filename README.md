# Cleaning robots

## Author
Cesar Espinosa


## Overview
An implementation of the Cleaning robots challenge using Clean architecture and DDD. The language used is Kotlin and Spring boot framework to take advantage of its injection container.

## Prerequisites for development

- [Git](https://git-scm.com/downloads)
- [JDK 17](https://adoptium.net/)
- [Maven](https://maven.apache.org/download.cgi)

## Assumptions

- Each robot operates sequentially and its final position does not interfere with the execution of the next robot. Overlapping positions are not a problem.
- The input (moves) is read from a file that is in the classpath. This was implemented as an adapter, so the application can easily evolve to support more adapters, e.g Rest endpoints.
- It is assumed that the input file is provided by a client, and that the client waits for a synchronous response.
- No information has to be persisted or sent to an external system.
- Floors can be generated using factories. For this exercise a factory is used to generate a floor with no obstacles.
- The response generated is displayed in the Console logger. The application can easily evolve to change the format used.


## Internal structure

### Domain

#### Core
Contains entities, value objects and a domain service. This layer does not reference any framework and does not reference classes in other layers.

The main entities are:
- `Robot`: It contains the business rules for a robot  to rotate and move forward. It also verifies the consistency of the requested move. Main entity (Aggregate root).
- `Floor`: It contains information about the grid's limits the robots will operate in. It can easily evolve to support obstacles.

A domain service is used:
- `RobotDomainService`: Contains the main logic to know when exactly a robot should rotate to left or right, or move forward. It handles only one robot at a time.

#### Application service
It contains the ports, which are interfaces that allow inbound or outbound flow. It also contains services that implement the business use cases.

For this exercise, the following use case (inbound port) is declared:
- `RobotsMoveUseCase`: It receives the input as plain text, then it parses the text to extract each robot configuration and their moves.  Then, it calls an implementation of `RobotDomainService` in the domain layer to process robot by robot sequentially.

It also contains a factory `FloorFactory` to build floors.

### Adapter

Contains an adapter that loads the input data as text.

For this exercise, the adapter reads a file from the classpath and then passes it to an implementation of `RobotsMoveUseCase` in the application service layer. It then prints the result in the Console logger.

### Bootstrap

Instantiates and inject the adapter implementation and the domain service implementation.

## Test strategy

Run integration test on `RobotsMoveService`, which is the application entry point. It tests some edge cases and happy paths. 

## How to execute the application
```
./mvnw clean spring-boot:run
```
It runs tests and then processes the default input file that is in the classpath. See `src/main/resources/data/robot_input.txt`.

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
Please just modify the file `src/main/resources/data/robot_input.txt`. 

