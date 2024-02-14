package g1401_1500.s1470_shuffle_the_array;

// #Easy #Array #2022_03_29_Time_1_ms_(51.62%)_Space_45.5_MB_(66.97%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is equal to `2n`.*);
	//@ requires(*The value of `n` is greater than or equal to 1.*);
	//@ requires(*The values in the input array `nums` are integers.*);
	//@ requires(*The values in the input array `nums` are between 1 and 1000 (inclusive).*);
	//@ ensures(*The output array is not null.*);
	//@ ensures(*The length of the output array is equal to the length of the input array `nums`.*);
	//@ ensures(*The output array is in the form `[x1, y1, x2, y2, ..., xn, yn]`.*);
	//@ ensures(*The values in the output array are in the same order as the input array `nums`, but rearranged according to the specified form.*);
	//@ ensures(*The values in the output array are integers.*);
	//@ ensures(*The values in the output array are between 1 and 1000 (inclusive).*);
    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[nums.length];
        int i = 0;
        int j = 0;
        while (i < n && j < 2 * n) {
            result[j] = nums[i];
            result[++j] = nums[i + n];
            i++;
            j++;
        }
        return result;
    }
}