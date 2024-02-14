package g2101_2200.s2121_intervals_between_identical_elements;

// #Medium #Array #Hash_Table #Prefix_Sum #2022_06_02_Time_93_ms_(79.63%)_Space_188.7_MB_(66.05%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	//@ requires(*The input array `arr` is not null.*);
	//@ requires(*The length of the input array `arr` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `arr` are integers.*);
	//@ requires(*The elements in the input array `arr` are within the range of 1 to 10^5.*);
	//@ ensures(*The output array `intervals` is not null.*);
	//@ ensures(*The length of the output array `intervals` is equal to the length of the input array `arr`.*);
	//@ ensures(*The elements in the output array `intervals` are integers.*);
	//@ ensures(*The elements in the output array `intervals` represent the sum of intervals between each element in the input array `arr` and each element in the input array `arr` with the same value.*);
    public long[] getDistances(int[] arr) {
        int n = arr.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = map.get(arr[i]);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(i);
            map.put(arr[i], list);
        }
        long[] ans = new long[n];
        Arrays.fill(ans, 0);
        for (List<Integer> list : map.values()) {
            long sum = 0;
            int first = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                sum = sum + list.get(i) - first;
            }
            ans[first] = sum;
            int prevElements = 0;
            int nextElements = list.size() - 2;
            for (int i = 1; i < list.size(); i++) {
                int diff = list.get(i) - list.get(i - 1);
                sum = sum + (long) diff * (prevElements - nextElements);
                ans[list.get(i)] = sum;
                prevElements++;
                nextElements--;
            }
        }
        return ans;
    }
}