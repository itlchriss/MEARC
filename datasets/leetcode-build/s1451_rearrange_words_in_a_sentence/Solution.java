package g1401_1500.s1451_rearrange_words_in_a_sentence;

// #Medium #String #Sorting #2022_03_28_Time_21_ms_(89.94%)_Space_42.8_MB_(98.28%)

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
	//@ requires(*The input `text` is a non-empty string.*);
	//@ requires(*The first letter of `text` is in uppercase.*);
	//@ requires(*Each word in `text` is separated by a single space.*);
	//@ ensures(*The output is a string in the same format as `text` (first letter is in uppercase, words are separated by a single space).*);
	//@ ensures(*The words in the output are rearranged in increasing order of their lengths.*);
	//@ ensures(*If two words have the same length, they are arranged in their original order.*);
    public String arrangeWords(String text) {
        TreeMap<Integer, List<String>> map = new TreeMap<>();
        String[] words = text.split(" ");
        for (String word : words) {
            int len = word.length();
            map.putIfAbsent(len, new ArrayList<>());

            map.get(len).add(word.toLowerCase());
        }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<Integer, List<String>> len : map.entrySet()) {
            List<String> strings = len.getValue();
            for (String str : strings) {
                if (first) {
                    str = Character.toUpperCase(str.charAt(0)) + str.substring(1);
                    first = false;
                }
                sb.append(str).append(" ");
            }
        }
        return sb.substring(0, sb.length() - 1);
    }
}