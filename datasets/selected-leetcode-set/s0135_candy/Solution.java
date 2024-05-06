package g0101_0200.s0135_candy;

// #Hard #Array #Greedy #2022_06_24_Time_2_ms_(99.95%)_Space_42.8_MB_(94.28%)

import java.util.Arrays;

public class Solution {
//@ ensures(*The integer array parameter `ratings` must not be null.*);
//@ ensures(*The integer array parameter `ratings` length is greater than or equal to 1 and is less than or equal to 20000.*);
//@ ensures(*All values in the integer array parameter `ratings` are greater than or equal to 0 and are less than or equal to 20000.*);
//@ ensures(*The integer result is the minimum number of candies needed to distribute to the children based on the given requirements.*);
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        for (int i = 0; i < ratings.length - 1; i++) {
            if (ratings[i + 1] > ratings[i]) {
                candies[i + 1] = candies[i] + 1;
            }
        }
        for (int i = ratings.length - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i] && candies[i - 1] < candies[i] + 1) {
                candies[i - 1] = candies[i] + 1;
            }
        }
        int total = 0;
        for (int candy : candies) {
            total += candy;
        }
        return total;
    }
}