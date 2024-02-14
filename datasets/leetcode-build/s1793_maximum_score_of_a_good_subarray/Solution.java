package g1701_1800.s1793_maximum_score_of_a_good_subarray;

// #Hard #Array #Binary_Search #Two_Pointers #Stack #Monotonic_Stack
// #2022_05_03_Time_3_ms_(97.56%)_Space_88.3_MB_(54.63%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to - The input integer `k` is a valid index within the range of the input array `nums`.*);
	//@ ensures(*The method returns an integer value representing the maximum possible score of a good subarray.*);
	//@ ensures(*The returned score is greater than or equal to - The returned score is calculated based on the formula `min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1)`, where `i` and `j` are indices of the subarray and `min(nums[i], nums[i+1], ..., nums[j])` represents the minimum value in the subarray.*);
    public int maximumScore(int[] nums, int k) {
        int i = k;
        int j = k;
        int res = nums[k];
        int min = nums[k];
        boolean goLeft;
        while (i >= 1 || j < nums.length - 1) {
            // sub array [i...j] is already traversed. Either goLeft or goRight to increase the
            // sequence
            if (i == 0) {
                goLeft = false;
            } else if (j == nums.length - 1) {
                goLeft = true;
            } else {
                goLeft = nums[j + 1] <= nums[i - 1];
            }
            min = goLeft ? Math.min(min, nums[i - 1]) : Math.min(min, nums[j + 1]);
            if (goLeft) {
                while (i >= 1 && min <= nums[i - 1]) {
                    i--;
                }
            } else {
                while (j < nums.length - 1 && min <= nums[j + 1]) {
                    j++;
                }
            }
            res = Math.max(res, min * (j - i + 1));
        }
        return res;
    }
}