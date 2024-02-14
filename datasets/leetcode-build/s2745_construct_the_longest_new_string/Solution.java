package g2701_2800.s2745_construct_the_longest_new_string;

// #Medium #Math #Greedy #Brainteaser #2023_09_23_Time_1_ms_(100.00%)_Space_40.1_MB_(66.80%)

public class Solution {
	//@ requires(*The method takes three integer parameters: x, y, and z.*);
	//@ requires(*The values of x, y, and z are between 1 and 50 (inclusive).*);
	//@ ensures(*The method returns an integer representing the maximum possible length of the new string.*);
	//@ ensures(*The new string is formed by concatenating some (possibly all or none) of the strings "AA", "BB", and "AB" in some order.*);
	//@ ensures(*The new string does not contain the substring "AAA" or "BBB".*);
    public int longestString(int x, int y, int z) {
        int min = Math.min(x, y);
        int res = 0;
        if (x == y) {
            res = 2 * min + z;
        } else {
            res = 2 * min + 1 + z;
        }
        return res * 2;
    }
}