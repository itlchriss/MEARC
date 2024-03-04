package g1501_1600.s1558_minimum_numbers_of_function_calls_to_make_target_array;

// #Medium #Array #Greedy #2022_04_11_Time_42_ms_(69.17%)_Space_55.7_MB_(81.20%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of `nums` is greater than or equal to 1.*);
//@ ensures(*The values in `nums` are non-negative integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is the minimum number of function calls required to convert `arr` to `nums`.*);
//@ ensures(*The output is a non-negative integer.*);
    public int minOperations(int[] nums) {
        int ops = 0;
        for (int bit = 0; bit < 32; bit++) {
            boolean nonzero = false;
            for (int i = 0; i < nums.length; i++) {
                ops += nums[i] % 2;
                nums[i] /= 2;
                nonzero |= nums[i] > 0;
            }
            if (nonzero) {
                ops++;
            } else {
                break;
            }
        }
        return ops;
    }
}