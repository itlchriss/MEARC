package g2201_2300.s2217_find_palindrome_with_fixed_length;

// #Medium #Array #Math #2022_06_12_Time_37_ms_(88.60%)_Space_53.7_MB_(77.19%)

@SuppressWarnings("java:S2184")
public class Solution {
	//@ requires(*The input array `queries` must not be null.*);
	//@ requires(*The length of the input array `queries` must be greater than or equal to 1.*);
	//@ requires(*The elements in the input array `queries` must be positive integers.*);
	//@ requires(*The input integer `intLength` must be a positive integer.*);
	//@ requires(*The input integer `intLength` must be less than or equal to 15.*);
	//@ ensures(*The output array `answer` must not be null.*);
	//@ ensures(*The length of the output array `answer` must be equal to the length of the input array `queries`.*);
	//@ ensures(*Each element in the output array `answer` must be either a positive palindrome of length `intLength` or -1 if no such palindrome exists.*);
    public long[] kthPalindrome(int[] queries, int intLength) {
        long minHalf = (long) Math.pow(10, (intLength - 1) / 2);
        long maxIndex = (long) Math.pow(10, (intLength + 1) / 2) - minHalf;
        boolean isOdd = intLength % 2 == 1;
        long[] res = new long[queries.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = queries[i] > maxIndex ? -1 : helper(queries[i], minHalf, isOdd);
        }
        return res;
    }

    private long helper(long index, long minHalf, boolean isOdd) {
        long half = minHalf + index - 1;
        long res = half;
        if (isOdd) {
            res /= 10;
        }
        while (half != 0) {
            res = res * 10 + half % 10;
            half /= 10;
        }
        return res;
    }
}