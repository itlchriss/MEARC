package g0001_0100.s0065_valid_number;

// #Hard #String #2023_08_11_Time_1_ms_(100.00%)_Space_41.3_MB_(91.10%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 20 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of only English letters (both uppercase and lowercase), digits (`0-9`), plus `'+'`, minus `'-'`, or dot `'.'`.*);
//@ ensures(*If the string parameter `s` is equal to "0", the boolean result is equal to true.*);
//@ ensures(*If the string parameter `s` is equal to "e", the boolean result is equal to false.*);
//@ ensures(*If the string parameter `s` is equal to ".", the boolean result is equal to false.*);
//@ ensures(*If the string parameter `s` is equal to ".1", the boolean result is equal to true.*);
//@ ensures(*If the string parameter `s` is equal to "2", the boolean result is equal to true.*);
//@ ensures(*If the string parameter `s` is equal to "0089", the boolean result is equal to true.*);
//@ ensures(*If the string parameter `s` is equal to "-0.1", the boolean result is equal to true.*);
//@ ensures(*If the string parameter `s` is equal to "+3.14", the boolean result is equal to true.*);
//@ ensures(*If the string parameter `s` is equal to "4.", the boolean result is equal to true.*);
//@ ensures(*If the string parameter `s` is equal to "-.9", the boolean result is equal to true.*);
//@ ensures(*If the string parameter `s` is equal to "2e10", the boolean result is equal to true.*);
//@ ensures(*If the string parameter `s` is equal to "-90E3", the boolean result is equal to true.*);
//@ ensures(*If the string parameter `s` is equal to "3e+7", the boolean result is equal to true.*);
//@ ensures(*If the string parameter `s` is equal to "+6e-1", the boolean result is equal to true.*);
//@ ensures(*If the string parameter `s` is equal to "53.5e93", the boolean result is equal to true.*);
//@ ensures(*If the string parameter `s` is equal to "-123.456e789", the boolean result is equal to true.*);
//@ ensures(*If the string parameter `s` is equal to "abc", the boolean result is equal to false.*);
//@ ensures(*If the string parameter `s` is equal to "1a", the boolean result is equal to false.*);
//@ ensures(*If the string parameter `s` is equal to "1e", the boolean result is equal to false.*);
//@ ensures(*If the string parameter `s` is equal to "e3", the boolean result is equal to false.*);
//@ ensures(*If the string parameter `s` is equal to "99e2.5", the boolean result is equal to false.*);
//@ ensures(*If the string parameter `s` is equal to "--6", the boolean result is equal to false.*);
//@ ensures(*If the string parameter `s` is equal to "-+3", the boolean result is equal to false.*);
//@ ensures(*If the string parameter `s` is equal to "95a54e53", the boolean result is equal to false.*);
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