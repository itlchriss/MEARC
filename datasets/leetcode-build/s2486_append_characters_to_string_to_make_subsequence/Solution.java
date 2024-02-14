package g2401_2500.s2486_append_characters_to_string_to_make_subsequence;

// #Medium #String #Greedy #Two_Pointers #2023_01_26_Time_2_ms_(99.89%)_Space_49.5_MB_(39.30%)

public class Solution {
	//@ requires(*The input strings `s` and `t` are not null.*);
	//@ requires(*The lengths of `s` and `t` are between 1 and 10^5.*);
	//@ ensures(*The method returns an integer representing the minimum number of characters that need to be appended to `s` so that `t` becomes a subsequence of `s`.*);
    public int appendCharacters(String s, String t) {
        int lengthOfT = t.length();
        int indexOfT = 0;
        int indexOfS = 0;
        int position;
        if (s.contains(t)) {
            return 0;
        }
        while (indexOfT < lengthOfT) {
            position = s.indexOf(t.charAt(indexOfT), indexOfS);
            if (position < 0) {
                return lengthOfT - indexOfT;
            }
            indexOfS = position;
            indexOfT++;
            indexOfS++;
        }
        return lengthOfT - (indexOfT);
    }
}