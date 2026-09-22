package vn.edu.vtiacademy.lesson11;

public abstract class TwoDShape {
  private int width;
  private int heigh;

  public TwoDShape(int width, int height) {
    this.width = width;
    this.heigh = height;
  }

  public int getWidth() { //find, read
    return width;
  }

  public void setWidth(int width) { //write, input, save
    this.width = width;
  }

  public int getHeigh() {
    return heigh;
  }

  public void setHeigh(int heigh) {
    this.heigh = heigh;
  }

  public abstract int getArea();
}
