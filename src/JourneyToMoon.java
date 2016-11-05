import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by gakshintala on 12/21/15.
 */
public class JourneyToMoon {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        QuickFind quickFind = new QuickFind(n);

        int pairs = scn.nextInt();
        while (pairs-- > 0)
            quickFind.union(scn.nextInt(), scn.nextInt());

        int[] groupCount = quickFind.getAllGroupCounts();
        
        /*int combinations = 0;
        for(int i=0; i<groupCount.length-1;i++)
            for(int j=i+1;j<groupCount.length;j++)
                combinations+=groupCount[i]*groupCount[j];*/

        int inEligibleCombinations = 0;
        for (int count : groupCount)
            inEligibleCombinations += getCombinations(count);

        System.out.println(getCombinations(n) - inEligibleCombinations);
    }

    private static long getCombinations(long n) {
        if (n <= 1) return 0;
        return n * (n - 1) / 2;
    }
}


class QuickFind {
    int[] id;

    QuickFind(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < this.id.length; i++) {
            if (id[i] == pid) id[i] = qid;
        }
    }

    public int[] getAllGroupCounts() {
        Map<Integer, Integer> idCountMap = new HashMap<>();
        for (int id : this.id) {
            Integer count = idCountMap.get(id);
            count = (count == null ? 0 : count);
            idCountMap.put(id, count + 1);
        }
        int[] countArray = new int[idCountMap.size()];
        int i = 0;
        for (int count : idCountMap.values())
            countArray[i++] = count;
        return countArray;
    }
}
