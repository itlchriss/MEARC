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
	//@ requires(*The input array `nums` must not be null.*);
	//@ requires(*The length of the input array `nums` must be greater than or equal to 1.*);
	//@ requires(*The target number `target` must be an integer from the input array `nums`.*);
	//@ ensures(*The object of the `Solution` class is initialized with the input array `nums`.*);
	//@ ensures(*The `pick` method returns an integer representing the index of a randomly chosen element from the input array `nums` where the element is equal to the target number `target`.*);
	//@ ensures(*If there are multiple valid indices, each index should have an equal probability of being returned by the `pick` method.*);

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