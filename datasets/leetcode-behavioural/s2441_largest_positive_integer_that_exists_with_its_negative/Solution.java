package g2401_2500.s2441_largest_positive_integer_that_exists_with_its_negative;

// #Easy #Array #Hash_Table #2022_12_13_Time_8_ms_(79.93%)_Space_42.7_MB_(84.42%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to - The elements in the input array `nums` are integers.*);
//@ ensures(*The elements in the input array `nums` are not zero.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value.*);
//@ ensures(*If there exists a positive integer `k` in the input array `nums` such that `-k` also exists in the array, the method returns the largest positive integer `k`.*);
//@ ensures(*If there is no such integer `k` in the input array `nums`, the method returns -1.*);
    public int findMaxK(int[] nums) {
        int[] arr = new int[nums.length];
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                arr[j++] = nums[i];
            }
        }
        Arrays.sort(arr);
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int num : nums) {
                if ((arr[i] * -1) == num) {
                    return num;
                }
            }
        }
        return -1;
    }
}