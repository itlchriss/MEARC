package g0801_0900.s0888_fair_candy_swap;

// #Easy #Array #Hash_Table #Sorting #Binary_Search
// #2022_03_28_Time_18_ms_(68.20%)_Space_72.2_MB_(19.02%)

import java.util.HashSet;

public class Solution {
//@ requires(*The length of the integer array parameter `aliceSizes` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The length of the integer array parameter `bobSizes` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `aliceSizes` are less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `bobSizes` are less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*Alice and Bob have a different total number of candies.*);
//@ requires(*There will be at least one valid answer for the given input.*);
//@ requires(*If there are multiple valid answers, you may return any one of them.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 1234\. String Transforms Into Another String*);
//@ requires(*Hard*);
//@ requires(*Given two strings `str1` and `str2` of the same length, determine whether you can transform `str1` into `str2` by doing the following operation **any number of times**:*);
//@ requires(**);
//@ requires(**   Swap the characters at two indices `i` and `j`, where `0 <= i, j < str1.length` and `i!= j`.*);
//@ requires(**);
//@ requires(**   **Notice** that both `str1` and `str2` contain **only lowercase English letters**.*);
//@ requires(**);
//@ requires(*Return `true` _if and only if you can transform_ `str1` _into_ `str2`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** str1 =  "aabcc ", str2 =  "ccdee "*);
//@ requires(***Output:** true*);
//@ requires(***Explanation:** Initially, str1 =  "aabcc " and str2 =  "ccdee ".*);
//@ requires(*We can transform str1 to str2 by doing the following:*);
//@ requires(*"swap str1[0] and str1[2], str1 =  "cbacc "*);
//@ requires(*"swap str1[0] and str1[1], str1 =  "bcaac "*);
//@ requires(*"swap str1[1] and str1[2], str1 =  "bccca "*);
//@ requires(*"swap str1[0] and str1[1], str1 =  "accab "*);
//@ requires(*"swap str1[0] and str1[2], str1 =  "ccdee "*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** str1 =  "leetcode ", str2 =  "codeleet "*);
//@ requires(***Output:** false*);
//@ requires(***Explanation:** There is no way to transform str1 to str2.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= str1.length, str2.length <= 2 * 104`*);
//@ requires(**   `str1` and `str2` contain **only lowercase English letters**.*);
//@ ensures(*The integer array result has exactly two elements.*);
//@ ensures(*The first element of the integer array result is less than or equal to the total number of candies Alice has.*);
//@ ensures(*The second element of the integer array result is less than or equal to the total number of candies Bob has.*);
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