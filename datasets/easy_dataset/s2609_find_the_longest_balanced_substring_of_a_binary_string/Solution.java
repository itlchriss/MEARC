package g2601_2700.s2609_find_the_longest_balanced_substring_of_a_binary_string;

// #Easy #String #2023_08_30_Time_1_ms_(100.00%)_Space_42.5_MB_(18.86%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input string `s` is not null.*);
//@ ensures(*2. The length of the input string `s` is between 1 and 50, inclusive.*);
//@ ensures(*3. The characters in the input string `s` are either '0' or '1'.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The returned value is an integer representing the length of the longest balanced substring of `s`.*);
//@ ensures(*2. The returned value is greater than or equal to 0.*);
//@ ensures(*3. If there is no balanced substring in `s`, the returned value is 0.*);
//@ ensures(*4. The returned value is the length of the longest substring in `s` where all zeroes are before ones and the number of zeroes is equal to the number of ones inside the substring.*);
    public int findTheLongestBalancedSubstring(String s) {
        char[] chars = s.toCharArray();
        int max = 0;
        int n = chars.length;
        int zero = 0;
        int one = 0;
        int i = 0;
        while (i < n) {
            if (chars[i] == '0') {
                zero++;
            } else {
                while (i < n) {
                    if (chars[i++] == '1') {
                        one++;
                    } else {
                        i--;
                        break;
                    }
                }
                max = Math.max(max, 2 * Math.min(one, zero));
                zero = 1;
                one = 0;
            }
            i++;
        }

        return max;
    }
}