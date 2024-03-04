package g1201_1300.s1300_sum_of_mutated_array_closest_to_target;

// #Medium #Array #Sorting #Binary_Search #Binary_Search_II_Day_16
// #2022_03_08_Time_7_ms_(33.33%)_Space_47.5_MB_(28.89%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is greater than or equal to 1.*);
//@ ensures(*The target value `target` is greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned value is an integer.*);
//@ ensures(*The returned value is the minimum integer such that when all the integers larger than it in the input array `arr` are changed to be equal to it, the sum of the array is as close as possible to the target value `target`.*);
//@ ensures(*If there are multiple integers that satisfy the above condition, the returned value is the minimum among them.*);
    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length;
        int lo = 0;
        int hi = arr[n - 1];
        int min = Integer.MAX_VALUE;
        int ans = -1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int m = check(mid, arr, target);
            int l = check(mid - 1, arr, target);
            int r = check(mid + 1, arr, target);
            if (m < min || (m == min && ans > mid)) {
                min = m;
                ans = mid;
            } else if (l <= r) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    public int check(int v, int[] arr, int target) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= v) {
                return Math.abs(sum + (arr.length - i) * v - target);
            } else {
                sum += arr[i];
            }
        }
        return Math.abs(sum - target);
    }
}