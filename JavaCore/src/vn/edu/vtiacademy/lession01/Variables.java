package vn.edu.vtiacademy.lession01;

public class Variables {

  public static void main(String[] args) {
    // Khai báo biến
    int age = 20;
    String name = "John Doe";
    double height = 1.75;
    boolean isStudent = true; //true/false != pass/fail
    char character = 'C';

    // In ra giá trị của các biến
    System.out.println("Name: " + name);
    System.out.println("Age: " + age);
    System.out.println("Height: " + height);
    System.out.println("Is Student: " + isStudent);
    for(int i = 65; i < 100; i++) {
      System.out.println("character = " + (char) i);
    }
    System.out.println("Character: " + character);
    age = 35;
    character = 70;
    System.out.println("Updated Age: " + age);
    System.out.println("Updated Character: " + (char) character);
  }

  // CTRL + ALT + SHIFT + L: Format code (Windows/Linux)
  // CMD + ALT + SHIFT + L: Format code (Mac)
  // CTRL + /: Comment/Uncomment code (Windows/Linux)
  // CMD + /: Comment/Uncomment code (Mac)
  // CTRL + C, CTRL + V: Copy/Paste code (Windows/Linux)
  // CMD + C, CMD + V: Copy/Paste code (Mac)
}
