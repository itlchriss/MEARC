package g0101_0200.s0168_excel_sheet_column_title;

// #Easy #String #Math #2022_06_25_Time_0_ms_(100.00%)_Space_41.3_MB_(58.37%)

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
//@ requires(*Input: columnNumber = 1*);
//@ requires(*Output: "A"*);
//@ requires(*Example 2:*);
//@ requires(*Input: columnNumber = 28*);
//@ requires(*Output: "AB"*);
//@ requires(*Example 3:*);
//@ requires(*Input: columnNumber = 701*);
//@ requires(*Output: "ZY"*);
//@ requires(*Example 4:*);
//@ requires(*Input: columnNumber = 2147483647*);
//@ requires(*Output: "FXSHRXW"*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= columnNumber <= 2<sup>31</sup> - 1</code>*);
//@ ensures(*Given an integer `columnNumber`, the result is its corresponding column title as it appears in an Excel sheet.*);
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