package g1401_1500.s1471_the_k_strongest_values_in_an_array;

// #Medium #Array #Sorting #Two_Pointers #2022_03_29_Time_37_ms_(88.20%)_Space_81.7_MB_(70.81%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is greater than or equal to 1.*);
//@ ensures(*The value of `k` is greater than or equal to 1.*);
//@ ensures(*The value of `k` is less than or equal to the length of the input array `arr`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an array of integers.*);
//@ ensures(*The length of the output array is equal to the value of `k`.*);
//@ ensures(*The elements in the output array are the strongest `k` values in the input array `arr`.*);
//@ ensures(*The order of the elements in the output array is arbitrary.*);
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int[] array = new int[k];
        int median = arr[(arr.length - 1) / 2];
        int start = 0;
        int end = arr.length - 1;
        for (int i = 0; i < k; i++) {
            if (Math.abs(arr[end] - median) >= Math.abs(arr[start] - median)) {
                array[i] = arr[end];
                end -= 1;
            } else {
                array[i] = arr[start];
                start += 1;
            }
        }
        return array;
    }
}