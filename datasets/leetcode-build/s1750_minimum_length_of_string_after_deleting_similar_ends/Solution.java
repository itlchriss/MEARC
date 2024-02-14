package g1701_1800.s1750_minimum_length_of_string_after_deleting_similar_ends;

// #Medium #String #Two_Pointers #2022_04_30_Time_5_ms_(68.68%)_Space_53.9_MB_(15.47%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is greater than or equal to 1.*);
	//@ requires(*The input string `s` consists only of characters 'a', 'b', and 'c'.*);
	//@ ensures(*The output is an integer representing the minimum length of the string `s` after performing the algorithm any number of times.*);
	//@ ensures(*The output is non-negative.*);
	//@ ensures(*The output is less than or equal to the length of the input string `s`.*);
    public int minimumLength(String s) {
        int i = 0;
        int j = s.length() - 1;
        if (s.charAt(i) == s.charAt(j)) {
            while (i < j && s.charAt(i) == s.charAt(j)) {
                char c = s.charAt(i);
                i++;
                while (c == s.charAt(i) && i < j) {
                    i++;
                }
                j--;
                while (c == s.charAt(j) && i < j) {
                    j--;
                }
            }
        }
        return i <= j ? s.substring(i, j).length() + 1 : 0;
    }
}