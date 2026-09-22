package vn.edu.vtiacademy.lesson11;

public class InheritanceDemo {

  public static void main(String[] args) {
    Rectangle rectangle = new Rectangle(23, 57, "solid");
//    rectangle.width = 56;
    System.out.println("Width of rectangle: " + rectangle.getWidth());
    System.out.println("Height of rectangle: " + rectangle.getHeigh());
    System.out.println("Style of rectangle: " + rectangle.getStyle());
    System.out.println("Area of rectangle: " + rectangle.getArea());
    System.out.println("Perimeter of rectangle: " + rectangle.getPerimeter());
    System.out.println("========================================");

    Square square = new Square(34);
    System.out.println("Width of square: " + square.getWidth());
    System.out.println("Height of square: " + square.getHeigh());
    System.out.println("Area of square: " + square.getArea());
    System.out.println("Perimeter of square: " + square.getPerimeter());
  }
}
