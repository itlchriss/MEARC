package g1001_1100.s1011_capacity_to_ship_packages_within_d_days;

// #Medium #Array #Binary_Search #2022_02_21_Time_10_ms_(75.31%)_Space_53.9_MB_(25.82%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The weights array must not be null.*);
//@ ensures(*The days value must be greater than or equal to 1.*);
//@ ensures(*The weights array must have at least one element.*);
//@ ensures(*The weights array must not contain any negative values.*);
//@ ensures(*The weights array must not contain any values greater than 500.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned ship capacity must be the minimum capacity required to ship all the packages within the given number of days.*);
//@ ensures(*The order of the packages on the conveyor belt must be maintained.*);
//@ ensures(*The ship must be loaded with packages in the order given by the weights array.*);
//@ ensures(*The ship must not be loaded with more weight than its maximum weight capacity.*);
//@ ensures(*All the packages on the conveyor belt must be shipped within the given number of days.*);
    public int shipWithinDays(int[] weights, int days) {
        int max = weights[0];
        int sum = 0;
        for (int val : weights) {
            sum += val;
            max = Math.max(max, val);
        }
        int lo = max;
        int hi = sum;
        int ans = 0;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (isPossible(weights, mid, days)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    private boolean isPossible(int[] arr, int mid, int days) {
        int capacity = 1;
        int sum = 0;
        for (int j : arr) {
            sum += j;
            if (sum > mid) {
                capacity++;
                sum = j;
            }
        }
        return capacity <= days;
    }
}