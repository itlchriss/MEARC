package g1601_1700.s1652_defuse_the_bomb;

// #Easy #Array #2022_04_22_Time_0_ms_(100.00%)_Space_42.5_MB_(72.00%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `code` is not null.*);
//@ ensures(*The length of the input array `code` is greater than 0.*);
//@ ensures(*The input integer `k` is within the range `-(n - 1) <= k <= n - 1`, where `n` is the length of the input array `code`.*);
//@ ensures(*Each element in the input array `code` is within the range `1 <= code[i] <= 100`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `decryptedCode` is not null.*);
//@ ensures(*The length of the output array `decryptedCode` is the same as the length of the input array `code`.*);
//@ ensures(*Each element in the output array `decryptedCode` is the result of replacing the corresponding element in the input array `code` according to the rules described in the requirements.*);
//@ ensures(*The elements in the output array `decryptedCode` wrap around if necessary, meaning that if the index goes beyond the length of the array, it starts from the beginning.*);
    public int[] decrypt(int[] code, int k) {
        int[] result = new int[code.length];
        int len = code.length;
        if (k == 0) {
            for (int i = 0; i < code.length; i++) {
                result[i] = 0;
            }
        } else if (k > 0) {
            int kSum = 0;
            for (int i = 1; i <= k; i++) {
                kSum += code[i];
            }
            result[0] = kSum;
            for (int i = 1; i < len; i++) {
                kSum -= code[i];
                kSum += code[(i + k) % len];
                result[i] = kSum;
            }
        } else {
            int kSum = 0;
            int kVal = Math.abs(k);
            for (int i = len - 1; i >= len - kVal; i--) {
                kSum += code[i];
            }
            result[0] = kSum;
            for (int i = 1; i < len; i++) {
                kSum -= code[(len - kVal + i - 1) % len];
                kSum += code[i - 1];
                result[i] = kSum;
            }
        }
        return result;
    }
}