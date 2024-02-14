package g2001_2100.s2035_partition_array_into_two_arrays_to_minimize_sum_difference;

// #Hard #Array #Dynamic_Programming #Binary_Search #Two_Pointers #Bit_Manipulation #Ordered_Set
// #Bitmask #2022_05_25_Time_1336_ms_(39.36%)_Space_117.6_MB_(50.53%)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is even.*);
	//@ requires(*The length of the input array `nums` is equal to `2 * n`.*);
	//@ requires(*The value of `n` is greater than or equal to 1 and less than or equal to 15.*);
	//@ requires(*The values in the input array `nums` are integers.*);
	//@ requires(*The values in the input array `nums` are within the range of -10^7 to 10^7.*);
	//@ ensures(*The method returns an integer value representing the minimum possible absolute difference.*);
	//@ ensures(*The absolute difference is calculated by finding the difference between the sums of the two arrays obtained after partitioning the input array `nums`.*);
	//@ ensures(*The two arrays obtained after partitioning the input array `nums` have a length of `n`.*);
	//@ ensures(*The sum of the elements in the first array plus the sum of the elements in the second array is equal to the sum of all elements in the input array `nums`.*);
	//@ ensures(*The absolute difference is minimized, meaning there is no other partitioning of the input array `nums` that results in a smaller absolute difference.*);
    public int minimumDifference(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int n = nums.length / 2;
        int sum = 0;
        List<List<Integer>> arr1 = new ArrayList<>();
        List<List<Integer>> arr2 = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            arr1.add(new ArrayList<>());
            arr2.add(new ArrayList<>());
            if (i < n) {
                sum += nums[i];
                sum += nums[i + n];
            }
        }
        for (int state = 0; state < (1 << n); state++) {
            int sum1 = 0;
            int sum2 = 0;
            for (int i = 0; i < n; i++) {
                if ((state & (1 << i)) == 0) {
                    continue;
                }
                int a1 = nums[i];
                int a2 = nums[i + n];
                sum1 += a1;
                sum2 += a2;
            }
            int numOfEleInSet = Integer.bitCount(state);
            arr1.get(numOfEleInSet).add(sum1);
            arr2.get(numOfEleInSet).add(sum2);
        }
        for (int i = 0; i <= n; i++) {
            Collections.sort(arr2.get(i));
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            List<Integer> sums1 = arr1.get(i);
            List<Integer> sums2 = arr2.get(n - i);
            for (int s1 : sums1) {
                int idx = Collections.binarySearch(sums2, sum / 2 - s1);
                if (idx < 0) {
                    idx = -(idx + 1);
                }
                if (idx < sums1.size()) {
                    min =
                            Math.min(
                                    min,
                                    Math.abs((sum - s1 - sums2.get(idx)) - (sums2.get(idx) + s1)));
                }
                if (idx - 1 >= 0) {
                    min =
                            Math.min(
                                    min,
                                    Math.abs(
                                            (sum - s1 - sums2.get(idx - 1))
                                                    - (sums2.get(idx - 1) + s1)));
                }
            }
        }
        return min;
    }
}