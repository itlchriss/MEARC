package g0101_0200.s0171_excel_sheet_column_number;

// #Easy #Top_Interview_Questions #String #Math #2022_06_26_Time_2_ms_(76.43%)_Space_43_MB_(34.53%)

public class Solution {
//@ requires(*For example:*);
//@ requires(*A -> 1*);
//@ requires(*B -> 2*);
//@ requires(*C -> 3*);
//@ requires(*...*);
//@ requires(*Z -> 26*);
//@ requires(*AA -> 27*);
//@ requires(*AB -> 28*);
//@ requires(*...*);
//@ requires(*Example 1:*);
//@ requires(*Input: columnTitle = "A"*);
//@ requires(*Output: 1*);
//@ requires(*Example 2:*);
//@ requires(*Input: columnTitle = "AB"*);
//@ requires(*Output: 28*);
//@ requires(*Example 3:*);
//@ requires(*Input: columnTitle = "ZY"*);
//@ requires(*Output: 701*);
//@ requires(*Example 4:*);
//@ requires(*Input: columnTitle = "FXSHRXW"*);
//@ requires(*Output: 2147483647*);
//@ requires(*Constraints:*);
//@ requires(*`1 <= columnTitle.length <= 7`*);
//@ requires(*`columnTitle` consists only of uppercase English letters.*);
//@ requires(*`columnTitle` is in the range `["A", "FXSHRXW"]`.*);
//@ ensures(*Given a string `columnTitle` that represents the column title as appear in an Excel sheet, the result is its corresponding column number.*);
    public int titleToNumber(String s) {
        int num = 0;
        int pow = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            num += (int) Math.pow(26, pow++) * (s.charAt(i) - 'A' + 1);
        }
        return num;
    }
}