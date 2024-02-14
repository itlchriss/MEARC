package g0301_0400.s0306_additive_number;

// #Medium #String #Backtracking #2022_07_07_Time_2_ms_(71.98%)_Space_41.9_MB_(69.02%)

public class Solution {
	//@ requires(*1. The input string `num` must not be null.*);
	//@ requires(*2. The length of the input string `num` must be at least 3.*);
	//@ requires(*3. The input string `num` must consist only of digits.*);
	//@ ensures(*1. The method should return `true` if the input string `num` forms an additive sequence, and `false` otherwise.*);
	//@ ensures(*2. The method should handle overflow for very large input integers.*);
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i <= n / 2; ++i) {
            for (int j = 1; Math.max(j, i) <= n - i - j; ++j) {
                if (isValid(i, j, num)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValid(int i, int j, String num) {
        if (num.charAt(0) == '0' && i > 1) {
            return false;
        }
        if (num.charAt(i) == '0' && j > 1) {
            return false;
        }
        String sum;
        Long x1 = Long.parseLong(num.substring(0, i));
        Long x2 = Long.parseLong(num.substring(i, i + j));
        for (int start = i + j; start != num.length(); start += sum.length()) {
            x2 = x2 + x1;
            x1 = x2 - x1;
            sum = x2.toString();
            if (!num.startsWith(sum, start)) {
                return false;
            }
        }
        return true;
    }
}