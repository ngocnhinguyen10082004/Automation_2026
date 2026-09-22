package vn.edu.vtiacademy.lesson05;

import java.util.Scanner;

public class SwitchCommand {

  public static void main(String[] args) {
    // Nhập một số nguyên dương trong khoảng từ 1 đến 7. In thành thứ ngày trong tuần ra màn hình console
    int number = inputNumber();
    convertNumberToDayOfWeek(number);
    System.out.println("=============================");
    if(number == 1) {
      printInfo("Today is Monday");
    } else if (number == 2) {
      printInfo("Today is Tuesday");
    } else if (number == 3) {
      printInfo("Today is Wednesday");
    } else if (number == 4) {
      printInfo("Today is Thursday");
    } else if (number == 5) {
      printInfo("Today is Friday");
    }else if (number == 6) {
      printInfo("Today is Saturday");
    } else if (number == 7 || number == 0) {
      printInfo("Today is Sunday");
    } else {
      printInfo("Number is invalid");
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

  public static void convertNumberToDayOfWeek(int number) {
    switch (number) {
      case 1:
        printInfo("Today is Monday");
        break;
      case 2:
        printInfo("Today is Tuesday");
        break;
      case 3:
        printInfo("Today is Wednesday");
        break;
      case 4:
        printInfo("Today is Thursday");
        break;
      case 5:
        printInfo("Today is Friday");
        break;
      case 6:
        printInfo("Today is Saturday");
        break;
      case 7:
      case 0:
        printInfo("Today is Sunday");
        break;
      default:
        printInfo("Number is invalid");
    }
  }

}
