package g2701_2800.s2736_maximum_sum_queries;

// #Hard #Array #Sorting #Binary_Search #Stack #Monotonic_Stack #Segment_Tree #Binary_Indexed_Tree
// #2023_09_23_Time_66_ms_(78.43%)_Space_84.1_MB_(94.12%)

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Solution {
    private void update(NavigableMap<Integer, Integer> map, int num, int sum) {
        Map.Entry<Integer, Integer> entry = map.floorEntry(num);
        while (entry != null && entry.getValue() <= sum) {
            map.remove(entry.getKey());
            int x = entry.getKey();
            entry = map.floorEntry(x);
        }
        entry = map.ceilingEntry(num);
        if (entry == null || entry.getValue() < sum) {
            map.put(num, sum);
        }
    }

    private int queryVal(NavigableMap<Integer, Integer> map, int num) {
        Map.Entry<Integer, Integer> entry = map.ceilingEntry(num);
        if (entry == null) {
            return -1;
        }
        return entry.getValue();
    }
	//@ requires(*The lengths of `nums1` and `nums2` are equal.*);
	//@ requires(*The length of `nums1` is equal to `n`.*);
	//@ requires(*The length of `queries` is greater than or equal to - Each query in `queries` has a length of - The values in `nums1` and `nums2` are integers.*);
	//@ requires(*The values in `nums1` and `nums2` are greater than or equal to - The values in `nums1` and `nums2` are less than or equal to 10^- The values in `queries` are integers.*);
	//@ requires(*The values in `queries` are greater than or equal to - The values in `queries` are less than or equal to 10^*);
	//@ ensures(*The method returns an array `answer`.*);
	//@ ensures(*The length of `answer` is equal to the length of `queries`.*);
	//@ ensures(*Each element in `answer` is an integer.*);
	//@ ensures(*Each element in `answer` is the maximum value of `nums1[j] + nums2[j]` among all indices `j` where `nums1[j] >= xi` and `nums2[j] >= yi`, or -1 if there is no `j` satisfying the constraints.*);

    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int m = queries.length;
        List<int[]> v = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            v.add(new int[] {nums1[i], nums2[i]});
        }
        v.sort(Comparator.comparingInt(a -> a[0]));
        List<Integer> ind = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            ind.add(i);
        }
        ind.sort((a, b) -> queries[b][0] - queries[a][0]);
        TreeMap<Integer, Integer> values = new TreeMap<>();
        int j = n - 1;
        int[] ans = new int[m];
        for (int i : ind) {
            int a = queries[i][0];
            int b = queries[i][1];
            for (; j >= 0 && v.get(j)[0] >= a; j--) {
                update(values, v.get(j)[1], v.get(j)[0] + v.get(j)[1]);
            }
            ans[i] = queryVal(values, b);
        }
        return ans;
    }
}