package g1301_1400.s1387_sort_integers_by_the_power_value;

// #Medium #Dynamic_Programming #Sorting #Memoization
// #2022_03_21_Time_52_ms_(87.20%)_Space_42_MB_(84.53%)

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    private Map<Integer, Integer> cacheMap;
	//@ requires(*The input integers `lo` and `hi` are within the range of 1 to 1000.*);
	//@ requires(*The input integer `k` is within the range of 1 to `hi - lo + 1`.*);
	//@ ensures(*The method returns an integer value.*);
	//@ ensures(*The returned integer is within the range of `lo` to `hi`.*);
	//@ ensures(*The returned integer is the `k`th integer in the range `[lo, hi]` when sorted by the power value.*);
	//@ ensures(*The power value of each integer in the range `[lo, hi]` is calculated correctly.*);
	//@ ensures(*The integers in the range `[lo, hi]` are sorted by the power value in ascending order.*);
	//@ ensures(*If two or more integers have the same power value, they are sorted by ascending order.*);
	//@ ensures(*The power value of each integer in the range `[lo, hi]` fits in a 32-bit signed integer.*);

    public int getKth(int lo, int hi, int k) {
        this.cacheMap = new HashMap<>();
        this.cacheMap.put(1, 0);
        int[][] arr = new int[hi - lo + 1][2];
        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = lo + i;
            arr[i][1] = getStepCount(lo + i);
        }
        Arrays.sort(arr, Comparator.comparing(a -> a[1]));
        return arr[k - 1][0];
    }

    private int getStepCount(int val) {
        if (cacheMap.containsKey(val)) {
            return cacheMap.get(val);
        }
        int step;
        if (val % 2 == 0) {
            // even
            step = 1 + getStepCount(val / 2);
        } else {
            step = 1 + getStepCount(3 * val + 1);
        }
        cacheMap.put(val, step);
        return step;
    }
}