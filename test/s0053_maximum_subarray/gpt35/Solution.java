package g0001_0100.s0053_maximum_subarray;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #Array #Dynamic_Programming
// #Divide_and_Conquer #Data_Structure_I_Day_1_Array #Dynamic_Programming_I_Day_5
// #Udemy_Famous_Algorithm #Big_O_Time_O(n)_Space_O(1)
// #2023_08_11_Time_1_ms_(100.00%)_Space_57.7_MB_(90.58%)

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*All the values in the integer array parameter `nums` are less than or equal to 10000 and are greater than or equal to -10000.*);
//@ ensures(*The integer result is less than or equal to the maximum value of java integer and is greater than or equal to the minimum value of java integer.*);
//@ ensures(*If the integer array parameter `nums` is equal to [-2,1,-3,4,-1,2,1,-5,4], the integer result is equal to 6.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1], the integer result is equal to 1.*);
//@ ensures(*If the integer array parameter `nums` is equal to [5,4,-1,7,8], the integer result is equal to 23.*);
    public int maxSubArray(int[] nums) {
        int maxi = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            // calculating sub-array sum
            sum += num;
            maxi = Math.max(sum, maxi);
            if (sum < 0) {
                // there is no point to carry a -ve subarray sum. hence setting to 0
                sum = 0;
            }
        }
        return maxi;
    }
}