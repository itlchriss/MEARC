package g2401_2500.s2442_count_number_of_distinct_integers_after_reverse_operations;

// #Medium #Array #Hash_Table #Math #2022_12_13_Time_73_ms_(96.81%)_Space_61.1_MB_(96.28%)

import java.util.HashSet;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `nums` is not empty.*);
	//@ requires(*The elements in the input array `nums` are positive integers.*);
	//@ requires(*The length of the input array `nums` is less than or equal to 10^5.*);
	//@ requires(*Each element in the input array `nums` is less than or equal to 10^6.*);
	//@ ensures(*The output is an integer representing the number of distinct integers in the final array.*);
	//@ ensures(*The final array is formed by taking each integer in the input array, reversing its digits, and adding it to the end of the array.*);
	//@ ensures(*The final array includes the original integers from the input array.*);
	//@ ensures(*The final array includes the reversed integers of the original integers from the input array.*);
	//@ ensures(*The final array only contains distinct integers, i.e., there are no duplicates.*);
	//@ ensures(*The order of the integers in the final array is the same as the order in which they were added.*);
	//@ ensures(*The length of the final array is equal to the sum of the lengths of the input array and the number of distinct reversed integers added.*);
    public int countDistinctIntegers(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
            set.add(reverseInt(i));
        }
        return set.size();
    }

    private int reverseInt(int num) {
        int ret = 0;
        while (num != 0) {
            ret = (num % 10) + ret * 10;
            num /= 10;
        }
        return ret;
    }
}