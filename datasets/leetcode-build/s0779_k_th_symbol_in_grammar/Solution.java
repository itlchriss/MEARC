package g0701_0800.s0779_k_th_symbol_in_grammar;

// #Medium #Math #Bit_Manipulation #Recursion #2022_03_26_Time_0_ms_(100.00%)_Space_40.9_MB_(42.87%)

@SuppressWarnings("java:S1172")
public class Solution {
    /*
     * Time: O(logn)
     * Space: O(1)
     */
	//@ requires(*The value of `n` must be a positive integer greater than or equal to 1.*);
	//@ requires(*The value of `k` must be a positive integer greater than or equal to 1.*);
	//@ requires(*The value of `k` must be less than or equal to `2^(n-1)`.*);
	//@ ensures(*The method returns an integer value representing the `k`th symbol in the `n`th row of the table.*);
	//@ ensures(*The returned value is either 0 or 1.*);
    public int kthGrammar(int n, int k) {
        return Integer.bitCount(k - 1) % 2;
    }
}