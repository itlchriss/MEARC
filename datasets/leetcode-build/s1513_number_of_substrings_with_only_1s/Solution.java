package g1501_1600.s1513_number_of_substrings_with_only_1s;

// #Medium #String #Math #2022_04_09_Time_3_ms_(97.83%)_Space_42.7_MB_(85.14%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is greater than or equal to 1.*);
	//@ requires(*The characters in the input string `s` are either '0' or '1'.*);
	//@ ensures(*The method returns an integer value.*);
	//@ ensures(*The returned value is the number of substrings in the input string `s` that consist only of '1' characters.*);
	//@ ensures(*The returned value is modulo 10^9 + 7.*);
    public int numSub(String s) {
        long count = 0;
        long res = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '0') {
                res += count * (count + 1) / 2;
                count = 0;
            } else {
                count++;
            }
        }
        res += count * (count + 1) / 2;
        return (int) (res % 1000000007);
    }
}