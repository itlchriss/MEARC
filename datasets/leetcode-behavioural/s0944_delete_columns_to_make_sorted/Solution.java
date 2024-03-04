package g0901_1000.s0944_delete_columns_to_make_sorted;

// #Easy #Array #String #2022_12_26_Time_9_ms_(87.09%)_Space_42.8_MB_(73.30%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `strs` is not null.*);
//@ ensures(*The length of `strs` is greater than - All strings in `strs` have the same length.*);
//@ ensures(*Each string in `strs` consists of lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of columns that will be deleted.*);
//@ ensures(*The original array `strs` remains unchanged.*);
    public int minDeletionSize(String[] strs) {
        int count = 0;
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) < strs[j - 1].charAt(i)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}