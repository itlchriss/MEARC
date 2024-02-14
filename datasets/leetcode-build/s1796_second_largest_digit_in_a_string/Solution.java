package g1701_1800.s1796_second_largest_digit_in_a_string;

// #Easy #String #Hash_Table #2022_04_25_Time_2_ms_(87.67%)_Space_42.1_MB_(81.91%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is between 1 and 500.*);
	//@ requires(*The input string `s` consists of only lowercase English letters and/or digits.*);
	//@ ensures(*The method returns an integer value.*);
	//@ ensures(*If there is a second largest numerical digit in the input string `s`, the method returns that digit.*);
	//@ ensures(*If there is no second largest numerical digit in the input string `s`, the method returns -1.*);
    public int secondHighest(String s) {
        int largest = -1;
        int sl = -1;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                int n = ch - '0';
                if (n > largest) {
                    sl = largest;
                    largest = n;
                } else if (n > sl && n < largest) {
                    sl = n;
                }
            }
        }
        return sl;
    }
}