package g1401_1500.s1422_maximum_score_after_splitting_a_string;

// #Easy #String #2022_03_28_Time_1_ms_(96.45%)_Space_42_MB_(57.42%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is at least 2.*);
	//@ requires(*The input string `s` consists only of characters '0' and '1'.*);
	//@ ensures(*The method returns an integer value representing the maximum score after splitting the string into two non-empty substrings.*);
	//@ ensures(*The maximum score is calculated by counting the number of zeros in the left substring and the number of ones in the right substring.*);
	//@ ensures(*The left substring and right substring are non-empty.*);
	//@ ensures(*The left substring and right substring are valid substrings of the input string `s`.*);
	//@ ensures(*The left substring and right substring are contiguous substrings of the input string `s`.*);
	//@ ensures(*The left substring and right substring do not overlap.*);
	//@ ensures(*The maximum score is the highest possible score among all possible ways of splitting the string into two non-empty substrings.*);
    public int maxScore(String s) {
        int zeroes = s.charAt(0) == '0' ? 1 : 0;
        int ones = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ones++;
            }
        }
        int maxScore = zeroes + ones;
        for (int i = 1; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                zeroes++;
            } else {
                ones--;
            }
            maxScore = Math.max(maxScore, zeroes + ones);
        }
        return maxScore;
    }
}