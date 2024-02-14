package g1401_1500.s1441_build_an_array_with_stack_operations;

// #Easy #Array #Stack #Simulation #2022_03_28_Time_1_ms_(38.47%)_Space_43.3_MB_(57.71%)

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
	//@ requires(*The `target` array is not null.*);
	//@ requires(*The `target` array is strictly increasing.*);
	//@ requires(*The length of the `target` array is between 1 and 100 (inclusive).*);
	//@ requires(*The value of `n` is between 1 and 100 (inclusive).*);
	//@ requires(*Each element in the `target` array is between 1 and `n` (inclusive).*);
	//@ ensures(*The returned list is not null.*);
	//@ ensures(*The returned list contains the operations needed to build the `target` array.*);
	//@ ensures(*If the `target` array is already built, the returned list is empty.*);
	//@ ensures(*The length of the returned list is equal to the number of operations needed to build the `target` array.*);
    public List<String> buildArray(int[] target, int n) {
        List<String> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i : target) {
            set.add(i);
        }
        int max = target[target.length - 1];
        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)) {
                result.add("Push");
                result.add("Pop");
            } else {
                result.add("Push");
            }
            if (i == max) {
                break;
            }
        }
        return result;
    }
}