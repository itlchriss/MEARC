package g2101_2200.s2145_count_the_hidden_sequences;

// #Medium #Array #Prefix_Sum #2022_06_07_Time_7_ms_(36.03%)_Space_111.3_MB_(13.23%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `diff` must not be null.*);
//@ ensures(*The length of the input array `diff` must be greater than or equal to 1.*);
//@ ensures(*The values in the input array `diff` must be within the range of -10^5 to 10^5.*);
//@ ensures(*The value of `lower` must be within the range of -10^5 to 10^5.*);
//@ ensures(*The value of `upper` must be within the range of -10^5 to 10^5.*);
//@ ensures(*The value of `lower` must be less than or equal to the value of `upper`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of possible hidden sequences.*);
//@ ensures(*If there are no possible sequences, the method returns 0.*);
    public int numberOfArrays(int[] diff, int lower, int upper) {
        int n = diff.length;
        if (lower == upper) {
            for (int j : diff) {
                if (j != 0) {
                    return 0;
                }
            }
        }
        int max = -(int) 1e9;
        int min = (int) 1e9;
        int[] hidden = new int[n + 1];
        hidden[0] = 0;
        for (int i = 1; i <= n; i++) {
            hidden[i] = hidden[i - 1] + diff[i - 1];
        }
        for (int i = 0; i <= n; i++) {
            if (hidden[i] > max) {
                max = hidden[i];
            }
            if (hidden[i] < min) {
                min = hidden[i];
            }
        }
        int low = lower - min;
        int high = upper - max;
        if (low > high) {
            return 0;
        }
        return (high - low) + 1;
    }
}