package vn.edu.vtiacademy.lesson11;

public class Cat extends Animal {

  @Override
  public void sound() {
    System.out.println("Meow meow");
  }

  @Override
  public void run(int speed) {
    System.out.println("Meo speed: " + speed);
  }

}
