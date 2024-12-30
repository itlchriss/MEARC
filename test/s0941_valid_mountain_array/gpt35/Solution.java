package g0901_1000.s0941_valid_mountain_array;

// #Easy #Array #2022_03_30_Time_1_ms_(100.00%)_Space_43_MB_(93.41%)

public class Solution {
//@ requires(*The length of the integer array parameter `arr` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `arr` are less than or equal to 10000 and are greater than or equal to 0.*);
//@ ensures(*The boolean result is equal to true if and only if the integer array parameter `arr` is a valid mountain array.*);
//@ ensures(*The boolean result is equal to false if the integer array parameter `arr` is not a valid mountain array.*);
//@ ensures(*If the integer array parameter `arr` is equal to [2,1], the boolean result is equal to false.*);
//@ ensures(*If the integer array parameter `arr` is equal to [3,5,5], the boolean result is equal to false.*);
//@ ensures(*If the integer array parameter `arr` is equal to [0,3,2,1], the boolean result is equal to true.*);
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