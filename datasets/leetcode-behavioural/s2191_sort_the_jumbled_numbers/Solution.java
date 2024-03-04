package g2101_2200.s2191_sort_the_jumbled_numbers;

// #Medium #Array #Sorting #2022_06_07_Time_117_ms_(96.53%)_Space_50.8_MB_(90.75%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static class RealNum {
        int index;
        int orig;
        int real = 0;
//@ ensures(*Preconditions:*);
//@ ensures(*The `mapping` array must have a length of 10.*);
//@ ensures(*Each element in the `mapping` array must be an integer between 0 and 9 (inclusive).*);
//@ ensures(*All elements in the `mapping` array must be unique.*);
//@ ensures(*The `nums` array must have a length greater than or equal to 1.*);
//@ ensures(*Each element in the `nums` array must be a non-negative integer less than 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The `nums` array is sorted in non-decreasing order based on the mapped values of its elements.*);
//@ ensures(*Elements with the same mapped values appear in the same relative order as in the input.*);

        public RealNum(int[] mapping, int orig, int index) {
            this.orig = orig;
            this.index = index;
            int mult = 1;
            if (orig == 0) {
                real = mapping[0];
            } else {
                while (orig > 0) {
                    int mod = orig % 10;
                    orig = orig / 10;
                    real += mapping[mod] * mult;
                    mult *= 10;
                }
            }
        }
    }

    public int[] sortJumbled(int[] mapping, int[] nums) {
        List<RealNum> realNums = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            RealNum realNum = new RealNum(mapping, num, i);
            realNums.add(realNum);
        }
        realNums.sort(
                (a, b) -> {
                    int retval = a.real - b.real;
                    if (retval != 0) {
                        return retval;
                    }
                    return a.index - b.index;
                });
        int[] retval = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            retval[i] = realNums.get(i).orig;
        }
        return retval;
    }
}