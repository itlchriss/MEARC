package g0001_0100.s0013_roman_to_integer;

// #Easy #Top_Interview_Questions #String #Hash_Table #Math
// #2023_08_09_Time_2_ms_(100.00%)_Space_44.1_MB_(19.16%)

public class Solution {
	//@ requires(*1. The input string `s` is not null.*);
	//@ requires(*2. The input string `s` is a valid Roman numeral.*);
	//@ requires(*3. The length of the input string `s` is between 1 and 15.*);
	//@ ensures(*1. The method returns an integer value representing the conversion of the Roman numeral to an integer.*);
	//@ ensures(*2. The returned integer value is greater than or equal to 1 and less than or equal to 3999.*);
    public int romanToInt(String s) {
        int x = 0;
        char y;
        for (int i = 0; i < s.length(); i++) {
            y = s.charAt(i);
            switch (y) {
                case 'I':
                    x = getX(s, x, i, 1, 'V', 'X');
                    break;
                case 'V':
                    x += 5;
                    break;
                case 'X':
                    x = getX(s, x, i, 10, 'L', 'C');
                    break;
                case 'L':
                    x += 50;
                    break;
                case 'C':
                    x = getX(s, x, i, 100, 'D', 'M');
                    break;
                case 'D':
                    x += 500;
                    break;
                case 'M':
                    x += 1000;
                    break;
                default:
                    break;
            }
        }
        return x;
    }

    private int getX(String s, int x, int i, int i2, char v, char x2) {
        if (i + 1 == s.length()) {
            x += i2;
        } else if (s.charAt(i + 1) == v) {
            x -= i2;
        } else if (s.charAt(i + 1) == x2) {
            x -= i2;
        } else {
            x += i2;
        }
        return x;
    }
}