package g2201_2300.s2278_percentage_of_letter_in_string;

// #Easy #String #2022_06_18_Time_0_ms_(100.00%)_Space_40.1_MB_(95.65%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The input string `s` is not empty.*);
//@ ensures(*The input string `s` consists of lowercase English letters.*);
//@ ensures(*The input character `letter` is a lowercase English letter.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the percentage of characters in `s` that equal `letter`, rounded down to the nearest whole percent.*);
    public int percentageLetter(String s, char letter) {
        int count = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == letter) {
                ++count;
            }
        }
        return (count * 100) / (n);
    }
}