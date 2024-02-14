package g2701_2800.s2712_minimum_cost_to_make_all_characters_equal;

// #Medium #String #Dynamic_Programming #Greedy
// #2023_09_15_Time_7_ms_(94.19%)_Space_44.2_MB_(74.81%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is greater than or equal to 1.*);
	//@ requires(*The input string `s` consists of only binary characters ('0' or '1').*);
	//@ ensures(*The method returns a long value representing the minimum cost to make all characters of the string equal.*);
	//@ ensures(*The minimum cost is calculated by applying the two types of operations described in the requirements.*);
	//@ ensures(*The characters of the input string `s` are inverted according to the chosen indices and the minimum cost is updated accordingly.*);
    public long minimumCost(String s) {
        long ans = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                ans += Math.min(i, s.length() - i);
            }
        }
        return ans;
    }
}