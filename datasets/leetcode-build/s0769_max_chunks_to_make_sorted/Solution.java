package g0701_0800.s0769_max_chunks_to_make_sorted;

// #Medium #Array #Sorting #Greedy #Stack #Monotonic_Stack
// #2022_03_26_Time_0_ms_(100.00%)_Space_41.8_MB_(23.34%)

public class Solution {
	//@ requires(*The input array `arr` is not null.*);
	//@ requires(*The length of the input array `arr` is greater than 0.*);
	//@ requires(*The input array `arr` represents a permutation of the integers in the range `[0, n - 1]`.*);
	//@ requires(*All elements in the input array `arr` are unique.*);
	//@ ensures(*The return value is an integer representing the largest number of chunks that can be made to sort the array.*);
	//@ ensures(*The chunks are individually sorted.*);
	//@ ensures(*After concatenating the sorted chunks, the result is equal to the sorted array.*);
    public int maxChunksToSorted(int[] arr) {
        int ans = 0;
        int max = 0;
        for (int i = 0; i < arr.length; ++i) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                ans++;
            }
        }
        return ans;
    }
}