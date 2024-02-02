package g2501_2600.s2543_check_if_point_is_reachable;

// #Hard #Math #Number_Theory #2023_05_11_Time_0_ms_(100.00%)_Space_39.3_MB_(70.37%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given two integers `targetX` and `targetY` representing the X-coordinate and Y-coordinate of your final position, return `true` _if you can reach the point from_ `(1, 1)` _using some number of steps, and_ `false` _otherwise_. **Explanation:** It is impossible to reach (6,9) from (1,1) using any sequence of moves, so false is returned.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
