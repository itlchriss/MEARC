package g1701_1800.s1775_equal_sum_arrays_with_minimum_number_of_operations;

// #Medium #Array #Hash_Table #Greedy #Counting
// #2022_04_30_Time_16_ms_(70.88%)_Space_106.1_MB_(19.23%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input arrays `nums1` and `nums2` are not null.*);
	//@ requires(*The lengths of `nums1` and `nums2` are between 1 and 10^5, inclusive.*);
	//@ requires(*The values in `nums1` and `nums2` are between 1 and 6, inclusive.*);
	//@ ensures(*The method returns an integer representing the minimum number of operations required to make the sum of values in `nums1` equal to the sum of values in `nums2`.*);
	//@ ensures(*If it is not possible to make the sum of the two arrays equal, the method returns -1.*);
    public int minOperations(int[] nums1, int[] nums2) {
        int[] longer = nums1.length > nums2.length ? nums1 : nums2;
        int[] shorter = nums1.length > nums2.length ? nums2 : nums1;
        if (longer.length > shorter.length * 6) {
            return -1;
        }
        Arrays.sort(longer);
        Arrays.sort(shorter);
        int i = 0;
        int j = 0;
        int diff = 0;
        while (i < longer.length || j < shorter.length) {
            if (i < longer.length) {
                diff += longer[i++];
            }
            if (j < shorter.length) {
                diff -= shorter[j++];
            }
        }
        int minOps = 0;
        i = 0;
        j = shorter.length - 1;
        if (diff < 0) {
            while (diff < 0) {
                if (i < longer.length && j >= 0) {
                    if (6 - longer[i] < shorter[j] - 1) {
                        diff += shorter[j--] - 1;
                    } else {
                        diff += 6 - longer[i++];
                    }
                } else if (i < longer.length) {
                    diff += 6 - longer[i++];
                } else {
                    diff += shorter[j--] - 1;
                }
                minOps++;
            }
            return minOps;
        } else if (diff > 0) {
            i = longer.length - 1;
            j = 0;
            while (diff > 0) {
                if (i >= 0 && j < shorter.length) {
                    if (longer[i] - 1 > 6 - shorter[j]) {
                        diff -= longer[i--] - 1;
                    } else {
                        diff -= 6 - shorter[j++];
                    }
                } else if (i >= 0) {
                    diff -= longer[i--] - 1;
                } else {
                    diff -= 6 - shorter[j++];
                }
                minOps++;
            }
            return minOps;
        } else {
            return minOps;
        }
    }
}