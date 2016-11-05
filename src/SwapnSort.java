// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

import java.util.Arrays;

public class SwapnSort {

    public static void main(String[] args) {
        int[] arr = {1, 5, 5, 5, 5};
        System.out.println(solution(arr));
    }

    public static boolean solution(int[] A) {
        int length = A.length;
        int i = length - 1;
        for (; i > 0; i--) {
            if (A[i] < A[i - 1]) {
                int k = i;
                while (A[k] == A[k + 1]) k++;
                int j = i - 1;
                while (j >= 0 && A[i] < A[j])
                    j--;
                swap(A, k, j + 1);
                System.out.println(Arrays.toString(A));
                return isArraySorted(A, length);
            }
        }

        return true;
    }

    private static boolean isArraySorted(int[] a, int length) {
        for (int i = 0; i < length - 1; i++) {
            if (a[i] > a[i + 1]) return false;
        }
        return true;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}