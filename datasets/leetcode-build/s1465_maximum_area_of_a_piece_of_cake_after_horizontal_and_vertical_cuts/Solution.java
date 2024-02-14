package g1401_1500.s1465_maximum_area_of_a_piece_of_cake_after_horizontal_and_vertical_cuts;

// #Medium #Array #Sorting #Greedy #2022_03_29_Time_21_ms_(35.40%)_Space_61.3_MB_(26.00%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input values `h` and `w` must be integers greater than or equal to - The arrays `horizontalCuts` and `verticalCuts` must not be null.*);
	//@ requires(*The length of `horizontalCuts` must be greater than or equal to 1 and less than or equal to `h - 1`.*);
	//@ requires(*The length of `verticalCuts` must be greater than or equal to 1 and less than or equal to `w - 1`.*);
	//@ requires(*All elements in `horizontalCuts` must be integers greater than or equal to 1 and less than `h`.*);
	//@ requires(*All elements in `verticalCuts` must be integers greater than or equal to 1 and less than `w`.*);
	//@ requires(*All elements in `horizontalCuts` must be distinct.*);
	//@ requires(*All elements in `verticalCuts` must be distinct.*);
	//@ ensures(*The method returns an integer representing the maximum area of a piece of cake.*);
	//@ ensures(*The returned value is the result of cutting the cake at each horizontal and vertical position provided in the arrays `horizontalCuts` and `verticalCuts`.*);
	//@ ensures(*The returned value is modulo `10^9 + 7`.*);
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        long maxVertical = Math.max(0L, verticalCuts[0]);
        for (int i = 1; i < verticalCuts.length; i++) {
            int diff = (verticalCuts[i] - verticalCuts[i - 1]);
            maxVertical = Math.max(maxVertical, diff);
        }
        maxVertical = Math.max(maxVertical, (long) w - verticalCuts[verticalCuts.length - 1]);
        long maxHorizontal = Math.max(0L, horizontalCuts[0]);
        for (int i = 1; i < horizontalCuts.length; i++) {
            int diff = (horizontalCuts[i] - horizontalCuts[i - 1]);
            maxHorizontal = Math.max(maxHorizontal, diff);
        }
        maxHorizontal =
                Math.max(maxHorizontal, (long) h - horizontalCuts[horizontalCuts.length - 1]);
        return (int) (maxVertical % 1000000007 * maxHorizontal % 1000000007) % 1000000007;
    }
}