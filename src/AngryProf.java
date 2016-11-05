import java.util.Scanner;

public class AngryProf {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int c = scn.nextInt();
        for (int i = 0; i < c; i++) {
            int n = scn.nextInt();
            int k = scn.nextInt();

            for (int j = 0; j < n; j++) {
                int num = scn.nextInt();
                if (k > 0 && num <= 0) k--;
                else continue;
            }

            if (k == 0) System.out.println("NO");
            else System.out.println("YES");
        }
    }
}