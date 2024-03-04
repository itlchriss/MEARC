package g2801_2900.s2862_maximum_element_sum_of_a_complete_subset_of_indices;

// #Hard #Array #Math #Number_Theory #2023_12_20_Time_3_ms_(78.95%)_Space_44.4_MB_(88.16%)

import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input list `nums` is not null.*);
//@ ensures(*The input list `nums` is not empty.*);
//@ ensures(*The length of the input list `nums` is greater than or equal to 1.*);
//@ ensures(*The length of the input list `nums` is less than or equal to 10^4.*);
//@ ensures(*Each element in the input list `nums` is an integer.*);
//@ ensures(*Each element in the input list `nums` is greater than or equal to 1.*);
//@ ensures(*Each element in the input list `nums` is less than or equal to 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a long integer.*);
//@ ensures(*The output is the maximum element-sum of a complete subset of the indices set {1, 2, ..., n}.*);
    public long maximumSum(List<Integer> nums) {
        long ans = 0;
        int n = nums.size();
        int bound = (int) Math.floor(Math.sqrt(n));
        int[] squares = new int[bound + 1];
        for (int i = 1; i <= bound + 1; i++) {
            squares[i - 1] = i * i;
        }
        for (int i = 1; i <= n; i++) {
            long res = 0;
            int idx = 0;
            int curr = i * squares[idx];
            while (curr <= n) {
                res += nums.get(curr - 1);
                curr = i * squares[++idx];
            }
            ans = Math.max(ans, res);
        }
        return ans;
    }
}