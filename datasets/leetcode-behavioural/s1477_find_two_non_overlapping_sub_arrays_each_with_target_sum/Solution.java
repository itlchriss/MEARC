package g1401_1500.s1477_find_two_non_overlapping_sub_arrays_each_with_target_sum;

// #Medium #Array #Hash_Table #Dynamic_Programming #Binary_Search #Sliding_Window
// #2022_04_04_Time_8_ms_(89.43%)_Space_50.1_MB_(97.73%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is greater than or equal to 2.*);
//@ ensures(*The target sum `target` is a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum sum of the lengths of the two required sub-arrays.*);
//@ ensures(*If there are no two non-overlapping sub-arrays with a sum equal to the target, the method returns -1.*);
    public int minSumOfLengths(int[] arr, int target) {
        int l = 0;
        int r = 0;
        int sum = 0;
        int[] idx = new int[arr.length];
        Arrays.fill(idx, arr.length + 1);
        int ans = 2 * arr.length + 1;

        while (r < arr.length || sum >= target) {
            if (sum < target) {
                sum += arr[r];
                r++;
            } else if (sum > target) {
                sum -= arr[l];
                l++;
            } else {
                int length = r - l;
                idx[r - 1] = length;
                if (l > 0 && idx[l - 1] < arr.length + 1) {
                    ans = Math.min(ans, length + idx[l - 1]);
                }
                sum -= arr[l];
                l++;
            }
            if (r > 1) {
                idx[r - 1] = Math.min(idx[r - 1], idx[r - 2]);
            }
        }
        return ans <= 2 * arr.length ? ans : -1;
    }
}