package g1901_2000.s1967_number_of_strings_that_appear_as_substrings_in_word;

// #Easy #String #2022_06_19_Time_1_ms_(82.02%)_Space_42.5_MB_(54.47%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `patterns` is not null.*);
//@ ensures(*The input string `word` is not null.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of strings in `patterns` that exist as a substring in `word`.*);
//@ ensures(*The returned integer is greater than or equal to 0.*);
//@ ensures(*The returned integer is less than or equal to the length of `patterns`.*);
//@ ensures(*The method does not modify the input arrays `patterns` and `word`.*);
    public int numOfStrings(String[] patterns, String word) {
        int res = 0;
        for (String p : patterns) {
            if (word.contains(p)) {
                res++;
            }
        }
        return res;
    }
}