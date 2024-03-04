package g2801_2900.s2849_determine_if_a_cell_is_reachable_at_a_given_time;

// #Medium #Math #2023_12_15_Time_1_ms_(91.15%)_Space_39.3_MB_(87.43%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*1. The values of `sx`, `sy`, `fx`, `fy` must be positive integers.*);
//@ ensures(*2. The value of `t` must be a non-negative integer.*);
//@ ensures(*3. The values of `sx`, `sy`, `fx`, `fy` must be within the range of 1 to 10^9.*);
//@ ensures(*4. The value of `t` must be within the range of 0 to 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The method returns `true` if it is possible to reach cell `(fx, fy)` after exactly `t` seconds.*);
//@ ensures(*2. The method returns `false` if it is not possible to reach cell `(fx, fy)` after exactly `t` seconds.*);
    public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        if (sx == fx && sy == fy) {
            return t != 1;
        }
        int width = Math.abs(sx - fx) + 1;
        int height = Math.abs(sy - fy) + 1;
        return Math.max(width, height) - 1 <= t;
    }
}