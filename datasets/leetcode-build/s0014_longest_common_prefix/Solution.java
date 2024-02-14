package g0001_0100.s0014_longest_common_prefix;

// #Easy #Top_Interview_Questions #String #Level_2_Day_2_String #Udemy_Strings
// #2023_08_09_Time_0_ms_(100.00%)_Space_40.2_MB_(83.48%)

public class Solution {
	//@ requires(*The input array `strs` is not null.*);
	//@ requires(*The length of the input array `strs` is greater than or equal to 1.*);
	//@ requires(*Each string in the input array `strs` is not null.*);
	//@ requires(*Each string in the input array `strs` consists of only lower-case English letters.*);
	//@ ensures(*The returned string is the longest common prefix among the input strings.*);
	//@ ensures(*If there is no common prefix, the returned string is an empty string `""`.*);
    public String longestCommonPrefix(String[] strs) {
        if (strs.length < 1) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String temp = strs[0];
        int i = 1;
        String cur;
        while (temp.length() > 0 && i < strs.length) {
            if (temp.length() > strs[i].length()) {
                temp = temp.substring(0, strs[i].length());
            }
            cur = strs[i].substring(0, temp.length());
            if (!cur.equals(temp)) {
                temp = temp.substring(0, temp.length() - 1);
            } else {
                i++;
            }
        }
        return temp;
    }
}