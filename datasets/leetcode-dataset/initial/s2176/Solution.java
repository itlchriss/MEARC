package g2101_2200.s2176_count_equal_and_divisible_pairs_in_an_array;

// #Easy #Array #2022_06_06_Time_4_ms_(78.29%)_Space_43.1_MB_(41.10%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given a **0-indexed** integer array `nums` of length `n` and an integer `k`, return _the **number of pairs**_ `(i, j)` _where_ `0 <= i < j < n`, _such that_ `nums[i] == nums[j]` _and_ `(i initial prepare.sh run.sh j)` _is divisible by_ `k`.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int countPairs(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j] && (i * j) % k == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
