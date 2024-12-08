package g0301_0400.s0387_first_unique_character_in_a_string;

// #Easy #Top_Interview_Questions #String #Hash_Table #Counting #Queue
// #Data_Structure_I_Day_6_String #2022_07_13_Time_1_ms_(100.00%)_Space_42.9_MB_(86.44%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((s.length() <= 100000) && (s.length() >= 1));
//@ requires(\forall int i; 0 <= i < s.length(); Character.isLowerCase(s.charAt(i)));
    public int firstUniqChar(String s) {
        int ans = Integer.MAX_VALUE;
        for (char i = 'a'; i <= 'z'; i++) {
            int ind = s.indexOf(i);
            if (ind > -1 && ind == s.lastIndexOf(i)) {
                ans = Math.min(ans, ind);
            }
        }
        if (ans == Integer.MAX_VALUE) {
            return -1;
        }
        return ans;
    }
}
