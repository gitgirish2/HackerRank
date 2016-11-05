import java.util.Scanner;

public class InsertionSort {

    public static void main(String[] args) {
        long[] a = readIntoArray();
        sort(a);
        int n = a.length;
        int[] minDiffIndex = new int[n - 1];
        long minDiff = Math.abs(a[1] - a[0]);
        int k = 0;
        minDiffIndex[k++] = 1;
        for (int i = 2; i < n; i++) {
            long diff = Math.abs(a[i] - a[i - 1]);
            if (minDiff > diff) {
                minDiff = diff;
                k = 0;
                minDiffIndex[k++] = i;
            } else if (minDiff == diff)
                minDiffIndex[k++] = i;
        }

        for (int i = 0; i < k; i++) {
            System.out.print(" " + a[minDiffIndex[i] - 1] + " " + a[minDiffIndex[i]]);
        }
    }

    public static long[] readIntoArray() {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        long[] a = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = scn.nextInt();
        }

        return a;
    }

    public static void sort(long[] a) {
        for (int i = 1; i < a.length; i++) {
            int j = i - 1;
            long temp = a[i];
            boolean flag = false;
            for (; j >= 0; j--) {
                if (temp < a[j]) {
                    flag = true;
                    a[j + 1] = a[j];
                } else break;
            }
            if (flag) {
                a[j + 1] = temp;
            }
        }
    }
}