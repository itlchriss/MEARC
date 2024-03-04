package g2401_2500.s2453_destroy_sequential_targets;

// #Medium #Array #Hash_Table #Counting #2022_12_15_Time_33_ms_(96.27%)_Space_58.9_MB_(95.38%)

import java.util.HashMap;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are positive integers.*);
//@ ensures(*The input integer `space` is a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the minimum value of `nums[i]` that can be used to destroy the maximum number of targets.*);
//@ ensures(*The output is one of the elements in the input array `nums`.*);
//@ ensures(*The output is the minimum value that can destroy the maximum number of targets.*);
//@ ensures(*The output is the value that allows the machine to destroy all targets with values that can be represented as `nums[i] + c * space`, where `c` is any non-negative integer.*);
//@ ensures(*The output is the value that maximizes the number of targets destroyed.*);
    public int destroyTargets(int[] nums, int space) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int reminder = num % space;
            int freq = map.getOrDefault(reminder, 0);
            map.put(reminder, freq + 1);
        }
        int maxCount = 0;
        int ans = Integer.MAX_VALUE;
        for (int count : map.values()) {
            maxCount = Math.max(count, maxCount);
        }
        for (int val : nums) {
            if (map.get(val % space) == maxCount) {
                ans = Math.min(ans, val);
            }
        }
        return ans;
    }
}