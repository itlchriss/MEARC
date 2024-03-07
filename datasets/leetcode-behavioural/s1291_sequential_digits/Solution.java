package g1201_1300.s1291_sequential_digits;

// #Medium #Enumeration #Udemy_Arrays #2022_03_10_Time_0_ms_(100.00%)_Space_42_MB_(5.03%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input values `low` and `high` are integers.*);
//@ ensures(*`low` is greater than or equal to 10.*);
//@ ensures(*`high` is less than or equal to 10^9.*);
//@ ensures(*`low` is less than or equal to `high`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a sorted list of integers.*);
//@ ensures(*The integers in the output list are in the range `[low, high]`.*);
//@ ensures(*Each integer in the output list has sequential digits.*);
    public List<Integer> sequentialDigits(int low, int high) {
        int[] arr = {
            12, 23, 34, 45, 56, 67, 78, 89, 123, 234, 345, 456, 567, 678, 789, 1234, 2345, 3456,
            4567, 5678, 6789, 12345, 23456, 34567, 45678, 56789, 123456, 234567, 345678, 456789,
            1234567, 2345678, 3456789, 12345678, 23456789, 123456789
        };
        List<Integer> result = new ArrayList<>();
        for (int j : arr) {
            // 234    148         234        256
            if (j >= low && j <= high) {
                result.add(j);
            }
        }
        return result;
    }
}