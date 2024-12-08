package g1601_1700.s1619_mean_of_array_after_removing_some_elements;

// #Easy #Array #Sorting #2022_04_13_Time_2_ms_(99.81%)_Space_41.8_MB_(91.95%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of `arr` is between 20 and 1000.*);
//@ ensures(*The length of `arr` is a multiple of 20.*);
//@ ensures(*Each element in `arr` is between 0 and 10^5.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned value is a double.*);
//@ ensures(*The returned value is within 10^-5 of the actual answer.*);
//@ ensures(*The returned value is the mean of the remaining integers after removing the smallest 5% and the largest 5% of the elements in `arr`.*);
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        long sum = 0;
        for (int i = (int) Math.round(n * 0.05); i < (n - n * 0.05); i++) {
            sum += arr[i];
        }
        return sum / (n - n * 0.1);
    }
}