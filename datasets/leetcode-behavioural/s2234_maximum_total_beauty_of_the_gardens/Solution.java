package g2201_2300.s2234_maximum_total_beauty_of_the_gardens;

// #Hard #Array #Sorting #Greedy #Binary_Search #Two_Pointers
// #2022_06_08_Time_63_ms_(73.03%)_Space_84_MB_(53.39%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `flowers` must not be null.*);
//@ ensures(*The length of the input array `flowers` must be greater than or equal to 1.*);
//@ ensures(*The elements in the input array `flowers` must be positive integers.*);
//@ ensures(*The input integer `newFlowers` must be a positive integer.*);
//@ ensures(*The input integer `target` must be a positive integer.*);
//@ ensures(*The input integer `full` must be a positive integer.*);
//@ ensures(*The input integer `partial` must be a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output must be a long integer representing the maximum total beauty that Alice can obtain.*);
//@ ensures(*The output must be greater than or equal to 0.*);
//@ ensures(*The output must be the sum of the number of complete gardens multiplied by `full` and the minimum number of flowers in any of the incomplete gardens multiplied by `partial`.*);
//@ ensures(*The output must be the maximum possible value that satisfies the given conditions.*);
    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        int n = flowers.length;
        long[] prefix = new long[n + 1];
        Arrays.sort(flowers);
        for (int i = 0; i < n; ++i) {
            prefix[i + 1] = prefix[i] + Math.min(flowers[i], target);
        }
        long res = 0;
        int i = n - 1;
        for (int c = 0; c <= n; ++c) {
            long remain = prefix[n] - prefix[n - c] + newFlowers - c * (long) target;
            long min = 0;
            if (0 > remain) {
                break;
            }
            i = Math.min(i, n - c - 1);
            while (0 <= i
                    && (target <= flowers[i]
                            || flowers[i] * (long) (i + 1) - prefix[i + 1] > remain)) {
                i--;
            }
            if (0 <= i) {
                long dif = flowers[i] * (long) (i + 1) - prefix[i + 1];
                min = Math.min(target - 1L, flowers[i] + (remain - dif) / (i + 1));
                if (i + 1 < n - c) {
                    min = Math.min(min, flowers[i + 1]);
                }
            }
            res = Math.max(res, c * (long) full + min * partial);
        }
        return res;
    }
}