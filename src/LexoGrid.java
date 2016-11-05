import java.util.Scanner;

public class LexoGrid {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int c = scn.nextInt();
        for (int i = 0; i < c; i++) {
            int n = scn.nextInt();
            scn.nextLine();
            char[][] a = new char[n][n];
            for (int j = 0; j < n; j++) {
                a[j] = scn.nextLine().toCharArray();
                sort(a[j]);
            }
            int j = 0, k = 0;
            row_loop:
            for (; j < n; j++) {
                k = 0;
                for (; k < n - 1; k++) {
                    if (a[k][j] > a[k + 1][j]) {
                        break row_loop;
                    }
                }
            }

            if (j == n && k == n - 1) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    public static void sort(char[] a) {
        for (int i = 0; i < a.length; i++) {
            char temp = a[i];
            int j = i - 1;
            boolean flag = false;
            for (; j >= 0; j--) {
                if (temp < a[j]) {
                    a[j + 1] = a[j];
                    flag = true;
                } else break;
            }
            if (flag) a[j + 1] = temp;
        }
    }
}