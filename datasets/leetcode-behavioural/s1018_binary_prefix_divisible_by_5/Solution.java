package g1001_1100.s1018_binary_prefix_divisible_by_5;

// #Easy #Array #2022_02_25_Time_3_ms_(84.58%)_Space_49.7_MB_(32.50%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `nums` is either 0 or 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output list `answer` is not null.*);
//@ ensures(*The length of the output list `answer` is equal to the length of the input array `nums`.*);
//@ ensures(*Each element in the output list `answer` is either true or false.*);
//@ ensures(*For each index `i` in the output list `answer`, if the number represented by the subarray `nums[0..i]` is divisible by 5, then the corresponding element in the output list `answer` is true. Otherwise, it is false.*);
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> result = new ArrayList<>(nums.length);
        int remainder = 0;
        for (int j : nums) {
            remainder = (j + (remainder << 1)) % 5;
            result.add(remainder == 0);
        }
        return result;
    }
}