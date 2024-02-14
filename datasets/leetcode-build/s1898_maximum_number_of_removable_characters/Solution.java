package g1801_1900.s1898_maximum_number_of_removable_characters;

// #Medium #Array #String #Binary_Search #Binary_Search_II_Day_6
// #2022_05_04_Time_121_ms_(72.51%)_Space_84.5_MB_(58.77%)

public class Solution {
	//@ requires(*The length of string `p` is greater than or equal to - The length of string `s` is greater than or equal to the length of string `p`.*);
	//@ requires(*The length of array `removable` is less than the length of string `s`.*);
	//@ requires(*The elements in array `removable` are distinct.*);
	//@ requires(*String `p` is a subsequence of string `s`.*);
	//@ requires(*String `s` and string `p` consist of lowercase English letters.*);
	//@ ensures(*The returned value is an integer representing the maximum number of characters that can be removed from string `s` such that string `p` is still a subsequence of `s`.*);
    public int maximumRemovals(String s, String p, int[] removable) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // binary search for the k which need to be removed
        char[] convertedS = s.toCharArray();
        int left = 0;
        int right = removable.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            // remove letters from 0 to mid by changing it into some other non letters
            for (int i = 0; i <= middle; i++) {
                convertedS[removable[i]] = '?';
            }
            // if it is still subsequence change left boundary
            // else replace all removed ones and change right boundary
            if (isSubsequence(convertedS, p)) {
                left = middle + 1;
            } else {
                for (int i = 0; i <= middle; i++) {
                    convertedS[removable[i]] = s.charAt(removable[i]);
                }
                right = middle - 1;
            }
        }
        return left;
    }

    // simple check for subsequence
    private boolean isSubsequence(char[] convertedS, String p) {
        int p1 = 0;
        int p2 = 0;
        while (p1 < convertedS.length && p2 < p.length()) {
            if (convertedS[p1] != '?' && convertedS[p1] == p.charAt(p2)) {
                p2 += 1;
            }
            p1 += 1;
        }
        return p2 == p.length();
    }
}