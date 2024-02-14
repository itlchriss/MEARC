package g2101_2200.s2149_rearrange_array_elements_by_sign;

// #Medium #Array #Two_Pointers #Simulation #2022_06_08_Time_10_ms_(34.66%)_Space_231.2_MB_(5.61%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of `nums` is even.*);
	//@ requires(*`nums` consists of an equal number of positive and negative integers.*);
	//@ ensures(*The modified array follows the given conditions:*);
	//@ ensures(*Every consecutive pair of integers have opposite signs.*);
	//@ ensures(*For all integers with the same sign, the order in which they were present in `nums` is preserved.*);
	//@ ensures(*The rearranged array begins with a positive integer.*);
	//@ ensures(*The modified array is returned as the output.*);
    public int[] rearrangeArray(int[] nums) {
        int[] negatives = new int[nums.length / 2];
        int[] positives = new int[nums.length / 2];
        int[] result = new int[nums.length];
        int pPtr = 0;
        int nPtr = 0;
        int rPtr = 0;
        for (int num : nums) {
            if (num > 0) {
                positives[pPtr++] = num;
            } else {
                negatives[nPtr++] = num;
            }
        }
        pPtr = 0;
        nPtr = 0;
        while (pPtr < positives.length && nPtr < negatives.length) {
            result[rPtr++] = positives[pPtr++];
            result[rPtr++] = negatives[nPtr++];
        }
        return result;
    }
}