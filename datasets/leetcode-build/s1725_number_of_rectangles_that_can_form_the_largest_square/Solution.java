package g1701_1800.s1725_number_of_rectangles_that_can_form_the_largest_square;

// #Easy #Array #2022_04_25_Time_1_ms_(100.00%)_Space_53.6_MB_(64.54%)

public class Solution {
	//@ requires(*The input array `rectangles` is not null.*);
	//@ requires(*The length of `rectangles` is greater than or equal to 1.*);
	//@ requires(*Each element in `rectangles` is an array of length 2.*);
	//@ requires(*The length and width of each rectangle in `rectangles` are positive integers.*);
	//@ requires(*The length and width of each rectangle in `rectangles` are not equal.*);
	//@ ensures(*The method returns an integer representing the number of rectangles that can make a square with a side length of `maxLen`.*);
	//@ ensures(*The value of `maxLen` is the side length of the largest square that can be obtained from any of the given rectangles.*);
    public int countGoodRectangles(int[][] rectangles) {
        int maxSoFar = 0;
        int count = 0;
        for (int[] rectangle : rectangles) {
            int sqLen = Math.min(rectangle[0], rectangle[1]);
            if (maxSoFar <= sqLen) {
                if (maxSoFar < sqLen) {
                    maxSoFar = sqLen;
                    count = 1;
                } else {
                    count++;
                }
            }
        }
        return count;
    }
}