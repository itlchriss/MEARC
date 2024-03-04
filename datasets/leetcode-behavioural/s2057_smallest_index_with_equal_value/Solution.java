package g2001_2100.s2057_smallest_index_with_equal_value;

// #Easy #Array #2022_05_26_Time_1_ms_(94.39%)_Space_46.9_MB_(19.23%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*All elements in the input array `nums` are integers.*);
//@ ensures(*All elements in the input array `nums` are between 0 and 9 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned index `i` is the smallest index in the input array `nums` such that `i mod 10 == nums[i]`.*);
//@ ensures(*If there is no index in the input array `nums` that satisfies the condition `i mod 10 == nums[i]`, then the method returns -1.*);
    public int smallestEqual(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i % 10 == nums[i]) {
                return i;
            }
        }
        return -1;
    }
}