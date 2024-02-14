package g1201_1300.s1297_maximum_number_of_occurrences_of_a_substring;

// #Medium #String #Hash_Table #Sliding_Window
// #2022_03_10_Time_42_ms_(90.85%)_Space_54.8_MB_(57.40%)

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("java:S1172")
public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The input string `s` is not empty.*);
	//@ requires(*The input integer `max` is greater than or equal to 1.*);
	//@ requires(*The input integer `minSize` is greater than or equal to 1.*);
	//@ requires(*The input integer `maxSize` is greater than or equal to `minSize`.*);
	//@ requires(*The input integer `maxSize` is less than or equal to the length of the input string `s`.*);
	//@ requires(*The input integer `maxLetters` is greater than or equal to 1.*);
	//@ requires(*The input integer `maxLetters` is less than or equal to 26.*);
	//@ ensures(*The method returns an integer representing the maximum number of occurrences of any substring that satisfies the given rules.*);
	//@ ensures(*The returned integer is greater than or equal to 0.*);
    public int maxFreq(String s, int max, int minSize, int maxSize) {
        // the map of occurrences
        Map<String, Integer> sub2Count = new HashMap<>();
        // sliding window indices
        int lo = 0;
        int hi = minSize - 1;
        int maxCount = 0;
        // unique letters counter
        char[] uniq = new char[26];
        int uniqCount = 0;
        // initial window calculation - `hi` is excluded here!
        for (char ch : s.substring(lo, hi).toCharArray()) {
            uniq[ch - 'a'] += 1;
            if (uniq[ch - 'a'] == 1) {
                uniqCount++;
            }
        }
        while (hi < s.length()) {
            // handle increment of hi
            char hiCh = s.charAt(hi);
            uniq[hiCh - 'a'] += 1;
            if (uniq[hiCh - 'a'] == 1) {
                uniqCount++;
            }
            ++hi;
            // add the substring to the map of occurences
            String sub = s.substring(lo, hi);
            if (uniqCount <= max) {
                int count = 1;
                if (sub2Count.containsKey(sub)) {
                    count += sub2Count.get(sub);
                }
                sub2Count.put(sub, count);
                maxCount = Math.max(maxCount, count);
            }
            // handle increment of lo
            char loCh = s.charAt(lo);
            uniq[loCh - 'a'] -= 1;
            if (uniq[loCh - 'a'] == 0) {
                uniqCount--;
            }
            ++lo;
        }
        return maxCount;
    }
}