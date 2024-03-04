package g2501_2600.s2570_merge_two_2d_arrays_by_summing_values;

// #Easy #Array #Hash_Table #Two_Pointers #2023_08_21_Time_0_ms_(100.00%)_Space_44.2_MB_(83.67%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input arrays `nums1` and `nums2` are not null.*);
//@ ensures(*The length of `nums1` and `nums2` is between 1 and 200 (inclusive).*);
//@ ensures(*Each subarray in `nums1` and `nums2` has a length of 2.*);
//@ ensures(*The ids in `nums1` and `nums2` are unique and sorted in ascending order.*);
//@ ensures(*The values in `nums1` and `nums2` are between 1 and 1000 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned array is not null.*);
//@ ensures(*The length of the returned array is equal to the number of unique ids in `nums1` and `nums2`.*);
//@ ensures(*The returned array is sorted in ascending order by id.*);
//@ ensures(*Each subarray in the returned array has a length of 2.*);
//@ ensures(*The first element of each subarray in the returned array is a unique id.*);
//@ ensures(*The second element of each subarray in the returned array is the sum of the values of the corresponding id in `nums1` and `nums2`. If the id does not exist in one of the arrays, its value is considered to be 0.*);
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[][] res = new int[n1 + n2][2];
        int i = 0;
        int j = 0;
        int idx = 0;
        while (i < n1 && j < n2) {
            if (nums1[i][0] == nums2[j][0]) {
                res[idx][0] = nums1[i][0];
                res[idx][1] = nums1[i][1] + nums2[j][1];
                i++;
                j++;
            } else if (nums1[i][0] < nums2[j][0]) {
                res[idx][0] = nums1[i][0];
                res[idx][1] = nums1[i][1];
                i++;
            } else {
                res[idx][0] = nums2[j][0];
                res[idx][1] = nums2[j][1];
                j++;
            }
            idx++;
        }
        while (i < n1) {
            res[idx][0] = nums1[i][0];
            res[idx][1] = nums1[i][1];
            i++;
            idx++;
        }
        while (j < n2) {
            res[idx][0] = nums2[j][0];
            res[idx][1] = nums2[j][1];
            j++;
            idx++;
        }

        return Arrays.copyOf(res, idx);
    }
}