package g1901_2000.s1954_minimum_garden_perimeter_to_collect_enough_apples;

// #Medium #Math #Binary_Search #2022_05_18_Time_2_ms_(59.57%)_Space_40.7_MB_(69.15%)

public class Solution {
	//@ requires(*The input `neededApples` is a positive integer.*);
	//@ ensures(*The method returns a long value representing the minimum perimeter of a plot such that at least `neededApples` apples are inside or on the perimeter of that plot.*);
	//@ ensures(*The returned perimeter is the minimum possible value.*);
	//@ ensures(*The returned perimeter is calculated based on the number of apples inside or on the perimeter of the plot.*);
	//@ ensures(*The plot is an axis-aligned square plot of land centered at `(0, 0)`.*);
	//@ ensures(*The plot contains at least `neededApples` apples.*);
	//@ ensures(*The plot may contain more than `neededApples` apples.*);
	//@ ensures(*The perimeter is calculated as the sum of the lengths of all sides of the plot multiplied by 4.*);
	//@ ensures(*The length of each side of the plot is an even number.*);
	//@ ensures(*The plot is a square, meaning all sides have the same length.*);
	//@ ensures(*The plot is centered at `(0, 0)`, meaning the coordinates `(0, 0)` are at the center of the plot.*);
	//@ ensures(*The apple tree planted at an integer coordinate `(i, j)` has `|i| + |j|` apples growing on it.*);
	//@ ensures(*The value of `|x|` is defined as `x` if `x >= 0` and `-x` if `x < 0`.*);
	//@ ensures(*The minimum perimeter is calculated by increasing the side length of the plot until the number of apples inside or on the perimeter is at least `neededApples`.*);
    public long minimumPerimeter(long neededApples) {
        long l = 1;
        long r = 1000000;
        long res = l;
        while (l <= r) {
            long m = l + (r - l) / 2;
            boolean isPossible = check(m, neededApples);
            if (isPossible) {
                res = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return res * 8;
    }

    private boolean check(long len, long neededApples) {
        long sum = len * (len + 1) / 2;
        long applesPerQuadrant = 2 * len * sum;
        long totalCount = 4 * sum + 4 * applesPerQuadrant;
        return totalCount >= neededApples;
    }
}