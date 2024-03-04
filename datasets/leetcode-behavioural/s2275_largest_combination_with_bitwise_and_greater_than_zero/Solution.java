package g2201_2300.s2275_largest_combination_with_bitwise_and_greater_than_zero;

// #Medium #Array #Hash_Table #Bit_Manipulation #Counting
// #2022_06_16_Time_19_ms_(96.92%)_Space_74.4_MB_(81.12%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `candidates` must not be null.*);
//@ ensures(*The length of the input array `candidates` must be greater than or equal to - Each element in the input array `candidates` must be a positive integer.*);
//@ ensures(*Each element in the input array `candidates` must be unique.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer representing the size of the largest combination of `candidates` with a bitwise AND greater than 0.*);
    public int largestCombination(int[] candidates) {
        int[] bits = new int[32];
        for (int x : candidates) {
            for (int i = 0; x != 0; i++, x >>= 1) {
                bits[i] += x & 1;
            }
        }
        int ans = 0;
        for (int b : bits) {
            ans = Math.max(ans, b);
        }
        return ans;
    }
}