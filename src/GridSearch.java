import java.util.Scanner;

/**
 * Created by gakshintala on 12/15/15.
 */
public class GridSearch {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int tc = scn.nextInt();

        while (tc-- > 0) {
            int pRow = scn.nextInt();
            int pCol = scn.nextInt();
            String[] pMat = readMatrix(scn, pRow);

            int cRow = scn.nextInt();
            int cCol = scn.nextInt();
            String[] cMat = readMatrix(scn, cRow);

            boolean isPresent = false;
            for (int i = 0; i <= pCol - cCol && !isPresent; i++) {
                int start = pMat[i].indexOf(cMat[0]);
                if (start != -1)
                    isPresent = checkPresence(pMat, cMat, start, i, cRow, cCol);
            }
            System.out.println(isPresent ? "YES" : "NO");
        }
    }

    private static boolean checkPresence(String[] pMat, String[] cMat, int start, int matchRow, int cRow, int cCol) {
        for (int i = matchRow + 1; i < matchRow + cRow; i++)
            if (!pMat[i].substring(start, start + cCol).equalsIgnoreCase(cMat[i - matchRow])) return false;
        return true;
    }

    private static String[] readMatrix(Scanner scn, int row) {
        String[] mat = new String[row];
        for (int i = 0; i < row; i++)
            mat[i] = scn.next();
        return mat;
    }
}
