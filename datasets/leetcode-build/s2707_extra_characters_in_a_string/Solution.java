package g2701_2800.s2707_extra_characters_in_a_string;

// #Medium #Array #String #Hash_Table #Dynamic_Programming #Trie
// #2023_09_15_Time_37_ms_(74.40%)_Space_44.2_MB_(74.60%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The input dictionary is not null.*);
	//@ requires(*The length of `s` is greater than or equal to 1.*);
	//@ requires(*The length of the dictionary is greater than or equal to 1.*);
	//@ requires(*Each word in the dictionary is not null.*);
	//@ requires(*Each word in the dictionary consists of only lowercase English letters.*);
	//@ requires(*The dictionary contains distinct words.*);
	//@ ensures(*The method returns an integer representing the minimum number of extra characters left over.*);
	//@ ensures(*The returned value is greater than or equal to 0.*);
    public int minExtraChar(String s, String[] dictionary) {
        return tabulationApproach(s, dictionary);
    }

    private int tabulationApproach(String s, String[] dictionary) {
        int m = s.length();
        int[] dp = new int[m + 1];
        for (int i = m - 1; i >= 0; i--) {
            dp[i] = dp[i + 1] + 1;
            int finalI = i;
            Arrays.stream(dictionary)
                    .filter(word -> s.startsWith(word, finalI))
                    .mapToInt(String::length)
                    .map(n -> dp[finalI + n])
                    .forEach(prev -> dp[finalI] = Math.min(dp[finalI], prev));
        }
        return dp[0];
    }
}