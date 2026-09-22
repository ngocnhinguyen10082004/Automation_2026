package vn.edu.vtiacademy.lession02;

public class LogicOperators {

  public static void main(String[] args) {
    boolean trueFlag = true;
    boolean falseFlag = false;

    System.out.println(trueFlag + " && " + falseFlag + " = " + (trueFlag && falseFlag));
    System.out.println(trueFlag + " || " + falseFlag + " = " + (trueFlag || falseFlag));
    System.out.println("!" + trueFlag + " = " + (!trueFlag));
    System.out.println("!" + falseFlag + " = " + (!falseFlag));

    int age = 4;
    boolean hasTicket = true;
    boolean validCustomer = (age >= 8 && hasTicket);
    System.out.println("Valid customer: " + validCustomer);
  }

}
