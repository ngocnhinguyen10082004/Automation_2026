package vn.edu.vtiacademy.lesson11;

public class Rectangle extends TwoDShape {

  private String style;

  public Rectangle(int width, int height, String style) {

    super(width, height);
//    setHeigh(height);
//    setWidth(width);
    this.style = style;
  }

  public int getPerimeter() {
//    width = 43;
    return 2 * (getWidth() + getHeigh());
  }

  public String getStyle() {
    return style;
  }

  @Override
  public int getArea() {
    return getWidth() * getHeigh();
  }
}
