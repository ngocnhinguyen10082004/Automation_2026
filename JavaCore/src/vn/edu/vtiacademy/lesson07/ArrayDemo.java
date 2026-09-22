package vn.edu.vtiacademy.lesson07;

public class ArrayDemo {

  String firstName; // global variable

  public static void main(String[] args) {
    int numbers[] = new int[10]; // C style => global variable - local variable
    for (int i = 0; i < 10; i++) { // i = 0, 1, 2, ..., 9, 10
      numbers[i] = i;
    }

    for (int i = 0; i < 10; i++) {
      System.out.print(numbers[i] + " ");
    }
    System.out.println();

    int[] intNumbers = new int[10]; // Java style

    for (int i = 0; i < intNumbers.length; i++) { // i means local variable
      intNumbers[i] = i;
    }

    for (int i = 0; i < intNumbers.length; i++) {
      System.out.print(numbers[i] + " ");
    }
    System.out.println();

    long[] longNumbers = {20, 34, 3424, 45435235};
    for (int i = 0; i < longNumbers.length; i++) {
      System.out.print(longNumbers[i] + " ");
    }
    System.out.println();
    for (long number: longNumbers) { // for-each = enhanced for
      System.out.print(number + " ");
    }
    System.out.println();
  }

  public void printInfo() {
    System.out.print(firstName);
  }
}
