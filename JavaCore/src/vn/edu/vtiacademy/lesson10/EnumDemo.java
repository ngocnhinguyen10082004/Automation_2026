package vn.edu.vtiacademy.lesson10;

public class EnumDemo {

  public static void main(String[] args) {
    for(Weekday weekday: Weekday.values()) {
      System.out.println(weekday);
    }
  }
}
