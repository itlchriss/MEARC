package g0401_0500.s0405_convert_a_number_to_hexadecimal;

// #Easy #Math #Bit_Manipulation #2022_07_16_Time_1_ms_(71.02%)_Space_42.2_MB_(15.68%)

public class Solution {
//@ requires(*The integer parameter `num` is less than or equal to 2147483647 and is greater than or equal to -2147483648.*);
//@ ensures(*The string result consists of only lowercase hexadecimal digits.*);
//@ ensures(*The string result does not contain leading zeros unless the string result is equal to "0".*);
//@ ensures(*If the integer parameter `num` is equal to 26, the string result is equal to "1a".*);
//@ ensures(*If the integer parameter `num` is equal to -1, the string result is equal to "ffffffff".*);
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int x;
        while (num != 0) {
            x = num & 0xf;
            if (x < 10) {
                sb.append(x);
            } else {
                sb.append((char) (x + 87));
            }
            num = num >>> 4;
        }
        return sb.reverse().toString();
    }
}