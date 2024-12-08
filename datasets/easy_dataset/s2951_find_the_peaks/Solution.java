package g2901_3000.s2951_find_the_peaks;

// #Easy #Array #Enumeration #2024_01_15_Time_1_ms_(100.00%)_Space_43.8_MB_(79.61%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `mountain` is not null.*);
//@ ensures(*The length of the input array `mountain` is at least - The elements in the input array `mountain` are integers.*);
//@ ensures(*The elements in the input array `mountain` are between 1 and 100 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a list of integers.*);
//@ ensures(*The output list contains the indices of the peaks in the input array `mountain`.*);
//@ ensures(*The peaks are defined as elements that are strictly greater than their neighboring elements.*);
//@ ensures(*The first and last elements of the input array `mountain` are not considered as peaks.*);
    public List<Integer> findPeaks(int[] mountain) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < mountain.length - 1; i++) {
            if ((mountain[i - 1] < mountain[i]) && (mountain[i] > mountain[i + 1])) {
                list.add(i);
            }
        }
        return list;
    }
}