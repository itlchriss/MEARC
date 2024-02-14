package g2301_2400.s2317_maximum_xor_after_operations;

// #Medium #Array #Math #Bit_Manipulation #2022_06_26_Time_1_ms_(100.00%)_Space_53.3_MB_(100.00%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to - Each element in the input array `nums` is a non-negative integer.*);
	//@ requires(*Each element in the input array `nums` is less than or equal to 10^*);
	//@ ensures(*The method returns an integer value.*);
	//@ ensures(*The returned integer value represents the maximum possible bitwise XOR of all elements in the input array `nums` after applying the operation any number of times.*);
    public int maximumXOR(int[] nums) {
        int max = 0;
        for (int n : nums) {
            max |= n;
        }
        return max;
    }
}