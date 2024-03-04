package g1201_1300.s1223_dice_roll_simulation;

// #Hard #Array #Dynamic_Programming #2022_03_12_Time_9_ms_(90.98%)_Space_41.1_MB_(86.47%)

public class Solution {
    private static final long MOD = 1000000007;
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `rollMax` must have a length of - The elements of `rollMax` must be integers.*);
//@ ensures(*The elements of `rollMax` must be between 1 and 15 (inclusive).*);
//@ ensures(*The input integer `n` must be between 1 and 5000 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method must return an integer.*);
//@ ensures(*The returned integer must be the number of distinct sequences that can be obtained with exactly `n` rolls.*);
//@ ensures(*The returned integer must be modulo 10^9 + 7.*);

    public int dieSimulator(int n, int[] rollMax) {
        long[][] all = new long[6][15 + 1];
        long[] countsBySide = new long[6];
        long total = 0;
        long newTotal;
        int max;
        for (int j = 0; j < all.length; j++) {
            all[j][1] = 1;
            countsBySide[j] = 1;

            total = 6;
        }
        for (int i = 1; i < n; i++) {
            newTotal = total;
            for (int j = 0; j < all.length; j++) {
                all[j][0] = (total - countsBySide[j]) % MOD;
                max = rollMax[j];
                newTotal = (newTotal - all[j][max] + all[j][0]);
                countsBySide[j] = (total - all[j][max]) % MOD;
                System.arraycopy(all[j], 0, all[j], 1, max);
            }
            total = newTotal;
        }
        return (int) (total % MOD);
    }
}