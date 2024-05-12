package g0401_0500.s0405_convert_a_number_to_hexadecimal;

// #Easy #Math #Bit_Manipulation #2022_07_16_Time_1_ms_(71.02%)_Space_42.2_MB_(15.68%)

public class Solution {
//@ ensures(*If the integer parameter `num` is positive, the string result represents the hexadecimal representation of the integer parameter `num`.*);
//@ ensures(*If the integer parameter `num` is negative, the string result represents the two's complement hexadecimal representation of the integer parameter `num`.*);
//@ ensures(*All letters in the string result are lowercase characters.*);
//@ ensures(*There are no leading zeros in the string result except for the zero itself.*);
//@ ensures(*The length of the string result is less than or equal to 8.*);
//@ ensures(*The string result is equal to "1a" if the integer parameter `num` is equal to 26.*);
//@ ensures(*The string result is equal to "ffffffff" if the integer parameter `num` is equal to -1.*);
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