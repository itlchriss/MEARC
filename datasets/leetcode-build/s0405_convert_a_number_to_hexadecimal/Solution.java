package g0401_0500.s0405_convert_a_number_to_hexadecimal;

// #Easy #Math #Bit_Manipulation #2022_07_16_Time_1_ms_(71.02%)_Space_42.2_MB_(15.68%)

public class Solution {
	//@ requires(*The input `num` is an integer.*);
	//@ ensures(*The output is a string representing the hexadecimal representation of `num`.*);
	//@ ensures(*The output string contains only lowercase characters.*);
	//@ ensures(*The output string does not have any leading zeros, except for the case when `num` is zero itself.*);
	//@ ensures(*For negative integers, the two's complement method is used to determine the hexadecimal representation.*);
	//@ ensures(*The output string represents the hexadecimal value of `num` in two's complement form.*);
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