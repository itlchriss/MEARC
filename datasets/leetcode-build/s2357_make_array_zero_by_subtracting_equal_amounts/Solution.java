package g2301_2400.s2357_make_array_zero_by_subtracting_equal_amounts;

// #Easy #Array #Hash_Table #Sorting #Heap_Priority_Queue #Simulation
// #2022_08_07_Time_1_ms_(98.24%)_Space_41.9_MB_(50.08%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are non-negative integers.*);
	//@ requires(*The elements in the input array `nums` are less than or equal to 100.*);
	//@ ensures(*The output is an integer representing the minimum number of operations required to make every element in `nums` equal to 0.*);
	//@ ensures(*The elements in the input array `nums` are modified such that every positive element is subtracted by a positive integer `x` less than or equal to the smallest non-zero element in `nums`.*);
	//@ ensures(*After the operations, every element in the input array `nums` is equal to 0.*);
    public int minimumOperations(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int a : nums) {
            if (a > 0) {
                set.add(a);
            }
        }
        return set.size();
    }
}