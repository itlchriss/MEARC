package g1301_1400.s1318_minimum_flips_to_make_a_or_b_equal_to_c;

// #Medium #Bit_Manipulation #2022_03_19_Time_0_ms_(100.00%)_Space_40.6_MB_(60.32%)

public class Solution {
	//@ requires(*The input values `a`, `b`, and `c` must be positive integers.*);
	//@ requires(*The values of `a`, `b`, and `c` must be within the range of 1 to 10^9.*);
	//@ ensures(*The method should return an integer representing the minimum number of flips required to make `a OR b` equal to `c`.*);
	//@ ensures(*After the flips, the values of `a`, `b`, and `c` should be updated accordingly.*);
    public static int csb(int n) {
        int cnt = 0;
        while (n > 0) {
            int rsb = n & -n;
            n -= rsb;
            cnt++;
        }
        return cnt;
    }

    public int minFlips(int a, int b, int c) {
        int ans = 0;
        int or = a | b;
        ans += csb(or ^ c);
        int and = a & b;
        ans += csb(and & ~c);
        return ans;
    }
}