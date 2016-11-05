import java.util.ArrayList;
import java.util.Scanner;

public class VeryBigSum {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        ArrayList<Integer> sum = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sum = sumArrays(sum, intToArrayList(scn.nextInt()));
        }

        for (int i = sum.size() - 1; i >= 0; i--) {
            System.out.print(sum.get(i));
        }
    }

    public static ArrayList<Integer> intToArrayList(int a) {
        ArrayList<Integer> number = new ArrayList<>();
        for (; a > 0; a /= 10) {
            number.add(a % 10);
        }
        return number;
    }

    public static ArrayList<Integer> sumArrays(ArrayList<Integer> a, ArrayList<Integer> b) {
        int i = 0, carry = 0;
        ArrayList<Integer> sum = new ArrayList<>();
        for (; i < a.size() && i < b.size(); i++) {
            int digitSum = carry + a.get(i) + b.get(i);
            sum.add(i, digitSum % 10);
            carry = digitSum / 10;
        }

        for (; i < a.size(); i++) {
            int digitSum = carry + a.get(i);
            sum.add(i, digitSum % 10);
            carry = digitSum / 10;
        }

        for (; i < b.size(); i++) {
            int digitSum = carry + b.get(i);
            sum.add(i, digitSum % 10);
            carry = digitSum / 10;
        }

        if (carry != 0) sum.add(i, carry);

        return sum;
    }
}