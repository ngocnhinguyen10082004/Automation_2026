package vn.edu.vtiacademy.lession02;

public class AssigmentOperators {

  public static void main(String[] args) {
    int number = 10;

    int sum = 3;
    System.out.println(sum + " += " + number + " = " + (sum += number)); // sum = sum + number
    System.out.println(sum + " -= " + number + " = " + (sum -= number));
    System.out.println(sum + " *= " + number + " = " + (sum *= number));
    System.out.println(sum + " /= " + number + " = " + (sum /= number));
    System.out.println(sum + " %= " + number + " = " + (sum %= number));
    int secondNumber = sum + number;
  }

}
