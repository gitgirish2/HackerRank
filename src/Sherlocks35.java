import java.util.Scanner;

/**
 * Created by gakshintala on 12/8/15.
 */
public class Sherlocks35 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int c = scn.nextInt();

        for (int i = 0; i < c; i++) {
            int digits = scn.nextInt();
            boolean flag = false;

            if (digits < 3) System.out.print(-1);
            else if (digits % 3 == 0) System.out.print(print('5', digits));
            else {
                int d = digits;
                /*while (d < digits / 5 && (digits - d * 5) % 3 != 0)
                    d++;*/

                while (d % 3 != 0) d -= 5;

                /*if (digits - d * 5 < 3) {
                    if (digits % 5 == 0) print('3', digits);
                    else System.out.print(-1);
                } else {
                    print('5', digits - d * 5);
                    print('3', d * 5);
                }*/

                if (d < 0) System.out.print(-1);
                else System.out.print(print('5', d).append(print('3', digits - d)));
            }
            System.out.println();
        }
    }

    public static StringBuilder print(char digit, int digits) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < digits; i++)
            str.append(digit);
        return str;
    }
}
