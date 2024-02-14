package g1601_1700.s1672_richest_customer_wealth;

// #Easy #Array #Matrix #Programming_Skills_I_Day_6_Array
// #2022_04_23_Time_0_ms_(100.00%)_Space_44.1_MB_(7.31%)

public class Solution {
	//@ requires(*The input `accounts` is a 2D integer array.*);
	//@ requires(*The length of `accounts` is equal to `m`.*);
	//@ requires(*The length of each subarray in `accounts` is equal to `n`.*);
	//@ requires(*The values in `accounts` are positive integers.*);
	//@ requires(*The values in `accounts` are less than or equal to 100.*);
	//@ ensures(*The method returns an integer representing the maximum wealth of any customer.*);
	//@ ensures(*The maximum wealth is calculated by summing the values in each subarray of `accounts`.*);
	//@ ensures(*The maximum wealth is the highest sum among all customers.*);
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