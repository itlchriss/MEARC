package g2701_2800.s2784_check_if_array_is_good;

// #Easy #Array #Hash_Table #Sorting #2023_09_21_Time_1_ms_(99.49%)_Space_41.2_MB_(98.36%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to - The elements in the input array `nums` are integers.*);
//@ ensures(*The elements in the input array `nums` are within the range of 1 to *);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a boolean value indicating whether the input array `nums` is a permutation of `base[n]`.*);
//@ ensures(*If the input array `nums` is a permutation of `base[n]`, the method returns true.*);
//@ ensures(*If the input array `nums` is not a permutation of `base[n]`, the method returns false.*);
    public boolean isGood(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i : nums) {
            if (i > max) {
                max = i;
            }
            sum += i;
        }
        if (max != nums.length - 1) {
            return false;
        }
        int newSum = max * (max + 1) / 2 + max;
        if (sum != newSum) {
            return false;
        }
        int count = 0;
        for (int i : nums) {
            if (i == max) {
                count++;
            }
        }
        return count == 2;
    }
}