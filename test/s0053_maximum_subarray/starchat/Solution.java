package g0001_0100.s0053_maximum_subarray;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #Array #Dynamic_Programming
// #Divide_and_Conquer #Data_Structure_I_Day_1_Array #Dynamic_Programming_I_Day_5
// #Udemy_Famous_Algorithm #Big_O_Time_O(n)_Space_O(1)
// #2023_08_11_Time_1_ms_(100.00%)_Space_57.7_MB_(90.58%)

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 10000 and is greater than or equal to -10000.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 152\. Maximum Product Subarray*);
//@ requires(*Medium*);
//@ requires(*Given an integer array `nums`, find a contiguous non-empty subarray within the array that has the largest product, and return _the product_.*);
//@ requires(**);
//@ requires(*It is guaranteed that the answer will fit in a **32-bit** integer.*);
//@ requires(**);
//@ requires(*A subarray is a contiguous part of an array.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** nums = \[2,3,-2,4\]*);
//@ requires(***Output:** 6*);
//@ requires(***Explanation:** \[2,3\] has the largest product 6.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** nums = \[-2,0,-1\]*);
//@ requires(***Output:** 0*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= nums.length <= 2 * 104`*);
//@ requires(**   `-10 <= nums[i] <= 10`*);
//@ requires(**   The product of any prefix or suffix of `nums` is **guaranteed** to fit in a **32-bit** integer.*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n)` time complexity and `O(1)` space complexity?*);
//@ requires(**);
//@ requires(*Method signature: public int maxProduct(int[] nums)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 20000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 10 and is greater than or equal to -10.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 155\. Minimum Operations to Make a Subsequence*);
//@ requires(*Hard*);
//@ requires(*You are given two arrays `arr1` and `arr2` of integers.*);
//@ requires(**);
//@ requires(*In one operation, you can replace `arr1[i]` with `arr2[j]` if:*);
//@ requires(**);
//@ requires(**   `arr1[i] == arr2[j]`*);
//@ requires(**   `i <= j < arr1.length`*);
//@ requires(**   `j < arr2.length`*);
//@ requires(**);
//@ requires(*Return _the minimum number of operations required to make_ `arr1` _a subsequence of_ `arr2`.*);
//@ requires(**);
//@ requires(*A **subsequence** of an array is a new array generated from the original array by deleting some elements (possibly none) without changing the remaining elements' relative order. For example, `[2,7,10]` is a subsequence of `[1,2,3,4,7,10]`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** arr1 = \[5,1,3], arr2 = \[9,4,2,3,4]*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** You can replace arr1\[0\] with arr2\[1\] and arr1\[2\] with arr2\[2\].*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** arr1 = \[3,5,*);
//@ ensures(*The integer result is greater than or equal to -100000 and is less than or equal to 100000.*);
//@ ensures(*If the integer array parameter `nums` is equal to [-2,1,-3,4,-1,2,1,-5,4], the integer result is equal to 6.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1], the integer result is equal to 1.*);
//@ ensures(*If the integer array parameter `nums` is equal to [5,4,-1,7,8], the integer result is equal to 23.*);
//@ ensures(***Explanation:** The result cannot be 2, because \[-2,-1\] is not a subarray.*);
//@ ensures(*The integer result is greater than or equal to -1000000000 and is less than or equal to 1000000000.*);
//@ ensures(*If the integer array parameter `nums` is equal to \[2,3,-2,4\], the integer result is equal to 6.*);
//@ ensures(*If the integer array parameter `nums` is equal to \[-2,0,-1\], the integer result is equal to 0.*);
//@ ensures(*If the integer array parameter `nums` is equal to \[-2,-3,-4\], the integer result is equal to 24.*);
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