/**
 * Created by gakshintala on 2/23/16.
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {1, 2, 3};
        int coinCount = 3;
        int total = 5;
        System.out.println(CountCoinCombinations(coins, coinCount, total));
    }

    private static int CountCoinCombinations(int[] coins, int coinCount, int total) {
        int[] table = new int[total + 1];
        table[0] = 1;
        /*
        * We are iterating coin after coin, so table[i] already consists of count excluding our coin, we just need to
        * add the value of count including our coin i.e table[j-s[i]];
        */
        for (int i = 0; i < coinCount; i++) {
            for (int j = coins[i]; j <= total; j++) {
                table[j] += table[j - coins[i]];
            }
            print(table);
            System.out.println();
        }
        return table[total];
    }

    private static void print(int[] table) {
        for (int i : table) {
            System.out.print(i + " ");
        }
    }
}
