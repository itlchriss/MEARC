package g0501_0600.s0522_longest_uncommon_subsequence_ii;

// #Medium #Array #String #Hash_Table #Sorting #Two_Pointers
// #2022_07_28_Time_1_ms_(99.61%)_Space_41.7_MB_(40.70%)

public class Solution {
	//@ requires(*The input array `strs` is not null.*);
	//@ requires(*The length of `strs` is at least - Each string in `strs` is not null.*);
	//@ requires(*Each string in `strs` consists of lowercase English letters.*);
	//@ requires(*Each string in `strs` has a length between 1 and*);
	//@ ensures(*The method returns an integer representing the length of the longest uncommon subsequence.*);
	//@ ensures(*If the longest uncommon subsequence does not exist, the method returns -1.*);
    public int findLUSlength(String[] strs) {
        int maxUncommonLen = -1;
        for (int indx1 = 0; indx1 < strs.length; indx1++) {
            boolean isCommon = false;
            for (int indx2 = 0; indx2 < strs.length; indx2++) {
                if (indx2 != indx1 && isSubSequence(strs[indx1], strs[indx2])) {
                    isCommon = true;
                    break;
                }
            }
            if (!isCommon) {
                maxUncommonLen = Math.max(maxUncommonLen, strs[indx1].length());
            }
        }
        return maxUncommonLen;
    }

    private boolean isSubSequence(String str1, String str2) {
        int indx1 = 0;
        for (int indx2 = 0; indx2 < str2.length(); indx2++) {
            if (str1.charAt(indx1) == str2.charAt(indx2)) {
                indx1++;
            }
            if (indx1 == str1.length()) {
                return true;
            }
        }
        return indx1 == str1.length();
    }
}