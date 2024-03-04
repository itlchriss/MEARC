package g1101_1200.s1137_n_th_tribonacci_number;

// #Easy #Dynamic_Programming #Math #Memoization #Dynamic_Programming_I_Day_1
// #2023_06_01_Time_0_ms_(100.00%)_Space_39.6_MB_(48.37%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` must be a non-negative integer.*);
//@ ensures(*The input `n` must be less than or equal to 37.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer value.*);
//@ ensures(*The returned value should be the `n`-th Tribonacci number.*);
//@ ensures(*The returned value should be less than or equal to 2^31 - 1.*);
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n <= 2) {
            return 1;
        } else {
            int tn = 0;
            int tn1 = 1;
            int tn2 = 1;
            int tmp = 0;
            for (int i = 3; i <= n; i++) {
                tmp = tn + tn1 + tn2;
                tn = tn1;
                tn1 = tn2;
                tn2 = tmp;
            }
            return tmp;
        }
    }
}