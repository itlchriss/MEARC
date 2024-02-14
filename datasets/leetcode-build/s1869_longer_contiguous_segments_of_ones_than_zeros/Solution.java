package g1801_1900.s1869_longer_contiguous_segments_of_ones_than_zeros;

// #Easy #String #2022_05_10_Time_1_ms_(88.10%)_Space_41.8_MB_(66.14%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is greater than or equal to 1.*);
	//@ requires(*The characters in the input string `s` are either '0' or '1'.*);
	//@ ensures(*The method returns a boolean value indicating whether the longest contiguous segment of '1's is strictly longer than the longest contiguous segment of '0's in the input string `s`.*);
	//@ ensures(*If there are no '0's in the input string `s`, the longest contiguous segment of '0's is considered to have a length of 0.*);
	//@ ensures(*If there are no '1's in the input string `s`, the longest contiguous segment of '1's is considered to have a length of 0.*);
    public boolean checkZeroOnes(String s) {
        int zeroes = 0;
        int ones = 0;
        int i = 0;
        while (i < s.length()) {
            int start = i;
            while (i < s.length() && s.charAt(i) == '0') {
                i++;
            }
            if (i > start) {
                zeroes = Math.max(zeroes, i - start);
            }
            start = i;
            while (i < s.length() && s.charAt(i) == '1') {
                i++;
            }
            if (i > start) {
                ones = Math.max(ones, i - start);
            }
        }
        return ones > zeroes;
    }
}