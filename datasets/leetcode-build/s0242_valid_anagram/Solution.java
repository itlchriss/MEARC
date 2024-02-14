package g0201_0300.s0242_valid_anagram;

// #Easy #Top_Interview_Questions #String #Hash_Table #Sorting #Data_Structure_I_Day_6_String
// #Programming_Skills_I_Day_11_Containers_and_Libraries #Udemy_Strings
// #2022_07_05_Time_2_ms_(99.01%)_Space_42.4_MB_(91.86%)

public class Solution {
	//@ requires(*1. The method should take two string parameters, `s` and `t`.*);
	//@ requires(*2. The length of `s` and `t` should be between 1 and 5 * 10^4.*);
	//@ requires(*3. `s` and `t` should consist of lowercase English letters.*);
	//@ ensures(*1. The method should return a boolean value indicating whether `t` is an anagram of `s`.*);
	//@ ensures(*2. If `t` is an anagram of `s`, the method should return `true`.*);
	//@ ensures(*3. If `t` is not an anagram of `s`, the method should return `false`.*);
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] charFreqMap = new int[26];
        for (char c : s.toCharArray()) {
            charFreqMap[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            if (charFreqMap[c - 'a'] == 0) {
                return false;
            }
            charFreqMap[c - 'a']--;
        }
        return true;
    }
}