package g0901_1000.s0941_valid_mountain_array;

// #Easy #Array #2022_03_30_Time_1_ms_(100.00%)_Space_43_MB_(93.41%)

public class Solution {
	//@ requires(*1. The input array `arr` must not be null.*);
	//@ requires(*2. The length of the input array `arr` must be greater than or equal to 3.*);
	//@ requires(*3. The elements of the input array `arr` must be integers.*);
	//@ requires(*4. The elements of the input array `arr` must be non-negative.*);
	//@ ensures(*1. The method should return `true` if the input array `arr` is a valid mountain array.*);
	//@ ensures(*2. The method should return `false` if the input array `arr` is not a valid mountain array.*);
    public boolean validMountainArray(int[] arr) {
        int i = 0;
        for (; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                return false;
            } else if (arr[i] > arr[i + 1]) {
                break;
            }
        }
        if (i == 0 || i >= arr.length - 1) {
            return false;
        }
        for (; i < arr.length - 1; i++) {
            if (arr[i] <= arr[i + 1]) {
                return false;
            }
        }
        return i == arr.length - 1;
    }
}