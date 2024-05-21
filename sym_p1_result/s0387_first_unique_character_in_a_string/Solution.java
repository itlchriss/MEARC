package g0301_0400.s0387_first_unique_character_in_a_string;

// #Easy #Top_Interview_Questions #String #Hash_Table #Counting #Queue
// #Data_Structure_I_Day_6_String #2022_07_13_Time_1_ms_(100.00%)_Space_42.9_MB_(86.44%)

public class Solution {
//@ requires(*Example 1:*);
//@ requires(*Input: s = "leetcode"*);
//@ requires(*Output: 0*);
//@ requires(*Example 2:*);
//@ requires(*Input: s = "loveleetcode"*);
//@ requires(*Output: 2*);
//@ requires(*Example 3:*);
//@ requires(*Input: s = "aabb"*);
//@ requires(*Output: -1*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= s.length <= 10<sup>5</sup></code>*);
//@ requires(*param_s consists of only lowercase English letters.*);
//@ ensures(*Given a string param_s, find the first non-repeating character in it and the result is its index.*);
//@ ensures(*If it does not exist, the result is `-1`.*);
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