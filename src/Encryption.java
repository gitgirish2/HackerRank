import java.util.Scanner;

/**
 * Created by gakshintala on 12/9/15.
 */
public class Encryption {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        int l = str.length();

        int row = (int) Math.sqrt(l);
        int col = row;
        if (row * col < l) {
            col++;
            if (row * col < l)
                row++;
        }
        int c = 0;
        char[][] mat = new char[row][col];

        up:
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                if (c == l) break up;
                mat[i][j] = str.charAt(c++);
            }

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++)
                if (mat[j][i] != '\u0000')
                    System.out.print(mat[j][i]);
            System.out.print(" ");
        }
    }
}
