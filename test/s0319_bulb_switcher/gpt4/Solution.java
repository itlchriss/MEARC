package g0301_0400.s0319_bulb_switcher;

// #Medium #Math #Brainteaser #2022_07_08_Time_0_ms_(100.00%)_Space_41.1_MB_(27.19%)

public class Solution {
//@ requires(*The integer parameter `n` is less than or equal to 1000000000 and is greater than or equal to 0.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the integer parameter `n`.*);
//@ ensures(*If the integer parameter `n` is equal to 3, the integer result is equal to 1.*);
//@ ensures(*If the integer parameter `n` is equal to 0, the integer result is equal to 0.*);
//@ ensures(*If the integer parameter `n` is equal to 1, the integer result is equal to 1.*);
    public int bulbSwitch(int n) {
        if (n < 2) {
            return n;
        }
        return (int) Math.sqrt(n);
    }
}