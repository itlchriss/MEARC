package g2501_2600.s2559_count_vowel_strings_in_ranges;

// #Medium #Array #String #Prefix_Sum #2023_08_19_Time_4_ms_(99.59%)_Space_85.6_MB_(78.46%)

public class Solution {
    private boolean validWord(String s) {
        char cStart = s.charAt(0);
        char cEnd = s.charAt(s.length() - 1);
        boolean flag1 =
                cStart == 'a' || cStart == 'e' || cStart == 'i' || cStart == 'o' || cStart == 'u';
        boolean flag2 = cEnd == 'a' || cEnd == 'e' || cEnd == 'i' || cEnd == 'o' || cEnd == 'u';
        return flag1 && flag2;
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `words` is not null.*);
//@ ensures(*The input array `queries` is not null.*);
//@ ensures(*The length of `words` is greater than or equal to the maximum value of `r` in `queries`.*);
//@ ensures(*The length of each string in `words` is between 1 and 40.*);
//@ ensures(*Each string in `words` consists only of lowercase English letters.*);
//@ ensures(*The sum of the lengths of all strings in `words` is less than or equal to 3 * 10^5.*);
//@ ensures(*The length of `queries` is between 1 and 10^5.*);
//@ ensures(*Each query in `queries` has a valid range, where 0 <= l <= r < length of `words`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `ans` is not null.*);
//@ ensures(*The length of `ans` is equal to the length of `queries`.*);
//@ ensures(*Each element in `ans` is an integer.*);
//@ ensures(*The value of each element in `ans` is the number of strings in the range l to r (inclusive) of `words` that start and end with a vowel.*);

    public int[] vowelStrings(String[] words, int[][] queries) {
        int[] prefixArr = new int[words.length];
        prefixArr[0] = validWord(words[0]) ? 1 : 0;
        for (int i = 1; i < words.length; ++i) {
            if (validWord(words[i])) {
                prefixArr[i] = prefixArr[i - 1] + 1;
            } else {
                prefixArr[i] = prefixArr[i - 1];
            }
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int upperBound = queries[i][1];
            int lowerBound = queries[i][0];
            int val =
                    (lowerBound == 0)
                            ? prefixArr[upperBound]
                            : prefixArr[upperBound] - prefixArr[lowerBound - 1];
            res[i] = val;
        }
        return res;
    }
}