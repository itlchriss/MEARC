package g2801_2900.s2810_faulty_keyboard;

// #Easy #String #Simulation #2023_11_20_Time_3_ms_(96.04%)_Space_44.2_MB_(14.74%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of `s` is greater than or equal to 1.*);
//@ ensures(*The first character of `s` is not 'i'.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned string is not null.*);
//@ ensures(*The length of the returned string is equal to the length of `s`.*);
//@ ensures(*The characters in the returned string are in the same order as in `s`, except for the characters 'i' which cause the reversal of the string.*);
    public String finalString(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == 'i') {
                stringBuilder.reverse();
                continue;
            }
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }
}