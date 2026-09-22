package vn.edu.vtiacademy.lesson06;

public class PersonDemo {

  public static void main(String[] args) {
    Person john = new Person("John", "havey", 45, "john_havey@mailinator.com", "0923423423");
    john.printInfo();
//    john.firstName = "John";
//    john.lastName = "Havey";
//    john.age = 45;
//    john.email = "john_havey@mailinator.com";
//    john.phoneNumber = "0923423423";

//    john.printInfo();

    Person john02 = new Person("John", "White", 33, "john_white@mailinator.com", "09354325");
    john02.printInfo();
//    john02.firstName = "John";
//    john02.lastName = "White";
//    john02.age = 33;
//    john02.email = "john_white@mailinator.com";
//    john02.phoneNumber = "09354325";

//    john02.printInfo();

    Person person03 = new Person("Kevin", "White", 45, "kevin_white@mailinator.com", "342424234234");
    person03.printInfo();

    Person person04 = new Person();
    person04.printInfo();

    Person person05 = new Person();
    person05.printInfo();

    Person person06 = null;
    person06.printInfo();
  }
}
