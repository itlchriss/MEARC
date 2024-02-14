package g0901_1000.s0955_delete_columns_to_make_sorted_ii;

// #Medium #Array #String #Greedy #2022_03_30_Time_2_ms_(68.84%)_Space_42.4_MB_(47.83%)

public class Solution {
	//@ requires(*The input array `strs` is not null.*);
	//@ requires(*The length of `strs` is greater than 0.*);
	//@ requires(*All strings in `strs` have the same length.*);
	//@ requires(*Each string in `strs` consists of lowercase English letters.*);
	//@ ensures(*The returned value is an integer representing the minimum number of deletion indices required to make the final array lexicographically sorted.*);
	//@ ensures(*The final array after deletions has its elements in lexicographic order.*);
	//@ ensures(*The final array has the same length as the original array `strs`.*);
    public int minDeletionSize(String[] strs) {
        boolean[] sorted = new boolean[strs.length];
        int res = 0;
        for (int i = 0; i < strs[0].length(); i++) {
            int j = 0;
            for (; j < strs.length - 1; j++) {
                if (!sorted[j] && strs[j].charAt(i) > strs[j + 1].charAt(i)) {
                    res++;
                    break;
                }
            }
            if (j < strs.length - 1) {
                continue;
            }
            j = 0;
            for (; j < strs.length - 1; j++) {
                if (strs[j].charAt(i) < strs[j + 1].charAt(i)) {
                    sorted[j] = true;
                }
            }
        }
        return res;
    }
}