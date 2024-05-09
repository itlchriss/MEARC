package g0201_0300.s0238_product_of_array_except_self;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Prefix_Sum
// #Data_Structure_II_Day_5_Array #Udemy_Arrays #Big_O_Time_O(n^2)_Space_O(n)
// #2022_07_04_Time_1_ms_(100.00%)_Space_50.8_MB_(85.60%)

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is greater than or equal to 2 and is less than or equal to 100000.*);
//@ requires(*All values in the integer array parameter `nums` are greater than or equal to -30 and are less than or equal to 30.*);
//@ requires(*The product of any prefix or suffix of the integer array parameter `nums` is guaranteed to fit in a 32-bit integer.*);
//@ requires(*The algorithm must run in O(n) time complexity.*);
//@ requires(*The algorithm must not use the division operation.*);
//@ requires(*The algorithm must solve the problem without using more than O(1) extra space.*);
//@ ensures(*The length of the integer array result is equal to the length of the integer array parameter `nums`.*);
//@ ensures(*Each element in the integer array result is equal to the product of all elements in the integer array parameter `nums` except the corresponding element at the same index.*);
    public int[] productExceptSelf(int[] nums) {
        int product = 1;
        int[] ans = new int[nums.length];
        for (int num : nums) {
            product = product * num;
        }
        //@ maintaining 0 <= i <= nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                ans[i] = product / nums[i];
            } else {
                int p = 1;
                //@ maintaining 0 <= j <= nums.length;
                for (int j = 0; j < nums.length; j++) {
                    if (j != i) {
                        p = p * nums[j];
                    }
                }
                ans[i] = p;
            }
        }
        return ans;
    }
}