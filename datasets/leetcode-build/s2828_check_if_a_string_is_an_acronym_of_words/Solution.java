package g2801_2900.s2828_check_if_a_string_is_an_acronym_of_words;

// #Easy #Array #String #2023_12_11_Time_1_ms_(100.00%)_Space_43.8_MB_(29.48%)

import java.util.List;

public class Solution {
	//@ requires(*The input list `words` is not null.*);
	//@ requires(*The input string `s` is not null.*);
	//@ ensures(*The method returns `true` if `s` is an acronym of `words`, and `false` otherwise.*);
    public boolean isAcronym(List<String> words, String s) {
        if (s.length() != words.size()) {
            return false;
        }
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).charAt(0) != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}