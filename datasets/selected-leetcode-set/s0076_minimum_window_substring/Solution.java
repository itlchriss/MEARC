package g0001_0100.s0076_minimum_window_substring;

// #Hard #Top_100_Liked_Questions #Top_Interview_Questions #String #Hash_Table #Sliding_Window
// #Level_2_Day_14_Sliding_Window/Two_Pointer #Big_O_Time_O(s.length())_Space_O(1)
// #2023_08_11_Time_2_ms_(99.94%)_Space_43.6_MB_(93.87%)

public class Solution {
//@ ensures(*The string parameter `s` must not be null.*);
//@ ensures(*The string parameter `t` must not be null.*);
//@ ensures(*The string result is the minimum window substring of string `s` that includes every character in string `t`.*);
//@ ensures(*If there is no such substring, the string result is equal to the empty string `""`.*);
//@ ensures(*The minimum window substring must be unique.*);
//@ ensures(*The algorithm must run in O(m + n) time complexity, where m is the length of string `s` and n is the length of string `t`.*);
    public String minWindow(String s, String t) {
        int[] map = new int[128];
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i) - 'A']++;
        }
        int count = t.length();
        int begin = 0;
        int end = 0;
        int d = Integer.MAX_VALUE;
        int head = 0;
        while (end < s.length()) {
            if (map[s.charAt(end++) - 'A']-- > 0) {
                count--;
            }
            while (count == 0) {
                if (end - begin < d) {
                    d = end - begin;
                    head = begin;
                }
                if (map[s.charAt(begin++) - 'A']++ == 0) {
                    count++;
                }
            }
        }
        return d == Integer.MAX_VALUE ? "" : s.substring(head, head + d);
    }
}