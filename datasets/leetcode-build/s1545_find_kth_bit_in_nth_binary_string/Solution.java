package g1501_1600.s1545_find_kth_bit_in_nth_binary_string;

// #Medium #String #Recursion #2022_04_11_Time_0_ms_(100.00%)_Space_41.2_MB_(90.34%)

@SuppressWarnings("java:S1172")
public class Solution {
	//@ requires(*The input integers `n` and `k` are positive.*);
	//@ requires(*The value of `k` is valid for the given value of `n`.*);
	//@ ensures(*The method returns a character representing the `k`th bit in the binary string `S_n`.*);
    public char findKthBit(int n, int k) {
        boolean flip = false;
        while (k != 1) {
            int base = floorTwo(k);
            if (base == k) {
                return flip ? '0' : '1';
            }
            flip = !flip;
            k = base - (k - base);
        }
        return flip ? '1' : '0';
    }

    private int floorTwo(int k) {
        while ((k & (k - 1)) > 0) {
            k &= k - 1;
        }
        return k;
    }
}