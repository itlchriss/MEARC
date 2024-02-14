package g0301_0400.s0384_shuffle_an_array;

// #Medium #Top_Interview_Questions #Array #Math #Randomized #Algorithm_II_Day_20_Others
// #2022_07_13_Time_52_ms_(91.77%)_Space_48.2_MB_(92.20%)

import java.util.Random;

@SuppressWarnings("java:S2245")
public class Solution {
    private int[] nums;
    private Random random;
	//@ requires(*The input array `nums` must not be null.*);
	//@ requires(*The length of the input array `nums` must be between 1 and 200.*);
	//@ requires(*Each element in the input array `nums` must be between -10^6 and 10^6.*);
	//@ requires(*All elements in the input array `nums` must be unique.*);
	//@ ensures(*The `reset` method should return the original configuration of the array `nums`.*);
	//@ ensures(*The `shuffle` method should return a random shuffling of the array `nums`.*);
	//@ ensures(*All permutations of the array `nums` should be equally likely to be returned by the `shuffle` method.*);
	//@ ensures(*The `reset` method should not modify the array `nums`.*);
	//@ ensures(*The `shuffle` method should not modify the array `nums`.*);

    public Solution(int[] nums) {
        this.nums = nums;
        this.random = new Random();
    }

    // Resets the array to its original configuration and return it.
    public int[] reset() {
        return this.nums;
    }

    // Returns a random shuffling of the array.
    public int[] shuffle() {
        int[] shuffled = this.nums.clone();
        for (int i = nums.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            swap(shuffled, i, j);
        }
        return shuffled;
    }

    private void swap(int[] shuffled, int i, int j) {
        int tmp = shuffled[i];
        shuffled[i] = shuffled[j];
        shuffled[j] = tmp;
    }
}