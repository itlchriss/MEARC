package g2001_2100.s2064_minimized_maximum_of_products_distributed_to_any_store;

// #Medium #Array #Binary_Search #2022_05_29_Time_65_ms_(70.18%)_Space_51.3_MB_(95.03%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is a positive integer representing the number of specialty retail stores.*);
//@ ensures(*The input `quantities` is a non-empty array of positive integers representing the number of products for each product type.*);
//@ ensures(*The length of `quantities` is equal to `m`.*);
//@ ensures(*Each element in `quantities` is a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the minimum possible maximum number of products given to any store.*);
//@ ensures(*The output is greater than or equal to 0.*);
//@ ensures(*The output is less than or equal to the maximum value in `quantities`.*);
//@ ensures(*The sum of all products distributed to the stores is equal to the sum of all products in `quantities`.*);
//@ ensures(*Each store is given at most one product type.*);
//@ ensures(*The number of products given to each store is a non-negative integer.*);
//@ ensures(*The maximum number of products given to any store is minimized.*);
    public int minimizedMaximum(int n, int[] q) {
        int min = 1;
        int max = maxi(q);
        int ans = 0;
        while (min <= max) {
            int mid = min + (max - min) / 2;
            if (condition(q, mid, n)) {
                ans = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return ans;
    }

    private boolean condition(int[] arr, int mid, int n) {
        int ans = 0;
        for (int num : arr) {
            ans += (num) / mid;
            if (num % mid != 0) {
                ans++;
            }
        }
        return ans <= n;
    }

    private int maxi(int[] arr) {
        int ans = 0;
        for (int n : arr) {
            ans = Math.max(ans, n);
        }
        return ans;
    }
}