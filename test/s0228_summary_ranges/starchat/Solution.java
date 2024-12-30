package g0201_0300.s0228_summary_ranges;

// #Easy #Array #2022_07_04_Time_0_ms_(100.00%)_Space_42.7_MB_(15.43%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 20 and is greater than or equal to 0.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 2147483647 and is greater than or equal to -2147483648.*);
//@ requires(*All the values of `nums` are unique.*);
//@ requires(*The integer array parameter `nums` is sorted in ascending order.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2527\. Maximum Count of Positive Integer and Negative Integer*);
//@ requires(**);
//@ requires(*Easy*);
//@ requires(**);
//@ requires(*Given an integer array `nums` sorted in **non-decreasing** order, return _the maximum between the number of positive integers and the number of negative integers_.*);
//@ requires(**);
//@ requires(**   In other words, if the number of positive integers in `nums` is `pos` and the number of negative integers is `neg`, then return the maximum of `pos` and `neg`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** nums = \[1,-1,1,-1,1,-1\]*);
//@ requires(***Output:** 3*);
//@ requires(***Explanation:** There are 3 positive integers and 3 negative integers.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** nums = \[1,1\]*);
//@ requires(***Output:** 1*);
//@ requires(***Explanation:** There is 1 positive integer and 0 negative integers.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= nums.length <= 100`*);
//@ requires(**   `-100 <= nums[i] <= 100`*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve this problem in `O(1)` time complexity?*);
//@ requires(**);
//@ requires(*Method signature: public int maximumCount(int[] nums)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 100 and is greater than or equal to -100.*);
//@ requires(*The integer array parameter `nums` is sorted in non-decreasing order.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 2414\. Length of the Longest Alphabetical Continuous Substring*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*An **alphabetical continuous string** is a string consisting of consecutive letters in the alphabet. In other words, it is any substring of the string `"abcdefghijklmnopqrstuvwxyz"`.*);
//@ requires(**);
//@ requires(**   For example, `"abc"` is an alphabetical continuous string, while `"acb"` and `"za"` are not.*);
//@ requires(**);
//@ requires(*Given a string `inputstring` consisting of lowercase letters only, return _the number of **distinct** alphabetical continuous substrings_ in `inputstring`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** inputstring =  "abacaba"*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** There are 4 distinct continuous substrings: "a", "b", "c", and "abacaba".*);
//@ requires(*"abacaba" is not a continuous substring because it contains the substring "ba" which is not consecutive.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** inputstring =  "abcde"*);
//@ requires(***Output:** 5*);
//@ requires(***Explanation:** There are 5 distinct continuous*);
//@ ensures(*The list result contains strings that represent the smallest sorted list of ranges that cover all the numbers in the array exactly.*);
//@ ensures(*Each string in the list result is in the format "a->b" if `a!= b` or "a" if `a == b`.*);
//@ ensures(*The list result does not contain any integer `x` such that `x` is in one of the ranges but not in `nums`.*);
//@ ensures(*The list result is not empty.*);
//@ ensures(*The list result does not contain any duplicate ranges.*);
//@ ensures(*If the integer array parameter `nums` is equal to [0,1,2,4,5,7], the list result is equal to ["0->2","4->5","7"].*);
//@ ensures(*If the integer array parameter `nums` is equal to [0,2,3,4,6,8,9], the list result is equal to ["0","2->4","6","8->9"].*);
//@ ensures(*The integer result is greater than or equal to 1 and is less than or equal to the length of the integer array parameter `nums`.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,-1,1,-1,1,-1], the integer result is equal to 3.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,1], the integer result is equal to 1.*);
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