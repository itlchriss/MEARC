package g1301_1400.s1354_construct_target_array_with_multiple_sums;

// #Hard #Array #Heap_Priority_Queue #2022_03_21_Time_2_ms_(100.00%)_Space_56.7_MB_(76.64%)

public class Solution {
	//@ requires(*The input array `target` is not null.*);
	//@ requires(*The length of the input array `target` is greater than 0.*);
	//@ requires(*The length of the input array `target` is less than or equal to 5 * 10^4.*);
	//@ requires(*Each element in the input array `target` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `target` is less than or equal to 10^9.*);
	//@ ensures(*The method returns a boolean value indicating whether it is possible to construct the `target` array from the starting array `arr`.*);
	//@ ensures(*If it is possible to construct the `target` array from the starting array `arr`, the method returns true.*);
	//@ ensures(*If it is not possible to construct the `target` array from the starting array `arr`, the method returns false.*);
    public boolean isPossible(int[] target) {
        long sum = target[0];
        int maxIndex = 0;
        for (int i = 1; i < target.length; i++) {
            sum += target[i];
            if (target[i] > target[maxIndex]) {
                maxIndex = i;
            }
        }
        long remainingSum = sum - target[maxIndex];
        if (target[maxIndex] == 1 || remainingSum == 1) {
            return true;
        }
        if (remainingSum >= target[maxIndex]
                || remainingSum == 0
                || target[maxIndex] % remainingSum == 0) {
            return false;
        }
        target[maxIndex] %= remainingSum;
        return isPossible(target);
    }
}