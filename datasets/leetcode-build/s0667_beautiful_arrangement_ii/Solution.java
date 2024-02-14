package g0601_0700.s0667_beautiful_arrangement_ii;

// #Medium #Array #Math #2022_03_22_Time_1_ms_(84.62%)_Space_42.4_MB_(81.82%)

public class Solution {
	//@ requires(*The input integers `n` and `k` are both positive.*);
	//@ requires(*`k` is less than `n`.*);
	//@ requires(*`n` is less than or equal to 10^*);
	//@ ensures(*The output is an array of length `n`.*);
	//@ ensures(*The array contains `n` different positive integers ranging from 1 to `n`.*);
	//@ ensures(*The absolute difference between any two adjacent elements in the array has exactly `k` distinct integers.*);
    public int[] constructArray(int n, int k) {
        int[] res = new int[n];
        int left = 1;
        int right = n;
        for (int i = 0; i < n; i++) {
            res[i] = k % 2 == 0 ? left++ : right--;
            if (k > 1) {
                k--;
            }
        }
        return res;
    }
}