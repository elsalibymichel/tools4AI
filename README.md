# Fibonacci Calculator: A Performance Improvement Case Study
This repository is a submission for the "Tools and Methods for AI" course exam. 
It illustrates a development workflow on a simple Java project, focusing on profiling, 
performance improvement, and environment reproducibility using Maven.

## Project Overview

The project provides a simple command-line application to calculate the *n*-th number in the Fibonacci sequence. 
The main goal is to show the process of identifying a performance bottleneck in an initial implementation 
and replacing it with a more efficient one.

## Git Workflow and Branching Strategy

The repository follows a standard `main`/`develop` branching strategy:

1.  **`main` branch**: This branch contains the stable version of the code. 
    The initial commit on `main` held a simple recursive implementation of the Fibonacci algorithm.

2.  **`develop` branch**: This branch was created from `main` to work on improvements. 
    The development process on this branch was split into two distinct commits:

    * **Profiling Commit**: A profiling mechanism was added to the recursive function 
      to diagnose and quantify its performance characteristics.

    * **Improvement Commit**: The initial algorithm was replaced with a more performant iterative solution.

3.  **Merge**: Once the work on the `develop` branch was complete, 
    it was merged back into the `main` branch to update the project with the final code.

## Performance Analysis and Improvement

The core of this project was to identify and solve a performance issue.

### 1. The Initial Implementation: A Recursive Approach

The initial version used a direct recursive translation of the Fibonacci mathematical definition, `F(n) = F(n-1) + F(n-2)`. 
While simple to write, this approach has an exponential time complexity of **O(2^n)**, 
which becomes impractical for even moderately large values of `n`.

### 2. Performance Analysis

To better understand the performance, a profiling step was introduced on the `develop` branch. 
This involved adding a counter to track how many times the `calculate(n)` function was called for each value of `n`.
Running the profiled code for `n = 40` produced the following results:

```
--- Fibonacci Calculator (Profiling) ---
Enter a non-negative integer number: 40

--- Profiling Results ---
The Fibonacci number at position 40 is: 102334155
Total calculation time: 7,508 milliseconds.

Redundant Calculation Report:
fib(0) was calculated 63,245,986 times.
fib(1) was calculated 102,334,155 times.
fib(2) was calculated 63,245,986 times.
fib(3) was calculated 39,088,169 times.
...
fib(38) was calculated 2 times.
fib(39) was calculated 1 times.
fib(40) was calculated 1 times.
```

This data highlights the core issue: to calculate `fib(40)`, 
the program performs **hundreds of millions of redundant calculations**. 
For example, `fib(2)` is calculated over 63 million times. 
This high number of operations is the primary reason for the slow performance.

### 3. The Improved Solution: An Iterative Approach

Based on the analysis, the recursive algorithm was replaced with an **iterative (bottom-up) solution**. 
This new implementation calculates the sequence starting from 0 and 1,
and computes the next value in the series until it reaches `n`.

This approach has a **linear time complexity (O(n))** and **constant space complexity (O(1))**, 
making it significantly more efficient. As a result, calculating `fib(40)` is nearly instantaneous, 
and it's possible to compute much larger values (e.g., `fib(1000)`) in milliseconds.

## Reproducibility and Usage

This project uses **Apache Maven** to manage dependencies and build the application, ensuring a reproducible environment.

### How to Build and Run

1.  **Prerequisites**:
    * Git
    * Java Development Kit (JDK), version 11 or later
    * Apache Maven

2.  **Clone the repository**:
    ```bash
    git clone <https://github.com/elsalibymichel/tools4AI.git>
    cd fibonacci-demo
    ```

3.  **Build the project with Maven**:
    This command will compile the source code, run any tests, and package the application into an executable `.jar` file in the `target/` directory.
    ```bash
    mvn clean package
    ```

4.  **Run the application**:
    Use the following command to run the packaged application.
    ```bash
    java -jar target/fibonacci-demo-1.0-SNAPSHOT.jar