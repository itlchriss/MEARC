package g2301_2400.s2363_merge_similar_items;

// #Easy #Array #Hash_Table #Sorting #Ordered_Set
// #2022_08_14_Time_3_ms_(100.00%)_Space_43_MB_(100.00%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	//@ requires(*1. The input arrays `arr1` and `arr2` must not be null.*);
	//@ requires(*2. The length of `arr1` and `arr2` must be greater than or equal to 1.*);
	//@ requires(*3. Each subarray in `arr1` and `arr2` must have a length of 2.*);
	//@ requires(*4. The value and weight of each item in `arr1` and `arr2` must be between 1 and 1000 (inclusive).*);
	//@ requires(*5. The value of each item in `arr1` must be unique.*);
	//@ requires(*6. The value of each item in `arr2` must be unique.*);
	//@ ensures(*1. The returned list `ret` must not be null.*);
	//@ ensures(*2. The length of `ret` must be equal to the number of unique values in `arr1` and `arr2`.*);
	//@ ensures(*3. Each subarray in `ret` must have a length of 2.*);
	//@ ensures(*4. The values in `ret` must be sorted in ascending order.*);
	//@ ensures(*5. The weight of each item in `ret` must be the sum of the weights of all items with the same value in `arr1` and `arr2`.*);
    public List<List<Integer>> mergeSimilarItems(int[][] arr1, int[][] arr2) {
        int[] cache = new int[1001];
        for (int[] num : arr1) {
            cache[num[0]] += num[1];
        }
        for (int[] num : arr2) {
            cache[num[0]] += num[1];
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < cache.length; i++) {
            int weight = cache[i];
            if (weight > 0) {
                int value = i;
                result.add(Arrays.asList(value, weight));
            }
        }
        return result;
    }
}