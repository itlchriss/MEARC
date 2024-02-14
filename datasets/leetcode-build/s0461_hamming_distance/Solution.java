package g0401_0500.s0461_hamming_distance;

// #Easy #Bit_Manipulation #Udemy_Bit_Manipulation
// #2022_07_19_Time_0_ms_(100.00%)_Space_40.9_MB_(60.77%)

public class Solution {
	//@ requires(*The method takes two integer parameters, x and y.*);
	//@ requires(*The values of x and y are non-negative integers.*);
	//@ requires(*The values of x and y are within the range of 0 to 2^31 -*);
	//@ ensures(*The method returns an integer value representing the Hamming distance between x and y.*);
	//@ ensures(*The returned value is the number of positions at which the corresponding bits of x and y are different.*);
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}