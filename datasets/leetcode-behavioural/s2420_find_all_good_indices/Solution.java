package g2401_2500.s2420_find_all_good_indices;

// #Medium #Array #Dynamic_Programming #Prefix_Sum
// #2022_11_18_Time_13_ms_(82.76%)_Space_111.2_MB_(78.83%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 3.*);
//@ ensures(*The value of `k` is a positive integer.*);
//@ ensures(*The value of `k` is less than or equal to half the length of the input array `nums`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned list is not null.*);
//@ ensures(*The returned list contains only integers.*);
//@ ensures(*The returned list is sorted in increasing order.*);
    public List<Integer> goodIndices(int[] nums, int k) {
        int amount = 1;
        List<Integer> result = new ArrayList<>();
        if (k == 1) {
            for (int i = 1; i < nums.length - 1; i++) {
                result.add(i);
            }
            return result;
        }
        for (int left = 1, right = k + 2; right < nums.length; left++, right++) {
            if (nums[left - 1] >= nums[left] && nums[right - 1] <= nums[right]) {
                amount++;
                if (amount >= k) {
                    result.add(left + 1);
                }
            } else {
                amount = 1;
            }
        }
        return result;
    }
}