package g0701_0800.s0713_subarray_product_less_than_k;

// #Medium #Array #Sliding_Window #Algorithm_II_Day_5_Sliding_Window #Programming_Skills_II_Day_12
// #Udemy_Arrays #2022_03_24_Time_8_ms_(39.00%)_Space_67_MB_(75.58%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are positive integers.*);
//@ ensures(*The input integer `k` is non-negative.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than `k`.*);
//@ ensures(*The returned integer is non-negative.*);
//@ ensures(*The method does not modify the input array `nums`.*);
//@ ensures(*The method does not modify the input integer `k`.*);
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int p = 1;
        int j = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            p = p * nums[i];
            while (p >= k && j < i) {
                p = p / nums[j];
                j++;
            }
            ans += p < k ? i - j + 1 : 0;
        }
        return ans;
    }
}