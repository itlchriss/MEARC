package g1201_1300.s1287_element_appearing_more_than_25_in_sorted_array;

// #Easy #Array #2022_03_11_Time_0_ms_(100.00%)_Space_46.7_MB_(65.15%)

public class Solution {
	//@ requires(*The input array `arr` is sorted in non-decreasing order.*);
	//@ requires(*The length of the input array `arr` is between 1 and 10^4.*);
	//@ requires(*The elements in the input array `arr` are between 0 and 10^5.*);
	//@ ensures(*The method returns an integer that occurs more than 25% of the time in the input array `arr`.*);
    public int findSpecialInteger(int[] arr) {
        int quarter = arr.length / 4;
        for (int i = 0; i < arr.length - quarter; i++) {
            if (arr[i] == arr[i + quarter]) {
                return arr[i];
            }
        }
        return -1;
    }
}