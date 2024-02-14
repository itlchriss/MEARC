package g1501_1600.s1529_bulb_switcher_iv;

// #Medium #String #Greedy #2022_04_09_Time_6_ms_(89.67%)_Space_48.3_MB_(7.02%)

public class Solution {
	//@ requires(*The input string `target` is not null.*);
	//@ requires(*The length of the input string `target` is greater than - The length of the input string `target` is less than or equal to 10^- Each character in the input string `target` is either '0' or '1'.*);
	//@ ensures(*The method returns an integer representing the minimum number of operations needed to make `s` equal to `target`.*);
    public int minFlips(String target) {
        int flipCount = target.charAt(0) - 48;
        char prev = target.charAt(0);
        for (char ch : target.toCharArray()) {
            if (ch != prev) {
                flipCount++;
                prev = ch;
            }
        }
        return flipCount;
    }
}