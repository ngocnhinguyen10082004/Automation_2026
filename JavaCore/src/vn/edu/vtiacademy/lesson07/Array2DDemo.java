package vn.edu.vtiacademy.lesson07;

public class Array2DDemo {

  public static void main(String[] args) {
    int i, j;
    int[][] table = new int[3][4];

    for (i = 0; i < 3; i++) {
      for (j = 0; j < 4; j++) {
        table[i][j] = (j * 4) + i + 1;
      }
    }

    for (i = 0; i < 3; i++) {
      for (j = 0; j < 4; j++) {
        System.out.print(table[i][j] + " "); //table[1][1] = [2 6 10 14][1] = 6
      }
      System.out.println();
    }
    System.out.println("=======================");
    for (int row[] : table) {
      for (int col: row) {
        System.out.print(col + " ");
      }
      System.out.println();
    }
  }
}
