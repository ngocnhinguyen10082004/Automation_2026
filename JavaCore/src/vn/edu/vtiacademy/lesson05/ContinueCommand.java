package vn.edu.vtiacademy.lesson05;

import java.util.Scanner;

public class ContinueCommand {

  public static void main(String[] args) {
    //Nhập một số nguyên n bất kỳ. Tính tổng các số chẵn từ 0 - n
    //sum = 0 + 2 + 4 + ...
    int number = inputNumber();
    int sum = 0;
    for (int i = 0; i <= number; i++) {
      System.out.print("Loop " + i + ": ");
      if(i % 2 != 0) {
        System.out.print("continue loop");
        System.out.println();
        continue;
      }
      System.out.print(sum + " + " + i + " = ");
      sum += i;
      System.out.print(sum);
      System.out.println();
    }
    printInfo("Sum = " + sum);

    //Nhập một số nguyên n bất kỳ. Tính tổng các số lẻ từ 0 - n
    //sum = 1 + 3 + 5 + ...
    number = inputNumber();
    sum = 0;
    for (int i = 0; i <= number; i++) {
      System.out.print("Loop " + i + ": ");
      if(i % 2 == 0) {
        System.out.print("continue loop");
        System.out.println();
        continue;
      }
      System.out.print(sum + " + " + i + " = ");
      sum += i;
      System.out.print(sum);
      System.out.println();
    }
    printInfo("Sum = " + sum);
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
