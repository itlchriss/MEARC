package g0601_0700.s0672_bulb_switcher_ii;

// #Medium #Math #Depth_First_Search #Breadth_First_Search #Bit_Manipulation
// #2022_03_22_Time_0_ms_(100.00%)_Space_38.9_MB_(80.85%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given the two integers `n` and `presses`, return _the number of **different possible statuses** after performing all_ `presses` _button presses_.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
