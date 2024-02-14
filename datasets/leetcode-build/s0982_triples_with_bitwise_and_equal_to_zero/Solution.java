package g0901_1000.s0982_triples_with_bitwise_and_equal_to_zero;

// #Hard #Array #Hash_Table #Bit_Manipulation
// #2022_03_31_Time_120_ms_(79.59%)_Space_55.5_MB_(18.37%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The length of the input array `nums` is less than or equal to 1000.*);
	//@ requires(*Each element in the input array `nums` is a non-negative integer.*);
	//@ requires(*Each element in the input array `nums` is less than 2^16.*);
	//@ ensures(*The method returns an integer representing the number of AND triples.*);
	//@ ensures(*The returned integer is greater than or equal to 0.*);
    public int countTriplets(int[] nums) {
        int[] arr = new int[1 << 17];
        for (int num : nums) {
            int mask = 0;
            for (int i = 0; i < 16; i++) {
                if ((num & (1 << i)) == 0) {
                    mask |= (1 << i);
                }
            }
            int s = mask;
            while (s > 0) {
                arr[s]++;
                s = (s - 1) & mask;
            }
        }
        int count = 0;
        for (int j : nums) {
            for (int num : nums) {
                int val = j & num;
                if (val == 0) {
                    count += nums.length;
                } else {
                    int mask = 0;
                    for (int k = 0; k < 16; k++) {
                        if ((val & (1 << k)) > 0) {
                            mask |= (1 << k);
                        }
                    }
                    count += arr[mask];
                }
            }
        }
        return count;
    }
}