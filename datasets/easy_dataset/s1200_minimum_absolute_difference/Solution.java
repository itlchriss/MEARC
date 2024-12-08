package g1101_1200.s1200_minimum_absolute_difference;

// #Easy #Array #Sorting #2022_02_27_Time_14_ms_(98.30%)_Space_52_MB_(84.02%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is at least 2.*);
//@ ensures(*The elements in the input array `arr` are distinct.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a list of pairs, where each pair `[a, b]` satisfies the following conditions:*);
//@ ensures(*  - `a` and `b` are elements from the input array `arr`.*);
//@ ensures(*  - `a` is less than `b`.*);
//@ ensures(*  - The difference between `b` and `a` is equal to the minimum absolute difference of any two elements in the input array `arr`.*);
//@ ensures(*The output list is sorted in ascending order with respect to the pairs.*);
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        int min = 10000000;
        Arrays.sort(arr);
        for (int i = 0; i + 1 < arr.length; i++) {
            int diff = arr[i + 1] - arr[i];
            if (diff <= min) {
                if (diff < min) {
                    min = diff;
                    result.clear();
                }
                result.add(Arrays.asList(arr[i], arr[i + 1]));
            }
        }
        return result;
    }
}