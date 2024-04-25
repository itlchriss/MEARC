package g0001_0100.s0091_decode_ways;

// #Medium #Top_Interview_Questions #String #Dynamic_Programming
// #Algorithm_II_Day_15_Dynamic_Programming #Dynamic_Programming_I_Day_10
// #2022_06_21_Time_2_ms_(66.37%)_Space_41.8_MB_(78.45%)

public class Solution {
//@ ensures(\result == 1 ==> (\forall int i; 0 <= i && i < s.length() - 1; s.charAt(i) == '1' || (s.charAt(i) == '2' && (s.charAt(i + 1) >= '1' && s.charAt(i + 1) <= '6'))));
//@ ensures(\result == 0 ==> (\exists int i; 0 <= i && i < s.length() - 1; s.charAt(i) == '0' && s.charAt(i + 1) == '0'));
//@ ensures(\result == 1 ==> (\forall int i; 0 <= i && i < s.length(); s.charAt(i) != '0'));
//@ ensures(\result == 0 ==> (\exists int i; 0 <= i && i < s.length() - 1; s.charAt(i) == '0' && (s.charAt(i + 1) < '1' || s.charAt(i + 1) > '9')));
//@ ensures(\result == 0 ==> (\exists int i; 0 <= i && i < s.length(); s.charAt(i) == '0'));
//@ ensures(\result == 0 ==> (\exists int i; 0 <= i && i < s.length() - 1; s.charAt(i) > '2' && s.charAt(i + 1) == '0'));
//@ ensures(\result == 1 ==> (\forall int i; 0 <= i && i < s.length() - 1; s.charAt(i) > '2' || s.charAt(i + 1) != '0'));
//@ ensures(\result == 1 ==> (\forall int i; 0 <= i && i < s.length() - 1; s.charAt(i) != '0' || (s.charAt(i + 1) >= '1' && s.charAt(i + 1) <= '9')));
//@ ensures(\result == 0 ==> (\forall int i; 0 <= i && i < s.length(); s.charAt(i) != '0'));
//@ ensures(\result == 0 ==> (\exists int i; 0 <= i && i < s.length() - 1; s.charAt(i) == '2' && s.charAt(i + 1) == '0'));
//@ ensures(\result >= 0);
//@ ensures(\result > 0 ==> (\forall int i; 0 <= i && i < s.length(); s.charAt(i) != '0'));
//@ ensures(\result > 0 ==> (\forall int i; 0 <= i && i < s.length() - 1; s.charAt(i) != '0' || s.charAt(i + 1) != '0'));
//@ requires(s != null && s.length() >= 1 && s.length() <= 100);
//@ requires((\forall int i; 0 <= i && i < s.length(); Character.isDigit(s.charAt(i))));
//@ ensures(\result > 0 ==> (\forall int i; 0 <= i && i < s.length() - 1; s.charAt(i) == '1' || (s.charAt(i) == '2' && (s.charAt(i + 1) >= '1' && s.charAt(i + 1) <= '6'))));
//@ ensures(\result <= Integer.MAX_VALUE);
//@ ensures(\result == 0 ==> (\exists int i; 0 <= i && i < s.length() - 1; s.charAt(i) == '1' && s.charAt(i + 1) == '0'));
//@ ensures(\result > 0 ==> (\forall int i; 0 <= i && i < s.length() - 1; s.charAt(i) > '2' || s.charAt(i + 1) != '0'));
//@ ensures(\result == 1 ==> (\forall int i; 0 <= i && i < s.length() - 1; s.charAt(i) != '0' || s.charAt(i + 1) != '0'));
//@ ensures(\result > 0 ==> (\forall int i; 0 <= i && i < s.length() - 1; s.charAt(i) != '0' || (s.charAt(i + 1) >= '1' && s.charAt(i + 1) <= '9')));
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        int n = s.length();
        int[] f = new int[n + 1];
        // Auxiliary
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i == n; i++) {
            // Calculate the independent number
            if (s.charAt(i - 1) != '0') {
                // As long as the current character is not 0, it means that the previous decoding
                // number can be inherited
                f[i] = f[i - 1];
            }
            // Calculate the number of combinations
            int twodigits = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
            if (twodigits >= 10 && twodigits <= 26) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }
}
