package g1501_1600.s1503_last_moment_before_all_ants_fall_out_of_a_plank;

// #Medium #Array #Simulation #Brainteaser #2022_04_07_Time_1_ms_(81.72%)_Space_53.9_MB_(54.84%)

public class Solution {
	//@ requires(*The input `n` is a positive integer representing the length of the wooden plank.*);
	//@ requires(*The input arrays `left` and `right` contain unique integers representing the positions of the ants moving to the left and right respectively.*);
	//@ requires(*The length of `left` and `right` arrays combined is less than or equal to `n + 1`.*);
	//@ ensures(*The output is an integer representing the moment when the last ant(s) fall out of the plank.*);
	//@ ensures(*The output is equal to the maximum value between the maximum position in the `left` array and the difference between `n` and the minimum position in the `right` array.*);
    public int getLastMoment(int n, int[] left, int[] right) {
        int highestLeft = 0;
        int smallestRight = Integer.MAX_VALUE;
        for (int i = 0; i <= n + 1; i++) {
            if (i < left.length && left[i] > highestLeft) {
                highestLeft = left[i];
            }
            if (i < right.length && right[i] < smallestRight) {
                smallestRight = right[i];
            }
        }
        if (left.length != 0 && right.length == 0) {
            return highestLeft;
        }
        if (right.length != 0 && left.length == 0) {
            return Math.abs(smallestRight - n);
        }
        return Math.max(highestLeft, Math.abs(smallestRight - n));
    }
}