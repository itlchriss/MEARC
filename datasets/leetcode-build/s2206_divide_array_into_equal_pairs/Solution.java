package g2201_2300.s2206_divide_array_into_equal_pairs;

// #Easy #Array #Hash_Table #Bit_Manipulation #Counting
// #2022_06_11_Time_1_ms_(100.00%)_Space_42.2_MB_(94.00%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of `nums` is even.*);
	//@ requires(*The length of `nums` is equal to `2 * n`.*);
	//@ requires(*The value of `n` is greater than or equal to 1.*);
	//@ requires(*The value of `n` is less than or equal to 500.*);
	//@ requires(*Each element in `nums` is an integer.*);
	//@ requires(*Each element in `nums` is greater than or equal to 1.*);
	//@ requires(*Each element in `nums` is less than or equal to 500.*);
	//@ ensures(*The method returns a boolean value indicating whether `nums` can be divided into `n` pairs.*);
	//@ ensures(*If `nums` can be divided into `n` pairs, the method returns `true`.*);
	//@ ensures(*If `nums` cannot be divided into `n` pairs, the method returns `false`.*);
    public boolean divideArray(int[] nums) {
        int[] freq = new int[501];
        for (int num : nums) {
            ++freq[num];
        }
        for (int f : freq) {
            if (f % 2 != 0) {
                return false;
            }
        }
        return true;
    }
}