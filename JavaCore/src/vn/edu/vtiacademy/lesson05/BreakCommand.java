package vn.edu.vtiacademy.lesson05;

import java.util.Scanner;

public class BreakCommand {

  public static void main(String[] args) {
    //Nhập số nguyên n bất kỳ. Tìm số nguyên đầu tiên trong khoảng từ 1 - n mà chia hết 5
    int number = inputNumber();
    for(int i = 1; i < number; i++) {
      if(i % 5 == 0) {
        printInfo(i + " is the first number which is divided by 5");
        break;
      }
      printInfo(i + " is not divided by 5");
    }
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
