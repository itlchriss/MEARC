package g1701_1800.s1734_decode_xored_permutation;

// #Medium #Array #Bit_Manipulation #2022_04_29_Time_6_ms_(34.52%)_Space_140.9_MB_(66.67%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `encoded` is not null.*);
//@ ensures(*The length of the input array `encoded` is equal to `n - 1`, where `n` is the length of the original array `perm`.*);
//@ ensures(*The length of the input array `encoded` is at least 2.*);
//@ ensures(*The elements in the input array `encoded` are non-negative integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `perm` is not null.*);
//@ ensures(*The length of the output array `perm` is equal to `n`.*);
//@ ensures(*The elements in the output array `perm` are positive integers.*);
//@ ensures(*The output array `perm` is a permutation of the first `n` positive integers.*);
//@ ensures(*The output array `perm` satisfies the condition `encoded[i] = perm[i] XOR perm[i + 1]` for all valid indices `i` in the range [0, `n - 2`].*);
//@ ensures(*The output array `perm` is unique and there is no other permutation that satisfies the condition `encoded[i] = perm[i] XOR perm[i + 1]` for all valid indices `i` in the range [0, `n - 2`].*);
    public int[] decode(int[] encoded) {
        int[] decoded = new int[encoded.length + 1];
        for (int i = 1; i < encoded.length; i = i + 2) {
            decoded[0] = decoded[0] ^ encoded[i];
            decoded[0] = decoded[0] ^ i;
            decoded[0] = decoded[0] ^ i + 1;
        }
        decoded[0] = decoded[0] ^ decoded.length;
        for (int i = 1; i < decoded.length; i++) {
            decoded[i] = decoded[i - 1] ^ encoded[i - 1];
        }
        return decoded;
    }
}