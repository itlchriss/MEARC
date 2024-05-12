package g0101_0200.s0168_excel_sheet_column_title;

// #Easy #String #Math #2022_06_25_Time_0_ms_(100.00%)_Space_41.3_MB_(58.37%)

public class Solution {
//@ requires(*The integer parameter `columnNumber` is greater than or equal to 1 and is less than or equal to 2^31 - 1.*);
//@ ensures(*The string result is the corresponding column title of the integer parameter `columnNumber` as it appears in an Excel sheet.*);
//@ ensures(*The string result consists of uppercase letters only.*);
//@ ensures(*The string result is not null.*);
//@ ensures(*The string result length is less than or equal to 7.*);
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int remainder = n % 26;
            if (remainder == 0) {
                remainder += 26;
            }
            if (n >= remainder) {
                n -= remainder;
                sb.append((char) (remainder + 64));
            }
            n /= 26;
        }
        return sb.reverse().toString();
    }
}