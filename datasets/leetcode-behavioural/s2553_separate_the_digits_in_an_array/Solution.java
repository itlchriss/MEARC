package g2501_2600.s2553_separate_the_digits_in_an_array;

// #Easy #Array #Simulation #2023_08_18_Time_3_ms_(92.00%)_Space_43.7_MB_(93.70%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `nums` is a positive integer.*);
//@ ensures(*Each element in the input array `nums` is less than or equal to 10^5.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `answer` is not null.*);
//@ ensures(*The length of the output array `answer` is equal to the sum of the number of digits in each element of the input array `nums`.*);
//@ ensures(*The elements in the output array `answer` are the digits of each integer in the input array `nums` after separating them in the same order they appear in `nums`.*);
    public int[] separateDigits(int[] nums) {
        StringBuilder str = new StringBuilder();
        for (int num : nums) {
            str.append(num);
        }
        int[] ar = new int[str.length()];
        String s = str.toString();
        for (int i = 0; i < s.length(); i++) {
            ar[i] = s.charAt(i) - '0';
        }
        return ar;
    }
}