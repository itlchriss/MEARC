package g0801_0900.s0858_mirror_reflection;

// #Medium #Math #Geometry #2022_03_27_Time_0_ms_(100.00%)_Space_40.8_MB_(64.41%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given the two integers `p` and `q`, return _the number of the receptor that the ray meets first_.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
