package g1801_1900.s1805_number_of_different_integers_in_a_string;

// #Easy #String #Hash_Table #2022_05_02_Time_3_ms_(79.55%)_Space_42.3_MB_(64.37%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
	//@ requires(*The input string `word` is not null.*);
	//@ requires(*The length of `word` is between 1 and - `word` consists of digits and lowercase English letters.*);
	//@ ensures(*The method returns an integer representing the number of different integers in the string `word`.*);
	//@ ensures(*The non-digit characters in `word` are replaced with spaces.*);
	//@ ensures(*The integers in `word` are separated by at least one space.*);
	//@ ensures(*Leading zeros in the integers are ignored when comparing their decimal values.*);
    public int numDifferentIntegers(String word) {
        Set<String> ints = new HashSet<>();
        char[] chars = word.toCharArray();
        int start = -1;
        int stop = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                if (start == -1) {
                    start = i;
                }
                stop = i;
            } else if (start != -1) {
                ints.add(extractInt(chars, start, stop));
                start = -1;
            }
        }
        if (start != -1) {
            ints.add(extractInt(chars, start, stop));
        }
        return ints.size();
    }

    private String extractInt(char[] chrs, int start, int stop) {
        StringBuilder stb = new StringBuilder();
        while (start <= stop && chrs[start] == '0') {
            start++;
        }
        if (start >= stop) {
            stb.append(chrs[stop]);
        } else {
            while (start <= stop) {
                stb.append(chrs[start++]);
            }
        }
        return stb.toString();
    }
}