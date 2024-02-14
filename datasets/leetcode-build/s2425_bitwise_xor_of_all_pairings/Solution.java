package g2401_2500.s2425_bitwise_xor_of_all_pairings;

// #Medium #Array #Bit_Manipulation #Brainteaser
// #2022_11_19_Time_5_ms_(38.15%)_Space_86.3_MB_(17.15%)

public class Solution {
	//@ requires(*The input arrays `nums1` and `nums2` are not null.*);
	//@ requires(*The lengths of `nums1` and `nums2` are greater than or equal to 1.*);
	//@ requires(*The elements in `nums1` and `nums2` are non-negative integers.*);
	//@ ensures(*The method returns an integer.*);
	//@ ensures(*The returned integer is the bitwise XOR of all integers in `nums3`.*);
	//@ ensures(*The length of `nums3` is equal to the product of the lengths of `nums1` and `nums2`.*);
	//@ ensures(*The elements in `nums3` are the bitwise XOR of all pairings of integers between `nums1` and `nums2`.*);
    public int xorAllNums(int[] nums1, int[] nums2) {
        int xor = 0;
        if (nums2.length % 2 == 1) {
            for (int x : nums1) {
                xor ^= x;
            }
        }
        if (nums1.length % 2 == 1) {
            for (int x : nums2) {
                xor ^= x;
            }
        }
        return xor;
    }
}