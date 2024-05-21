package g0101_0200.s0152_maximum_product_subarray;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Dynamic_Programming
// #Dynamic_Programming_I_Day_6 #Level_2_Day_13_Dynamic_Programming #Udemy_Dynamic_Programming
// #Big_O_Time_O(N)_Space_O(1) #2022_06_25_Time_0_ms_(100.00%)_Space_42.7_MB_(82.46%)

public class Solution {
//@ requires(*It is guaranteed that the answer will fit in a 32-bit integer.*);
//@ requires(*A subarray is a contiguous subsequence of the array.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [2,3,-2,4]*);
//@ requires(*Output: 6*);
//@ requires(*Explanation: [2,3] has the largest product 6.*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [-2,0,-1]*);
//@ requires(*Output: 0*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= nums.length <= 2  10<sup>4</sup></code>*);
//@ requires(*`-10 <= nums[i] <= 10`*);
//@ requires(*The product of any prefix or suffix of `nums` is guaranteed to fit in a 32-bit integer.*);
//@ ensures(*Given an integer array `nums`, find a contiguous non-empty subarray within the array that has the largest product, and the result is the product.*);
//@ ensures(*Explanation: The result cannot be 2, because [-2,-1] is not a subarray.*);
    public int maxProduct(int[] arr) {
        int ans = Integer.MIN_VALUE;
        int cprod = 1;
        for (int j : arr) {
            cprod = cprod * j;
            ans = Math.max(ans, cprod);
            if (cprod == 0) {
                cprod = 1;
            }
        }
        cprod = 1;
        //@ maintaining 0 <= i <= arr.length - 1 || i == -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            cprod = cprod * arr[i];
            ans = Math.max(ans, cprod);
            if (cprod == 0) {
                cprod = 1;
            }
        }
        return ans;
    }
}