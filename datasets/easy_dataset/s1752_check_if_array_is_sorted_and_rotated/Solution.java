package g1701_1800.s1752_check_if_array_is_sorted_and_rotated;

// #Easy #Array #2022_05_01_Time_0_ms_(100.00%)_Space_42.4_MB_(9.86%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to - The elements in the input array `nums` are integers.*);
//@ ensures(*The elements in the input array `nums` are within the range of 1 to *);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a boolean value indicating whether the array was originally sorted in non-decreasing order, then rotated some number of positions (including zero).*);
//@ ensures(*If the array was originally sorted in non-decreasing order, then rotated some number of positions (including zero), the method returns `true`.*);
//@ ensures(*If the array was not originally sorted in non-decreasing order, then rotated some number of positions (including zero), the method returns `false`.*);
    public boolean check(int[] nums) {
        int checker = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                checker++;
            }
        }
        // checking if the last element is greater than the first
        if (nums[nums.length - 1] > nums[0]) {
            checker++;
        }
        return checker <= 1;
    }
}