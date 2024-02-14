package g0901_1000.s0922_sort_array_by_parity_ii;

// #Easy #Array #Sorting #Two_Pointers #2022_03_29_Time_4_ms_(52.95%)_Space_55.6_MB_(17.78%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of `nums` is even.*);
	//@ requires(*Half of the integers in `nums` are even.*);
	//@ requires(*The integers in `nums` are between 0 and 1000 (inclusive).*);
	//@ ensures(*The output array is not null.*);
	//@ ensures(*The length of the output array is the same as the input array.*);
	//@ ensures(*The integers in the output array are sorted such that whenever `nums[i]` is odd, `i` is odd, and whenever `nums[i]` is even, `i` is even.*);
    public int[] sortArrayByParityII(int[] nums) {
        int i = 0;
        int j = 1;
        while (i < nums.length - 1 && j < nums.length) {
            if (nums[i] % 2 != 0 && nums[j] % 2 == 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i += 2;
                j += 2;
            }
            while (i < nums.length - 1 && nums[i] % 2 == 0) {
                i += 2;
            }
            while (j < nums.length && nums[j] % 2 != 0) {
                j += 2;
            }
        }
        return nums;
    }
}