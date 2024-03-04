package g2501_2600.s2506_count_pairs_of_similar_strings;

// #Easy #Array #String #Hash_Table #2023_03_20_Time_6_ms_(85.15%)_Space_42.4_MB_(59.86%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `words` is not null.*);
//@ ensures(*The length of the input array `words` is greater than or equal to 2.*);
//@ ensures(*Each string in the input array `words` is not null.*);
//@ ensures(*Each string in the input array `words` consists of only lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of pairs `(i, j)` such that `0 <= i < j <= word.length - 1` and the two strings `words[i]` and `words[j]` are similar.*);
    public int similarPairs(String[] words) {
        int len = words.length;
        if (len == 1) {
            return 0;
        }
        byte[][] alPh = new byte[len][26];
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            String word = words[i];
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (alPh[i][idx] == 0) {
                    alPh[i][idx]++;
                }
            }
            String s = new String(alPh[i]);
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        int pairs = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            if (freq > 1) {
                pairs += (freq * (freq - 1)) / 2;
            }
        }
        return pairs;
    }
}