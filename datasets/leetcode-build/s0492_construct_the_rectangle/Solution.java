package g0401_0500.s0492_construct_the_rectangle;

// #Easy #Math #2022_07_21_Time_1_ms_(74.88%)_Space_42_MB_(8.37%)

public class Solution {
    /*
      Algorithm:
     - start with an index i from the square root all the way to 1;
     - if at any time, area % i == 0 (so i is a divisor of area), then it's the closest solution.
    */
	//@ requires(*The input `area` must be a positive integer greater than or equal to 1.*);
	//@ requires(*The input `area` must be less than or equal to 10^7.*);
	//@ ensures(*The output array `result` must have a length of 2.*);
	//@ ensures(*The elements in the output array `result` must be positive integers.*);
	//@ ensures(*The product of the elements in the output array `result` must be equal to the input `area`.*);
	//@ ensures(*The first element in the output array `result` must be greater than or equal to the second element.*);
	//@ ensures(*The difference between the first and second elements in the output array `result` must be minimized.*);
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