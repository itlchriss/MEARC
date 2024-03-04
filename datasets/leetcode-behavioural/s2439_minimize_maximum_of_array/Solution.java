package g2401_2500.s2439_minimize_maximum_of_array;

// #Medium #Array #Dynamic_Programming #Greedy #Binary_Search #Prefix_Sum
// #2022_12_13_Time_10_ms_(90.25%)_Space_83_MB_(30.03%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 2.*);
//@ ensures(*The elements of the input array `nums` are non-negative integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the minimum possible value of the maximum integer in the array `nums` after performing any number of operations.*);
    public int minimizeArrayValue(int[] nums) {
        long max = 0;
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, (sum + i) / (i + 1));
        }
        return (int) max;
    }
}