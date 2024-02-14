package g1501_1600.s1573_number_of_ways_to_split_a_string;

// #Medium #String #Math #2022_04_11_Time_9_ms_(82.09%)_Space_42.7_MB_(87.31%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of `s` is at least 3.*);
	//@ ensures(*The return value is an integer.*);
	//@ ensures(*The return value is the number of ways `s` can be split such that the number of ones is the same in each split.*);
	//@ ensures(*The return value is modulo 10^9 + 7.*);
    public int numWays(String s) {
        long totalOnesCount = 0;
        long mod = 1000000007;
        long waysOfFirstString = 0;
        long waysOfSecondString = 0;
        long onesCount = 0;
        long n = s.length();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                totalOnesCount += 1;
            }
        }

        if (totalOnesCount % 3 != 0) {
            return 0;
        }

        long onesFirstPart = totalOnesCount / 3;
        long onesSecondPart = onesFirstPart * 2;

        if (totalOnesCount == 0) {
            return (int) ((n - 1) * (n - 2) / 2 % mod);
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                onesCount += 1;
            }
            if (onesCount == onesFirstPart) {
                waysOfFirstString += 1;
            } else if (onesCount == onesSecondPart) {
                waysOfSecondString += 1;
            } else if (onesCount > onesSecondPart) {
                break;
            }
        }

        return (int) ((waysOfFirstString * waysOfSecondString) % mod);
    }
}