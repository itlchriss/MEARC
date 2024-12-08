package g1701_1800.s1784_check_if_binary_string_has_at_most_one_segment_of_ones;

// #Easy #String #2022_04_30_Time_1_ms_(65.60%)_Space_42.6_MB_(11.20%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of the input string `s` is at least 1 and at most 100.*);
//@ ensures(*The first character of the input string `s` is '1'.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a boolean value indicating whether `s` contains at most one contiguous segment of ones.*);
//@ ensures(*If `s` contains at most one contiguous segment of ones, the method returns true.*);
//@ ensures(*If `s` does not contain at most one contiguous segment of ones, the method returns false.*);
    public boolean checkOnesSegment(String s) {
        boolean metOne = false;
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '1' && metOne) {
                return false;
            }
            if (s.charAt(i) == '1') {
                metOne = true;
                while (i < s.length() && s.charAt(i) == '1') {
                    i++;
                }
            }
            i++;
        }
        return true;
    }
}