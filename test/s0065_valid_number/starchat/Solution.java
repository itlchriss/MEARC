package g0001_0100.s0065_valid_number;

// #Hard #String #2023_08_11_Time_1_ms_(100.00%)_Space_41.3_MB_(91.10%)

public class Solution {
//@ requires(*The string parameter `s` is not null.*);
//@ requires(*The length of the string parameter `s` is less than or equal to 20.*);
//@ requires(*The string parameter `s` consists of only English letters (both uppercase and lowercase), digits (`0-9`), plus `'+'`, minus `'-'`, or dot `'.'`.*);
//@ ensures(*The integer result is either true or false.*);
//@ ensures(*If the string parameter `s` is equal to "0", the integer result is equal to true.*);
//@ ensures(*If the string parameter `s` is equal to "e", the integer result is equal to false.*);
//@ ensures(*If the string parameter `s` is equal to ".", the integer result is equal to false.*);
//@ ensures(*If the string parameter `s` is equal to ".1", the integer result is equal to true.*);
//@ ensures(*If the string parameter `s` is equal to "2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789", the integer result is equal to true.*);
//@ ensures(*If the string parameter `s` is equal to "abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53", the integer result is equal to false.*);
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