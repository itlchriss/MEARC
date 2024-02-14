package g0801_0900.s0858_mirror_reflection;

// #Medium #Math #Geometry #2022_03_27_Time_0_ms_(100.00%)_Space_40.8_MB_(64.41%)

public class Solution {
	//@ requires(*The values of p and q are positive integers.*);
	//@ requires(*The value of q is less than or equal to the value of p.*);
	//@ requires(*The square room has mirrors on each of the four walls.*);
	//@ requires(*There are receptors on each of the corners except for the southwest corner.*);
	//@ requires(*The receptors are numbered 0, 1, and 2.*);
	//@ ensures(*The method returns an integer representing the number of the receptor that the ray meets first.*);
	//@ ensures(*The returned value is one of the numbers 0, 1, or 2.*);
	//@ ensures(*The ray meets a receptor eventually.*);
    public int mirrorReflection(int p, int q) {
        while (p % 2 == 0 && q % 2 == 0) {
            p /= 2;
            q /= 2;
        }
        if (p % 2 == 0) {
            return 2;
        } else if (q % 2 == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}