package vn.edu.vtiacademy.lesson04;

public class Casting {

  public static void main(String[] args) {
    int i = 10;
    float f = i;
    System.out.println("i = " + i);
    System.out.println("f = " + f);

    double firstNumber = 10.5, secondNumber = 2.4;
    double divDouble = firstNumber / secondNumber;
    System.out.println(divDouble);
    int div = (int) (firstNumber / secondNumber);
    System.out.println(div);
  }
}
