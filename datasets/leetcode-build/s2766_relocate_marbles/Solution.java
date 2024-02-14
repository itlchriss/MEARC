package g2701_2800.s2766_relocate_marbles;

// #Medium #Array #Hash_Table #Sorting #Simulation
// #2023_09_24_Time_47_ms_(91.67%)_Space_60.2_MB_(36.33%)

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
	//@ requires(*The `nums` array must not be null.*);
	//@ requires(*The `moveFrom` array must not be null.*);
	//@ requires(*The `moveTo` array must not be null.*);
	//@ requires(*The length of `nums` array must be greater than or equal to 1.*);
	//@ requires(*The length of `moveFrom` array must be greater than or equal to 1.*);
	//@ requires(*The length of `moveFrom` array must be equal to the length of `moveTo` array.*);
	//@ requires(*The values in `nums` array must be integers.*);
	//@ requires(*The values in `moveFrom` array must be integers.*);
	//@ requires(*The values in `moveTo` array must be integers.*);
	//@ requires(*The values in `nums` array must be within the range of 1 to 10^9.*);
	//@ requires(*The values in `moveFrom` array must be within the range of 1 to 10^9.*);
	//@ requires(*The values in `moveTo` array must be within the range of 1 to 10^9.*);
	//@ requires(*The test cases are generated such that there is at least one marble in `moveFrom[i]` at the moment we want to apply the i-th move.*);
	//@ ensures(*The returned list must not be null.*);
	//@ ensures(*The returned list must contain integers.*);
	//@ ensures(*The returned list must be sorted in ascending order.*);
	//@ ensures(*The returned list must contain the occupied positions after completing all the steps.*);
    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 0; i < moveTo.length; i++) {
            if (set.contains(moveFrom[i])) {
                set.remove(moveFrom[i]);
                set.add(moveTo[i]);
            }
        }
        List<Integer> result = new ArrayList<>(set);
        result.sort(null);
        return result;
    }
}