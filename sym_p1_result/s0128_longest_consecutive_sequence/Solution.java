package g0101_0200.s0128_longest_consecutive_sequence;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Hash_Table #Union_Find
// #Big_O_Time_O(N_log_N)_Space_O(1) #2022_06_23_Time_18_ms_(91.05%)_Space_64.8_MB_(63.58%)

import java.util.Arrays;

@SuppressWarnings("java:S135")
public class Solution {
//@ requires(*You must write an algorithm that runs in `O(n)` time.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [100,4,200,1,3,2]*);
//@ requires(*Output: 4*);
//@ requires(*Explanation: The longest consecutive elements sequence is `[1, 2, 3, 4]`.*);
//@ requires(*Therefore its length is 4.*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [0,3,7,2,5,8,4,6,0,1]*);
//@ requires(*Output: 9*);
//@ requires(*Constraints:*);
//@ requires(*<code>0 <= nums.length <= 10<sup>5</sup></code>*);
//@ requires(*<code>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup></code>*);
//@ ensures(*Given an unsorted array of integers param_nums, the result is the length of the longest consecutive elements sequence.*);
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        //@ assume 1 <= nums.length <= 100;
        Arrays.sort(nums);
        int max = Integer.MIN_VALUE;
        int thsMax = 1;
        //@ maintaining 0 <= i <= nums.length || i == nums.length -1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] == nums[i] + 1) {
                thsMax += 1;
                continue;
            }
            if (nums[i + 1] == nums[i]) {
                continue;
            }
            // Start of a new Sequene
            max = Math.max(max, thsMax);
            thsMax = 1;
        }
        return Math.max(max, thsMax);
    }
}