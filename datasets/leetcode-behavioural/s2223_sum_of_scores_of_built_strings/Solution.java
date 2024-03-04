package g2201_2300.s2223_sum_of_scores_of_built_strings;

// #Hard #String #Binary_Search #Hash_Function #String_Matching #Rolling_Hash #Suffix_Array
// #2022_06_12_Time_21_ms_(63.91%)_Space_54.3_MB_(42.86%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of `s` is between 1 and 10^5.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a long value representing the sum of the scores of every `s_i`.*);
//@ ensures(*The sum of the scores is calculated correctly according to the given rules.*);
//@ ensures(*The method terminates and returns the correct result within a reasonable amount of time.*);
    public long sumScores(String s) {
        int n = s.length();
        char[] ss = s.toCharArray();
        int[] z = new int[n];
        int l = 0;
        int r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r) {
                z[i] = Math.min(z[i - l], r - i + 1);
            }
            while (i + z[i] < n && ss[z[i]] == ss[i + z[i]]) {
                z[i]++;
            }
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        long sum = n;
        for (int i = 0; i < n; i++) {
            sum += z[i];
        }
        return sum;
    }
}