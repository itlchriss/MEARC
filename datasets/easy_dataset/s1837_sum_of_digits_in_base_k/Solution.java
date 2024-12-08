package g1801_1900.s1837_sum_of_digits_in_base_k;

// #Easy #Math #2022_05_07_Time_1_ms_(10.42%)_Space_38.9_MB_(91.55%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is an integer in base 10.*);
//@ ensures(*The input `k` is an integer representing the base to convert `n` to.*);
//@ ensures(*`n` is greater than or equal to 1 and less than or equal to 100.*);
//@ ensures(*`k` is greater than or equal to 2 and less than or equal to 10.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the sum of the digits of `n` after converting it from base 10 to base `k`.*);
//@ ensures(*Each digit in the converted number should be interpreted as a base 10 number.*);
//@ ensures(*The sum should be returned in base 10.*);
    public int sumBase(int n, int k) {
        String str = Integer.toString(Integer.parseInt(n + "", 10), k);
        int sum = 0;
        for (char c : str.toCharArray()) {
            sum += Character.getNumericValue(c);
        }
        return sum;
    }
}