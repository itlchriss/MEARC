package g0801_0900.s0875_koko_eating_bananas;

// #Medium #Array #Binary_Search #Binary_Search_II_Day_4
// #2022_03_28_Time_15_ms_(91.32%)_Space_55_MB_(6.01%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `piles` must not be null.*);
//@ ensures(*The length of the input array `piles` must be greater than or equal to 1.*);
//@ ensures(*The input integer `h` must be greater than or equal to the length of the input array `piles`.*);
//@ ensures(*The input array `piles` must contain positive integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output integer `k` must be the minimum integer such that Koko can eat all the bananas within `h` hours.*);
//@ ensures(*The output integer `k` must be greater than or equal to 1.*);
//@ ensures(*The output integer `k` must be less than or equal to the maximum value in the input array `piles`.*);
//@ ensures(*Koko must be able to finish eating all the bananas within `h` hours using the output integer `k`.*);
    public int minEatingSpeed(int[] piles, int h) {
        int maxP = piles[0];
        long sumP = 0L;
        for (int pile : piles) {
            maxP = Math.max(maxP, pile);
            sumP += pile;
        }
        // binary search
        int low = (int) ((sumP - 1) / h + 1);
        int high = maxP;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (isPossible(piles, mid, h)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean isPossible(int[] piles, int k, int h) {
        int sum = 0;
        for (int pile : piles) {
            sum += (pile - 1) / k + 1;
        }
        return sum <= h;
    }
}