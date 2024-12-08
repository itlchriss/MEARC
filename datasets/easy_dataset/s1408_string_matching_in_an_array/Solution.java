package g1401_1500.s1408_string_matching_in_an_array;

// #Easy #String #String_Matching #2022_03_26_Time_8_ms_(24.88%)_Space_43.3_MB_(13.46%)

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `words` is not null.*);
//@ ensures(*The length of the input array `words` is greater than or equal to - Each string in the input array `words` is not null.*);
//@ ensures(*Each string in the input array `words` contains only lowercase English letters.*);
//@ ensures(*Each string in the input array `words` has a length greater than or equal to 1 and less than or equal to - Each string in the input array `words` is unique.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a list of strings.*);
//@ ensures(*The list contains all strings in `words` that are substrings of another word in `words`.*);
//@ ensures(*The order of the strings in the output list can be in any order.*);
    public List<String> stringMatching(String[] words) {
        Set<String> set = new HashSet<>();
        for (String word : words) {
            for (String s : words) {
                if (!word.equals(s) && word.length() < s.length() && s.contains(word)) {
                    set.add(word);
                }
            }
        }
        return new ArrayList<>(set);
    }
}