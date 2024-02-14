package g2101_2200.s2182_construct_string_with_repeat_limit;

// #Medium #String #Greedy #Heap_Priority_Queue #Counting
// #2022_06_07_Time_26_ms_(96.11%)_Space_63.6_MB_(62.78%)

@SuppressWarnings("java:S135")
public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The input string `s` consists of lowercase English letters.*);
	//@ requires(*The input integer `repeatLimit` is greater than or equal to 1.*);
	//@ requires(*The input integer `repeatLimit` is less than or equal to the length of `s`.*);
	//@ ensures(*The output string `repeatLimitedString` is not null.*);
	//@ ensures(*The output string `repeatLimitedString` consists of characters from `s`.*);
	//@ ensures(*No letter in `repeatLimitedString` appears more than `repeatLimit` times in a row.*);
	//@ ensures(*The output string `repeatLimitedString` is the lexicographically largest possible string that satisfies the above conditions.*);
    public String repeatLimitedString(String s, int repeatLimit) {
        char[] result = new char[s.length()];
        int[] freq = new int[128];
        int index = 0;
        for (char c : s.toCharArray()) {
            freq[c]++;
        }
        char max = 'z';
        char second = 'y';
        while (true) {
            while (max >= 'a' && freq[max] == 0) {
                max--;
            }
            if (max < 'a') {
                break;
            }
            second = (char) Math.min(max - 1, second);
            int count = Math.min(freq[max], repeatLimit);
            freq[max] -= count;
            while (count-- > 0) {
                result[index++] = max;
            }
            if (freq[max] == 0) {
                max = second--;
                continue;
            }
            while (second >= 'a' && freq[second] == 0) {
                second--;
            }
            if (second < 'a') {
                break;
            }
            result[index++] = second;
            freq[second]--;
        }
        return new String(result, 0, index);
    }
}