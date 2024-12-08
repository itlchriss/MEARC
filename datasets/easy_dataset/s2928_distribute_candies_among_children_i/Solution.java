package g2901_3000.s2928_distribute_candies_among_children_i;

// #Easy #Math #Enumeration #Combinatorics #2023_12_29_Time_1_ms_(98.72%)_Space_40.9_MB_(6.45%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integers `n` and `limit` must be positive.*);
//@ ensures(*The value of `n` must be between 1 and 50 (inclusive).*);
//@ ensures(*The value of `limit` must be between 1 and 50 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer representing the total number of ways to distribute `n` candies among 3 children such that no child gets more than `limit` candies.*);
//@ ensures(*The returned value should be between 0 and the maximum possible number of distributions, which is `(limit + 1) ^ 3`.*);
//@ ensures(*The order of the children in the distribution does not matter. For example, if the distribution (1, 2, 2) is counted, the distribution (2, 1, 2) should not be counted separately.*);
//@ ensures(*The method should handle the case where `n` is less than or equal to 0 by returning 0.*);
//@ ensures(*The method should handle the case where `limit` is less than or equal to 0 by returning 0.*);
//@ ensures(*The method should handle the case where `n` is less than the number of children (3) by returning 0.*);
//@ ensures(*The method should handle the case where `limit` is less than the number of children (3) by returning 0.*);
    public int distributeCandies(int n, int limit) {
        int count = 0;
        for (int i = 0; i < Math.min(limit, n) + 1; i++) {
            for (int j = 0; j < Math.min(limit, n) + 1; j++) {
                int k = n - i - j;
                if (k >= 0 && k <= limit) {
                    count++;
                }
            }
        }
        return count;
    }
}