package g1501_1600.s1590_make_sum_divisible_by_p;

// #Medium #Array #Hash_Table #Prefix_Sum #2022_04_11_Time_56_ms_(62.20%)_Space_85.8_MB_(73.58%)

import java.util.HashMap;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than 0.*);
//@ ensures(*The elements in the input array `nums` are positive integers.*);
//@ ensures(*The input integer `p` is a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned value is an integer.*);
//@ ensures(*The returned value is the length of the smallest subarray that needs to be removed.*);
//@ ensures(*If it is impossible to remove a subarray such that the sum of the remaining elements is divisible by `p`, the returned value is -1.*);
    public int minSubarray(int[] nums, int p) {
        HashMap<Integer, Integer> hmp = new HashMap<>();
        int n = nums.length;
        int target = 0;
        int sum = 0;
        for (int num : nums) {
            target = (num + target) % p;
        }
        if (target == 0) {
            return 0;
        }
        hmp.put(0, -1);
        int ans = n;
        for (int i = 0; i < n; i++) {
            sum = (sum + nums[i]) % p;
            int key = (sum - target + p) % p;
            if (hmp.containsKey(key)) {
                ans = Math.min(ans, i - hmp.get(key));
            }
            hmp.put(sum % p, i);
        }
        if (ans < n) {
            return ans;
        } else {
            return -1;
        }
    }
}