package com.aiexam.fibonacci;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Application to calculate Fibonacci numbers.
 * This version includes advanced profiling to visualize the call tree
 * and count redundant calculations, exposing the core inefficiency.
 */
public class App {

  // PROFILING: A map to count how many times each number `n` is calculated.
  private static Map<Integer, Long> calculationCounts;

  /**
   * Calculates the n-th Fibonacci number using recursion, with detailed profiling.
   *
   * @param n The non-negative integer position in the Fibonacci sequence.
   * @return The n-th Fibonacci number as a BigInteger.
   */
  public static BigInteger calculate(int n) {
    // Record how many times a calculation of a given n have been performed
    calculationCounts.put(n, calculationCounts.getOrDefault(n, 0L) + 1);

    if (n < 0) {
      throw new IllegalArgumentException("Input must be a non-negative integer.");
    }
    if (n <= 1) {
      return BigInteger.valueOf(n);
    }
    return calculate(n - 1).add(calculate(n - 2));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("--- Fibonacci Calculator (Profiling) ---");
    System.out.print("Enter a non-negative integer number: ");

    try {
      int number = scanner.nextInt();
      System.out.println("Calculating...");

      // Initialize profiling tools
      calculationCounts = new HashMap<>();
      long startTime = System.nanoTime();

      BigInteger result = calculate(number);

      long endTime = System.nanoTime();
      long duration = (endTime - startTime) / 1000000;

      System.out.println("\n--- Profiling Results ---");
      System.out.printf("The Fibonacci number at position %d is: %s%n", number, result);
      System.out.printf("Total calculation time: %,d milliseconds.%n", duration);

      System.out.println("\nRedundant Calculation Report:");
      calculationCounts.entrySet().stream()
          .sorted(Map.Entry.comparingByKey())
          .forEach(entry -> System.out.printf("fib(%d) was calculated %,d times.%n", entry.getKey(), entry.getValue()));

    } catch (java.util.InputMismatchException e) {
      System.err.println("Invalid input. Please enter an integer.");
    } catch (IllegalArgumentException e) {
      System.err.println("Error: " + e.getMessage());
    } finally {
      scanner.close();
    }
  }
}