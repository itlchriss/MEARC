package g0401_0500.s0423_reconstruct_original_digits_from_english;

// #Medium #String #Hash_Table #Math #2022_07_16_Time_6_ms_(89.85%)_Space_47.2_MB_(46.91%)

public class Solution {
//@ ensures(*The string parameter `s` must not be null.*);
//@ ensures(*The string parameter `s` must contain characters from the set `["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"]`.*);
//@ ensures(*The string result is the digits in ascending order extracted from the out-of-order English representation of digits in the string parameter `s`.*);
    public String originalDigits(String s) {
        int[] count = new int[26];
        int[] digits = new int[10];
        StringBuilder str = new StringBuilder();
        for (char c : s.toCharArray()) {
            ++count[c - 'a'];
        }
        digits[0] = count[25];
        digits[2] = count[22];
        digits[4] = count[20];
        digits[6] = count[23];
        digits[8] = count[6];
        digits[1] = count[14] - digits[0] - digits[2] - digits[4];
        digits[3] = count[7] - digits[8];
        digits[5] = count[5] - digits[4];
        digits[7] = count[18] - digits[6];
        digits[9] = count[8] - digits[5] - digits[6] - digits[8];
        for (int i = 0; i < 10; i++) {
            while (digits[i]-- != 0) {
                str.append((char) (i + 48));
            }
        }
        return str.toString();
    }
}