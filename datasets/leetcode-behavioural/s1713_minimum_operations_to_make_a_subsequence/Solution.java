package g1701_1800.s1713_minimum_operations_to_make_a_subsequence;

// #Hard #Array #Hash_Table #Greedy #Binary_Search
// #2022_04_24_Time_81_ms_(95.39%)_Space_60.5_MB_(96.05%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input arrays `target` and `arr` are not null.*);
//@ ensures(*The length of `target` is greater than or equal to 1.*);
//@ ensures(*The length of `arr` is greater than or equal to 1.*);
//@ ensures(*The elements in `target` are distinct.*);
//@ ensures(*The elements in `target` and `arr` are integers.*);
//@ ensures(*The elements in `target` and `arr` are within the range of 1 to 10^9.*);
//@ ensures(*`target` does not contain any duplicates.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of operations needed to make `target` a subsequence of `arr`.*);
    public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            map.put(target[i], i);
        }
        List<Integer> list = new ArrayList<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                list.add(map.get(num));
            }
        }
        return target.length - longestIncreasingSubsequence(list);
    }

    private int longestIncreasingSubsequence(List<Integer> list) {
        int n = list.size();
        int l = 0;
        int[] arr = new int[n];
        for (int num : list) {
            int index = Arrays.binarySearch(arr, 0, l, num);
            if (index < 0) {
                index = ~index;
            }
            arr[index] = num;
            if (index == l) {
                l++;
            }
        }

        return l;
    }
}