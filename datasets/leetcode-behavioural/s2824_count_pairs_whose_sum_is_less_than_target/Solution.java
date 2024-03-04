package g2801_2900.s2824_count_pairs_whose_sum_is_less_than_target;

// #Easy #Array #Sorting #Two_Pointers #2023_11_20_Time_2_ms_(98.16%)_Space_40.7_MB_(93.99%)

import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input list `nums` must not be null.*);
//@ ensures(*The input list `nums` must have a length greater than or equal to 2.*);
//@ ensures(*The input integer `target` must be within the range of -50 to 50.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of pairs `(i, j)` where `0 <= i < j < n` and `nums[i] + nums[j] < target`.*);
    public int countPairs(List<Integer> nums, int target) {
        int cnt = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(i) + nums.get(j) < target) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}