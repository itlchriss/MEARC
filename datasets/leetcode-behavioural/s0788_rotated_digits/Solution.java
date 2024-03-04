package g0701_0800.s0788_rotated_digits;

// #Medium #Dynamic_Programming #Math #2022_03_26_Time_2_ms_(98.95%)_Space_39.6_MB_(87.35%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is a positive integer.*);
//@ ensures(*The input `n` is less than or equal to 10^4.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of "good" integers in the range [1, n].*);
//@ ensures(*The returned integer is non-negative.*);
//@ ensures(*The returned integer is less than or equal to n.*);
    public int rotatedDigits(int n) {
        int[] flag = new int[n + 1];
        flag[0] = 2;
        int[] indexesValueTwo = {1, 8};
        for (int value : indexesValueTwo) {
            if (n >= value) {
                flag[value] = 2;
            }
        }
        int[] indexesValueOne = {2, 5, 6, 9};
        for (int value : indexesValueOne) {
            if (n >= value) {
                flag[value] = 1;
            }
        }
        int rs = 0;
        for (int i = 1; i <= n; i++) {
            int residual = i % 10;
            if (flag[residual] != 0) {
                if ((residual == 1 || residual == 0 || residual == 8) && (flag[i / 10] == 2)) {
                    flag[i] = 2;
                    continue;
                }
                if (flag[i / 10] != 0) {
                    flag[i] = 1;
                    rs++;
                }
            }
        }
        return rs;
    }
}