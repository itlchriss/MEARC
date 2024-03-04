package g0301_0400.s0398_random_pick_index;

// #Medium #Hash_Table #Math #Randomized #Reservoir_Sampling
// #2022_07_15_Time_102_ms_(83.94%)_Space_81.3_MB_(76.10%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@SuppressWarnings("java:S2245")
public class Solution {
    // O(n) time | O(n) space
    private Map<Integer, List<Integer>> map;
    private Random rand;
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The integer array parameter `nums` may contain duplicates.*);
//@ ensures(*The integer parameter `target` must exist in the integer array parameter `nums`.*);
//@ ensures(*The integer result of the `pick` method is an index `i` from the integer array parameter `nums` where `nums[i] == target`.*);
//@ ensures(*If there are multiple valid indices `i` where `nums[i] == target`, each index should have an equal probability of returning.*);

    public Solution(int[] nums) {
        map = new HashMap<>();
        rand = new Random();
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int pick(int target) {
        List<Integer> list = map.get(target);
        return list.get(rand.nextInt(list.size()));
    }
}
/*
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */