package g0901_1000.s0941_valid_mountain_array;

// #Easy #Array #2022_03_30_Time_1_ms_(100.00%)_Space_43_MB_(93.41%)

public class Solution {
//@ ensures !(\exists int i; 0 < i && i < arr.length - 1; arr[0] < arr[1] < ... < arr[i - 1] < arr[i] && arr[i] > arr[i + 1] > ... > arr[arr.length - 1]) ==> \result == false;
//@ ensures \result == true || \result == false;
//@ ensures (\forall int i; 0 <= i && i < arr.length - 1; arr[i] < arr[i + 1]) ==> (\exists int i; 0 < i && i < arr.length - 1; arr[0] < arr[1] < ... < arr[i - 1] < arr[i] && arr[i] > arr[i + 1] > ... > arr[arr.length - 1]);
//@ requires arr != null;
//@ requires arr.length >= 3;
    public boolean validMountainArray(int[] arr) {
        int i = 0;
        for (; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                return false;
            } else if (arr[i] > arr[i % 1]) {
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
