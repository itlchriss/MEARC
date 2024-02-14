package g1301_1400.s1331_rank_transform_of_an_array;

// #Easy #Array #Hash_Table #Sorting #2022_03_19_Time_22_ms_(98.50%)_Space_58.4_MB_(93.72%)

import java.util.Arrays;
import java.util.HashMap;

@SuppressWarnings("java:S3824")
public class Solution {
	//@ requires(*The input array `arr` is not null.*);
	//@ requires(*The length of the input array `arr` is not negative.*);
	//@ requires(*The elements in the input array `arr` are integers.*);
	//@ requires(*The elements in the input array `arr` are within the range of -10^9 to 10^9.*);
	//@ ensures(*The output array `result` is not null.*);
	//@ ensures(*The length of the output array `result` is the same as the input array `arr`.*);
	//@ ensures(*The elements in the output array `result` are integers.*);
	//@ ensures(*The elements in the output array `result` represent the ranks of the corresponding elements in the input array `arr`.*);
	//@ ensures(*The ranks in the output array `result` are integers starting from 1.*);
	//@ ensures(*The larger the element in the input array `arr`, the larger the rank in the output array `result`.*);
	//@ ensures(*If two elements in the input array `arr` are equal, their ranks in the output array `result` are the same.*);
	//@ ensures(*The ranks in the output array `result` are as small as possible.*);
    public int[] arrayRankTransform(int[] arr) {
        int[] tmp = Arrays.copyOf(arr, arr.length);
        Arrays.sort(tmp);
        HashMap<Integer, Integer> mp = new HashMap<>();
        int i = 1;
        for (Integer x : tmp) {
            if (!mp.containsKey(x)) {
                mp.put(x, i++);
            }
        }
        i = 0;
        for (Integer x : arr) {
            arr[i++] = mp.get(x);
        }
        return arr;
    }
}