package vn.edu.vtiacademy.lesson13;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListDemo {

  public static void main(String[] args) {
    ArrayList<Integer> numbers = new ArrayList<>();

//    System.out.println("Number of elements in Number list: " + numbers.size());
//    numbers.add(12);
//    numbers.add(34);
//    numbers.add(20);
//
//    System.out.println("Number of elements in Number list: " + numbers.size());
//    for (Integer number: numbers) {
//      System.out.print(number + " ");
//    }
//    System.out.println();
//    ArrayList<Integer> secondNumbers = new ArrayList<>(Arrays.asList(12, 67, 58));
//    System.out.println("Number of elements in secondNumber list: " + secondNumbers.size());
//    for (Integer number: secondNumbers) {
//      System.out.print(number + " ");
//    }
//    System.out.println();
//    System.out.println("Merge second numbers to first numbers");
//    numbers.addAll(secondNumbers);
//    System.out.println("Number of elements in Number list: " + numbers.size());
//    for (Integer number: numbers) {
//      System.out.print(number + " ");
//    }
//    System.out.println();

    addElement(numbers, 12);
    addElement(numbers, 34);
    addElement(numbers, 20);
    show(numbers);
    System.out.println();
    ArrayList<Integer> secondNumbers = new ArrayList<>(Arrays.asList(12, 67, 58));
    merge(numbers, secondNumbers);
    show(numbers);
    editElementAtIndex(numbers, 3, 100);
    show(numbers);
    deleteElementAtIndex(numbers, 5);
    show(numbers);

    System.out.println("Index of 67 is: " + findIndexOf(numbers, 67));
    System.out.println("Index of 99 is: " + findIndexOf(numbers, 99));
  }

  public static void addElement(ArrayList<Integer> numbers, Integer number) { // = create
    numbers.add(number);
  }

  public static void merge(ArrayList<Integer> firstList, ArrayList<Integer> secondList) { // = create/update
    firstList.addAll(secondList);
  }

  public static void show(ArrayList<Integer> numbers) { // = read
    for (Integer number: numbers) {
      System.out.print(number + " ");
    }
    System.out.println();
  }

  public static void editElementAtIndex(ArrayList<Integer> numbers, int index, int newValue) { // = update
    numbers.set(index, newValue);
  }

  public static void deleteElementAtIndex(ArrayList<Integer> numbers, int index) { // = delete
    numbers.remove(index);
  }

  public static int findIndexOf(ArrayList<Integer> numbers, Integer number) {
    return numbers.indexOf(number);
  }
}