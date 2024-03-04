package g2901_3000.s2942_find_words_containing_character;

// #Easy #Array #String #2024_01_04_Time_2_ms_(72.65%)_Space_44.8_MB_(14.23%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `words` is not null.*);
//@ ensures(*The input array `words` is not empty.*);
//@ ensures(*The character `x` is a lowercase English letter.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned list is not null.*);
//@ ensures(*The returned list contains only integers.*);
//@ ensures(*The returned list represents the indices of the words that contain the character `x`.*);
//@ ensures(*The order of the indices in the returned list can be any order.*);
//@ ensures(*If no word in the input array `words` contains the character `x`, the returned list is empty.*);
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                if (words[i].charAt(j) == x) {
                    ans.add(i);
                    break;
                }
            }
        }
        return ans;
    }
}