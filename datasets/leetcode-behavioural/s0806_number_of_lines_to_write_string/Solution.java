package g0801_0900.s0806_number_of_lines_to_write_string;

// #Easy #Array #String #2022_03_23_Time_1_ms_(65.23%)_Space_42.1_MB_(53.96%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The `widths` array must have a length of 26.*);
//@ ensures(*Each element in the `widths` array must be between 2 and 10 (inclusive).*);
//@ ensures(*The `s` string must have a length between 1 and 1000 (inclusive).*);
//@ ensures(*The `s` string must only contain lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned array `result` must have a length of 2.*);
//@ ensures(*The first element of the `result` array (`result[0]`) represents the total number of lines used to write the string `s`.*);
//@ ensures(*The second element of the `result` array (`result[1]`) represents the width of the last line in pixels.*);
    public int[] numberOfLines(int[] widths, String s) {
        int count = 0;
        int line = 0;
        int i = 0;
        while (i < s.length()) {
            count += widths[s.charAt(i) - 'a'];
            if (count == 100) {
                line++;
                count = 0;
            }
            if (count > 100) {
                line++;
                i--;
                count = 0;
            }
            i++;
        }
        if (count > 0 && count < 100) {
            line++;
        }
        if (count == 0) {
            count = 100;
        }
        return new int[] {line, count};
    }
}