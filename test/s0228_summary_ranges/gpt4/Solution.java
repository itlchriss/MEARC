package g0201_0300.s0228_summary_ranges;

// #Easy #Array #2022_07_04_Time_0_ms_(100.00%)_Space_42.7_MB_(15.43%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 20 and is greater than or equal to 0.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 2147483647 and are greater than or equal to -2147483648.*);
//@ requires(*All values in the integer array parameter `nums` are unique.*);
//@ requires(*The integer array parameter `nums` is sorted in ascending order.*);
//@ ensures(*If the list result is not empty, each element in the list result is a string representing a range that covers all numbers in the integer array parameter `nums` exactly.*);
//@ ensures(*If the integer array parameter `nums` is equal to [0,1,2,4,5,7], the list result is equal to ["0->2","4->5","7"].*);
//@ ensures(*If the integer array parameter `nums` is equal to [0,2,3,4,6,8,9], the list result is equal to ["0","2->4","6","8->9"].*);
//@ ensures(*If the integer array parameter `nums` is empty, the list result is empty.*);
//@ ensures(*If the integer array parameter `nums` is equal to [-1], the list result is equal to ["-1"].*);
//@ ensures(*If the integer array parameter `nums` is equal to [0], the list result is equal to ["0"].*);
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