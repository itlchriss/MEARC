package g2401_2500.s2412_minimum_money_required_before_transactions;

// #Hard #Array #Sorting #Greedy #2022_11_15_Time_4_ms_(97.57%)_Space_98_MB_(87.50%)

public class Solution {
	//@ requires(*The input array `transactions` is not null.*);
	//@ requires(*The length of `transactions` is greater than or equal to 1 and less than or equal to 10^- Each element in `transactions` is an array of length - The cost and cashback values in each transaction are non-negative integers.*);
	//@ ensures(*The method returns a long value representing the minimum amount of money required before any transaction.*);
	//@ ensures(*The minimum amount of money required is such that all transactions can be completed regardless of the order of transactions.*);
    public long minimumMoney(int[][] transactions) {
        int max = 0;
        long sum = 0;
        for (int[] transaction : transactions) {
            int diff = transaction[1] - transaction[0];
            if (diff < 0) {
                sum -= diff;
                transaction[0] += diff;
            }
            max = Math.max(max, transaction[0]);
        }
        return max + sum;
    }
}