package g1501_1600.s1577_number_of_ways_where_square_of_number_is_equal_to_product_of_two_numbers;

// #Medium #Array #Hash_Table #Math #Two_Pointers
// #2022_04_11_Time_20_ms_(86.27%)_Space_41.8_MB_(94.12%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input arrays `nums1` and `nums2` are not null.*);
	//@ requires(*The lengths of `nums1` and `nums2` are both greater than or equal to 1.*);
	//@ requires(*The elements in `nums1` and `nums2` are all positive integers.*);
	//@ ensures(*The method returns an integer representing the number of valid triplets.*);
	//@ ensures(*The returned value is greater than or equal to 0.*);
    public int numTriplets(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        return count(nums1, nums2) + count(nums2, nums1);
    }

    public int count(int[] a, int[] b) {
        int m = b.length;
        int count = 0;
        for (int value : a) {
            long x = (long) value * value;
            int j = 0;
            int k = m - 1;
            while (j < k) {
                long prod = (long) b[j] * b[k];
                if (prod < x) {
                    j++;
                } else if (prod > x) {
                    k--;
                } else if (b[j] != b[k]) {
                    int jNew = j;
                    int kNew = k;
                    while (b[j] == b[jNew]) {
                        jNew++;
                    }
                    while (b[k] == b[kNew]) {
                        kNew--;
                    }
                    count += (jNew - j) * (k - kNew);
                    j = jNew;
                    k = kNew;
                } else {
                    int q = k - j + 1;
                    count += (q) * (q - 1) / 2;
                    break;
                }
            }
        }
        return count;
    }
}