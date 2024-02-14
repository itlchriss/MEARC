package g1701_1800.s1758_minimum_changes_to_make_alternating_binary_string;

// #Easy #String #2022_05_01_Time_2_ms_(98.92%)_Space_43.5_MB_(18.77%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is greater than or equal to 1.*);
	//@ requires(*The characters in the input string `s` are either '0' or '1'.*);
	//@ ensures(*The output is an integer representing the minimum number of operations needed to make the string `s` alternating.*);
    public int minOperations(String s) {
        return Math.min(countFlips(s, '0'), countFlips(s, '1'));
    }

    private int countFlips(String s, char next) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (next != c) {
                count++;
            }
            next = (next == '0') ? '1' : '0';
        }
        return count;
    }
}