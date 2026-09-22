package vn.edu.vtiacademy.lession02;

import java.util.Scanner;

public class RelationalOperators {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in); // Create a Scanner object to read input from console
    System.out.println("Enter first number: ");
    int a = scanner.nextInt(); // Read an integer from console and store it in variable a
    System.out.println("Enter second number: ");
    int b = scanner.nextInt(); // Read an integer from console and store it in variable b

    // Greater than
    System.out.println("a > b = " + a + " > " + b + " => " + (a > b));

    // Less than
    System.out.println("a < b = " + a + " < " + b + " => " + (a < b));

    // Greater than or equal to
    System.out.println("a >= b = " + a + " >= " + b + " => " + (a >= b));

    // Less than or equal to
    System.out.println("a <= b = " + a + " <= " + b + " => " + (a <= b));

    // Equal to
    System.out.println("a == b = " + a + " == " + b + " => " + (a == b));

    // Not equal to
    System.out.println("a != b = " + a + " != " + b + " => " + (a != b));
  }

}
