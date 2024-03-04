package g2901_3000.s2963_count_the_number_of_good_partitions;

// #Hard #Array #Hash_Table #Math #Combinatorics
// #2024_01_16_Time_30_ms_(80.04%)_Space_64.3_MB_(30.54%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*All elements in the input array `nums` are positive integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the total number of good partitions of the input array `nums`.*);
//@ ensures(*The returned value is non-negative.*);
//@ ensures(*The returned value is less than or equal to <code>10<sup>9</sup> + 7</code>.*);
    public int numberOfGoodPartitions(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            mp.put(nums[i], i);
        }
        int i = 0;
        int j = 0;
        int cnt = 0;
        while (i < n) {
            j = Math.max(j, mp.get(nums[i]));
            if (i == j) {
                cnt++;
            }
            i++;
        }
        int res = 1;
        for (int k = 1; k < cnt; k++) {
            res = res * 2;
            int mod = 1000000007;
            res %= mod;
        }
        return res;
    }
}