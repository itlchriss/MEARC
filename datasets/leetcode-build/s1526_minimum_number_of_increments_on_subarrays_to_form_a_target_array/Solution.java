package g1501_1600.s1526_minimum_number_of_increments_on_subarrays_to_form_a_target_array;

// #Hard #Array #Dynamic_Programming #Greedy #Stack #Monotonic_Stack
// #2022_04_09_Time_4_ms_(70.36%)_Space_72.7_MB_(76.68%)

public class Solution {
	//@ requires(*The input array `target` is not null.*);
	//@ requires(*The length of the input array `target` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `target` is a positive integer.*);
	//@ ensures(*The output is an integer representing the minimum number of operations required to form the `target` array from the `initial` array.*);
	//@ ensures(*The `initial` array is modified such that it becomes the `target` array after the minimum number of operations.*);
	//@ ensures(*Each element in the `initial` array is a non-negative integer.*);
	//@ ensures(*The sum of all elements in the `initial` array is equal to the sum of all elements in the `target` array.*);
    public int minNumberOperations(int[] target) {
        int operations = target[0];
        for (int i = 1; i < target.length; i++) {
            if (target[i] > target[i - 1]) {
                operations += target[i] - target[i - 1];
            }
        }
        return operations;
    }
}