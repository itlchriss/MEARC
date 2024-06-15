package g0301_0400.s0319_bulb_switcher;

// #Medium #Math #Brainteaser #2022_07_08_Time_0_ms_(100.00%)_Space_41.1_MB_(27.19%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((n >= 0) && (n <= 1000000000));
//@ ensures((n == 3) ==> (\result == 1));
//@ ensures((n == 1) ==> (\result == 1));
//@ ensures((n == 0) ==> (\result == 0));
    public int bulbSwitch(int n) {
        if (n <= 2) {
            return n;
        }
        return (int) Math.sqrt(n);
    }
}
