package g1801_1900.s1859_sorting_the_sentence;

// #Easy #String #Sorting #2022_05_08_Time_2_ms_(50.32%)_Space_42_MB_(55.85%)

import java.util.Map;
import java.util.TreeMap;

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of `s` is between 2 and 200.*);
	//@ requires(*`s` consists of lowercase and uppercase English letters, spaces, and digits from 1 to 9.*);
	//@ requires(*The number of words in `s` is between 1 and 9.*);
	//@ requires(*The words in `s` are separated by a single space.*);
	//@ requires(*`s` contains no leading or trailing spaces.*);
	//@ ensures(*The output string is the original sentence before shuffling.*);
	//@ ensures(*The output string does not contain any numbers.*);
    public String sortSentence(String s) {
        String[] words = s.split(" ");
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        for (String word : words) {
            int key = Integer.parseInt(word.charAt(word.length() - 1) + "");
            treeMap.put(key, word.substring(0, word.length() - 1));
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            sb.append(entry.getValue());
            sb.append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }
}