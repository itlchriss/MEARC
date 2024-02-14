package g1301_1400.s1365_how_many_numbers_are_smaller_than_the_current_number;

// #Easy #Array #Hash_Table #Sorting #Counting #2022_05_16_Time_1_ms_(99.78%)_Space_45_MB_(22.25%)

public class Solution {
	//@ requires(*The input array `nums` must not be null.*);
	//@ requires(*The length of the input array `nums` must be at least 2.*);
	//@ requires(*Each element in the input array `nums` must be between 0 and 100 (inclusive).*);
	//@ ensures(*The output array must not be null.*);
	//@ ensures(*The length of the output array must be the same as the input array.*);
	//@ ensures(*Each element in the output array must be an integer.*);
	//@ ensures(*For each element `nums[i]` in the input array, the corresponding element in the output array must be the count of valid `j's` such that `j != i` and `nums[j] < nums[i]`.*);
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] ans = new int[nums.length];
        int[] temp = new int[101];
        for (int num : nums) {
            temp[num]++;
        }
        for (int i = 1; i < 101; i++) {
            temp[i] += temp[i - 1];
        }
        for (int i = 0; i < ans.length; i++) {
            if (nums[i] == 0) {
                ans[i] = 0;
            } else {
                ans[i] = temp[nums[i] - 1];
            }
        }
        return ans;
    }
}