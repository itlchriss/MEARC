package g0201_0300.s0238_product_of_array_except_self;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Prefix_Sum
// #Data_Structure_II_Day_5_Array #Udemy_Arrays #Big_O_Time_O(n^2)_Space_O(n)
// #2022_07_04_Time_1_ms_(100.00%)_Space_50.8_MB_(85.60%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of `nums` is at least 2.*);
	//@ requires(*The elements of `nums` are integers.*);
	//@ requires(*The product of any prefix or suffix of `nums` is guaranteed to fit in a 32-bit integer.*);
	//@ ensures(*The output array `answer` is not null.*);
	//@ ensures(*The length of `answer` is the same as the length of `nums`.*);
	//@ ensures(*Each element `answer[i]` is equal to the product of all the elements of `nums` except `nums[i]`.*);
	//@ ensures(*The product of any prefix or suffix of `answer` is guaranteed to fit in a 32-bit integer.*);
	//@ ensures(*The algorithm runs in O(n) time complexity.*);
	//@ ensures(*The algorithm does not use the division operation.*);
	//@ ensures(*Additional postcondition (follow-up):*);
	//@ ensures(*The algorithm uses O(1) extra space complexity.*);
    public int[] productExceptSelf(int[] nums) {
        int product = 1;
        int[] ans = new int[nums.length];
        for (int num : nums) {
            product = product * num;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                ans[i] = product / nums[i];
            } else {
                int p = 1;
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