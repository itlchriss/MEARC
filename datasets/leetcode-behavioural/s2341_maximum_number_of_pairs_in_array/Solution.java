package g2301_2400.s2341_maximum_number_of_pairs_in_array;

// #Easy #Array #Hash_Table #Sorting #2022_07_18_Time_2_ms_(80.00%)_Space_42.3_MB_(80.00%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is at least 1 and at most - Each element in the input array `nums` is an integer between 0 and 100 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `answer` is not null.*);
//@ ensures(*The length of the output array `answer` is - The first element of the output array `answer[0]` is the number of pairs that are formed.*);
//@ ensures(*The second element of the output array `answer[1]` is the number of leftover integers in `nums` after doing the operation as many times as possible.*);
    public int[] numberOfPairs(int[] nums) {
        Arrays.sort(nums);
        int pairs = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                nums[i] = -1;
                nums[i - 1] = -1;
                pairs++;
            }
        }
        return new int[] {pairs, nums.length - (2 * pairs)};
    }
}