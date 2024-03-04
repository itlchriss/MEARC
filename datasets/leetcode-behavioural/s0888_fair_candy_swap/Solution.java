package g0801_0900.s0888_fair_candy_swap;

// #Easy #Array #Hash_Table #Sorting #Binary_Search
// #2022_03_28_Time_18_ms_(68.20%)_Space_72.2_MB_(19.02%)

import java.util.HashSet;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input arrays `aliceSizes` and `bobSizes` are not null.*);
//@ ensures(*The lengths of `aliceSizes` and `bobSizes` are greater than or equal to 1.*);
//@ ensures(*The elements of `aliceSizes` and `bobSizes` are positive integers.*);
//@ ensures(*The total number of candies in `aliceSizes` is not equal to the total number of candies in `bobSizes`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `answer` is not null.*);
//@ ensures(*The length of `answer` is 2.*);
//@ ensures(*The elements of `answer` are positive integers.*);
//@ ensures(*The sum of `aliceSizes` minus `answer[0]` is equal to the sum of `bobSizes` minus `answer[1]`.*);
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int aSum = 0;
        int bSum = 0;
        int diff;
        int[] ans = new int[2];
        for (int bar : aliceSizes) {
            aSum += bar;
        }
        for (int bar : bobSizes) {
            bSum += bar;
        }
        diff = aSum - bSum;
        HashSet<Integer> set = new HashSet<>();
        for (int bar : aliceSizes) {
            set.add(bar);
        }
        for (int bar : bobSizes) {
            if (set.contains(bar + diff / 2)) {
                ans[0] = bar + diff / 2;
                ans[1] = bar;
                break;
            }
        }
        return ans;
    }
}