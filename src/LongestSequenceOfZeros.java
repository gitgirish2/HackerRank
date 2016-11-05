/**
 * Created by gakshintala on 3/13/16.
 */
public class LongestSequenceOfZeros {
    public static void main(String[] args) {
        System.out.println(longestSequenceOfZeros(toBinary(100)));
    }

    private static int longestSequenceOfZeros(int[] a) {
        int len = a.length;
        int prev = 0, cur = 0;
        int maxZeros = 0;
        for (int i = 0; i < len; i++) {
            if (a[i] == 0) {
                prev = i;
                while (i < len && a[i] == 0) i++;
                maxZeros = Math.max(maxZeros, i - prev);
            }
        }
        return maxZeros;
    }

    private static int[] toBinary(int N) {
        int[] binary = new int[N == 0 ? 0 : (int) (Math.log(N) / Math.log(2)) + 1];
        int i = 0;
        while (N > 0) {
            binary[i++] = N % 2;
            N /= 2;
        }
        return binary;
    }
}
