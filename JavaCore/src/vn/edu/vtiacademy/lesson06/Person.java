package vn.edu.vtiacademy.lesson06;

public class Person {

  private String firstName;  // null
  private String lastName; // null
  private int age; // 0
  private String email;  // null
  private String phoneNumber; //null

//  public Person() {
//    firstName = null;
//    lastName = null;
//    age = 0;
//    email = null;
//    phoneNumber = null;
//  }

  public Person() {
    firstName = null;
    lastName = null;
    age = 0;
    email = null;
    phoneNumber = null;
  }
//
  public Person(String firstName, String lastName, int age, String email, String phoneNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  public Person(String firstName, String lastName, String email, String phoneNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    age = 20;
  }

  public Person(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    age = 30;
    phoneNumber = null;
  }

  void printInfo() {
    System.out.println("Full name: " + firstName + " " + lastName);
    System.out.println("Age: " + age + " Email: " + email + " Phone number: " + phoneNumber);
  }
}
