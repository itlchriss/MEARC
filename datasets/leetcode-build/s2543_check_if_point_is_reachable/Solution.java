package g2501_2600.s2543_check_if_point_is_reachable;

// #Hard #Math #Number_Theory #2023_05_11_Time_0_ms_(100.00%)_Space_39.3_MB_(70.37%)

public class Solution {
	//@ requires(*The method should take two integer parameters `targetX` and `targetY`.*);
	//@ requires(*The values of `targetX` and `targetY` should be between 1 and 10^9 (inclusive).*);
	//@ ensures(*The method should return a boolean value indicating whether it is possible to reach the point `(targetX, targetY)` from `(1, 1)` using some number of steps.*);
	//@ ensures(*If it is possible to reach the point, the method should return `true`.*);
	//@ ensures(*If it is not possible to reach the point, the method should return `false`.*);
    public boolean isReachable(int x, int y) {
        int g = gcd(x, y);
        return (g & (g - 1)) == 0;
    }

    private int gcd(int x, int y) {
        while (x != 0) {
            int tmp = x;
            x = y % x;
            y = tmp;
        }
        return y;
    }
}