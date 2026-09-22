package vn.edu.vtiacademy.lesson11;

public class Dog extends Animal {

  @Override
  public void sound() {
    System.out.println("Gau gau");
  }

  @Override
  public void run(int speed) {
    System.out.println("Gau gau speed: " + speed);
  }

}
