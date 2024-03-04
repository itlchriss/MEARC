package g2301_2400.s2305_fair_distribution_of_cookies;

// #Medium #Array #Dynamic_Programming #Bit_Manipulation #Backtracking #Bitmask
// #2022_06_16_Time_14_ms_(84.35%)_Space_39.6_MB_(92.46%)

public class Solution {
    private int res = Integer.MAX_VALUE;
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `cookies` must not be null.*);
//@ ensures(*The length of the input array `cookies` must be greater than or equal to 2.*);
//@ ensures(*The elements in the input array `cookies` must be positive integers.*);
//@ ensures(*The input integer `k` must be greater than or equal to 2.*);
//@ ensures(*The input integer `k` must be less than or equal to the length of the input array `cookies`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum unfairness of all distributions.*);
//@ ensures(*The minimum unfairness is the maximum total cookies obtained by a single child in the distribution.*);
//@ ensures(*The distribution must allocate all the bags of cookies to the given number of children.*);
//@ ensures(*The cookies in the same bag must go to the same child and cannot be split up.*);
//@ ensures(*The unfairness of the distribution cannot be less than the minimum unfairness returned by the method.*);

    public int distributeCookies(int[] c, int k) {
        int[] nums = new int[k];
        dfs(c, nums, 0);
        return res;
    }

    private void dfs(int[] c, int[] nums, int cur) {
        if (cur == c.length) {
            int r = 0;
            for (int num : nums) {
                r = Math.max(r, num);
            }
            res = Math.min(res, r);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] + c[cur] > res) {
                continue;
            }
            nums[i] += c[cur];
            dfs(c, nums, cur + 1);
            nums[i] -= c[cur];
        }
    }
}