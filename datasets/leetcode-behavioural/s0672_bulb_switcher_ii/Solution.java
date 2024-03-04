package g0601_0700.s0672_bulb_switcher_ii;

// #Medium #Math #Depth_First_Search #Breadth_First_Search #Bit_Manipulation
// #2022_03_22_Time_0_ms_(100.00%)_Space_38.9_MB_(80.85%)

public class Solution {
//@ ensures(*Method behavioural requirements:*);
//@ ensures(*The integer parameter `n` must be greater than or equal to 1 and less than or equal to 1000.*);
//@ ensures(*The integer parameter `presses` must be greater than or equal to 0 and less than or equal to 1000.*);
//@ ensures(*The integer result is the number of different possible statuses after performing all `presses` button presses.*);
    public int flipLights(int n, int m) {
        if (n == 1 && m > 0) {
            return 2;
        } else if (n == 2 && m == 1) {
            return 3;
        } else if ((n > 2 && m == 1) || (n == 2 && m > 1)) {
            return 4;
        } else if (n > 2 && m == 2) {
            return 7;
        } else if (n > 2 && m > 2) {
            return 8;
        } else {
            return 1;
        }
    }
}