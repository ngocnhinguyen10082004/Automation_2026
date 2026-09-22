package vn.edu.vtiacademy.lesson05;

import java.util.Scanner;

public class WhileCommand {

  public static void main(String[] args) {
    //Nhập một số nguyên n bất kỳ. Tính tổng các số từ 0 - n
    // sum = 0 + 1 + 2 + ... + n
    int number = inputNumber();
    int sum = 0;
    int i = 0;
    while (i <= number) { //i = index
      System.out.print("Loop " + i + ": " + sum + " + " + i + " = ");
      sum += i;
      System.out.print(sum);
      System.out.println();
      i++;
    }
    printInfo("Tổng = " + sum);
    //Nhập một số nguyên n bất kỳ. Tính tổng các số chẵn từ 0 - n
    //sum = 0 + 2 + 4 + ...
    number = inputNumber();
    sum = 0;
    i = 0;
    while (i <= number) { //i = index
      System.out.print("Loop " + i + ": " + sum + " + " + i + " = ");
      sum += i;
      System.out.print(sum);
      System.out.println();
      i += 2;
    }
    printInfo("Tổng = " + sum);
    //Nhập một số nguyên n bất kỳ. Tính tổng các số lẻ từ 0 - n
    //sum = 1 + 3 + 5 + ...
    number = inputNumber();
    sum = 0;
    i = 1;
    while (i <= number) { //i = index
      System.out.print("Loop " + i + ": " + sum + " + " + i + " = ");
      sum += i;
      System.out.print(sum);
      System.out.println();
      i += 2;
    }
    printInfo("Tổng = " + sum);

//    while (true) {
//      System.out.print("loop");
//    }
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
