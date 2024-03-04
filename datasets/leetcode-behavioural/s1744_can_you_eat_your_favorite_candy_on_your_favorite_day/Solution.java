package g1701_1800.s1744_can_you_eat_your_favorite_candy_on_your_favorite_day;

// #Medium #Array #Prefix_Sum #2022_04_29_Time_5_ms_(100.00%)_Space_91.1_MB_(76.00%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `candiesCount` must not be null.*);
//@ ensures(*The length of `candiesCount` must be greater than or equal to 1.*);
//@ ensures(*The elements of `candiesCount` must be positive integers.*);
//@ ensures(*The input array `queries` must not be null.*);
//@ ensures(*The length of `queries` must be greater than or equal to 1.*);
//@ ensures(*Each element of `queries` must be an array of length 3.*);
//@ ensures(*The first element of each query must be a valid index in `candiesCount`.*);
//@ ensures(*The second element of each query must be a non-negative integer.*);
//@ ensures(*The third element of each query must be a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The length of the output array `answer` must be equal to the length of `queries`.*);
//@ ensures(*Each element of `answer` must be a boolean value.*);
//@ ensures(*The values in `answer` must correspond to whether it is possible to eat a candy of the favorite type on the favorite day without eating more than the daily cap.*);
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        boolean[] result = new boolean[queries.length];
        long[] candiesComm = new long[candiesCount.length + 1];
        for (int i = 1; i <= candiesCount.length; i++) {
            candiesComm[i] = candiesComm[i - 1] + candiesCount[i - 1];
        }
        for (int i = 0; i < queries.length; i++) {
            int type = queries[i][0];
            long day = queries[i][1];
            long cap = queries[i][2];
            result[i] = ((day + 1) * cap > candiesComm[type]) && day < candiesComm[type + 1];
        }
        return result;
    }
}