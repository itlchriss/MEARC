package g1001_1100.s1006_clumsy_factorial;

// #Medium #Math #Stack #Simulation #2022_02_21_Time_1_ms_(87.97%)_Space_41.9_MB_(13.29%)

public class Solution {
    private int m = 1;
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is a positive integer.*);
//@ ensures(*`n` is less than or equal to 10,000.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer.*);
//@ ensures(*The returned integer is the clumsy factorial of `n`.*);

    public int clumsy(int n) {
        int num;
        if (n >= 4) {
            num = m * n * (n - 1) / (n - 2) + (n - 3);
        } else if (n == 3) {
            num = m * n * (n - 1) / (n - 2);
        } else if (n == 2) {
            num = m * n * (n - 1);
        } else if (n == 1) {
            num = m * n;
        } else {
            return 0;
        }
        m = -1;
        return num + clumsy(n - 4);
    }
}