Live Football World Cup Scoreboard library

Command to build the lib: gradlew build

Library is written using DDD approach.
Tests are following TDD and BDD.
Mockito used to mock the dependencies.

I've skipped implementation of any dependency injection mechanism intentionally ( despite the tests ).
Still, code is written following Spring constructor based injection.

Intentionally I've ignored the explicit thread safety, assuming that on production a solution based on queues or events
would allow true life update experience.

