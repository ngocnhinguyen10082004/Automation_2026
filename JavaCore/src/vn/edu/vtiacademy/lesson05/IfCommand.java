package vn.edu.vtiacademy.lesson05;

import java.util.Scanner;

public class IfCommand {

  static void main(String[] args) {

    //Nhập vào một số nguyên. Nếu số đó lớn hơn 0 thì in thông tin ra màn hình console
    Scanner scanner = new Scanner(System.in); // Create a Scanner object to read input from console
    System.out.println("Enter number: ");
    int number = scanner.nextInt(); // Read an integer from console and store it in variable a
    if (number > 0) {
      System.out.println(number + " is positive number");
    }

    System.out.println("==========================");
    //Nhập vào một số nguyên. Kiểm tra số đó lớn hơn 0 hay nhỏ hơn 0, in thông tin ra màn hình console
    if (number >= 0) {
      System.out.println(number + " is positive number");
    } else {
      System.out.println(number + " is negative number");
    }
    System.out.println("==========================");

    //Nhập vào một số nguyên. Kiểm tra số đó lớn hơn 0, nhỏ hơn 0 hay bằng 0, in thông tin ra màn hình console
    if (number > 0) {
      System.out.println(number + " is positive number");
    } else if(number < 0){
      System.out.println(number + " is negative number");
    } else {
      System.out.println(number + " is zero");
    }
    System.out.println("==========================");
    //Nhập vào một số nguyên. Kiểm tra số đó lớn hơn 0, nhỏ hơn 0 hay bằng 0, in thông tin ra màn hình console. Kiêm tra xem số đó có chia hết cho 2 hay không, in thông tin ra màn hình console
    if (number > 0) {
      System.out.println(number + " is positive number");
      if(number % 2 == 0) {
        System.out.println(number + " is divided by 2");
      }  else {
        System.out.println(number + " is not divided by 2");
      }
    } else if(number < 0){
      System.out.println(number + " is negative number");
      if(number % 2 == 0) {
        System.out.println(number + " is divided by 2");
      } else {
        System.out.println(number + " is not divided by 2");
      }
    } else {
      System.out.println(number + " is zero");
    }

    System.out.println("==========================");
    //Nhập vào một số nguyên. Kiểm tra số đó lớn hơn 0, nhỏ hơn 0 hay bằng 0, in thông tin ra màn hình console. Kiêm tra xem số đó có chia hết cho 2 hay không, in thông tin ra màn hình console
    if (number > 0) {
      System.out.println(number + " is positive number");
    } else if(number < 0){
      System.out.println(number + " is negative number");
    } else {
      System.out.println(number + " is zero");
    }

    if(number % 2 == 0) {
      System.out.println(number + " is divided by 2");
    } else {
      System.out.println(number + " is not divided by 2");
    }

    System.out.println("==========================");
    //Nhập vào một số nguyên. Kiểm tra số đó lớn hơn 0, nhỏ hơn 0 hay bằng 0, in thông tin ra màn hình console. Kiêm tra xem số đó có chia hết cho 2 hay không, in thông tin ra màn hình console
    if(isPositiveNumber(number)) {  // if(true)  -  if (false)
      printInfo(number + " is positive number");
    } else if(isNegativeNumber(number)){
      printInfo(number + " is negative number");
    } else {
      printInfo(number + " is zero");
    }

    if(isEvenNumber(number)) { // if number is event number
      printInfo(number + " is divided by 2"); // print info:
    } else { // else
      printInfo(number + " is not divided by 2"); // print info:
    }
  }

  public static boolean isPositiveNumber(int number) {
    return number > 0;
  }

  public static boolean isNegativeNumber(int number) {
    return number < 0;
  }

  public static boolean isEvenNumber(int number) {
    return number % 2 == 0;
  }

  public boolean isOddNumber(int number) {
    return number % 2 != 0;
  }

  public static void printInfo(String message) {
    System.out.println(message);
  }
}
