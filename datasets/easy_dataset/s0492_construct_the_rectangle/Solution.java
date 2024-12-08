package g0401_0500.s0492_construct_the_rectangle;

// #Easy #Math #2022_07_21_Time_1_ms_(74.88%)_Space_42_MB_(8.37%)

public class Solution {
    /*
      Algorithm:
     - start with an index i from the square root all the way to 1;
     - if at any time, area % i == 0 (so i is a divisor of area), then it's the closest solution.
    */
//@ ensures(*The integer parameter `area` must be greater than or equal to 1 and less than or equal to 10^7.*);
//@ ensures(*The integer array result `[L, W]` must contain two elements representing the length `L` and width `W` of the rectangular web page designed.*);
//@ ensures(*The area of the rectangular web page designed must be equal to the given target area.*);
//@ ensures(*The width `W` must not be larger than the length `L`, i.e., `L >= W`.*);
//@ ensures(*The absolute difference between the length `L` and width `W` should be as small as possible.*);
    public int[] constructRectangle(int area) {
        int low = (int) Math.sqrt(area);
        while (low > 0) {
            if (area % low == 0) {
                return new int[] {area / low, low};
            }
            low--;
        }
        return new int[] {0, 0};
    }
}