# Ocean Explorer Kata – Probe Control API

TDD-based Java implementation (NatWest assessment)

---

## Overview

This project implements a Java-based API to control a remotely operated probe navigating the ocean floor on a 2D grid.

The probe can move across the grid, change direction, execute command sequences, avoid obstacles, and remain within defined boundaries. The solution was developed using Test-Driven Development (TDD), with a strong emphasis on clean code, robustness, and maintainable object-oriented design.

The implementation focuses on correctness, clarity, and incremental design driven by tests.

---

## Key Features

* Grid-based navigation using `(x, y)` coordinates
* Directional movement: NORTH, SOUTH, EAST, WEST
* Turn left and right
* Move forward and backward
* Obstacle detection and avoidance
* Boundary enforcement within the grid
* Command execution using string input
* Case-insensitive command support
* Whitespace handling (spaces, tabs, new lines ignored)
* Strong input validation and error handling
* Comprehensive unit test coverage

---

## Design Approach

### Test-Driven Development (TDD)

The system was built incrementally using the Red → Green → Refactor cycle:

* Red: Write a failing test describing expected behavior
* Green: Implement the minimal code required to pass the test
* Refactor: Improve code structure while preserving behavior

This approach ensured correctness, guided design decisions, and kept the implementation focused and testable.

---

### Object-Oriented Design

The system is structured around well-defined domain classes:

| Class        | Responsibility                                     |
| ------------ | -------------------------------------------------- |
| `Probe`      | Handles movement, direction, and command execution |
| `OceanGrid`  | Manages grid boundaries and obstacle validation    |
| `Direction`  | Encapsulates orientation and turning logic         |
| `Coordinate` | Represents positions in a type-safe manner         |

Responsibilities are clearly separated, leading to high cohesion and low coupling.

---

## Architecture

* Separation of concerns between probe behavior and environment
* Encapsulation of movement and command logic within the Probe
* Validation responsibilities delegated to OceanGrid
* Minimal dependencies between components
* Design kept simple and extensible

---

## Project Structure

```text
src/
├── main/java/com/oceanexplorer/
│   ├── Probe.java
│   ├── OceanGrid.java
│   ├── Direction.java
│   └── Coordinate.java
└── test/java/com/oceanexplorer/
    └── ProbeTest.java
```

---

## How to Run

### Prerequisites

* Java 17 or later
* Maven installed

### Run Tests

```bash
mvn test
```

### Run a Specific Test

```bash
mvn -Dtest=ProbeTest#shouldThrowExceptionWhenCommandsAreNull test
```

---

## Command Execution

Commands are passed as a string:

| Command | Action        |
| ------- | ------------- |
| F       | Move Forward  |
| B       | Move Backward |
| L       | Turn Left     |
| R       | Turn Right    |

### Example

```java
probe.execute("FFRFF");
```

### Input Handling

* Commands are case-insensitive
* All whitespace characters are ignored
* Invalid characters result in an IllegalArgumentException

---

## Example Scenario

Initial Position: (0,0) facing NORTH
Command: FFRFF

Execution:
(Example)
* F → (0,1)
* F → (0,2)
* R → EAST
* F → (1,2)
* F → (2,2) (if no obstacle present)

Final Position: (2,2) facing EAST

---

## Validation and Edge Cases

The implementation includes defensive validation to ensure system stability:

* Null command input → exception
* Invalid command characters → exception
* Null direction → prevented
* Null grid → prevented
* Starting position outside grid → prevented
* Starting position on obstacle → prevented
* Invalid grid dimensions → prevented
* Obstacle placement outside grid → prevented
* Empty command string → treated as no operation

---

## Testing Strategy

* Unit tests written for all core behaviors
* Edge cases explicitly validated
* Tests guide development through TDD cycles
* Refactoring performed with confidence due to test coverage

Test categories include:

* Initialization
* Direction changes
* Movement logic
* Boundary enforcement
* Obstacle handling
* Command execution
* Input validation

---

## Refactoring Highlights

* Introduced `Coordinate` class for improved type safety
* Extracted command processing into a dedicated method
* Reduced duplication in movement logic using helper methods
* Improved readability and maintainability

Refactoring was performed without altering behavior, ensured by passing tests.

---

## Design Decisions

* Movement logic is contained within `Probe` to maintain behavioral ownership
* `OceanGrid` is responsible for boundary and obstacle validation
* Command execution is handled within `Probe` for cohesion
* Validation is enforced at object creation to prevent invalid states

---

## Use of AI

AI tools were used as assistive support during development to explore edge cases, validate design approaches, and refine implementation strategies. All final decisions and code were reviewed and validated manually.

AI tools were also used to assist in drafting and refining this README.

---

## Conclusion

This project demonstrates:

* Strong application of Test-Driven Development
* Clean and maintainable object-oriented design
* Careful handling of edge cases and validation
* Focus on readability, simplicity, and extensibility

---

## Author

Pallavi Metri

---
