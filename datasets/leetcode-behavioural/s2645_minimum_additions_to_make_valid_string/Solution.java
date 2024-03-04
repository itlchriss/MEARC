package g2601_2700.s2645_minimum_additions_to_make_valid_string;

// #Medium #String #Dynamic_Programming #Greedy #Stack
// #2023_09_06_Time_1_ms_(100.00%)_Space_41.8_MB_(38.93%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `word` is not null.*);
//@ ensures(*The length of `word` is between 1 and 50 (inclusive).*);
//@ ensures(*`word` consists only of the letters "a", "b", and "c".*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of letters that must be inserted to make `word` valid.*);
//@ ensures(*The modified `word` is valid, meaning it can be formed by concatenating the string "abc" several times.*);
    public int addMinimum(String word) {
        int res = 0;
        char last = word.charAt(0);
        res += word.charAt(0) - 'a';
        for (int i = 1; i < word.length(); i++) {
            char curr = word.charAt(i);
            if (curr > last) {
                res += curr - last - 1;
            } else {
                res += curr - last + 2;
            }
            last = curr;
        }
        res += 'c' - last;
        return res;
    }
}