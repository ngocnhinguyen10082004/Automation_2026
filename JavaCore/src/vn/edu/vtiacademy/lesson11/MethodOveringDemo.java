package vn.edu.vtiacademy.lesson11;

public class MethodOveringDemo {

  public static void main(String[] args) {
    Animal animal = new Animal();
    animal.sound();
    animal.run(30);

    Cat cat = new Cat();
    cat.sound();
    cat.run(50);

    Dog dog = new Dog();
    dog.sound();
    dog.run(60);
  }
}
