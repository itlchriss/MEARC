package g1801_1900.s1806_minimum_number_of_operations_to_reinitialize_a_permutation;

// #Medium #Array #Math #Simulation #2022_05_02_Time_0_ms_(100.00%)_Space_38.8_MB_(98.28%)

public class Solution {
	//@ requires(*The input `n` is an even integer.*);
	//@ requires(*The initial permutation `perm` is an array of size `n` where `perm[i] == i` for each index `i` (0-indexed).*);
	//@ ensures(*The array `perm` has been modified to its initial value.*);
	//@ ensures(*The minimum non-zero number of operations needed to perform on `perm` to return it to its initial value has been returned as an integer.*);
    public int reinitializePermutation(int n) {
        final int factor = n - 1;
        if (factor < 2) {
            return 1;
        }
        int powerOfTwo = 2;
        int ops = 1;
        while (powerOfTwo != 1) {
            powerOfTwo = ((powerOfTwo << 1) % factor);
            ops++;
        }
        return ops;
    }
}