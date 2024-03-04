package g2601_2700.s2656_maximum_sum_with_exactly_k_elements;

// #Easy #Array #Greedy #2023_09_07_Time_1_ms_(100.00%)_Space_43.8_MB_(40.39%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The input integer `k` is greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the maximum score that can be achieved after performing the operation exactly `k` times.*);
//@ ensures(*The input array `nums` is modified after each iteration of the operation.*);
//@ ensures(*The length of the input array `nums` remains the same after each iteration of the operation.*);
//@ ensures(*The values in the input array `nums` are updated according to the operation.*);
//@ ensures(*The sum of the selected elements from the input array `nums` is maximized after performing the operation exactly `k` times.*);
    public int maximizeSum(int[] nums, int k) {
        int max = 0;
        int sum = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        for (int i = 0; i < k; i++) {
            sum += max;
            max++;
        }
        return sum;
    }
}