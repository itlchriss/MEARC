package g0601_0700.s0645_set_mismatch;

// #Easy #Array #Hash_Table #Sorting #Bit_Manipulation
// #2022_03_21_Time_2_ms_(97.45%)_Space_43.6_MB_(89.93%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is at least 2.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ requires(*The elements in the input array `nums` are within the range of 1 to 10^4.*);
	//@ ensures(*The output array is not null.*);
	//@ ensures(*The length of the output array is 2.*);
	//@ ensures(*The elements in the output array are integers.*);
	//@ ensures(*The first element in the output array is the number that occurs twice in the input array `nums`.*);
	//@ ensures(*The second element in the output array is the number that is missing from the input array `nums`.*);
	//@ ensures(*The number that occurs twice in the input array `nums` is different from the number that is missing from the input array `nums`.*);
	//@ ensures(*The number that occurs twice in the input array `nums` is within the range of 1 to 10^4.*);
	//@ ensures(*The number that is missing from the input array `nums` is within the range of 1 to 10^4.*);
    public int[] findErrorNums(int[] nums) {
        int[] ans = new int[2];
        int i = 0;
        while (i < nums.length) {
            int correct = nums[i] - 1;
            if (nums[i] != nums[correct]) {
                int temp = nums[i];
                nums[i] = nums[correct];
                nums[correct] = temp;
            } else {
                i++;
            }
        }
        for (int a = 0; a < nums.length; a++) {
            if (nums[a] != a + 1) {
                ans[0] = nums[a];
                ans[1] = a + 1;
                break;
            }
        }
        return ans;
    }
}