package g0101_0200.s0171_excel_sheet_column_number;

// #Easy #Top_Interview_Questions #String #Math #2022_06_26_Time_2_ms_(76.43%)_Space_43_MB_(34.53%)

public class Solution {
//@ requires(*The string parameter `columnTitle` consists only of uppercase English letters.*);
//@ requires(*The length of the string parameter `columnTitle` is greater than or equal to 1 and is less than or equal to 7.*);
//@ ensures(*The integer result is the corresponding column number of the string parameter `columnTitle`.*);
//@ ensures(*The integer result is greater than or equal to 1 and is less than or equal to 2147483647.*);
    public int titleToNumber(String s) {
        int num = 0;
        int pow = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            num += (int) Math.pow(26, pow++) * (s.charAt(i) - 'A' + 1);
        }
        return num;
    }
}