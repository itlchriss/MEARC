package g2201_2300.s2243_calculate_digit_sum_of_a_string;

// #Easy #String #Simulation #2022_06_11_Time_1_ms_(91.46%)_Space_42.3_MB_(29.50%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The length of the string `s` must be greater than `k`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The string `s` is divided into consecutive groups of size `k`.*);
//@ ensures(*Each group of `s` is replaced with a string representing the sum of all its digits.*);
//@ ensures(*The consecutive groups are merged together to form a new string.*);
//@ ensures(*If the length of the new string is greater than `k`, repeat the process from step 1.*);
//@ ensures(*Return the final string `s` after all rounds have been completed.*);
    public String digitSum(String s, int k) {
        while (s.length() > k) {
            int n = s.length();
            int count = 0;
            int sum = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (count == k) {
                    sb.append(sum);
                    sum = 0;
                    count = 0;
                }
                sum += s.charAt(i) - '0';
                count++;
            }
            sb.append(sum);
            s = sb.toString();
        }
        return s;
    }
}