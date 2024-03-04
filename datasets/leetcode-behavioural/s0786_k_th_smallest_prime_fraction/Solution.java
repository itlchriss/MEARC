package g0701_0800.s0786_k_th_smallest_prime_fraction;

// #Medium #Array #Binary_Search #Heap_Priority_Queue
// #2022_03_26_Time_2_ms_(96.60%)_Space_42.4_MB_(86.79%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is at least 2.*);
//@ ensures(*The input array `arr` is sorted in strictly increasing order.*);
//@ ensures(*The first element of the input array `arr` is 1.*);
//@ ensures(*All elements of the input array `arr` are prime numbers.*);
//@ ensures(*The input integer `k` is greater than or equal to 1.*);
//@ ensures(*The input integer `k` is less than or equal to `arr.length * (arr.length - 1) / 2`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `answer` is not null.*);
//@ ensures(*The length of the output array `answer` is 2.*);
//@ ensures(*The first element of the output array `answer` is equal to `arr[i]`, where `i` is the index of the numerator in the fraction.*);
//@ ensures(*The second element of the output array `answer` is equal to `arr[j]`, where `j` is the index of the denominator in the fraction.*);
//@ ensures(*The fraction `arr[i] / arr[j]` is the `k`-th smallest fraction considered.*);
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        double low = 0;
        double high = 1.0;
        while (low < high) {
            double mid = (low + high) / 2;
            int[] res = getFractionsLessThanMid(arr, n, mid);
            if (res[0] == k) {
                return new int[] {arr[res[1]], arr[res[2]]};
            } else if (res[0] > k) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return new int[] {};
    }

    private int[] getFractionsLessThanMid(int[] arr, int n, double mid) {
        double maxLessThanMid = 0.0;
        // stores indices of max fraction less than mid;
        int x = 0;
        int y = 0;
        // for storing fractions less than mid
        int total = 0;
        int j = 1;
        for (int i = 0; i < n - 1; i++) {
            // while fraction is greater than mid increment j
            while (j < n && arr[i] > arr[j] * mid) {
                j++;
            }
            if (j == n) {
                break;
            }
            // j fractions greater than mid, n-j fractions smaller than mid, add fractions smaller
            // than mid to total
            total += (n - j);
            double fraction = (double) arr[i] / arr[j];
            if (fraction > maxLessThanMid) {
                maxLessThanMid = fraction;
                x = i;
                y = j;
            }
        }
        return new int[] {total, x, y};
    }
}