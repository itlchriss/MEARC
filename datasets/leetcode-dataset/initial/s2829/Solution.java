package g2801_2900.s2829_determine_the_minimum_sum_of_a_k_avoiding_array;

// #Medium #Math #Greedy #2023_12_11_Time_1_ms_(100.00%)_Space_40.6_MB_(77.32%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _the **minimum** possible sum of a k-avoiding array of length_ `n`.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
