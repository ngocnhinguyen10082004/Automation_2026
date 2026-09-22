package vn.edu.vtiacademy.lesson11;

public class MethodOverloadingDemo {

  public static void main(String[] args) {
    Number number = new Number();
    System.out.println(number.sum());
    System.out.println(number.sum(23, 45));
    System.out.println(number.sum(12, 34, 56));
    System.out.println(number.sum(10.5, 34.6));
    System.out.println(number.sum(24.6, 78.3, 90.0, 23.67));
    System.out.println("=============================================");
    Number number1 = new Number(27, 50);
    System.out.println(number1.sum());
    System.out.println("=============================================");
    Number.tempNumber = 80;
    System.out.println(Number.sum(23));
  }
}
