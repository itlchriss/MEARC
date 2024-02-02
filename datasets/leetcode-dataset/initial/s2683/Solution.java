package g2601_2700.s2683_neighboring_bitwise_xor;

// #Medium #Array #Bit_Manipulation #2023_09_12_Time_2_ms_(100.00%)_Space_59.9_MB_(62.03%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _**true** if such an array exists or **false** otherwise._*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public boolean doesValidArrayExist(int[] derived) {
        int xor = 0;
        for (int j : derived) {
            xor = xor ^ j;
        }
        return xor == 0;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
