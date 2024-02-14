package g2401_2500.s2433_find_the_original_array_of_prefix_xor;

// #Medium #Array #Bit_Manipulation #2022_12_07_Time_2_ms_(96.00%)_Space_54.8_MB_(93.08%)

public class Solution {
	//@ requires(*The input array `pref` is not null.*);
	//@ requires(*The length of the input array `pref` is greater than or equal to - The elements of the input array `pref` are non-negative integers.*);
	//@ ensures(*The output array `arr` is not null.*);
	//@ ensures(*The length of the output array `arr` is equal to the length of the input array `pref`.*);
	//@ ensures(*The elements of the output array `arr` are non-negative integers.*);
	//@ ensures(*For each index `i` from 0 to `n-1`, `pref[i]` is equal to the bitwise XOR of all elements in `arr` from index 0 to `i`.*);
    public int[] findArray(int[] pref) {
        int[] result = new int[pref.length];
        result[0] = pref[0];
        for (int i = 1; i < pref.length; i++) {
            result[i] = pref[i] ^ pref[i - 1];
        }
        return result;
    }
}