package g1501_1600.s1551_minimum_operations_to_make_array_equal;

// #Medium #Math #2022_06_22_Time_0_ms_(100.00%)_Space_41.3_MB_(35.47%)

public class Solution {
	//@ requires(*The input `n` is a positive integer.*);
	//@ requires(*The array `arr` has a length of `n`.*);
	//@ requires(*The elements of `arr` are calculated using the formula `arr[i] = (2 * i) + 1` for all valid values of `i` (i.e., `0 <= i < n`).*);
	//@ ensures(*The output is an integer representing the minimum number of operations needed to make all the elements of `arr` equal.*);
	//@ ensures(*All the elements of `arr` are equal.*);
    public int minOperations(int n) {
        return (n * n) / 4;
    }
}