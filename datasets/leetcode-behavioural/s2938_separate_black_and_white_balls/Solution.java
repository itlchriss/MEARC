package g2901_3000.s2938_separate_black_and_white_balls;

// #Medium #String #Greedy #Two_Pointers #2024_01_03_Time_7_ms_(99.65%)_Space_45.1_MB_(16.43%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of the input string `s` is greater than or equal to 1.*);
//@ ensures(*The input string `s` consists of only binary digits ('0' and '1').*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a non-negative integer representing the minimum number of steps required to group all the black balls to the right and all the white balls to the left.*);
//@ ensures(*The output is the minimum number of steps needed to group the black balls to the right and the white balls to the left, based on the given input string `s`.*);
    public long minimumSteps(String s) {
        int left = 0;
        int right = s.length() - 1;
        long total = 0;
        while (left < right) {
            while (left < right && s.charAt(left) == '0') {
                left++;
            }
            while (left < right && s.charAt(right) == '1') {
                right--;
            }
            if (left < right) {
                total += (right - left);
                left++;
                right--;
            }
        }
        return total;
    }
}