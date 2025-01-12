package g0101_0200.s0171_excel_sheet_column_number;

// #Easy #Top_Interview_Questions #String #Math #2022_06_26_Time_2_ms_(76.43%)_Space_43_MB_(34.53%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 7 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of only uppercase English letters.*);
//@ ensures(*The integer result is greater than or equal to 1 and is less than or equal to 2147483647.*);
//@ ensures(*If the string parameter `s` is equal to "A", the integer result is equal to 1.*);
//@ ensures(*If the string parameter `s` is equal to "AB", the integer result is equal to 28.*);
//@ ensures(*If the string parameter `s` is equal to "ZY", the integer result is equal to 701.*);
//@ ensures(*If the string parameter `s` is equal to "FXSHRXW", the integer result is equal to 2147483647.*);
    public int titleToNumber(String s) {
        int num = 0;
        int pow = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            num += (int) Math.pow(26, pow++) * (s.charAt(i) - 'A' + 1);
        }
        return num;
    }
}