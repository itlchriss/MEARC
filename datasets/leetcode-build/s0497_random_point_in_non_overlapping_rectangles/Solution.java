package g0401_0500.s0497_random_point_in_non_overlapping_rectangles;

// #Medium #Math #Binary_Search #Prefix_Sum #Ordered_Set #Randomized #Reservoir_Sampling
// #2022_07_24_Time_53_ms_(94.83%)_Space_48.9_MB_(85.06%)

import java.util.Random;

@SuppressWarnings("java:S2245")
public class Solution {
    private final int[] weights;
    private final int[][] rects;
    private final Random random;
	//@ requires(*The input `rects` is a non-empty array of non-overlapping axis-aligned rectangles.*);
	//@ requires(*Each rectangle in `rects` is represented by an array of length 4.*);
	//@ requires(*The first two elements of each rectangle array represent the coordinates of the bottom-left corner point.*);
	//@ requires(*The last two elements of each rectangle array represent the coordinates of the top-right corner point.*);
	//@ requires(*The coordinates of the rectangles are within the range of -10^9 to 10^9.*);
	//@ requires(*The width of each rectangle is less than or equal to 2000.*);
	//@ requires(*The height of each rectangle is less than or equal to 2000.*);
	//@ ensures(*The `Solution` object is initialized with the given rectangles.*);
	//@ ensures(*The `pick` method returns a random integer point `[u, v]` inside the space covered by one of the given rectangles.*);
	//@ ensures(*The returned point `[u, v]` has integer coordinates.*);
	//@ ensures(*Any integer point inside the space covered by one of the given rectangles is equally likely to be returned.*);
	//@ ensures(*The `pick` method can be called multiple times and each time it should return a different random point.*);

    public Solution(int[][] rects) {
        this.weights = new int[rects.length];
        this.rects = rects;
        this.random = new Random();
        for (int i = 0; i < rects.length; i++) {
            int[] rect = rects[i];
            int count = (1 + rect[2] - rect[0]) * (1 + rect[3] - rect[1]);
            weights[i] = (i == 0 ? 0 : weights[i - 1]) + count;
        }
    }

    public int[] pick() {
        int picked = 1 + random.nextInt(weights[weights.length - 1]);
        int idx = findGreaterOrEqual(picked);
        return getRandomPoint(idx);
    }

    private int findGreaterOrEqual(int target) {
        int left = 0;
        int right = weights.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (weights[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return weights[left] >= target ? left : right;
    }

    private int[] getRandomPoint(int idx) {
        int[] r = rects[idx];
        int left = r[0];
        int right = r[2];
        int bot = r[1];
        int top = r[3];
        return new int[] {
            left + random.nextInt(right - left + 1), bot + random.nextInt(top - bot + 1)
        };
    }
}

/*
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */