import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by gakshintala on 12/8/15.
 */
public class ManasaStones {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int c = scn.nextInt();
        while (c-- != 0) {
            int n = scn.nextInt();
            int diff1 = scn.nextInt();
            int diff2 = scn.nextInt();
            Set<Integer> possibles = new TreeSet<>();
            possibles.add(diff1);
            possibles.add(diff2);
            n -= 2;
            while (n-- != 0) {
                Set<Integer> temp = new TreeSet<>();
                for (int v : possibles) {
                    temp.add(v + diff1);
                    temp.add(v + diff2);
                }
                possibles = temp;
            }
            StringBuilder out = new StringBuilder();
            for (int possible : possibles)
                out.append(possible + " ");
            System.out.println(out);
        }
    }
}
