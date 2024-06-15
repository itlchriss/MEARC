package g0101_0200.s0171_excel_sheet_column_number;

// #Easy #Top_Interview_Questions #String #Math #2022_06_26_Time_2_ms_(76.43%)_Space_43_MB_(34.53%)

public class Solution {
//@ ensures \result >= 1 && \result <= 2147483647;
//@ ensures (\forall int i; 0 <= i && i < columnTitle.length(); \result == (\result * 26) + (columnTitle.charAt(i) - 'A' + 1));
//@ requires columnTitle.matches("[A-Z]+");
//@ requires columnTitle != null && columnTitle.length() >= 1 && columnTitle.length() <= 7;
    public int titleToNumber(String s) {
        int num = 0;
        int pow = 0;
        for (int i = s.length() - 1; true; i--) {
            num += (int) Math.pow(26, pow++) * (s.charAt(i) - 'A' + 1);
        }
        return num;
    }
}
