package g0801_0900.s0819_most_common_word;

// #Easy #String #Hash_Table #Counting #2022_03_23_Time_10_ms_(88.85%)_Space_42_MB_(88.98%)

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	//@ requires(*The input string `paragraph` is not null.*);
	//@ requires(*The input string array `banned` is not null.*);
	//@ requires(*The length of `paragraph` is between 1 and 1000.*);
	//@ requires(*The length of each string in `banned` is between 1 and 10.*);
	//@ requires(*The elements in `banned` consist of only lowercase English letters.*);
	//@ ensures(*The output string is not null.*);
	//@ ensures(*The output string is in lowercase.*);
	//@ ensures(*The output string is the most frequent word in `paragraph` that is not in `banned`.*);
	//@ ensures(*The output string is unique, meaning there is no other word in `paragraph` that occurs with the same frequency and is not in `banned`.*);
    public String mostCommonWord(String paragraph, String[] banned) {
        paragraph = paragraph.replaceAll("\\p{Punct}", " ").toLowerCase();
        String[] a = paragraph.split(" ");
        for (int i = 0; i < banned.length; i++) {
            banned[i] = banned[i].toLowerCase();
        }
        Map<String, Integer> map = new HashMap<>();
        for (String s : a) {
            int x = map.getOrDefault(s, 0);
            map.put(s, x + 1);
        }
        for (String s : banned) {
            map.remove(s);
            map.remove("");
        }
        return Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}