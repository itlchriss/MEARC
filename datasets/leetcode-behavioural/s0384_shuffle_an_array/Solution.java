package g0301_0400.s0384_shuffle_an_array;

// #Medium #Top_Interview_Questions #Array #Math #Randomized #Algorithm_II_Day_20_Others
// #2022_07_13_Time_52_ms_(91.77%)_Space_48.2_MB_(92.20%)

import java.util.Random;

@SuppressWarnings("java:S2245")
public class Solution {
    private int[] nums;
    private Random random;
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The integer array parameter `nums` must contain unique elements.*);
//@ ensures(*The integer array parameter `nums` must have a length between 1 and 200.*);
//@ ensures(*The integer result of the `reset` method is the original configuration of the integer array parameter `nums`.*);
//@ ensures(*The integer result of the `shuffle` method is a random shuffling of the integer array parameter `nums`.*);
//@ ensures(*Any permutation of the integer array parameter `nums` must be equally likely to be returned after shuffling.*);

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