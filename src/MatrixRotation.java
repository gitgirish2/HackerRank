import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by gakshintala on 12/10/15.
 */
public class MatrixRotation {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int row = scn.nextInt();
        int col = scn.nextInt();
        int rotationCount = scn.nextInt();

        long[][] mat = new long[row][col];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                mat[i][j] = scn.nextInt();

        for (int layer = 0; layer < row / 2 && layer < col / 2; layer++) {
            int layerRow = row - 2 * layer;
            int layerCol = col - 2 * layer;
            int layerPerimeter = 2 * (layerRow + layerCol - 2);
            int layerRotateCount = rotationCount % layerPerimeter;
            if (layerRotateCount != 0)
                //rotateLayerOneByOne(row, col, mat, layer, layerRotateCount);
                rotateLayer(mat, layer, layerRow, layerCol, layerPerimeter, layerRotateCount);
        }
        printMatrix(row, col, mat);
    }

    private static void printMatrix(int row, int col, long[][] mat) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++)
                str.append(mat[i][j]).append(" ");
            str.append("\n");
        }
        System.out.println(str);
    }

    /*private static void rotateLayerOneByOne(int row, int col, long[][] mat, int layer, int layerRotateCount) {
        while (layerRotateCount-- > 0) {
            int r = row - layer - 1;
            int c = col - layer - 1;
            long temp = mat[r][c];

            //Bottom
            while (--c >= layer)
                mat[r][c + 1] = mat[r][c];

            //Left
            r = row - layer - 1;
            c = layer;
            while (--r >= layer)
                mat[r + 1][c] = mat[r][c];

            // Top
            r = c = layer;
            while (++c <= col - layer - 1)
                mat[r][c - 1] = mat[r][c];

            // Right
            r = layer;
            c = col - layer - 1;
            while (++r <= row - layer - 1)
                mat[r - 1][c] = mat[r][c];

            mat[row - layer - 2][col - layer - 1] = temp;
        }
    }*/

    private static void rotateLayer(long[][] mat, int layer, int layerRow, int layerCol, int layerPerimeter, int layerRotateCount) {
        for (int currentRotateOriginVal = 1; currentRotateOriginVal <= gcd(layerPerimeter, layerRotateCount); currentRotateOriginVal++) {
            int[] originIndex, nextIndex, currentIndex;
            originIndex = findIndexOnPerimeter(currentRotateOriginVal, layerRow, layerCol, layer);
            long temp = mat[originIndex[0]][originIndex[1]];
            nextIndex = originIndex;
            int i = 1;
            while (true) {
                currentIndex = nextIndex;
                int nextIndexVal = (currentRotateOriginVal + (i * layerRotateCount) % layerPerimeter) % layerPerimeter;
                if (nextIndexVal == 0) {
                    nextIndex = new int[2];
                    nextIndex[0] = 1 + layer;
                    nextIndex[1] = layer;
                } else
                    nextIndex = findIndexOnPerimeter(nextIndexVal, layerRow, layerCol, layer);

                if (Arrays.equals(nextIndex, originIndex))
                    break;

                mat[currentIndex[0]][currentIndex[1]] = mat[nextIndex[0]][nextIndex[1]];
                i++;
            }
            mat[currentIndex[0]][currentIndex[1]] = temp;
        }
    }

    private static int[] findIndexOnPerimeter(int indexVal, int layerRow, int layerCol, int layer) {
        int[] nextIndex = new int[2];
        if (indexVal <= layerCol) { // Top
            nextIndex[0] = 0;
            nextIndex[1] = indexVal - 1;
        } else if (indexVal <= layerRow + layerCol - 1) { // Right
            nextIndex[0] = indexVal - layerCol;
            nextIndex[1] = layerCol - 1;
        } else if (indexVal <= layerRow + 2 * layerCol - 2) { // Bottom
            nextIndex[0] = layerRow - 1;
            nextIndex[1] = layerCol - (indexVal - (layerRow + layerCol - 1)) - 1;
        } else { // Left
            nextIndex[0] = layerRow - (indexVal - (2 * layerCol + layerRow - 2)) - 1;
            nextIndex[1] = 0;
        }
        nextIndex[0] += layer;
        nextIndex[1] += layer;
        return nextIndex;
    }

    public static int gcd(int a, int b) {
        while (true) {
            if (a % b == 0) return b;
            return gcd(b, a % b);
        }
    }
}
