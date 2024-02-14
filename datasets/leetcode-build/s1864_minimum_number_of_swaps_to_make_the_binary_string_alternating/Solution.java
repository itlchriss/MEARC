package g1801_1900.s1864_minimum_number_of_swaps_to_make_the_binary_string_alternating;

// #Medium #String #Greedy #2022_05_10_Time_3_ms_(43.20%)_Space_41.6_MB_(72.00%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is between 1 and 1000.*);
	//@ requires(*Each character in the input string `s` is either '0' or '1'.*);
	//@ ensures(*The method returns an integer value representing the minimum number of character swaps required to make the string alternating.*);
	//@ ensures(*If it is impossible to make the string alternating, the method returns -1.*);
    public int minSwaps(String s) {
        int[][] count = new int[2][2];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i % 2 == 0) {
                count[0][c - '0']++;
            } else {
                count[1][c - '0']++;
            }
        }
        if ((count[0][0] == 0 && count[1][1] == 0) || (count[0][1] == 0 && count[1][0] == 0)) {
            return 0;
        }
        if (count[0][0] != count[1][1] && count[0][1] != count[1][0]) {
            return -1;
        }
        int ans1 = count[0][0] == count[1][1] ? count[0][0] : Integer.MAX_VALUE;
        int ans2 = count[0][1] == count[1][0] ? count[0][1] : Integer.MAX_VALUE;
        return Math.min(ans1, ans2);
    }
}