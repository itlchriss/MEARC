package g1501_1600.s1524_number_of_sub_arrays_with_odd_sum;

// #Medium #Array #Dynamic_Programming #Math #Prefix_Sum
// #2022_04_09_Time_9_ms_(90.24%)_Space_97_MB_(68.09%)

public class Solution {
	//@ requires(*The input array `arr` is not null.*);
	//@ requires(*The length of the input array `arr` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `arr` is an integer.*);
	//@ requires(*Each element in the input array `arr` is between 1 and 100 (inclusive).*);
	//@ ensures(*The return value is an integer.*);
	//@ ensures(*The return value is the number of subarrays with an odd sum.*);
	//@ ensures(*The return value is modulo 10^9 + 7.*);
    public int numOfSubarrays(int[] arr) {
        int number = arr[0] % 2 == 0 ? 0 : 1;
        long res = number;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                number = i - number + 1;
            }
            res += number;
        }
        long mod = 1_000_000_007;
        return (int) (res % mod);
    }
}