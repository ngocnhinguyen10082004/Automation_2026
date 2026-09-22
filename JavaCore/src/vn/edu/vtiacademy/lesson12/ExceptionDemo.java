package vn.edu.vtiacademy.lesson12;

public class ExceptionDemo {

  public static void main(String[] args) {
    try {
      int a = 10 / 0;
      System.out.println(a);
    } catch (ArithmeticException e) {
      System.out.println("Exception: " + e.getMessage());
    }
    try {
      String obj = null;
      System.out.println(obj.length());
    } catch (NullPointerException e) {
      System.out.println("Exception: " + e.getMessage());
    }

    try {
      String obj = "dffdsfs";
      System.out.println(Integer.parseInt(obj));
      int[] numbers = {1, 2, 3, 4};
      System.out.println(numbers[5]);
    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
      System.out.println("Exception: " + e.getMessage());
    }

    try {
      int a = 10 / 0;
      System.out.println(a);
    } catch (Exception e) {
      System.out.println("Exception: " + e.getMessage());
    }
  }

}
