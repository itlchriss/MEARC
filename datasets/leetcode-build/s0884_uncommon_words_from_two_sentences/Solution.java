package g0801_0900.s0884_uncommon_words_from_two_sentences;

// #Easy #String #Hash_Table #2022_03_28_Time_1_ms_(100.00%)_Space_40.8_MB_(89.40%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	//@ requires(*1. The input strings `s1` and `s2` are not null.*);
	//@ requires(*2. The lengths of `s1` and `s2` are between 1 and 200 (inclusive).*);
	//@ requires(*3. `s1` and `s2` consist only of lowercase English letters and spaces.*);
	//@ requires(*4. `s1` and `s2` do not have leading or trailing spaces.*);
	//@ requires(*5. All the words in `s1` and `s2` are separated by a single space.*);
	//@ ensures(*1. The method returns an array of strings.*);
	//@ ensures(*2. The array contains all the uncommon words between `s1` and `s2`.*);
	//@ ensures(*3. The order of the words in the array can be arbitrary.*);
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : s1.split(" ")) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (String word : s2.split(" ")) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                result.add(entry.getKey());
            }
        }
        String[] strs = new String[result.size()];
        result.toArray(strs);
        return strs;
    }
}