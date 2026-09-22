package vn.vtiacademy;

import io.qameta.allure.Step;

public class MathProvider {

  @Step("Add two numbers: {0}, {1}")
  public int add(int a, int b) {
    return a + b;
  }

  @Step("Subtract two numbers: {0}, {1}")
  public int subtract(int a, int b) {
    return a - b;
  }

  @Step("Multiply two numbers: {0}, {1}")
  public int multiply(int a, int b) {
    return a * b;
  }

  public int divide(int a, int b) {
    return a / b;
  }
}
