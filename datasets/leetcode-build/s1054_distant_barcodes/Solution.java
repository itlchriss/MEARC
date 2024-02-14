package g1001_1100.s1054_distant_barcodes;

// #Medium #Array #Hash_Table #Sorting #Greedy #Heap_Priority_Queue #Counting
// #2022_02_28_Time_45_ms_(64.21%)_Space_69.1_MB_(71.24%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	//@ requires(*The input array `barcodes` is not null.*);
	//@ requires(*The length of the input array `barcodes` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `barcodes` is a positive integer.*);
	//@ ensures(*The output array is not null.*);
	//@ ensures(*The length of the output array is the same as the length of the input array.*);
	//@ ensures(*No two adjacent elements in the output array are equal.*);
	//@ ensures(*The elements in the output array are a rearrangement of the elements in the input array.*);
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i : barcodes) {
            cnt.put(i, cnt.getOrDefault(i, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(cnt.entrySet());
        list.sort(Map.Entry.<Integer, Integer>comparingByValue().reversed());
        int l = barcodes.length;
        int i = 0;
        int[] res = new int[l];
        for (Map.Entry<Integer, Integer> e : list) {
            int time = e.getValue();
            while (time-- > 0) {
                res[i] = e.getKey();
                i += 2;
                if (i >= barcodes.length) {
                    i = 1;
                }
            }
        }
        return res;
    }
}