package g1001_1100.s1037_valid_boomerang;

// #Easy #Array #Math #Geometry #2022_02_27_Time_0_ms_(100.00%)_Space_41.7_MB_(21.15%)

public class Solution {
	//@ requires(*1. The input array `points` must have a length of 3.*);
	//@ requires(*2. Each element in the `points` array must have a length of 2.*);
	//@ requires(*3. The values of `x` and `y` in each point must be between 0 and 100 (inclusive).*);
	//@ ensures(*1. The method should return `true` if the points form a boomerang.*);
	//@ ensures(*2. The method should return `false` if the points do not form a boomerang.*);
	//@ ensures(*3. A boomerang is defined as a set of three points that are all distinct and not in a straight line.*);
    public boolean isBoomerang(int[][] points) {
        return (points[1][1] - points[0][1]) * (points[2][0] - points[0][0])
                != (points[2][1] - points[0][1]) * (points[1][0] - points[0][0]);
    }
}