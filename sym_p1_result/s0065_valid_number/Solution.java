package g0001_0100.s0065_valid_number;

// #Hard #String #2023_08_11_Time_1_ms_(100.00%)_Space_41.3_MB_(91.10%)

public class Solution {
//@ requires(*A valid number can be split up into these components (in order):*);
//@ requires(*1.*);
//@ requires(*A decimal number or an integer.*);
//@ requires(*2.*);
//@ requires(*(Optional) An `'e'` or `'E'`, followed by an integer.*);
//@ requires(*A decimal number can be split up into these components (in order):*);
//@ requires(*1.*);
//@ requires(*(Optional) A sign character (either `'+'` or `'-'`).*);
//@ requires(*2.*);
//@ requires(*One of the following formats:*);
//@ requires(*1.*);
//@ requires(*One or more digits, followed by a dot `'.'*);
//@ requires(*`.*);
//@ requires(*2.*);
//@ requires(*One or more digits, followed by a dot `'.'*);
//@ requires(*`, followed by one or more digits.*);
//@ requires(*3.*);
//@ requires(*A dot `'.'*);
//@ requires(*`, followed by one or more digits.*);
//@ requires(*An integer can be split up into these components (in order):*);
//@ requires(*1.*);
//@ requires(*(Optional) A sign character (either `'+'` or `'-'`).*);
//@ requires(*2.*);
//@ requires(*One or more digits.*);
//@ requires(*For example, all the following are valid numbers: `["2", "0089", "-0.1", "+3.14", "4.", "*);
//@ requires(*-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]`, while the following are not valid numbers: `["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]`.*);
//@ requires(*Example 1:*);
//@ requires(*Input: s = "0"*);
//@ requires(*Output: true*);
//@ requires(*Example 2:*);
//@ requires(*Input: s = "e"*);
//@ requires(*Output: false*);
//@ requires(*Example 3:*);
//@ requires(*Input: s = "."*);
//@ requires(*Output: false*);
//@ requires(*Example 4:*);
//@ requires(*Input: s = ".1"*);
//@ requires(*Output: true*);
//@ requires(*Constraints:*);
//@ requires(*`1 <= s.length <= 20`*);
//@ requires(*param_s consists of only English letters (both uppercase and lowercase), digits (`0-9`), plus `'+'`, minus `'-'`, or dot `'.'*);
//@ requires(*`.*);
//@ ensures(*Given a string param_s, the result is `true` if param_s is a valid number.*);
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean decimalSeen = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 48 && c <= 57) {
                numberSeen = true;
            } else if (c == '+' || c == '-') {
                if (i == s.length() - 1
                        || (i != 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E')) {
                    return false;
                }
            } else if (c == '.') {
                if (eSeen || decimalSeen) {
                    return false;
                }
                decimalSeen = true;
            } else if (c == 'e' || c == 'E') {
                if (i == s.length() - 1 || eSeen || !numberSeen) {
                    return false;
                }
                eSeen = true;
            } else {
                return false;
            }
        }
        return numberSeen;
    }
}