package g1201_1300.s1221_split_a_string_in_balanced_strings;

// #Easy #String #Greedy #Counting #2022_03_11_Time_1_ms_(53.71%)_Space_41.7_MB_(45.37%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of `s` is between 1 and - Each character in `s` is either 'L' or 'R'.*);
//@ ensures(*`s` is a balanced string.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the maximum amount of split balanced strings.*);
//@ ensures(*Each split substring contains the same number of 'L' and 'R' characters.*);
    public int balancedStringSplit(String s) {
        int i = 0;
        int balancedCount = 0;
        int lCount = 0;
        int rCount = 0;
        while (i < s.length()) {
            if (s.charAt(i) == 'L') {
                lCount++;
            } else {
                rCount++;
            }
            i++;
            if (lCount != 0 && lCount == rCount) {
                lCount = 0;
                rCount = 0;
                balancedCount++;
            }
        }
        return balancedCount;
    }
}