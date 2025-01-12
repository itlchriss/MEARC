package g0301_0400.s0387_first_unique_character_in_a_string;

// #Easy #Top_Interview_Questions #String #Hash_Table #Counting #Queue
// #Data_Structure_I_Day_6_String #2022_07_13_Time_1_ms_(100.00%)_Space_42.9_MB_(86.44%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of only lowercase English letters.*);
//@ ensures(*The integer result is greater than or equal to -1 and is less than the length of the string parameter `s`.*);
//@ ensures(*If the string parameter `s` is equal to "leetcode", the integer result is equal to 0.*);
//@ ensures(*If the string parameter `s` is equal to "loveleetcode", the integer result is equal to 2.*);
//@ ensures(*If the string parameter `s` is equal to "aabb", the integer result is equal to -1.*);
    public int firstUniqChar(String s) {
        int ans = Integer.MAX_VALUE;
        for (char i = 'a'; i <= 'z'; i++) {
            int ind = s.indexOf(i);
            if (ind != -1 && ind == s.lastIndexOf(i)) {
                ans = Math.min(ans, ind);
            }
        }
        if (ans == Integer.MAX_VALUE) {
            return -1;
        }
        return ans;
    }
}