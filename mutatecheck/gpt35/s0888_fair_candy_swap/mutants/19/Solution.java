package g0801_0900.s0888_fair_candy_swap;

// #Easy #Array #Hash_Table #Sorting #Binary_Search
// #2022_03_28_Time_18_ms_(68.20%)_Space_72.2_MB_(19.02%)

import java.util.HashSet;

public class Solution {
// requires aliceSizes.length <= 10000 && bobSizes.length <= 10000
// ensures \result != null
// requires (\forall int i; 0 <= i && i < aliceSizes.length; aliceSizes[i] >= 1 && aliceSizes[i] <= 100000)
// ensures aliceSizes.length > 0 && bobSizes.length > 0
// ensures aliceSizes.length <= 10000 && bobSizes.length <= 10000
// requires (\forall int j; 0 <= j && j < bobSizes.length; bobSizes[j] >= 1 && bobSizes[j] <= 100000)
// ensures \result.length == 2
// requires aliceSizes != null && bobSizes != null
// requires public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes)
// requires aliceSizes.length > 0 && bobSizes.length > 0
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
                ans[0] = bar + diff + 2;
                ans[1] = bar;
                break;
            }
        }
        return ans;
    }
}
