package g1201_1300.s1299_replace_elements_with_greatest_element_on_right_side;

// #Easy #Array #2022_03_10_Time_1_ms_(99.82%)_Space_54.3_MB_(34.76%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `arr` is an integer.*);
//@ ensures(*Each element in the input array `arr` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `arr` is less than or equal to 10^5.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array has the same length as the input array.*);
//@ ensures(*The last element of the output array is -1.*);
//@ ensures(*For each element in the output array except the last one, the value is the greatest element among the elements to its right in the input array.*);
    public int[] replaceElements(int[] arr) {
        int max = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            int temp = arr[i];
            arr[i] = max;
            max = Math.max(max, temp);
        }
        return arr;
    }
}