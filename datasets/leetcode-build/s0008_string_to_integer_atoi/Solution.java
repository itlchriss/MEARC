package g0001_0100.s0008_string_to_integer_atoi;

// #Medium #Top_Interview_Questions #String #2024_01_04_Time_1_ms_(100.00%)_Space_42.7_MB_(8.86%)

public class Solution {
	//@ requires(*1. The input string `str` is not null.*);
	//@ requires(*2. The input string `str` is not empty.*);
	//@ requires(*3. The input string `str` consists of English letters (lower-case and upper-case), digits (`0-9`), `' '`, `'+'`, `'-'`, and `'.'`.*);
	//@ requires(*4. The input string `str` has a length less than or equal to 200.*);
	//@ ensures(*1. The method returns an integer value.*);
	//@ ensures(*2. The returned integer value is a 32-bit signed integer.*);
	//@ ensures(*3. The returned integer value represents the conversion of the input string `str` to an integer.*);
	//@ ensures(*4. The returned integer value is positive if the input string `str` does not contain a `'-'` or `'+'` character.*);
	//@ ensures(*5. The returned integer value is negative if the input string `str` contains a `'-'` character.*);
	//@ ensures(*6. The returned integer value is clamped to the range [-2^31, 2^31 - 1] if it is outside this range.*);
	//@ ensures(*7. If the input string `str` does not contain any digits, the returned integer value is 0.*);
	//@ ensures(*8. The method ignores any leading whitespace in the input string `str`.*);
	//@ ensures(*9. The method stops reading characters from the input string `str` when it encounters a non-digit character or reaches the end of the input string.*);
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int i = 0;
        boolean negetiveSign = false;
        char[] input = str.toCharArray();
        while (i < input.length && input[i] == ' ') {
            i++;
        }
        if (i == input.length) {
            return 0;
        } else if (input[i] == '+') {
            i++;
        } else if (input[i] == '-') {
            i++;
            negetiveSign = true;
        }
        int num = 0;
        while (i < input.length && input[i] <= '9' && input[i] >= '0') {
            // current char
            int tem = input[i] - '0';
            tem = negetiveSign ? -tem : tem;
            // avoid invalid number like 038
            if (num == 0 && tem == '0') {
                i++;
            } else if (num == Integer.MIN_VALUE / 10 && tem <= -8 || num < Integer.MIN_VALUE / 10) {
                return Integer.MIN_VALUE;
            } else if (num == Integer.MAX_VALUE / 10 && tem >= 7 || num > Integer.MAX_VALUE / 10) {
                return Integer.MAX_VALUE;
            } else {
                num = num * 10 + tem;
                i++;
            }
        }
        return num;
    }
}