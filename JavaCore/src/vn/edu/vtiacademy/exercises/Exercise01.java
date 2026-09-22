package vn.edu.vtiacademy.exercises;

import java.util.Scanner;

public class Exercise01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Doc ba canh tam giac tu ban phim
        System.out.print("Nhap canh a: ");
        double a = scanner.nextDouble();
        System.out.print("Nhap canh b: ");
        double b = scanner.nextDouble();
        System.out.print("Nhap canh c: ");
        double c = scanner.nextDouble();

        // Bat dang thuc tam giac: tong hai canh bat ky phai lon hon canh con lai,
        // neu khong thoa thi ba canh nay khong the tao thanh mot tam giac
        if (a <= 0 || b <= 0 || c <= 0 || a + b <= c || b + c <= a || a + c <= b) {
            System.out.println("Ba canh nhap vao khong tao thanh mot tam giac.");
            return;
        }

        boolean isEqual = (a == b) && (b == c); // ca ba canh bang nhau -> tam giac deu
        boolean isIsosceles = (a == b) || (b == c) || (a == c); // co it nhat hai canh bang nhau -> tam giac can
        boolean isRight = isRightTriangle(a, b, c); // thoa dinh ly Pythagoras -> tam giac vuong

        // Uu tien kiem tra vuong can truoc vi no vua vuong vua can,
        // sau do moi den deu, vuong, can, va cuoi cung la thuong
        String result;
        if (isRight && isIsosceles) { // if ís right triangle and is isosceles triangle
            result = "Tam giac vuong can"; // print Tam giac vuong can
        } else if (isEqual) {
            result = "Tam giac deu";
        } else if (isRight) {
            result = "Tam giac vuong";
        } else if (isIsosceles) {
            result = "Tam giac can";
        } else {
            result = "Tam giac thuong";
        }

        System.out.println("Ket qua: " + result);
    }

    // Kiem tra tam giac vuong bang dinh ly Pythagoras dao:
    // binh phuong canh lon nhat (canh huyen) bang tong binh phuong hai canh con lai
    private static boolean isRightTriangle(double a, double b, double c) {
        double max = Math.max(a, Math.max(b, c)); // canh lon nhat, ung vien canh huyen
        double sumOfSquaresOfOthers = a * a + b * b + c * c - max * max; // tong binh phuong hai canh con lai

        // So sanh bang mot khoang dung sai nho (1e-9) thay vi == de tranh sai so lam tron cua kieu double
        return Math.abs(max * max - sumOfSquaresOfOthers) < 1e-9;
    }
}
