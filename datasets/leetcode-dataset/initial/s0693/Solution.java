package g0601_0700.s0693_binary_number_with_alternating_bits;

// #Easy #Bit_Manipulation #2022_03_22_Time_0_ms_(100.00%)_Space_41.3_MB_(21.55%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public boolean hasAlternatingBits(int n) {
        int prev = -1;
        while (n != 0) {
            int v = n & 1;
            n = n >> 1;
            if (prev == v) {
                return false;
            }
            prev = v;
        }
        return true;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
