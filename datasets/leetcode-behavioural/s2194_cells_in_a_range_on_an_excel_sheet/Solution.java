package g2101_2200.s2194_cells_in_a_range_on_an_excel_sheet;

// #Easy #String #2022_06_06_Time_1_ms_(99.92%)_Space_42.6_MB_(99.23%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `str` must be in the format `"<col1><row1>:<col2><row2>"`.*);
//@ ensures(*The column letters in `str` must be uppercase alphabetical letters.*);
//@ ensures(*The row numbers in `str` must be integers.*);
//@ ensures(*The value of `r1` in `str` must be less than or equal to the value of `r2`.*);
//@ ensures(*The value of `c1` in `str` must be less than or equal to the value of `c2`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return a list of cells `(x, y)` such that `r1 <= x <= r2` and `c1 <= y <= c2`.*);
//@ ensures(*The cells in the list should be represented as strings in the format `"<col><row>"`.*);
//@ ensures(*The cells in the list should be sorted in non-decreasing order first by columns and then by rows.*);
    public List<String> cellsInRange(String str) {
        char[] c = str.toCharArray();
        List<String> strings = new ArrayList<>();
        for (char i = c[0]; i <= c[3]; i++) {
            for (char j = c[1]; j <= c[4]; j++) {
                strings.add(new String(new char[] {i, j}));
            }
        }
        return strings;
    }
}