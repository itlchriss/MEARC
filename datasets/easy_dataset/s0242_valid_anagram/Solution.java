package g0201_0300.s0242_valid_anagram;

// #Easy #Top_Interview_Questions #String #Hash_Table #Sorting #Data_Structure_I_Day_6_String
// #Programming_Skills_I_Day_11_Containers_and_Libraries #Udemy_Strings
// #2022_07_05_Time_2_ms_(99.01%)_Space_42.4_MB_(91.86%)

public class Solution {
//@ ensures(*If the length of string parameter `s` is equal to the length of string parameter `t` and the characters of string parameter `s` are rearranged to form string parameter `t`, the boolean result is true.*);
//@ ensures(*If the length of string parameter `s` is not equal to the length of string parameter `t` or the characters of string parameter `s` cannot be rearranged to form string parameter `t`, the boolean result is false.*);
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