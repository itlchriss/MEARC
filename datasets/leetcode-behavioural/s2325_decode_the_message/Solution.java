package g2301_2400.s2325_decode_the_message;

// #Easy #String #Hash_Table #2022_07_03_Time_7_ms_(42.86%)_Space_42.6_MB_(57.14%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The length of `key` is between 26 and 2000.*);
//@ ensures(*`key` consists of lowercase English letters and spaces.*);
//@ ensures(*`key` contains every letter in the English alphabet at least once.*);
//@ ensures(*The length of `message` is between 1 and 2000.*);
//@ ensures(*`message` consists of lowercase English letters and spaces.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a string representing the decoded message.*);
    public String decodeMessage(String key, String message) {
        StringBuilder sb = new StringBuilder();
        Map<Character, Character> temp = new HashMap<>();
        char[] alphabet = new char[26];
        int itr = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            alphabet[c - 'a'] = c;
        }
        for (int i = 0; i < key.length(); i++) {
            if (!temp.containsKey(key.charAt(i)) && key.charAt(i) != ' ') {
                temp.put(key.charAt(i), alphabet[itr++]);
            }
        }
        for (int j = 0; j < message.length(); j++) {
            if (message.charAt(j) == ' ') {
                sb.append(' ');
            } else {
                char result = temp.get(message.charAt(j));
                sb.append(result);
            }
        }
        return sb.toString();
    }
}