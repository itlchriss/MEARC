package g2801_2900.s2859_sum_of_values_at_indices_with_k_set_bits;

// #Easy #Array #Bit_Manipulation #2023_12_19_Time_1_ms_(100.00%)_Space_43.3_MB_(64.43%)

import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input list `nums` is not null.*);
//@ ensures(*The input list `nums` is not empty.*);
//@ ensures(*The input integer `k` is non-negative.*);
//@ ensures(*The input integer `k` is less than or equal to 10.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is an integer.*);
//@ ensures(*The return value is the sum of elements in `nums` whose corresponding indices have exactly `k` set bits in their binary representation.*);
    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (countSetBits(i) == k) {
                sum += nums.get(i);
            }
        }
        return sum;
    }

    public static int countSetBits(int num) {
        int count = 0;
        while (num > 0) {
            num = num & (num - 1);
            count++;
        }
        return count;
    }
}