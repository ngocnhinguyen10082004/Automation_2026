package vn.edu.vtiacademy.lession02;

import java.util.Scanner;

public class ArithmeticOperators {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in); // Create a Scanner object to read input from console
    System.out.println("Enter first number: ");
    int a = scanner.nextInt(); // Read an integer from console and store it in variable a
    System.out.println("Enter second number: ");
    int b = scanner.nextInt(); // Read an integer from console and store it in variable b

    // Addition
    int sum = a + b;
    System.out.println("a + b = " + a + " + " + b + " = " + sum);

    // Subtraction
    int sub = a - b;
    System.out.println("a - b = " + a + " - " + b + " = " + sub);

    // Multiplication
    int mul = a * b;
    System.out.println("a * b = " + a + " * " + b + " = " + mul);

    // Division
    int div = a / b;
    System.out.println("a / b = " + a + " / " + b + " = " + div);

    // Modulus
    int remainder = a % b;
    System.out.println("a % b = " + a + " % " + b + " = " + remainder);

    System.out.println("Before incrementing, a = " + a);
    System.out.println("After incrementing, a = " + (a++)); // Increment a by 1 => a = a + 1
    System.out.println("Current a = " + a);
    System.out.println("After incrementing again, a = " + (++a)); // Increment a by 1 again => a = a + 1
    System.out.println("Current a = " + a);
    System.out.println("After decrementing, a = " + (a--)); // Decrement a by 1 => a = a - 1
    System.out.println("Current a = " + a);
    System.out.println("After decrementing again, a = " + (--a)); // Decrement a by 1 again => a = a - 1
    System.out.println("Current a = " + a);
  }
}
