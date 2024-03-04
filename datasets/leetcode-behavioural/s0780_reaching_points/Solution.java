package g0701_0800.s0780_reaching_points;

// #Hard #Math #2022_03_26_Time_0_ms_(100.00%)_Space_41.3_MB_(32.70%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The values of `sx`, `sy`, `tx`, and `ty` are integers.*);
//@ ensures(*The values of `sx`, `sy`, `tx`, and `ty` are greater than or equal to 1.*);
//@ ensures(*The values of `sx`, `sy`, `tx`, and `ty` are less than or equal to 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a boolean value indicating whether it is possible to convert the point `(sx, sy)` to the point `(tx, ty)` through some operations.*);
//@ ensures(*If it is possible to convert the point `(sx, sy)` to the point `(tx, ty)` through some operations, the method returns `true`.*);
//@ ensures(*If it is not possible to convert the point `(sx, sy)` to the point `(tx, ty)` through some operations, the method returns `false`.*);
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if (tx > ty) {
                if (ty == sy) {
                    // ty==sy
                    return (tx - sx) % sy == 0;
                } else {
                    // ty > sy
                    tx %= ty;
                }
            } else if (sx == tx) {
                // ty >= tx
                return (ty - sy) % sx == 0;
            } else {
                // (tx > sx)
                ty %= tx;
            }
        }
        return false;
    }
}