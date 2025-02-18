# Two-Level Scheduler Algorithm

This program implements a two-level scheduling algorithm. At the lower level (priority 0), it uses the Shortest Remaining Time First (SRTF) scheduler, and at the higher level (priority 1), it employs a Round Robin (RR) scheduler with a time slice of 2 units. The program takes task data as input, calculates the execution order and waiting times of the tasks, and then outputs the results.

## Usage

When using the program, the user must provide task data in the following format: `task name`, `priority`, `start time`, `CPU burst`

### Example inputs:

**First example:**

```
A, 0, 0, 1
B, 0, 0, 2
C, 1, 2, 1
D, 1, 2, 4
E, 0, 3, 7
```

**Second example:**

```
A, 0, 0, 6
B, 1, 1, 2
C, 1, 3, 5
D, 0, 4, 3
E, 1, 5, 1
```

Based on the inputs, the program calculates the execution order and waiting times of the tasks and then outputs the results.

## Output

The program's output is displayed in two lines:

- The first line shows the execution order of the tasks.
- The second line lists the waiting times of the tasks.

### Example outputs:

**First example output:**

```
ABCDBE
A : 0, B : 6, C : 0, D : 1, E : 5
```

**Second example output:**

```
ABCECDA
A : 11, B : 0, C : 1, D : 5, E : 0
```


## Extensibility

The program is designed to be easily extendable with other schedulers as needed.

## Requirements

To run the program, no special software or libraries are required; a standard Java environment is sufficient.

## Running the Program

To run the program, simply execute the `Main.java` file and provide the task data in the format described above.<br>The program will accept new tasks until an empty input (ENTER) is provided.

```bash
javac Main.java
java Main
```

## License
This project is licensed under the MIT License. For more information, see the LICENSE file.
