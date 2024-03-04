package g2501_2600.s2554_maximum_number_of_integers_to_choose_from_a_range_i;

// #Medium #Array #Hash_Table #Sorting #Greedy #Binary_Search
// #2023_08_19_Time_4_ms_(100.00%)_Space_44.9_MB_(17.88%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `banned` is not null.*);
//@ ensures(*The input array `banned` contains integers within the range `[1, n]`.*);
//@ ensures(*The input integer `n` is greater than or equal to 1.*);
//@ ensures(*The input integer `maxSum` is greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output integer represents the maximum number of integers that can be chosen.*);
//@ ensures(*The output integer is within the range `[0, n]`.*);
//@ ensures(*The sum of the chosen integers does not exceed `maxSum`.*);
//@ ensures(*The chosen integers are within the range `[1, n]`.*);
//@ ensures(*Each chosen integer does not appear in the array `banned`.*);
    public int maxCount(int[] banned, int n, int maxSum) {
        boolean[] arr = new boolean[10002];
        for (int j : banned) {
            arr[j] = true;
        }
        int sum = 0;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (!arr[i]) {
                sum += i;
                if (sum > maxSum) {
                    return count;
                }
                count++;
            }
        }
        return count;
    }
}