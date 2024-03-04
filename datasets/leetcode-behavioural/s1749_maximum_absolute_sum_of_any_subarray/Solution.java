package g1701_1800.s1749_maximum_absolute_sum_of_any_subarray;

// #Medium #Array #Dynamic_Programming #2022_04_30_Time_3_ms_(90.60%)_Space_65.8_MB_(11.86%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is at least 1.*);
//@ ensures(*The elements in the input array `nums` are integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned value is an integer.*);
//@ ensures(*The returned value is the maximum absolute sum of any subarray of `nums`.*);
//@ ensures(*The returned value is non-negative.*);
    public int maxAbsoluteSum(int[] nums) {
        int min = 0;
        int max = 0;
        int s = 0;
        for (int num : nums) {
            s += num;
            min = Math.min(min, s);
            max = Math.max(max, s);
        }
        return max - min;
    }
}