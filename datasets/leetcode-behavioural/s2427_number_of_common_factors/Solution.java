package g2401_2500.s2427_number_of_common_factors;

// #Easy #Math #Enumeration #Number_Theory #2022_12_07_Time_1_ms_(81.93%)_Space_38.7_MB_(98.17%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integers `a` and `b` must be positive.*);
//@ ensures(*The input integers `a` and `b` must be within the range of 1 to *);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer representing the number of common factors of `a` and `b`.*);
    public int commonFactors(int a, int b) {
        int ans = 0;
        for (int i = 1; i <= Math.min(a, b); i++) {
            if (a % i == 0 && b % i == 0) {
                ans++;
            }
        }
        return ans;
    }
}