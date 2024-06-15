package g0901_1000.s0941_valid_mountain_array;

// #Easy #Array #2022_03_30_Time_1_ms_(100.00%)_Space_43_MB_(93.41%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires(\forall int i; 0 <= i < arr.length; ((arr[i] <= 10000) && (arr[i] >= 0)));
//@ requires((arr.length <= 10000) && (arr.length >= 1));
//@ requires(arr.length >= 3);
//@ ensures((Arrays.equals(arr, new int[] {2 , 1})) ==> (\result == false));
//@ ensures((Arrays.equals(arr, new int[] {0 , 3 , 2 , 1})) ==> (\result == true));
//@ ensures((Arrays.equals(arr, new int[] {3 , 5 , 5})) ==> (\result == false));
    public boolean validMountainArray(int[] arr) {
        int i = 0;
        for (; i <= arr.length - 1; i++) {
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
