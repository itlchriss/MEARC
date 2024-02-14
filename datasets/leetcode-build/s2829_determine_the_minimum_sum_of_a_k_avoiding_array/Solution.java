package g2801_2900.s2829_determine_the_minimum_sum_of_a_k_avoiding_array;

// #Medium #Math #Greedy #2023_12_11_Time_1_ms_(100.00%)_Space_40.6_MB_(77.32%)

public class Solution {
	//@ requires(*The input integers `n` and `k` must be positive.*);
	//@ requires(*The input integer `n` must be greater than or equal to 1.*);
	//@ requires(*The input integer `k` must be greater than or equal to 1.*);
	//@ ensures(*The output integer must be the minimum possible sum of a k-avoiding array of length `n`.*);
	//@ ensures(*The output integer must be greater than or equal to 0.*);
    public int minimumSum(int n, int k) {
        int[] arr = new int[n];
        int a = k / 2;
        int sum = 0;
        if (a > n) {
            for (int i = 0; i < n; i++) {
                arr[i] = i + 1;
                sum += arr[i];
            }
        } else {
            for (int i = 0; i < a; i++) {
                arr[i] = i + 1;
                sum += arr[i];
            }
            for (int j = a; j < n; j++) {
                arr[j] = k++;
                sum += arr[j];
            }
        }
        return sum;
    }
}