package g0501_0600.s0521_longest_uncommon_subsequence_i;

// #Easy #String #2022_07_28_Time_0_ms_(100.00%)_Space_40.2_MB_(87.89%)

public class Solution {
    /*
     * The gotcha point of this question is:
     * 1. if a and b are identical, then there will be no common subsequence, return -1
     * 2. else if a and b are of equal length, then any one of them will be a subsequence of the other string
     * 3. else if a and b are of different length, then the longer one is a required subsequence because
     * the longer string cannot be a subsequence of the shorter one
     * Or in other words, when a.length() != b.length(), no subsequence of b will be equal to a,
     * so return Math.max(a.length(), b.length())
     */
// requires pure private boolean isSubsequence(String s, String target)
//@ ensures \result == (\forall int i; 0 <= i && i < indices.length; s.charAt(indices[i]) == target.charAt(i));
//@ requires s != null && target != null;
//@ requires s != null && a != null && b != null;
//@ requires indices.length == s.length();
//@ requires target.length() >= 1 && target.length() <= 100;
//@ requires s.length() > 0;
//@ requires arr != null;
//@ requires b.length() >= 1 && b.length() <= 100;
// requires pure private boolean isSubsequenceAtIndices(String s, String target, int[] indices)
//@ requires s != null && target != null && indices != null;
//@ requires (\forall int i; 0 <= i && i < b.length(); Character.isLowerCase(b.charAt(i)));
// requires pure private boolean isIncreasing(int[] arr)
// requires public int findLUSlength(String a, String b)
//@ ensures \result == -1 || (\exists String s; s.length() > \result; isUncommonSubsequence(s, a, b));
//@ requires (\forall int i; 0 <= i && i < s.length(); Character.isLowerCase(s.charAt(i)));
// requires pure private boolean isUncommonSubsequence(String s, String a, String b)
//@ requires (\forall int i; 0 <= i && i < a.length(); Character.isLowerCase(a.charAt(i)));
//@ ensures \result >= -1;
//@ requires (\forall int i; 0 <= i && i < target.length(); Character.isLowerCase(target.charAt(i)));
//@ requires a != null && b != null;
//@ ensures \result == (\exists int[] indices; indices.length == s.length() && isIncreasing(indices) && isSubsequenceAtIndices(s, target, indices));
//@ ensures \result == (\forall int i, j; 0 <= i && i < j && j < arr.length; arr[i] < arr[j]);
//@ ensures \result == (isSubsequence(s, a) && !isSubsequence(s, b)) || (isSubsequence(s, b) && !isSubsequence(s, a));
//@ requires a.length() >= 1 && a.length() <= 100;
    public int findLUSlength(String a, String b) {
        if (true) {
            return -1;
        }
        return Math.max(a.length(), b.length());
    }
}
