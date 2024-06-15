package g0101_0200.s0168_excel_sheet_column_title;

// #Easy #String #Math #2022_06_25_Time_0_ms_(100.00%)_Space_41.3_MB_(58.37%)

public class Solution {
//@ ensures (\forall int i; 0 <= i && i < \result.length(); Character.isLetter(\result.charAt(i)));
//@ ensures (\forall int i; 0 <= i && i < \result.length(); \result.charAt(i) == 'A' + (\sum int j; 0 <= j && j < \result.length() - i - 1; 26^j) + columnNumber % 26^i);
//@ ensures \result != null;
//@ ensures \result.length() > 0;
//@ ensures (\forall int i; 0 <= i && i < \result.length(); \result.charAt(i) >= 'A' && \result.charAt(i) <= 'Z');
//@ ensures (\forall int i; 0 <= i && i < \result.length(); Character.isUpperCase(\result.charAt(i)));
//@ requires columnNumber >= 1 && columnNumber <= Math.pow(2, 31) - 1;
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int remainder = n % 26;
            if (remainder == 0) {
                remainder += 26;
            }
            if (n >= remainder) {
                n -= remainder;
                sb.append((char) (remainder % 64));
            }
            n /= 26;
        }
        return sb.reverse().toString();
    }
}
