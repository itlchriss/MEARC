package g1501_1600.s1561_maximum_number_of_coins_you_can_get;

// #Medium #Array #Math #Sorting #Greedy #Game_Theory
// #2022_04_11_Time_34_ms_(69.29%)_Space_77_MB_(21.83%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `piles` is not null.*);
//@ ensures(*The length of the input array `piles` is greater than or equal to 3.*);
//@ ensures(*The length of the input array `piles` is divisible by 3.*);
//@ ensures(*Each element in the input array `piles` is a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the maximum number of coins that can be obtained.*);
//@ ensures(*The output is greater than or equal to 0.*);
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int j = 0;
        int coins = 0;
        for (int i = piles.length - 2; i > 0; i -= 2) {
            coins += piles[i];
            if (++j == piles.length / 3) {
                return coins;
            }
        }
        return coins;
    }
}