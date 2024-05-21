package g0701_0800.s0709_to_lower_case;

// #Easy #String #Programming_Skills_I_Day_9_String
// #2022_03_23_Time_1_ms_(71.74%)_Space_42_MB_(52.94%)

public class Solution {
//@ requires(*Example 1:*);
//@ requires(*Input: s = "Hello"*);
//@ requires(*Output: "hello"*);
//@ requires(*Example 2:*);
//@ requires(*Input: s = "here"*);
//@ requires(*Output: "here"*);
//@ requires(*Example 3:*);
//@ requires(*Input: s = "LOVELY"*);
//@ requires(*Output: "lovely"*);
//@ requires(*Constraints:*);
//@ requires(*`1 <= s.length <= 100`*);
//@ requires(*param_s consists of printable ASCII characters.*);
//@ ensures(*Given a string param_s, the result is the string after replacing every uppercase letter with the same lowercase letter.*);
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