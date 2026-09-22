package vn.edu.vtiacademy.lesson06;

public class VehicleDemo {

  public static void main(String[] args) {
    Vehicle minivan = new Vehicle();
    Vehicle sportsCar = new Vehicle();

    System.out.println("===========Minivan");
    minivan.passenger = 7;
    minivan.fuelCap = 16;
    minivan.mpg = 21;
    System.out.println("Range: " + minivan.calculateRange());
    System.out.print("Need fuel " + minivan.fuelNeed(250) + " gallon for 250 miles");
    System.out.println();

    System.out.println("===========SportsCar");
    sportsCar.passenger = 2;
    sportsCar.fuelCap = 14;
    sportsCar.mpg = 12;
    System.out.println("Range: " + sportsCar.calculateRange());
  }
}
