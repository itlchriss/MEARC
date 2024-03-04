package g2801_2900.s2809_minimum_time_to_make_array_sum_at_most_x;

// #Hard #Array #Dynamic_Programming #Sorting #2023_11_20_Time_14_ms_(100.00%)_Space_44_MB_(47.62%)

import java.util.Arrays;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The length of `nums1` and `nums2` is the same.*);
//@ ensures(*The length of `nums1` is greater than or equal to - The length of `nums1` is less than or equal to - Each element in `nums1` is greater than or equal to - Each element in `nums1` is less than or equal to - Each element in `nums2` is greater than or equal to - Each element in `nums2` is less than or equal to - `x` is greater than or equal to - `x` is less than or equal to *);
//@ ensures(*Postconditions:*);
//@ ensures(*The sum of all elements in `nums1` is less than or equal to `x`.*);
//@ ensures(*The minimum time required to make the sum of all elements in `nums1` less than or equal to `x` is returned.*);
//@ ensures(*If it is not possible to make the sum of all elements in `nums1` less than or equal to `x`, -1 is returned.*);
    public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        int n = nums1.size();
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i] = new int[] {nums1.get(i), nums2.get(i)};
        }
        Arrays.sort(nums, (a, b) -> a[1] - b[1]);
        int[] dp = new int[n + 1];
        long sum1 = 0;
        long sum2 = 0;
        for (int i = 0; i < n; i++) {
            sum1 += nums[i][0];
            sum2 += nums[i][1];
        }
        if (sum1 <= x) {
            return 0;
        }
        for (int j = 0; j < n; j++) {
            for (int i = j + 1; i > 0; i--) {
                dp[i] = Math.max(dp[i], nums[j][0] + (nums[j][1] * i) + dp[i - 1]);
            }
        }
        for (int i = 1; i <= n; i++) {
            if (sum1 + sum2 * i - dp[i] <= x) {
                return i;
            }
        }
        return -1;
    }
}