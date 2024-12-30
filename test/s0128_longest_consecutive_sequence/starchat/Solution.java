package g0101_0200.s0128_longest_consecutive_sequence;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Hash_Table #Union_Find
// #Big_O_Time_O(N_log_N)_Space_O(1) #2022_06_23_Time_18_ms_(91.05%)_Space_64.8_MB_(63.58%)

import java.util.Arrays;

@SuppressWarnings("java:S135")
public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100000 and is greater than or equal to 0.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 1000000000 and is greater than or equal to -1000000000.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 1346\. Check If N and Its Double Exist*);
//@ requires(**);
//@ requires(*Easy*);
//@ requires(**);
//@ requires(*Given an array `arr` of integers, write a function to check if there exists two integers `N` and its double (2 * N) that appear **exactly twice** in the array.*);
//@ requires(**);
//@ requires(*Return `true` if such a pair exists, otherwise, return `false`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** arr = \[10,2,5,3\]*);
//@ requires(**);
//@ requires(***Output:** true*);
//@ requires(**);
//@ requires(***Explanation:** N = 10, 2 * N = 20.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** arr = \[7,1,14,11\]*);
//@ requires(**);
//@ requires(***Output:** true*);
//@ requires(**);
//@ requires(***Explanation:** N = 7, 2 * N = 14.*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** arr = \[3,1,7,11\]*);
//@ requires(**);
//@ requires(***Output:** false*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `2 <= arr.length <= 500`*);
//@ requires(**   `-10^3 <= arr[i] <= 10^3`*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n)` time complexity?*);
//@ requires(**);
//@ requires(*Method signature: public boolean checkIfExist(int[] arr)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `arr` is less than or equal to 500 and is greater than or equal to 2.*);
//@ requires(*All values in the integer array parameter `arr` are less than or equal to 1000 and is greater than or equal to -1000.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 1365\. How Many Numbers Are Smaller Than the Current Number*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given the array `nums`, for each `nums[i]` you need to count the number of valid `j`'s such that `j!= i` and `nums[j]` is smaller than `nums[i]`.*);
//@ requires(**);
//@ requires(*Return _the answer in an array_.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** nums = \[8,1,2,2,3\]*);
//@ requires(**);
//@ requires(***Output:** \[4,0,1,1,3\]*);
//@ requires(**);
//@ requires(***Explanation:** *);
//@ requires(*For nums\[0\]=8 you have 4 valid `j`'s (0,1,2,3). *);
//@ requires(**);
//@ requires(*For nums\[1\]=1 you have 0 valid `j`'s.*);
//@ requires(**);
//@ requires(*For nums\[2\]=2 you have 1 valid `j` (1). *);
//@ requires(**);
//@ requires(*For nums\[3\]=2 you have 1 valid `j` (1). *);
//@ requires(**);
//@ requires(*For nums\[4\]=3 you have 3 valid `j`'s (1,2,3).*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** nums = \[6,5,4,8\]*);
//@ requires(**);
//@ requires(***Output:** \[2,1,0,3\]*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** nums = \[7,7,7,7\]*);
//@ requires(**);
//@ requires(***Output:** \[0,0,0,0\]*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `2 <= nums.length <= 500`*);
//@ requires(**   `0*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the integer array parameter `nums`.*);
//@ ensures(*If the integer array parameter `nums` is equal to [100,4,200,1,3,2], the integer result is equal to 4.*);
//@ ensures(*If the integer array parameter `nums` is equal to [0,3,7,2,5,8,4,6,0,1], the integer result is equal to 9.*);
//@ ensures(*If the integer array parameter `arr` is equal to [10,2,5,3], the boolean result is equal to true.*);
//@ ensures(*If the integer array parameter `arr` is equal to [7,1,14,11], the boolean result is equal to true.*);
//@ ensures(*If the integer array parameter `arr` is equal to [3,1,7,11], the boolean result is equal to false.*);
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