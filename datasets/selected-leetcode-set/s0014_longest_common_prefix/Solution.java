package g0001_0100.s0014_longest_common_prefix;

// #Easy #Top_Interview_Questions #String #Level_2_Day_2_String #Udemy_Strings
// #2023_08_09_Time_0_ms_(100.00%)_Space_40.2_MB_(83.48%)

public class Solution {
//@ ensures(*If there is a common prefix among the input strings, the string result is equal to the longest common prefix string amongst the array of strings.*);
//@ ensures(*If there is no common prefix among the input strings, the string result is equal to an empty string `""`.*);
//@ ensures(*The length of the string result is less than or equal to 200.*);
//@ ensures(*The string result consists of only lower-case English letters.*);
    public String longestCommonPrefix(String[] strs) {
        //@ assume strs != null && 1 < strs.length <= 200;
        //@ assume \forall int i; 0 <= i < strs.length; strs[i] != null && 1 < strs[i].length() <= 200;
        if (strs.length < 1) {
            return "";
        }        
        if (strs.length == 1) {
            // assume strs != null && strs[0] != null;
            return strs[0];
        }
        // assume strs[0] != null;
        String temp = strs[0];
        // assume temp != null;
        int i = 1;
        String cur;
        //@ maintaining 0 <= i <= strs.length;
        while (temp.length() > 0 && i < strs.length) {
            // assume strs[i] != null;
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