package g1201_1300.s1238_circular_permutation_in_binary_representation;

// #Medium #Math #Bit_Manipulation #Backtracking
// #2022_03_12_Time_4_ms_(100.00%)_Space_49.9_MB_(90.59%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//@ requires(*The input integer `n` must be greater than or equal to 1.*);
	//@ requires(*The input integer `start` must be greater than or equal to 0 and less than 2^n.*);
	//@ ensures(*The output list `p` must be a valid permutation of (0, 1, 2, ..., 2^n - 1).*);
	//@ ensures(*The first element of the output list `p` must be equal to the input integer `start`.*);
	//@ ensures(*The binary representation of any two adjacent elements in the output list `p` must differ by only one bit.*);
	//@ ensures(*The binary representation of the first and last elements in the output list `p` must differ by only one bit.*);
    public List<Integer> circularPermutation(int n, int start) {

        List<Integer> l1 = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            l1.add(start ^ (i ^ (i >> 1)));
        }
        return l1;
    }
}