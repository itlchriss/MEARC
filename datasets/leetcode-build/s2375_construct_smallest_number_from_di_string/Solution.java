package g2301_2400.s2375_construct_smallest_number_from_di_string;

// #Medium #String #Greedy #Stack #Backtracking
// #2022_08_19_Time_0_ms_(100.00%)_Space_40.2_MB_(98.07%)

public class Solution {
	//@ requires(*The input string `pattern` is not null.*);
	//@ requires(*The length of `pattern` is at least 1 and at most - `pattern` consists of only the letters 'I' and 'D'.*);
	//@ ensures(*The output string `num` is not null.*);
	//@ ensures(*The length of `num` is equal to the length of `pattern` + - `num` consists of the digits '1' to '9', where each digit is used at most once.*);
	//@ ensures(*For each index i in `pattern`:*);
	//@ ensures(*- If `pattern[i]` is 'I', then `num[i]` is less than `num[i + 1]`.*);
	//@ ensures(*- If `pattern[i]` is 'D', then `num[i]` is greater than `num[i + 1]`.*);
	//@ ensures(*`num` is the lexicographically smallest possible string that meets the conditions.*);
    public String smallestNumber(String pattern) {
        int[] ret = new int[pattern.length() + 1];
        ret[0] = 1;
        int max = 2;
        int lastI = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == 'I') {
                ret[i + 1] = max++;
                lastI = i + 1;
            } else {
                for (int j = i; j >= lastI; j--) {
                    ret[j + 1] = ret[j];
                }
                ret[lastI] = max++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i : ret) {
            sb.append(i);
        }
        return sb.toString();
    }
}