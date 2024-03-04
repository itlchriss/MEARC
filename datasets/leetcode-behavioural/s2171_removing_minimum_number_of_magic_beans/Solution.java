package g2101_2200.s2171_removing_minimum_number_of_magic_beans;

// #Medium #Array #Sorting #Prefix_Sum #2022_06_09_Time_42_ms_(77.68%)_Space_62.3_MB_(73.82%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `beans` is not null.*);
//@ ensures(*The length of the input array `beans` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `beans` is a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a long value representing the minimum number of magic beans that need to be removed.*);
//@ ensures(*The number of beans in each remaining non-empty bag is equal.*);
//@ ensures(*Once a bean has been removed from a bag, it cannot be returned to any of the bags.*);
    public long minimumRemoval(int[] beans) {
        Arrays.sort(beans);
        int n = beans.length;
        long sum = 0;
        for (int bean : beans) {
            sum += bean;
        }
        long minbeans = Long.MAX_VALUE;
        long prefix = 0;
        long suffix;
        long count;
        for (int i = 0; i < n; i++) {
            prefix += beans[i];
            suffix = sum - prefix;
            count = (prefix - beans[i]) + (suffix - beans[i] * (n - i - 1L));
            if (count < minbeans) {
                minbeans = count;
            }
        }
        return minbeans;
    }
}