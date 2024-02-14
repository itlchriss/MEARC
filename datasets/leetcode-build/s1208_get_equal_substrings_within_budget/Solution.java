package g1201_1300.s1208_get_equal_substrings_within_budget;

// #Medium #String #Binary_Search #Prefix_Sum #Sliding_Window
// #2022_03_09_Time_7_ms_(73.41%)_Space_43.3_MB_(45.62%)

public class Solution {
	//@ requires(*The lengths of strings `s` and `t` are the same.*);
	//@ requires(*The length of string `s` is greater than or equal to 1.*);
	//@ requires(*The maximum cost `maxCost` is between 0 and 1,000,000 (inclusive).*);
	//@ requires(*Strings `s` and `t` consist of only lowercase English letters.*);
	//@ ensures(*The method returns an integer representing the maximum length of a substring of `s` that can be changed to be the same as the corresponding substring of `t` with a cost less than or equal to `maxCost`.*);
	//@ ensures(*If there is no substring from `s` that can be changed to its corresponding substring from `t`, the method returns 0.*);
    public int equalSubstring(String s, String t, int maxCost) {
        int start = 0;
        int end = 0;
        int currCost = 0;
        int maxLength = Integer.MIN_VALUE;
        while (end < s.length()) {
            currCost += Math.abs(s.charAt(end) - t.charAt(end));
            while (currCost > maxCost) {
                currCost -= Math.abs(s.charAt(start) - t.charAt(start));
                start++;
            }
            if (end - start + 1 > maxLength) {
                maxLength = Math.max(end - start + 1, maxLength);
            }
            end++;
        }
        return maxLength;
    }
}