package g1801_1900.s1848_minimum_distance_to_the_target_element;

// #Easy #Array #2022_05_08_Time_1_ms_(58.33%)_Space_44.7_MB_(6.94%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of `nums` is greater than or equal to 1.*);
//@ ensures(*The target value is in the `nums` array.*);
//@ ensures(*The start index is within the bounds of the `nums` array.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value representing the minimum distance between the target value and the start index.*);
//@ ensures(*The returned value is non-negative.*);
    public int getMinDistance(int[] nums, int target, int start) {
        int result = 0;
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target && Math.abs(start - i) < minDiff) {
                minDiff = Math.abs(start - i);
                result = minDiff;
            }
        }
        return result;
    }
}