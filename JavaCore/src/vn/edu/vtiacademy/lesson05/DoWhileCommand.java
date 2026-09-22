package vn.edu.vtiacademy.lesson05;

import java.util.Scanner;

public class DoWhileCommand {

  public static void main(String[] args) {
    //Nhập một số nguyên n bất kỳ. Tính tổng các số từ 0 - n
    // sum = 0 + 1 + 2 + ... + n
    int number = inputNumber();
    int sum = 0;
    int i = 0;
    do {
      System.out.print("Loop " + i + ": " + sum + " + " + i + " = ");
      sum += i;
      System.out.print(sum);
      System.out.println();
      i++;
    } while (i <= number);
    printInfo("Tổng = " + sum);
    //Nhập một số nguyên n bất kỳ. Tính tổng các số chẵn từ 0 - n
    //sum = 0 + 2 + 4 + ...
    number = inputNumber();
    sum = 0;
    i = 0;
    do {
      System.out.print("Loop " + i + ": " + sum + " + " + i + " = ");
      sum += i;
      System.out.print(sum);
      System.out.println();
      i += 2;
    } while (i <= number);
    printInfo("Tổng = " + sum);
    //Nhập một số nguyên n bất kỳ. Tính tổng các số lẻ từ 0 - n
    //sum = 1 + 3 + 5 + ...
    number = inputNumber();
    sum = 0;
    i = 1;
    do {
      System.out.print("Loop " + i + ": " + sum + " + " + i + " = ");
      sum += i;
      System.out.print(sum);
      System.out.println();
      i += 2;
    } while (i <= number);
    printInfo("Tổng = " + sum);
  }

  public static int inputNumber() {
    Scanner scanner = new Scanner(System.in); // Create a Scanner object to read input from console
    System.out.println("Enter number: ");
    return scanner.nextInt(); // Read an integer from console and store it in variable a
  }

  public static void printInfo(String message) {
    System.out.println(message);
  }
}
