package g0301_0400.s0349_intersection_of_two_arrays;

// #Easy #Array #Hash_Table #Sorting #Binary_Search #Two_Pointers
// #2022_07_11_Time_1_ms_(99.77%)_Space_42.3_MB_(92.31%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//@ requires(*The input arrays `nums1` and `nums2` are not null.*);
	//@ requires(*The input arrays `nums1` and `nums2` are not empty.*);
	//@ requires(*The elements in the input arrays `nums1` and `nums2` are integers.*);
	//@ requires(*The length of the input arrays `nums1` and `nums2` is between 1 and 1000.*);
	//@ requires(*The elements in the input arrays `nums1` and `nums2` are between 0 and 1000.*);
	//@ ensures(*The output array is not null.*);
	//@ ensures(*The output array contains unique elements.*);
	//@ ensures(*The output array can be in any order.*);
	//@ ensures(*The length of the output array is less than or equal to the length of the input arrays `nums1` and `nums2`.*);
    public int[] intersection(int[] nums1, int[] nums2) {
        boolean[] occ = new boolean[1001];
        for (int k : nums1) {
            occ[k] = true;
        }
        List<Integer> res = new ArrayList<>();
        for (int j : nums2) {
            if (occ[j]) {
                occ[j] = false;
                res.add(j);
            }
        }
        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }
}