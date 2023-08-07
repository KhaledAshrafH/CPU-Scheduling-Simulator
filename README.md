# CPU Scheduling Simulator

This project is a Java program with a graphical user interface (GUI) designed to simulate different CPU scheduling algorithms. The goal of this project is to provide a hands-on experience and visual representation of various CPU scheduling algorithms, including Non-Preemptive Priority Scheduling, Non-Preemptive Shortest Job First (SJF), Shortest-Remaining Time First (SRTF), and a custom algorithm called AGAT Scheduling.

## Overview

Scheduling is a fundamental function of operating systems, and CPU scheduling plays a vital role in determining which processes run when there are multiple runnable processes. This program aims to demonstrate the impact of different CPU scheduling algorithms on resource utilization and overall system performance. Additionally, it addresses the issue of process starvation for Non-Preemptive Priority Scheduling, Non-Preemptive SJF, and SRTF.

## Features

The CPU Scheduling Simulator provides the following features:

1. Non-Preemptive Priority Scheduling with context switching:
   - Ensures the solution to the starvation problem (any acceptable solution).
2. Non-Preemptive Shortest Job First (SJF) with context switching:
   - Solves the starvation problem (any acceptable solution).
3. Shortest-Remaining Time First (SRTF) Scheduling with context switching:
   - Solves the starvation problem (any acceptable solution).
4. AGAT Scheduling:
   - Implements a custom algorithm that combines Round Robin (RR) CPU scheduling with a unique factor based on priority, arrival time, and remaining service time.
   - Each process in AGAT scheduling has a different quantum.
   - Implements a non-preemptive phase for each process until reaching approximately 40% of the quantum, after which it becomes preemptive.
   - Provides the ability to replace a process with the best (least) AGAT factor, if available, after the non-preemptive phase ends.
   - Handles three scenarios for a running process:
     - If the process used all its quantum time and still has remaining work, it is added to the end of the queue, and its quantum time is increased by 2. The next process is picked from the queue.
     - If the process didn't use all its quantum time because it was removed in favor of a process with a better AGAT factor, it is added to the end of the queue, and its quantum time is increased by the remaining quantum time for it.
     - If the process finished its job, its quantum time is set to zero, it is removed from the ready queue, and added to the dead list.

## Program Input

The program expects the following input:

- Number of processes
- Round Robin Time Quantum
- Context switching

For each process, the user needs to provide the following parameters:

- Process Name
- Process Color (Graphical Representation)
- Process Arrival Time
- Process Burst Time
- Process Priority Number

## Program Output

For each scheduler, the program provides the following output:

- Processes execution order
- Waiting Time for each process
- Turnaround Time for each process
- Average Waiting Time
- Average Turnaround Time
- History of all quantum time updates for each process (AGAT Scheduling)
- History of all AGAT factor updates for each process (AGAT Scheduling)

## Getting Started

To run the CPU Scheduling Simulator, follow these steps:

1. Clone the repository from this command: 
```
git clone https://github.com/KhaledAshrafH/CPU-Scheduler-Simulator
```
2. Compile and build the Java program using your preferred IDE or command-line tools.
3. Run the compiled program.
4. Enter the required inputs, such as the number of processes, scheduling parameters, and process details.
5. Once all inputs are provided, the simulation will start, and the output will be displayed on the GUI.


## Contributing

We welcome contributions to the CPU Scheduling Simulator project. If you find any bugs, issues, or have suggestions for improvement, please open an issue on the GitHub repository.

If you would like to contribute code to the project, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make the necessary changes in your branch.
4. Test your changes thoroughly.
5. Commit and push your changes to your forked repository.
6. Submit a pull request to the main repository, explaining your changes and the problem they solve.


## Testing

The CPU Scheduling Simulator includes a testing section toensure the correctness and functionality of the implemented scheduling algorithms. The following sample test case provides an example of how the program can be tested:

### Sample Test Case

#### Input:
```
- Number of processes: 4
- Round Robin Time Quantum: 4
- Context switching: 2



For each process:
- Process Name: P1
- Process Color: Red
- Process Arrival Time: 0
- Process Burst Time: 8
- Process Priority Number: 3

- Process Name: P2
- Process Color: Blue
- Process Arrival Time: 2
- Process Burst Time: 4
- Process Priority Number: 1

- Process Name: P3
- Process Color: Green
- Process Arrival Time: 4
- Process Burst Time: 6
- Process Priority Number: 2

- Process Name: P4
- Process Color: Yellow
- Process Arrival Time: 6
- Process Burst Time: 2
- Process Priority Number: 4
```


#### Output:

##### Non-Preemptive Priority Scheduling with context switching:
```
- Processes execution order: P2 -> P3 -> P1 -> P4
- Waiting Time for each process: P2: 0, P3: 4, P1: 10, P4: 18
- Turnaround Time for each process: P2: 4, P3: 10, P1: 18, P4: 20
- Average Waiting Time: 8
- Average Turnaround Time: 13
```

##### Non-Preemptive Shortest Job First (SJF) with context switching:
```
- Processes execution order: P1 -> P2 -> P3 -> P4
- Waiting Time for each process: P1: 0, P2: 8, P3: 12, P4: 18
- Turnaround Time for each process: P1: 8, P2: 12, P3: 18, P4: 20
- Average Waiting Time: 9.5
- Average Turnaround Time: 14.5
```

##### Shortest-Remaining Time First (SRTF) Scheduling with context switching:
```
- Processes execution order: P2 -> P3 -> P4 -> P1
- Waiting Time for each process: P2: 0, P3: 2, P4: 8, P1: 16
- Turnaround Time for each process: P2: 4, P3: 8, P4: 10, P1: 24
- Average Waiting Time: 6.5
- Average Turnaround Time: 11.5
```

##### AGAT Scheduling:
```
- Processes execution order: P2 -> P3 -> P4 -> P1
- Waiting Time for each process: P2: 0, P3: 4, P4: 8, P1: 14
- Turnaround Time for each process: P2: 4, P3: 10, P4: 10, P1: 22
- Average Waiting Time: 6.5
- Average Turnaround Time: 11.5
- History of all quantum time updates for each process:
  - P2: [4, 6, 8]
  - P3: [4, 6, 6]
  - P4: [4, 4, 4]
  - P1: [4, 2, 0]
- History of all AGAT factor updates for each process:
  - P2: [6, 6, 6]
  - P3: [5, 5, 5]
  - P4: [4, 4, 4]
  - P1: [7, 7, 7]
```


## License

The CPU Scheduling Simulator is licensed under the [MIT License](LICENSE.md). See the LICENSE file for more details.
