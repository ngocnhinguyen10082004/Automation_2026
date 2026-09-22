package vn.edu.vtiacademy.lesson09;

public class VarargsDemo {

  public static void main(String[] args) {
    System.out.println("sum of zero number: " + sum());
    System.out.println("sum of two number: " + sum02(new int[]{10, 20}));
    System.out.println("sum of one number: " + sum(10));
    System.out.println("sum of two numbers: " + sum(10, 15));
    System.out.println("sum of three numbers: " + sum(10, 15, 20));
    System.out.println("sum of four numbers: " + sum(10, 15, 20, 25));
    System.out.println("sum of five numbers: " + sum(10, 15, 20, 25, 30));

    for(String arg: args) {
      System.out.println(arg);
    }
  }

  public static int sum(int... numbers) { // ... <=> []
    int sum = 0;
    for (int number: numbers) {
      sum += number;
    }
    return sum;
  }

  public static int sum02(int[] numbers) {
    int sum = 0;
    for (int number: numbers) {
      sum += number;
    }
    return sum;
  }
}
