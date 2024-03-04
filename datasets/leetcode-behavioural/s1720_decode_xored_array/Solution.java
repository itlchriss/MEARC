package g1701_1800.s1720_decode_xored_array;

// #Easy #Array #Bit_Manipulation #2022_04_24_Time_1_ms_(100.00%)_Space_57.3_MB_(6.33%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `encoded` is not null.*);
//@ ensures(*The length of `encoded` is equal to `n - 1`, where `n` is the length of the original array `arr`.*);
//@ ensures(*The elements of `encoded` are non-negative integers.*);
//@ ensures(*The input integer `first` is not null.*);
//@ ensures(*The value of `first` is a non-negative integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `arr` is not null.*);
//@ ensures(*The length of `arr` is equal to `n`.*);
//@ ensures(*The elements of `arr` are non-negative integers.*);
//@ ensures(*The first element of `arr` is equal to the input integer `first`.*);
//@ ensures(*For each index `i` from 0 to `n - 2`, the element at index `i + 1` of `arr` is equal to the XOR of the elements at index `i` and `i + 1` of `encoded`.*);
    public int[] decode(int[] encoded, int first) {
        int[] arr = new int[encoded.length + 1];
        arr[0] = first;
        for (int i = 0; i < encoded.length; i++) {
            arr[i + 1] = encoded[i] ^ arr[i];
        }
        return arr;
    }
}