package g0701_0800.s0728_self_dividing_numbers;

// #Easy #Math #2022_03_24_Time_1_ms_(100.00%)_Space_39.8_MB_(88.16%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integers `left` and `right` must be positive integers.*);
//@ ensures(*The value of `left` must be less than or equal to the value of `right`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a list of integers.*);
//@ ensures(*The list contains all the self-dividing numbers in the range `[left, right]`.*);
//@ ensures(*Each number in the list is divisible by every digit it contains.*);
//@ ensures(*Each number in the list does not contain the digit zero.*);
    public List<Integer> selfDividingNumbers(final int left, final int right) {
        final List<Integer> list = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isSelfDividing(i)) {
                list.add(i);
            }
        }
        return list;
    }

    private boolean isSelfDividing(int value) {
        final int origin = value;
        while (value != 0) {
            final int digit = value % 10;
            value /= 10;
            if (digit == 0 || origin % digit != 0) {
                return false;
            }
        }
        return true;
    }
}