package g0801_0900.s0899_orderly_queue;

// #Hard #String #Math #Sorting #2022_03_28_Time_1_ms_(100.00%)_Space_40.5_MB_(98.64%)

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The input string `s` consists of lowercase English letters.*);
	//@ requires(*The input integer `k` is greater than or equal to 1.*);
	//@ requires(*The input integer `k` is less than or equal to the length of the string `s`.*);
	//@ ensures(*The output string is not null.*);
	//@ ensures(*The output string is the lexicographically smallest string that can be obtained by moving one of the first `k` letters of `s` to the end any number of times.*);
    public String orderlyQueue(String s, int k) {
        if (k > 1) {
            char[] ans = s.toCharArray();
            Arrays.sort(ans);
            return String.valueOf(ans);
        }
        char min = 'z';
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char cc = s.charAt(i);
            if (cc < min) {
                min = cc;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == min) {
                list.add(i);
            }
        }
        String ans = s;
        for (Integer integer : list) {
            String after = s.substring(0, integer);
            String before = s.substring(integer);
            String f = before + after;
            if (f.compareTo(ans) < 0) {
                ans = f;
            }
        }
        return ans;
    }
}