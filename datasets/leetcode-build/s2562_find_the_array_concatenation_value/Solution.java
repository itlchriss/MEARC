package g2501_2600.s2562_find_the_array_concatenation_value;

// #Easy #Array #Two_Pointers #Simulation #2023_08_21_Time_0_ms_(100.00%)_Space_42.5_MB_(97.64%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of `nums` is greater than or equal to 1.*);
	//@ requires(*The elements of `nums` are integers.*);
	//@ requires(*The elements of `nums` are greater than or equal to 1.*);
	//@ requires(*The elements of `nums` are less than or equal to 10^4.*);
	//@ ensures(*The method returns a long value representing the concatenation value of `nums`.*);
	//@ ensures(*The input array `nums` is modified such that it becomes empty after performing the operations.*);
	//@ ensures(*The concatenation value of `nums` is calculated by repeatedly picking the first and last elements of `nums`, concatenating their values, and adding it to the concatenation value.*);
	//@ ensures(*If `nums` has more than one element, the first and last elements are deleted after each operation.*);
	//@ ensures(*If `nums` has only one element, it is deleted after the operation.*);
    public long findTheArrayConcVal(int[] nums) {
        long sum = 0;
        int idxLeft = 0;
        int idxRight = nums.length - 1;
        while (idxLeft < idxRight) {
            sum += getConcatenationValue(nums[idxLeft], nums[idxRight]);
            ++idxLeft;
            --idxRight;
        }
        if (idxLeft == idxRight) {
            sum += nums[idxLeft];
        }
        return sum;
    }

    private long getConcatenationValue(int leftVal, int rightVal) {
        if ((long) rightVal == 10000) {
            return leftVal * 100000L + rightVal;
        }
        if ((long) rightVal >= 1000) {
            return leftVal * 10000L + rightVal;
        }
        if ((long) rightVal >= 100) {
            return leftVal * 1000L + rightVal;
        }
        if ((long) rightVal >= 10) {
            return leftVal * 100L + rightVal;
        }
        return leftVal * 10L + rightVal;
    }
}