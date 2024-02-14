package g0301_0400.s0354_russian_doll_envelopes;

// #Hard #Array #Dynamic_Programming #Sorting #Binary_Search
// #2022_07_11_Time_46_ms_(99.83%)_Space_85.4_MB_(19.85%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input `envelopes` is a 2D array of integers.*);
	//@ requires(*Each element in `envelopes` is an array of length 2, representing the width and height of an envelope.*);
	//@ requires(*The width and height of each envelope are positive integers.*);
	//@ ensures(*The method returns an integer representing the maximum number of envelopes that can be Russian dolled.*);
	//@ ensures(*The returned value is greater than or equal to - The returned value is the maximum number of envelopes that can be Russian dolled, considering the given constraints.*);
	//@ ensures(*The order of the envelopes in the input array is not changed.*);
	//@ ensures(*The input array `envelopes` is not modified.*);
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int[] tails = new int[envelopes.length];
        int size = 0;
        for (int[] enve : envelopes) {
            int i = 0;
            int j = size;
            while (i != j) {
                int mid = i + ((j - i) >> 1);
                if (tails[mid] < enve[1]) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }
            tails[i] = enve[1];
            if (i == size) {
                size++;
            }
        }
        return size;
    }
}