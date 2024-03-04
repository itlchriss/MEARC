package g1401_1500.s1480_running_sum_of_1d_array;

// #Easy #Array #Prefix_Sum #Level_1_Day_1_Prefix_Sum
// #2022_04_04_Time_0_ms_(100.00%)_Space_42.9_MB_(76.13%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `runningSum` is not null.*);
//@ ensures(*The length of the output array `runningSum` is the same as the length of the input array `nums`.*);
//@ ensures(*The elements in the output array `runningSum` are the running sum of the corresponding elements in the input array `nums`.*);
    public int[] runningSum(int[] nums) {
        int sum = 0;
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            result[i] = sum;
        }
        return result;
    }
}