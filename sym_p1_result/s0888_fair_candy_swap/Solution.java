package g0801_0900.s0888_fair_candy_swap;

// #Easy #Array #Hash_Table #Sorting #Binary_Search
// #2022_03_28_Time_18_ms_(68.20%)_Space_72.2_MB_(19.02%)

import java.util.HashSet;

public class Solution {
//@ requires(*Alice and Bob have a different total number of candies.*);
//@ requires(*You are given two integer arrays param_aliceSizes and param_bobSizes where `aliceSizes[i]` is the number of candies of the <code>i<sup>th</sup></code> box of candy that Alice has and `bobSizes[j]` is the number of candies of the <code>j<sup>th</sup></code> box of candy that Bob has.*);
//@ requires(*Since they are friends, they would like to exchange one candy box each so that after the exchange, they both have the same total amount of candy.*);
//@ requires(*The total amount of candy a person has is the sum of the number of candies in each box they have.*);
//@ requires(*Return an integer array `answer` where `answer[0]` is the number of candies in the box that Alice must exchange, and `answer[1]` is the number of candies in the box that Bob must exchange.*);
//@ requires(*It is guaranteed that at least one answer exists.*);
//@ requires(*Example 1:*);
//@ requires(*Input: aliceSizes = [1,1], bobSizes = [2,2]*);
//@ requires(*Output: [1,2]*);
//@ requires(*Example 2:*);
//@ requires(*Input: aliceSizes = [1,2], bobSizes = [2,3]*);
//@ requires(*Output: [1,2]*);
//@ requires(*Example 3:*);
//@ requires(*Input: aliceSizes = [2], bobSizes = [1,3]*);
//@ requires(*Output: [2,3]*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= aliceSizes.length, bobSizes.length <= 10<sup>4</sup></code>*);
//@ requires(*<code>1 <= aliceSizes[i], bobSizes[j] <= 10<sup>5</sup></code>*);
//@ requires(*Alice and Bob have a different total number of candies.*);
//@ requires(*There will be at least one valid answer for the given input.*);
//@ ensures(*If there are multiple answers, you may the result is any one of them.*);
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