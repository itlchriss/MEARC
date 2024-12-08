package g0301_0400.s0349_intersection_of_two_arrays;

// #Easy #Array #Hash_Table #Sorting #Binary_Search #Two_Pointers
// #2022_07_11_Time_1_ms_(99.77%)_Space_42.3_MB_(92.31%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*The integer array parameter `nums1` and the integer array parameter `nums2` must not be null.*);
//@ ensures(*The integer array result contains unique elements that are the intersection of the integer array parameter `nums1` and the integer array parameter `nums2`.*);
//@ ensures(*The order of elements in the integer array result is not specified.*);
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