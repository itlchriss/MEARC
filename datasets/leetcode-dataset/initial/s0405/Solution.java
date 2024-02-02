package g0401_0500.s0405_convert_a_number_to_hexadecimal;

// #Easy #Math #Bit_Manipulation #2022_07_16_Time_1_ms_(71.02%)_Space_42.2_MB_(15.68%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given an integer `num`, return _a string representing its hexadecimal representation_. For negative integers, [two’s complement](https://en.wikipedia.org/wiki/Two%27s_complement) method is used.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
