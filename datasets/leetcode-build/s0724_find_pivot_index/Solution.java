package g0701_0800.s0724_find_pivot_index;

// #Easy #Array #Prefix_Sum #Level_1_Day_1_Prefix_Sum
// #2022_03_24_Time_2_ms_(69.67%)_Space_52.1_MB_(59.19%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ requires(*The elements in the input array `nums` are within the range of -1000 to 1000.*);
	//@ ensures(*The method returns an integer representing the leftmost pivot index.*);
	//@ ensures(*If a pivot index exists, the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.*);
	//@ ensures(*If no pivot index exists, the method returns -1.*);
    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            int temp = sums[nums.length - 1] - sums[i];
            if (i == 0 && 0 == temp || (i > 0 && sums[i - 1] == temp)) {
                return i;
            }
        }
        return -1;
    }
}