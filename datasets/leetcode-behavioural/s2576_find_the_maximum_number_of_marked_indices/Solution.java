package g2501_2600.s2576_find_the_maximum_number_of_marked_indices;

// #Medium #Array #Sorting #Greedy #Binary_Search #Two_Pointers
// #2023_08_22_Time_27_ms_(95.36%)_Space_54.9_MB_(88.74%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `nums` is a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is an integer representing the maximum possible number of marked indices in `nums`.*);
//@ ensures(*The indices marked in `nums` satisfy the condition `2 * nums[i] <= nums[j]` for any two different marked indices `i` and `j`.*);
    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length / 2;
        long count = 0;
        while (i < nums.length / 2 && j < nums.length) {
            if (nums[i] * 2 <= nums[j]) {
                i++;
                j++;
                count = count + 2;
            } else {
                j++;
            }
        }
        return (int) count;
    }
}