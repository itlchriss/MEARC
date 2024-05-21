package g0401_0500.s0405_convert_a_number_to_hexadecimal;

// #Easy #Math #Bit_Manipulation #2022_07_16_Time_1_ms_(71.02%)_Space_42.2_MB_(15.68%)

public class Solution {
//@ requires(*For negative integers, [two’s complement](https://en.wikipedia.org/wiki/Two%27scomplement) method is used.*);
//@ requires(*All the letters in the answer string should be lowercase characters, and there should not be any leading zeros in the answer except for the zero itself.*);
//@ requires(*Note: You are not allowed to use any built-in library method to directly solve this problem.*);
//@ requires(*Example 1:*);
//@ requires(*Input: num = 26*);
//@ requires(*Output: "1a"*);
//@ requires(*Example 2:*);
//@ requires(*Input: num = -1*);
//@ requires(*Output: "ffffffff"*);
//@ requires(*Constraints:*);
//@ requires(*<code>-2<sup>31</sup> <= num <= 2<sup>31</sup> - 1</code>*);
//@ ensures(*Given an integer param_num, the result is a string representing its hexadecimal representation.*);
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