package g2501_2600.s2582_pass_the_pillow;

// #Easy #Math #Simulation #2023_08_22_Time_0_ms_(100.00%)_Space_39.3_MB_(46.65%)

public class Solution {
	//@ requires(*The value of `n` must be greater than or equal to 2.*);
	//@ requires(*The value of `time` must be greater than or equal to 1.*);
	//@ ensures(*The returned index must be between 1 and `n`, inclusive.*);
	//@ ensures(*The person holding the pillow after `time` seconds must be the same as the person holding the pillow at the end of the line after `time` seconds.*);
    public int passThePillow(int n, int time) {
        int roundTrip = (n - 1) * 2;
        time = time % roundTrip;
        if (time < n) {
            return time + 1;
        }
        return n - (time - n + 1);
    }
}