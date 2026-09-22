package vn.edu.vtiacademy.lesson06;

public class Vehicle {

  int passenger;
  int fuelCap;
  int mpg; //mile per gallon

  void showRange() {
    System.out.print("Quãng đường đi dược: " + fuelCap * mpg + " dặm");
  }

  int calculateRange() {
    return fuelCap * mpg;
  }

  int fuelNeed(int miles) {
    return miles / mpg;
  }

  void printInfo(int fuelCap, int mpg) {
    System.out.println("Miles: " + fuelCap * mpg);
  }


  // method starts with: is, has, contains => data type of method is boolean
  // method starts with: show, print, set, input, ... => data type of method is void
  // method starts with: get, find, calculate, ... => data type of method is int, double, String, ...

}
