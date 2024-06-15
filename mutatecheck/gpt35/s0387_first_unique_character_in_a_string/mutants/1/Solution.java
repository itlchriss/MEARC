package g0301_0400.s0387_first_unique_character_in_a_string;

// #Easy #Top_Interview_Questions #String #Hash_Table #Counting #Queue
// #Data_Structure_I_Day_6_String #2022_07_13_Time_1_ms_(100.00%)_Space_42.9_MB_(86.44%)

public class Solution {
//@ ensures \result >= -1 && \result < s.length();
//@ ensures (\forall int i; 0 <= i && i < s.length(); (\exists int j; 0 <= j && j < s.length(); j != i && s.charAt(i) == s.charAt(j)) ==> \result == -1);
//@ ensures (\exists int i; 0 <= i && i < s.length(); (\forall int j; 0 <= j && j < s.length(); j != i ==> s.charAt(i) != s.charAt(j)) && \result == i);
//@ requires (\forall int i, j; 0 <= i && i < s.length() && 0 <= j && j < s.length(); i != j ==> s.charAt(i) != s.charAt(j));
// requires public int firstUniqChar(String s)
//@ requires s != null && s.length() >= 1 && s.length() <= 100000;
    public int firstUniqChar(String s) {
        int ans = Integer.MAX_VALUE;
        for (char i = 'a'; i == 'z'; i++) {
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
