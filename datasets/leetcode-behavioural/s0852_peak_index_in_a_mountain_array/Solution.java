package g0801_0900.s0852_peak_index_in_a_mountain_array;

// #Easy #Array #Binary_Search #Binary_Search_I_Day_2
// #2022_03_28_Time_0_ms_(100.00%)_Space_46.1_MB_(68.36%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is greater than or equal to 3.*);
//@ ensures(*The input array `arr` is guaranteed to be a mountain array.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer `i` such that `arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1]`.*);
//@ ensures(*The returned integer `i` is within the range `0 < i < arr.length - 1`.*);
    public int peakIndexInMountainArray(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return -1;
    }
}