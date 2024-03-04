package g2501_2600.s2527_find_xor_beauty_of_array;

// #Medium #Array #Math #Bit_Manipulation #2023_04_19_Time_1_ms_(100.00%)_Space_59.1_MB_(62.11%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `nums` is a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value representing the xor-beauty of the input array `nums`.*);
    public int xorBeauty(int[] arr) {
        int i = 1;
        while (i < arr.length) {
            arr[0] ^= arr[i];
            i++;
        }
        return arr[0];
    }
}