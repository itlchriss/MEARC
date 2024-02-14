package g0301_0400.s0319_bulb_switcher;

// #Medium #Math #Brainteaser #2022_07_08_Time_0_ms_(100.00%)_Space_41.1_MB_(27.19%)

public class Solution {
	//@ requires(*The input `n` must be a non-negative integer.*);
	//@ ensures(*The method returns an integer representing the number of bulbs that are on after `n` rounds.*);
	//@ ensures(*The returned value is non-negative.*);
	//@ ensures(*If `n` is 0, the method returns 0.*);
	//@ ensures(*If `n` is 1, the method returns 1.*);
	//@ ensures(*If `n` is greater than 1, the method returns the number of bulbs that are on after `n` rounds.*);
    public int bulbSwitch(int n) {
        if (n < 2) {
            return n;
        }
        return (int) Math.sqrt(n);
    }
}