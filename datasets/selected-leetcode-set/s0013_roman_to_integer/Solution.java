package g0001_0100.s0013_roman_to_integer;

// #Easy #Top_Interview_Questions #String #Hash_Table #Math
// #2023_08_09_Time_2_ms_(100.00%)_Space_44.1_MB_(19.16%)

public class Solution {
//@ requires(*The string parameter `s` contains only the characters `('I', 'V', 'X', 'L', 'C', 'D', 'M')`.*);
//@ requires(*The length of the string parameter `s` is greater than or equal to 1 and is less than or equal to 15.*);
//@ ensures(*The integer result is less than or equal to 3999 and is greater than or equal to 1.*);
//@ ensures(*If the string parameter `s` is equal to "III", the integer result is equal to 3.*);
//@ ensures(*If the string parameter `s` is equal to "IV", the integer result is equal to 4.*);
//@ ensures(*If the string parameter `s` is equal to "IX", the integer result is equal to 9.*);
//@ ensures(*If the string parameter `s` is equal to "LVIII", the integer result is equal to 58.*);
//@ ensures(*If the string parameter `s` is equal to "MCMXCIV", the integer result is equal to 1994.*);
    public int romanToInt(String s) {
        int x = 0;
        char y;
        //@ loop_invariant 0 <= i <= s.length();
        for (int i = 0; i < s.length(); i++) {
            y = s.charAt(i);
            switch (y) {
                case 'I':
                    x = getX(s, x, i, 1, 'V', 'X');
                    break;
                case 'V':
                    //@ assume Integer.MIN_VALUE + 1 <= x + 5 <= Integer.MAX_VALUE - 1;
                    x += 5;
                    break;
                case 'X':
                    x = getX(s, x, i, 10, 'L', 'C');
                    break;
                case 'L':
                    //@ assume Integer.MIN_VALUE + 1 <= x + 50 <= Integer.MAX_VALUE - 1;
                    x += 50;
                    break;
                case 'C':
                    x = getX(s, x, i, 100, 'D', 'M');
                    break;
                case 'D':
                    //@ assume Integer.MIN_VALUE + 1 <= x + 500 <= Integer.MAX_VALUE - 1;
                    x += 500;
                    break;
                case 'M':
                    //@ assume Integer.MIN_VALUE + 1 <= x + 1000 <= Integer.MAX_VALUE - 1;                    
                    x += 1000;
                    break;
                default:
                    break;
            }
        }
        return x;
    }

    //@ requires 0 <= i < s.length();
    //@ requires i2 == 1 || i2 == 10 || i2 == 100;
    private int getX(String s, int x, int i, int i2, char v, char x2) {
        if (i + 1 == s.length()) {
            //@ assume Integer.MIN_VALUE + 1 <= x + i2 <= Integer.MAX_VALUE - 1;
            x += i2;
        } else if (s.charAt(i + 1) == v) {
            //@ assume Integer.MIN_VALUE + 1 <= x - i2 <= Integer.MAX_VALUE - 1;
            x -= i2;
        } else if (s.charAt(i + 1) == x2) {
            //@ assume Integer.MIN_VALUE + 1 <= x - i2 <= Integer.MAX_VALUE - 1;
            x -= i2;
        } else {
            //@ assume Integer.MIN_VALUE + 1 <= x + i2 <= Integer.MAX_VALUE - 1;
            x += i2;
        }
        return x;
    }
}