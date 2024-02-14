package g0201_0300.s0290_word_pattern;

// #Easy #String #Hash_Table #Data_Structure_II_Day_7_String
// #2022_07_06_Time_1_ms_(97.26%)_Space_40.4_MB_(85.78%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
	//@ requires(*1. The pattern string must not be null.*);
	//@ requires(*2. The pattern string must not be empty.*);
	//@ requires(*3. The pattern string must contain only lowercase English letters.*);
	//@ requires(*4. The string s must not be null.*);
	//@ requires(*5. The string s must not be empty.*);
	//@ requires(*6. The string s must not contain any leading or trailing spaces.*);
	//@ requires(*7. The string s must contain only lowercase English letters and spaces.*);
	//@ requires(*8. All the words in s must be separated by a single space.*);
	//@ ensures(*1. The method returns true if s follows the same pattern as the pattern string.*);
	//@ ensures(*2. The method returns false if s does not follow the same pattern as the pattern string.*);
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        String[] words = s.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            if (!map.containsKey(pattern.charAt(i))) {
                if (map.containsValue(words[i])) {
                    return false;
                }
                map.put(pattern.charAt(i), words[i]);
            } else {
                if (!words[i].equals(map.get(pattern.charAt(i)))) {
                    return false;
                }
            }
        }
        return true;
    }
}