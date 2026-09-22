package vn.edu.vtiacademy.lesson11;

public class Square extends TwoDShape {

  public Square(int width) {
    super(width, width);
//    setWidth(width);
//    setHeigh(width);
  }

  public int getPerimeter() {
    return 4 * getWidth();
  }

  @Override
  public int getArea() {
    return getWidth() * getWidth();
  }
}
