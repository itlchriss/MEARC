package g1701_1800.s1764_form_array_by_concatenating_subarrays_of_another_array;

// #Medium #Array #Greedy #String_Matching #2022_07_12_Time_3_ms_(43.69%)_Space_45.5_MB_(69.90%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The length of the `groups` array must be equal to `n`.*);
//@ ensures(*The length of each subarray in `groups` must be greater than or equal to 1.*);
//@ ensures(*The sum of the lengths of all subarrays in `groups` must be less than or equal to 1000.*);
//@ ensures(*The length of the `nums` array must be greater than or equal to 1.*);
//@ ensures(*The values in `groups` and `nums` must be within the range of -10^7 to 10^7.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return a boolean value indicating whether it is possible to choose `n` disjoint subarrays from `nums` that match the subarrays in `groups` and satisfy the ordering condition.*);
//@ ensures(*If it is possible, the method should return `true`.*);
//@ ensures(*If it is not possible, the method should return `false`.*);
    public boolean canChoose(int[][] groups, int[] nums) {
        int prev = 0;
        for (int i = 0; i < groups.length; i++) {
            int[] temp = new int[groups[i].length];
            if (prev + groups[i].length > nums.length) {
                return false;
            }
            int index = 0;
            int j;
            for (j = prev; j < prev + groups[i].length; j++) {
                temp[index++] = nums[j];
            }
            if (Arrays.equals(temp, groups[i])) {
                prev = j;
                continue;
            }
            int k;
            for (k = j; k < nums.length; k++) {
                int l;
                for (l = 0; l < temp.length - 1; l++) {
                    temp[l] = temp[l + 1];
                }
                temp[l] = nums[k];
                if (Arrays.equals(temp, groups[i])) {
                    prev = k + 1;
                    break;
                }
            }
            if (k == nums.length) {
                return false;
            }
        }
        return true;
    }
}