package g0701_0800.s0775_global_and_local_inversions;

// #Medium #Array #Math #2022_03_26_Time_1_ms_(99.74%)_Space_51.6_MB_(90.31%)

public class Solution {
    /*
     * from the above solution, we can tell that if we can find the minimum of A[j] where j >= i + 2,
     * then we could quickly return false, so two steps:
     * 1. remembering minimum
     * 2. scanning from right to left
     * <p>
     * Time: O(n)
     */
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is equal to `n`.*);
//@ ensures(*The integers in the input array `nums` are unique.*);
//@ ensures(*The integers in the input array `nums` are within the range `[0, n - 1]`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a boolean value indicating whether the number of global inversions is equal to the number of local inversions.*);
    public boolean isIdealPermutation(int[] nums) {
        int min = nums.length;
        for (int i = nums.length - 1; i >= 2; i--) {
            min = Math.min(min, nums[i]);
            if (nums[i - 2] > min) {
                return false;
            }
        }
        return true;
    }
}