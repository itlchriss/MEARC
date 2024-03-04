package g0101_0200.s0171_excel_sheet_column_number;

// #Easy #Top_Interview_Questions #String #Math #2022_06_26_Time_2_ms_(76.43%)_Space_43_MB_(34.53%)

public class Solution {
//@ ensures(*The string parameter `s` must not be null.*);
//@ ensures(*The integer result is the corresponding column number of the string parameter `s` based on the Excel sheet column title mapping.*);
//@ ensures(*The integer result is within the range of [1, 2147483647].*);
//@ ensures(*The string parameter `s` consists only of uppercase English letters.*);
//@ ensures(*The length of the string parameter `s` is between 1 and 7 characters.*);
    public int titleToNumber(String s) {
        int num = 0;
        int pow = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            num += (int) Math.pow(26, pow++) * (s.charAt(i) - 'A' + 1);
        }
        return num;
    }
}