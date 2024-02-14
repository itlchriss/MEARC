package g2501_2600.s2597_the_number_of_beautiful_subsets;

// #Medium #Array #Dynamic_Programming #Backtracking
// #2023_08_29_Time_4_ms_(100.00%)_Space_43.8_MB_(67.74%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `nums` contains only positive integers.*);
	//@ requires(*The input integer `k` is positive.*);
	//@ requires(*The length of the input array `nums` is between 1 and 20 (inclusive).*);
	//@ requires(*The values in the input array `nums` are between 1 and 1000 (inclusive).*);
	//@ requires(*The value of `k` is between 1 and 1000 (inclusive).*);
	//@ ensures(*The method returns an integer representing the number of non-empty beautiful subsets of the array `nums`.*);
    public int beautifulSubsets(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        int res = 1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (!map.containsKey(entry.getKey() - k)) {
                if (map.containsKey(entry.getKey() + k)) {
                    List<Integer> freq = new ArrayList<>();
                    int localKey = entry.getKey();
                    while (map.containsKey(localKey)) {
                        freq.add(map.get(localKey));
                        localKey += k;
                    }
                    res *= helper(freq);
                } else {
                    res *= 1 << entry.getValue();
                }
            }
        }
        return res - 1;
    }

    private int helper(List<Integer> freq) {
        int n = freq.size();
        if (n == 1) {
            return 1 << freq.get(0);
        }
        int[] dp = new int[n];
        dp[0] = (1 << freq.get(0)) - 1;
        dp[1] = (1 << freq.get(1)) - 1;
        if (n == 2) {
            return dp[0] + dp[1] + 1;
        }
        for (int i = 2; i < n; i++) {
            if (i > 2) {
                dp[i - 2] += dp[i - 3];
            }

            dp[i] = (dp[i - 2] + 1) * ((1 << freq.get(i)) - 1);
        }
        return dp[n - 1] + dp[n - 2] + dp[n - 3] + 1;
    }
}