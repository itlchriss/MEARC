package g0101_0200.s0171_excel_sheet_column_number;

// #Easy #Top_Interview_Questions #String #Math #2022_06_26_Time_2_ms_(76.43%)_Space_43_MB_(34.53%)

public class Solution {
	//@ requires(*1. The input string `columnTitle` is not null.*);
	//@ requires(*2. The input string `columnTitle` is not empty.*);
	//@ requires(*3. The input string `columnTitle` consists only of uppercase English letters.*);
	//@ requires(*4. The length of the input string `columnTitle` is between 1 and 7 (inclusive).*);
	//@ requires(*5. The input string `columnTitle` is within the range of "A" to "FXSHRXW".*);
	//@ ensures(*1. The method returns an integer value representing the corresponding column number of the input string `columnTitle`.*);
	//@ ensures(*2. The returned column number is greater than or equal to 1.*);
	//@ ensures(*3. The returned column number is less than or equal to 2147483647.*);
    public int titleToNumber(String s) {
        int num = 0;
        int pow = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            num += (int) Math.pow(26, pow++) * (s.charAt(i) - 'A' + 1);
        }
        return num;
    }
}