package g0201_0300.s0228_summary_ranges;

// #Easy #Array #2022_07_04_Time_0_ms_(100.00%)_Space_42.7_MB_(15.43%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ requires(*You are given a sorted unique integer array param_nums.*);
//@ requires(*Return the smallest sorted list of ranges that cover all the numbers in the array exactly.*);
//@ requires(*That is, each element of param_nums is covered by exactly one of the ranges, and there is no integer `x` such that `x` is in one of the ranges but not in param_nums.*);
//@ requires(*Each range `[a,b]` in the list should be output as:*);
//@ requires(*`"a->b"` if `a !*);
//@ requires(*= b`*);
//@ requires(*`"a"` if `a == b`*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [0,1,2,4,5,7]*);
//@ requires(*Output: ["0->2","4->5","7"]*);
//@ requires(*Explanation: The ranges are: [0,2] --> "0->2" [4,5] --> "4->5" [7,7] --> "7"*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [0,2,3,4,6,8,9]*);
//@ requires(*Output: ["0","2->4","6","8->9"]*);
//@ requires(*Explanation: The ranges are: [0,0] --> "0" [2,4] --> "2->4" [6,6] --> "6" [8,9] --> "8->9"*);
//@ requires(*Example 3:*);
//@ requires(*Input: nums = []*);
//@ requires(*Output: []*);
//@ requires(*Example 4:*);
//@ requires(*Input: nums = [-1]*);
//@ requires(*Output: ["-1"]*);
//@ requires(*Example 5:*);
//@ requires(*Input: nums = [0]*);
//@ requires(*Output: ["0"]*);
//@ requires(*Constraints:*);
//@ requires(*`0 <= nums.length <= 20`*);
//@ requires(*<code>-2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1</code>*);
//@ requires(*All the values of param_nums are unique.*);
//@ requires(*param_nums is sorted in ascending order.*);
    public List<String> summaryRanges(int[] nums) {
        List<String> ranges = new ArrayList<>();
        if (nums.length == 0) {
            return ranges;
        }
        // size of array
        int n = nums.length;
        // start of range
        int a = nums[0];
        // end of range
        int b = a;
        StringBuilder strB = new StringBuilder();
        //@ maintaining 1 <= i <= n;
        for (int i = 1; i < n; i++) {
            // we need to make a decision if the next element
            // will expand the range
            // i starts at 1, not 0, because 1 is the next
            // candidate for expanding the range
            if (nums[i] != b + 1) {
                // only when our next element does not expand the range
                // do we add the range a->b to our list of ranges
                strB.append(a);
                if (a != b) {
                    strB.append("->").append(b);
                }
                ranges.add(strB.toString());
                // since nums[i] is not accounted for by our range a->b
                // because nums[i] is not b+1, we need to set a and b
                // to this new range start point of bigger than b+1
                // maybe it is b+2? b+3? b+4? all we know is it is not b+1
                a = nums[i];
                b = a;
                // Reset string builder
                strB.setLength(0);
            } else {
                // if the next element expands our range we do so
                b++;
            }
        }
        // the only range that is not accounted for at this point is the last range
        // if our a and b are not equal then we add the range accordingly
        strB.append(a);
        if (a != b) {
            strB.append("->").append(b);
        }
        ranges.add(strB.toString());
        return ranges;
    }
}