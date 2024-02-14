package g1001_1100.s1002_find_common_characters;

// #Easy #Array #String #Hash_Table #2022_02_20_Time_4_ms_(83.51%)_Space_43.9_MB_(23.45%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S112")
public class Solution {
	//@ requires(*The input array `words` is not null.*);
	//@ requires(*The length of the input array `words` is greater than or equal to - Each string in the input array `words` is not null.*);
	//@ requires(*Each string in the input array `words` consists of lowercase English letters.*);
	//@ requires(*The length of each string in the input array `words` is greater than or equal to*);
	//@ ensures(*The method returns a list of strings.*);
	//@ ensures(*The list contains all characters that show up in all strings within the input array `words`, including duplicates.*);
	//@ ensures(*The order of the characters in the list can be in any order.*);
    public List<String> commonChars(String[] words) {
        if (words == null) {
            throw new RuntimeException("words null");
        }
        if (words.length == 0) {
            return new ArrayList<>();
        }
        String tmp = words[0];
        for (int i = 1; i < words.length; i++) {
            tmp = getCommon(tmp, words[i]);
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < tmp.length(); i++) {
            result.add(String.valueOf(tmp.charAt(i)));
        }
        return result;
    }

    private String getCommon(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return "";
        }
        int[] c1c = countChars(s1);
        int[] c2c = countChars(s2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c1c.length; i++) {
            int m = Math.min(c1c[i], c2c[i]);
            while (m > 0) {
                sb.append((char) ('a' + i));
                m--;
            }
        }
        return sb.toString();
    }

    private int[] countChars(String str) {
        int[] result = new int[26];
        for (int i = 0; i < str.length(); i++) {
            result[str.charAt(i) - 'a']++;
        }
        return result;
    }
}