package g2701_2800.s2716_minimize_string_length;

// #Easy #String #Hash_Table #2023_09_15_Time_3_ms_(100.00%)_Space_43.3_MB_(98.28%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of the input string `s` is between 1 and 100 (inclusive).*);
//@ ensures(*The input string `s` contains only lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer denoting the length of the minimized string.*);
//@ ensures(*The length of the minimized string is between 1 and the length of the input string `s` (inclusive).*);
//@ ensures(*The minimized string is obtained by repeatedly performing the operation of deleting the closest occurrence of a character to the left and right of a chosen index in the string.*);
//@ ensures(*After performing the operation any number of times, the minimized string cannot be further reduced in length.*);
    public int minimizedStringLength(String s) {
        int[] arr = new int[26];
        int count = 0;
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }
        for (int n : arr) {
            if (n != 0) {
                count++;
            }
        }
        return count;
    }
}