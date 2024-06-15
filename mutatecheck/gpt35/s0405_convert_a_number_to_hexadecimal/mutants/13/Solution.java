package g0401_0500.s0405_convert_a_number_to_hexadecimal;

// #Easy #Math #Bit_Manipulation #2022_07_16_Time_1_ms_(71.02%)_Space_42.2_MB_(15.68%)

public class Solution {
//@ ensures num < 0 ==> \result.equals(Integer.toHexString(num + 4294967296));
//@ ensures \result != null;
//@ ensures num >= 0 ==> \result.equals(Integer.toHexString(num));
//@ ensures \result.matches("[0-9a-f]+");
//@ requires num >= -2147483648 && num <= 2147483647;
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
                sb.append((char) (x - 87));
            }
            num = num >>> 4;
        }
        return sb.reverse().toString();
    }
}
