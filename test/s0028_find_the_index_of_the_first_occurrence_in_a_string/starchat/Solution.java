package g0001_0100.s0028_find_the_index_of_the_first_occurrence_in_a_string;

// #Easy #Top_Interview_Questions #String #Two_Pointers #String_Matching
// #Programming_Skills_II_Day_1 #2023_08_09_Time_0_ms_(100.00%)_Space_40.5_MB_(71.14%)

public class Solution {
//@ requires(*The length of the string parameter `haystack` is less than or equal to 50000 and is greater than or equal to 0.*);
//@ requires(*The length of the string parameter `needle` is less than or equal to 50000 and is greater than or equal to 0.*);
//@ requires(*The string parameter `haystack` consists of only English lowercase letters.*);
//@ requires(*The string parameter `needle` consists of only English lowercase letters.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 283\. Move Zeroes*);
//@ requires(**);
//@ requires(*Easy*);
//@ requires(**);
//@ requires(*Given an integer array `nums`, move all `0`'s to the end of it while maintaining the relative order of the non-zero elements.*);
//@ requires(**);
//@ requires(*Note that you must do this **in-place** without making a copy of the array.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** nums = \[0,1,0,3,12\]*);
//@ requires(***Output:** \[1,3,12,0,0\]*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** nums = \[0\]*);
//@ requires(***Output:** \[0\]*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= nums.length <= 104`*);
//@ requires(**   `-231 <= nums[i] <= 231 - 1`*);
//@ requires(**);
//@ requires(***Follow up:** Could you minimize the total number of operations done?*);
//@ requires(**);
//@ requires(***Method signature:** public void moveZeroes(int[] nums)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 2147483647 and is greater than or equal to -2147483648.*);
//@ requires(*The integer array parameter `nums` is modified in-place.*);
//@ requires(*The relative order of the non-zero elements in the integer array parameter `nums` is maintained after moving all `0`'s to the end of it.*);
//@ requires(*The number of operations done to move all `0`'s to the end of the integer array parameter `nums` is minimized.*);
//@ requires(*If the integer array parameter `nums` is equal to [0,1,0,3,12], the integer array parameter `nums` is modified to [1,3,12,0,0].*);
//@ requires(*If the integer array parameter `nums` is equal to [0], the integer array parameter `nums` is modified to [0].*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 274\. H-Index*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*The **h-index** is a metric that measures the productivity and citation impact of the published work of a scientist, and is used to assess the researcher's research output.*);
//@ requires(**);
//@ requires(*Given an array of integers `citations` where `citations[i]` is the number of citations a researcher received for their `ith` paper, return _the h-index of this researcher_.*);
//@ requires(**);
//@ requires(*According to the [h-index definition](https://*);
//@ ensures(*If the string parameter `needle` is an empty string, the integer result is equal to 0.*);
//@ ensures(*If the string parameter `needle` is not an empty string and is part of the string parameter `haystack`, the integer result is greater than or equal to 0 and is less than the length of the string parameter `haystack`.*);
//@ ensures(*If the string parameter `needle` is not an empty string and is not part of the string parameter `haystack`, the integer result is equal to -1.*);
//@ ensures(*If the string parameter `haystack` is an empty string and the string parameter `needle` is an empty string, the integer result is equal to 0.*);
//@ ensures(*If the string parameter `haystack` is an empty string and the string parameter `needle` is not an empty string, the integer result is equal to -1.*);
//@ ensures(*If the string parameter `haystack` is not an empty string and the string parameter `needle` is an empty string, the integer result is equal to 0.*);
//@ ensures(*If the string parameter `haystack` is not an empty string and the string parameter `needle` is not an empty string and is part of the string parameter `haystack`, the integer result is the index of the first occurrence of the string parameter `needle` in the string parameter `haystack`.*);
//@ ensures(*If the string parameter `haystack` is not an empty string and the string parameter `needle` is not an empty string and is not part of the string parameter `haystack`, the integer result is equal to -1.*);
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        int m = haystack.length();
        int n = needle.length();
        //@ decreases (m - n + 1) - start;
        for (int start = 0; start < m - n + 1; start++) {
            if (haystack.substring(start, start + n).equals(needle)) {
                return start;
            }
        }
        return -1;
    }
}