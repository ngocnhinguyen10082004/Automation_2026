package vn.edu.vtiacademy.lesson11;

public class Number {

  private int firstNumber;
  private int secondNumber;

  public static int tempNumber;

  private final double PI = 3.14;

  public Number() {
    firstNumber = secondNumber = -1;
  }

  public Number(int firstNumber, int secondNumber) {
    this.firstNumber = firstNumber;
    this.secondNumber = secondNumber;
  }

  public int sum() {
    return firstNumber + secondNumber;
  }

  public int sum(int... numbers) {
    int sum = 0;
    for (int number : numbers) {
      sum += number;
    }
    return sum;
  }

  public double sum(double... numbers) {
    double sum = 0;
    for (double number : numbers) {
      sum += number;
    }
    return sum;
  }

  public int sum(int firstNumber, int secondNumber) {
    return firstNumber + secondNumber;
  }

  public double sum(double firstNumber, double secondNumber) {
    return firstNumber + secondNumber;
  }

  public double sum(double firstNumber, int secondNumber) {
    return firstNumber + secondNumber;
  }

  public double sum(int firstNumber, double secondNumber) {
    return firstNumber + secondNumber;
  }

  public static int sum(int firstNumber) {
    return tempNumber + firstNumber;
  }
}
