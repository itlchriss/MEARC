package g1801_1900.s1850_minimum_adjacent_swaps_to_reach_the_kth_smallest_number;

// #Medium #String #Greedy #Two_Pointers #2022_05_08_Time_24_ms_(72.99%)_Space_42.8_MB_(27.01%)

public class Solution {
	//@ requires(*The input string `num` must have a length between 2 and 1000.*);
	//@ requires(*The input integer `k` must be between 1 and 1000.*);
	//@ requires(*The input string `num` must only consist of digits.*);
	//@ ensures(*The method returns an integer representing the minimum number of adjacent digit swaps needed to reach the kth smallest wonderful integer.*);
	//@ ensures(*The kth smallest wonderful integer exists for the given input.*);
	//@ ensures(*The input string `num` remains unchanged after the method is called.*);
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
}