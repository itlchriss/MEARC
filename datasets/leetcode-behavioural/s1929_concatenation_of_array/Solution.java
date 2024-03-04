package g1901_2000.s1929_concatenation_of_array;

// #Easy #Array #2022_05_15_Time_1_ms_(92.52%)_Space_42.9_MB_(80.72%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than 0.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `ans` is not null.*);
//@ ensures(*The length of the output array `ans` is equal to twice the length of the input array `nums`.*);
//@ ensures(*For each index `i` from 0 to `n-1`, the value at index `i` in the output array `ans` is equal to the value at index `i` in the input array `nums`.*);
//@ ensures(*For each index `i` from `n` to `2n-1`, the value at index `i` in the output array `ans` is equal to the value at index `i-n` in the input array `nums`.*);
    public int[] getConcatenation(int[] nums) {
        int[] result = new int[nums.length * 2];
        System.arraycopy(nums, 0, result, 0, nums.length);
        int i = nums.length;
        for (int j = 0; i < result.length && j < nums.length; i++, j++) {
            result[i] = nums[j];
        }
        return result;
    }
}