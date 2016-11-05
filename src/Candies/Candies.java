package Candies;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by gakshintala on 2/25/16.
 */
public class Candies {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int childrenCount = scn.nextInt();
        long[] rating = new long[childrenCount];
        for (int i = 0; i < childrenCount; i++) {
            rating[i] = scn.nextInt();
        }

        long[] candies = new long[childrenCount];

        candies[0] = 1;

        for (int i = 1; i < childrenCount; i++) {
            if (rating[i - 1] < rating[i])
                candies[i] = candies[i - 1] + 1;
            else candies[i] = 1;
        }

        for (int i = childrenCount - 1; i > 0; i--) {
            if (rating[i] < rating[i - 1])
                candies[i - 1] = Math.max(candies[i - 1], candies[i] + 1); // This is required at peak points
        }

        System.out.println("Distribution: " + Arrays.toString(candies));
        int total = 0;
        for (int i = 0; i < childrenCount; i++)
            total += candies[i];

        System.out.println(total);
    }
}
