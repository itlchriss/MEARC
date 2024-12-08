package g1901_2000.s1961_check_if_string_is_a_prefix_of_array;

// #Easy #Array #String #2022_05_20_Time_2_ms_(60.87%)_Space_43.5_MB_(27.83%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The input array `words` is not null.*);
//@ ensures(*The length of the input array `words` is greater than 0.*);
//@ ensures(*The length of each string in the input array `words` is greater than 0.*);
//@ ensures(*The length of the input string `s` is greater than 0.*);
//@ ensures(*The input string `s` and each string in the input array `words` consist of only lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns `true` if `s` can be made by concatenating the first `k` strings in `words` for some positive `k` no larger than `words.length`.*);
//@ ensures(*The method returns `false` otherwise.*);
    public boolean isPrefixString(String s, String[] words) {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word);
            if (sb.toString().equals(s)) {
                return true;
            }
        }
        return false;
    }
}