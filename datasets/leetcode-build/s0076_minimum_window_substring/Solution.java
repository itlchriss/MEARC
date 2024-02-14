package g0001_0100.s0076_minimum_window_substring;

// #Hard #Top_100_Liked_Questions #Top_Interview_Questions #String #Hash_Table #Sliding_Window
// #Level_2_Day_14_Sliding_Window/Two_Pointer #Big_O_Time_O(s.length())_Space_O(1)
// #2023_08_11_Time_2_ms_(99.94%)_Space_43.6_MB_(93.87%)

public class Solution {
	//@ requires(*1. The input strings `s` and `t` are not null.*);
	//@ requires(*2. The lengths of `s` and `t` are greater than or equal to 1.*);
	//@ requires(*3. The characters in `s` and `t` are all uppercase or lowercase English letters.*);
	//@ ensures(*1. The returned string is a substring of `s`.*);
	//@ ensures(*2. The returned string contains all the characters from `t`, including duplicates.*);
	//@ ensures(*3. If there is no substring of `s` that contains all the characters from `t`, the returned string is empty ("").*);
	//@ ensures(*4. The returned string is the minimum window substring, meaning it is the smallest possible substring of `s` that satisfies the previous conditions.*);
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