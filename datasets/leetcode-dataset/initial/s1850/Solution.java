package g1801_1900.s1850_minimum_adjacent_swaps_to_reach_the_kth_smallest_number;

// #Medium #String #Greedy #Two_Pointers #2022_05_08_Time_24_ms_(72.99%)_Space_42.8_MB_(27.01%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _the **minimum number of adjacent digit swaps** that needs to be applied to_ `num` _to reach the_ <code>k<sup>th</sup></code> _**smallest wonderful** integer_.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int getMinSwaps(String num, int k) {
        char[] result = num.toCharArray();
        while (--k >= 0) {
            int swap = result.length - 2;
            while (swap >= 0 && result[swap] >= result[swap + 1]) {
                --swap;
            }
            int pair = result.length - 1;
            while (pair > swap && result[swap] >= result[pair]) {
                --pair;
            }
            swap(result, swap, pair);
            int lo = swap + 1;
            int hi = result.length - 1;
            while (lo < hi) {
                swap(result, lo++, hi--);
            }
        }
        int ans = 0;
        char[] arr = num.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == result[i]) {
                continue;
            }
            int j = i;
            while (arr[i] != result[j]) {
                ++j;
            }
            ans += j - i;
            while (--j >= i) {
                swap(result, j, j + 1);
            }
        }
        return ans;
    }

    private void swap(char[] arr, int a, int b) {
        char tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
