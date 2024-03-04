package g2201_2300.s2206_divide_array_into_equal_pairs;

// #Easy #Array #Hash_Table #Bit_Manipulation #Counting
// #2022_06_11_Time_1_ms_(100.00%)_Space_42.2_MB_(94.00%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of `nums` is even.*);
//@ ensures(*The length of `nums` is equal to `2 * n`.*);
//@ ensures(*The value of `n` is greater than or equal to 1.*);
//@ ensures(*The value of `n` is less than or equal to 500.*);
//@ ensures(*Each element in `nums` is an integer.*);
//@ ensures(*Each element in `nums` is greater than or equal to 1.*);
//@ ensures(*Each element in `nums` is less than or equal to 500.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a boolean value indicating whether `nums` can be divided into `n` pairs.*);
//@ ensures(*If `nums` can be divided into `n` pairs, the method returns `true`.*);
//@ ensures(*If `nums` cannot be divided into `n` pairs, the method returns `false`.*);
    public boolean divideArray(int[] nums) {
        int[] freq = new int[501];
        for (int num : nums) {
            ++freq[num];
        }
        for (int f : freq) {
            if (f % 2 != 0) {
                return false;
            }
        }
        return true;
    }
}