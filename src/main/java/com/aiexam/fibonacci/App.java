package com.aiexam.fibonacci;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Application to calculate Fibonacci numbers solving inefficiencies
 * found with profiling.
 */
public class App {

  /**
   * Calculates the n-th Fibonacci number using recursion, with detailed profiling.
   *
   * @param n The non-negative integer position in the Fibonacci sequence.
   * @return The n-th Fibonacci number as a BigInteger.
   */
  public static BigInteger calculate(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("Input must be a non-negative integer.");
    }
    if (n <= 1) {
      return BigInteger.valueOf(n);
    }

    // Iterative approach
    BigInteger a = BigInteger.ZERO;
    BigInteger b = BigInteger.ONE;
    for (int i = 2; i <= n; i++) {
      BigInteger temp = a.add(b);
      a = b;
      b = temp;
    }
    return b;
  }

  public static void main(String[] args) {

    try (Scanner scanner = new Scanner(System.in)) {
      System.out.println("--- Fibonacci Calculator (Improved Version) ---");
      System.out.print("Enter a non-negative integer number: ");
      int number = scanner.nextInt();
      System.out.println("Calculating...");

      long startTime = System.nanoTime();
      BigInteger result = calculate(number);
      long endTime = System.nanoTime();
      long duration = (endTime - startTime) / 1000000;

      System.out.printf("The Fibonacci number at position %d is: %s%n", number, result);
      System.out.printf("Total calculation time: %,d milliseconds.%n", duration);

    } catch (java.util.InputMismatchException e) {
      System.err.println("Invalid input. Please enter an integer.");
    } catch (IllegalArgumentException e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}