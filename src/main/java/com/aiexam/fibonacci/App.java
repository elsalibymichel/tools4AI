package com.aiexam.fibonacci;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Application to calculate Fibonacci numbers.
 */
public class App {

  /**
   * Calculates the n-th Fibonacci number using recursion.
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
    // Recursive step: F(n) = F(n-1) + F(n-2)
    return calculate(n - 1).add(calculate(n - 2));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("--- Fibonacci Calculator ---");
    System.out.print("Enter a non-negative integer number: ");

    try {
      int number = scanner.nextInt();
      System.out.println("Calculating...");
      BigInteger result = calculate(number);
      System.out.printf("The Fibonacci number at position %d is: %s%n", number, result);
    } catch (java.util.InputMismatchException e) {
      System.err.println("Invalid input. Please enter an integer.");
    } catch (IllegalArgumentException e) {
      System.err.println("Error: " + e.getMessage());
    } finally {
      scanner.close();
    }
  }
}
