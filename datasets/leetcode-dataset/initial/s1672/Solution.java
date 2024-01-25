package g1601_1700.s1672_richest_customer_wealth;

// #Easy #Array #Matrix #Programming_Skills_I_Day_6_Array
// #2022_04_23_Time_0_ms_(100.00%)_Space_44.1_MB_(7.31%)

public class Solution {
//@ ensures(*Both customers are considered the richest with a wealth of 6 each, so return 6.
You are given an `m x n` integer grid `accounts` where `accounts[i][j]` is the amount of money the <code>i<sup>th</sup></code> customer has in the <code>j<sup>th</sup></code> bank. Return _the **wealth** that the richest customer has._*);

    public int maximumWealth(int[][] accounts) {
        int max = Integer.MIN_VALUE;
        for (int[] account : accounts) {
            int sum = 0;
            for (int i : account) {
                sum += i;
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}