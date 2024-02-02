package g2401_2500.s2433_find_the_original_array_of_prefix_xor;

// #Medium #Array #Bit_Manipulation #2022_12_07_Time_2_ms_(96.00%)_Space_54.8_MB_(93.08%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*You are given an **integer** array `pref` of size `n`. Find and return _the array_ `arr` _of size_ `n` _that satisfies_:*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int[] findArray(int[] pref) {
        int[] result = new int[pref.length];
        result[0] = pref[0];
        for (int i = 1; i < pref.length; i++) {
            result[i] = pref[i] ^ pref[i - 1];
        }
        return result;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
