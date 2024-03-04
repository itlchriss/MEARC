package g0801_0900.s0890_find_and_replace_pattern;

// #Medium #Array #String #Hash_Table #2022_03_28_Time_1_ms_(97.13%)_Space_42.3_MB_(86.23%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("java:S135")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `words` is not null.*);
//@ ensures(*The input string `pattern` is not null.*);
//@ ensures(*The length of `pattern` is between 1 and 20 (inclusive).*);
//@ ensures(*The length of `words` is between 1 and 50 (inclusive).*);
//@ ensures(*Each string in `words` has the same length as `pattern`.*);
//@ ensures(*Each character in `pattern` and each string in `words` is a lowercase English letter.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a list of strings that match the pattern.*);
//@ ensures(*The order of the strings in the output list can be in any order.*);
//@ ensures(*The output list does not contain any duplicates.*);
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> finalans = new ArrayList<>();
        if (pattern.length() == 1) {
            Collections.addAll(finalans, words);
            return finalans;
        }
        for (String word : words) {
            char[] check = new char[26];
            Arrays.fill(check, '1');
            HashMap<Character, Character> ans = new HashMap<>();
            for (int j = 0; j < word.length(); j++) {
                char pat = pattern.charAt(j);
                char wor = word.charAt(j);
                if (ans.containsKey(pat)) {
                    if (ans.get(pat) == wor) {
                        if (j == word.length() - 1) {
                            finalans.add(word);
                        }
                    } else {
                        break;
                    }
                } else {
                    if (j == word.length() - 1 && check[wor - 'a'] == '1') {
                        finalans.add(word);
                    }
                    if (check[wor - 'a'] != '1' && check[wor - 'a'] != pat) {
                        break;
                    }
                    if (check[wor - 'a'] == '1') {
                        ans.put(pat, wor);
                        check[wor - 'a'] = pat;
                    }
                }
            }
        }
        return finalans;
    }
}