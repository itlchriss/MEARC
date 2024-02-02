package g2901_3000.s2928_distribute_candies_among_children_i;

// #Easy #Math #Enumeration #Combinatorics #2023_12_29_Time_1_ms_(98.72%)_Space_40.9_MB_(6.45%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _the **total number** of ways to distribute_ `n` _candies among_ `3` _children such that no child gets more than_ `limit` _candies._*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
