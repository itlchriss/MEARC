package g2501_2600.s2568_minimum_impossible_or;

// #Medium #Array #Bit_Manipulation #Brainteaser
// #2023_08_21_Time_14_ms_(81.48%)_Space_62.4_MB_(6.17%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to - The elements in the input array `nums` are positive integers.*);
	//@ requires(*The elements in the input array `nums` are not greater than 10^*);
	//@ ensures(*The method returns a positive non-zero integer.*);
	//@ ensures(*The returned integer is the minimum integer that is not expressible from the input array `nums`.*);
    public int minImpossibleOR(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (n == 1 || (n & 1) == 0) {
                set.add(n);
            }
        }
        int powerOfTwo = 1;
        while (true) {
            if (!set.contains(powerOfTwo)) {
                return powerOfTwo;
            }
            powerOfTwo *= 2;
        }
    }
}