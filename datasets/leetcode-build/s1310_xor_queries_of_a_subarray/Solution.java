package g1301_1400.s1310_xor_queries_of_a_subarray;

// #Medium #Array #Bit_Manipulation #Prefix_Sum #2022_03_15_Time_3_ms_(68.91%)_Space_67_MB_(34.60%)

public class Solution {
	//@ requires(*The input array `arr` is not null.*);
	//@ requires(*The input array `queries` is not null.*);
	//@ requires(*The length of `arr` is greater than or equal to - The length of `queries` is greater than or equal to - The elements in `arr` are positive integers.*);
	//@ requires(*The elements in `queries` are valid indices within the range of `arr`.*);
	//@ ensures(*The returned array `answer` is not null.*);
	//@ ensures(*The length of `answer` is equal to the length of `queries`.*);
	//@ ensures(*Each element in `answer` is the XOR of the elements in `arr` from the corresponding range specified in `queries`.*);
    public int[] xorQueries(int[] a, int[][] queries) {
        int[] res = new int[queries.length];
        for (int i = 1; i < a.length; i++) {
            a[i] = a[i - 1] ^ a[i];
        }
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            res[i] = (query[0] == 0 ? a[query[1]] : a[query[0] - 1] ^ a[query[1]]);
        }
        return res;
    }
}