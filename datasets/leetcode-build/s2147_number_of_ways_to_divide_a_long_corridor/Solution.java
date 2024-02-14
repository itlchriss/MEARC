package g2101_2200.s2147_number_of_ways_to_divide_a_long_corridor;

// #Hard #String #Dynamic_Programming #Math #2022_06_07_Time_54_ms_(62.96%)_Space_71.7_MB_(39.81%)

public class Solution {
	//@ requires(*The input `corridor` is a string of length `n` consisting of letters `'S'` and `'P'`.*);
	//@ requires(*The input `corridor` is 0-indexed.*);
	//@ requires(*The input `corridor` represents a long library corridor with seats and plants.*);
	//@ requires(*There is already a room divider installed to the left of index 0.*);
	//@ requires(*There is already a room divider installed to the right of index `n - 1`.*);
	//@ requires(*Additional room dividers can be installed between positions `i - 1` and `i` for `1 <= i <= n - 1`.*);
	//@ requires(*Each section of the divided corridor must have exactly two seats.*);
	//@ requires(*The answer should be returned modulo `10^9 + 7`.*);
	//@ ensures(*The method returns the number of ways to divide the corridor.*);
	//@ ensures(*If there is no way to divide the corridor, the method returns 0.*);
    public int numberOfWays(String corridor) {
        int seat = 0;
        int mod = (int) 1e9 + 7;
        for (int i = 0; i < corridor.length(); i++) {
            if (corridor.charAt(i) == 'S') {
                seat++;
            }
        }
        if (seat == 0 || seat % 2 != 0) {
            return 0;
        }
        seat /= 2;
        long curr = 0;
        long ans = 1;
        int i = 0;
        while (corridor.charAt(i) != 'S') {
            i++;
        }
        i++;
        while (seat > 1) {
            while (corridor.charAt(i) != 'S') {
                i++;
            }
            i++;
            while (corridor.charAt(i) != 'S') {
                i++;
                curr++;
            }
            curr++;
            ans = (ans * curr) % mod;
            curr = 0;
            seat--;
            i++;
        }
        return (int) ans;
    }
}