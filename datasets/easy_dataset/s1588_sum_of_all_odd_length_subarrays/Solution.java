package g1501_1600.s1588_sum_of_all_odd_length_subarrays;

// #Easy #Array #Math #Prefix_Sum #Programming_Skills_I_Day_6_Array
// #2022_04_11_Time_0_ms_(100.00%)_Space_41.9_MB_(46.97%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `arr` are positive integers.*);
//@ ensures(*The elements in the input array `arr` are not null.*);
//@ ensures(*The elements in the input array `arr` are not empty.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value.*);
//@ ensures(*The returned integer value is the sum of all possible odd-length subarrays of the input array `arr`.*);
    public int sumOddLengthSubarrays(int[] arr) {
        int len = arr.length;
        int sum = 0;
        for (int i = 0; i <= len - 1; i++) {
            sum = sum + (((i + 1) * (len - i) + 1) / 2) * arr[i];
        }
        return sum;
    }
}