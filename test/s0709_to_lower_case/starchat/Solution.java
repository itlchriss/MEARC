package g0701_0800.s0709_to_lower_case;

// #Easy #String #Programming_Skills_I_Day_9_String
// #2022_03_23_Time_1_ms_(71.74%)_Space_42_MB_(52.94%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of printable ASCII characters.*);
//@ ensures(*The string result consists of lowercase letters.*);
//@ ensures(*If the string parameter `s` is equal to "Hello", the string result is equal to "hello".*);
//@ ensures(*If the string parameter `s` is equal to "LOVELY", the string result is equal to "lovely".*);
    public String toLowerCase(String s) {
        char[] c = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (c[i] <= 'Z' && c[i] >= 'A') {
                c[i] = (char) (c[i] - 'A' + 'a');
            }
        }
        return new String(c);
    }
}