package g1301_1400.s1338_reduce_array_size_to_the_half;

// #Medium #Array #Hash_Table #Sorting #Greedy #Heap_Priority_Queue
// #2022_03_19_Time_61_ms_(73.82%)_Space_83.5_MB_(71.31%)

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	//@ requires(*The input array `arr` is not null.*);
	//@ requires(*The length of the input array `arr` is even.*);
	//@ requires(*The length of the input array `arr` is at least 2.*);
	//@ requires(*Each element in the input array `arr` is a positive integer.*);
	//@ requires(*Each element in the input array `arr` is less than or equal to 10^5.*);
	//@ ensures(*The output is an integer representing the minimum size of the set.*);
	//@ ensures(*The output is greater than or equal to 1.*);
	//@ ensures(*The output is less than or equal to half the length of the input array `arr`.*);
	//@ ensures(*After removing the chosen set of integers from the input array `arr`, at least half of the integers in the array are removed.*);
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> freq = new ArrayList<>(map.values());
        freq.sort(Collections.reverseOrder());
        int i = 0;
        int count = 0;
        int totalLength = arr.length;
        while (totalLength > arr.length / 2) {
            totalLength -= freq.get(i);
            count++;
            i++;
        }
        return count;
    }
}